/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Max Harar
 * Section: MWF 11am
 *Date: 4/26/22
 * Time: 3:39 PM
 *
 * Project: csci205_FinalProject
 * Package: MineSweeper.fxFiles
 * Class: HighScoreTracker
 ** Description:
 *
 *
 *****************************************/
package MineSweeper.fxFiles;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.io.Read;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class HighScoreTracker extends Label {

    private DIFFICULTY currentDifficulty;



    public HighScoreTracker(DIFFICULTY currentDifficulty) throws IOException, URISyntaxException {

        this.currentDifficulty = currentDifficulty;
        DataFrame allScores = readInFile("HighScores.txt",',');


        List<String> players = setUpAndTrimData(allScores, currentDifficulty.getStringName() + "Name");
        List<Integer> scores = getIntegerListfromStringList( setUpAndTrimData(allScores, currentDifficulty.getStringName() + "Time"));
        Queue<String> playersQueue = getStringQueue(players);

        this.setFont(new Font(20));
        this.setText("Fastest Time: Set by: ");
    }

    private static Queue<String> getStringQueue(List<String> tempStrings) {
        return new LinkedList<>(tempStrings);
    }

    private static Queue<Integer> getIntQueue(List<Integer> tempInts) {
        return new LinkedList<>(tempInts);
    }

    public static DataFrame readInFile(String fileName, Character splitter) throws IOException, URISyntaxException {

        URL url = HighScoreTracker.class.getClassLoader().getResource(fileName);
        assert url != null;
        Path p = Paths.get(url.toURI());

        CSVFormat format = CSVFormat.DEFAULT
                .withDelimiter(splitter)
                .withFirstRecordAsHeader();


        return Read.csv(p, format);
    }

    private static List<String> setUpAndTrimData(DataFrame data, String thefield){

       return  data.stream()
                       .map(tuple -> tuple.getString(thefield))
                       .collect(Collectors.toList());
    }

    private static List<String> getStringFromDataFrame(DataFrame tempDF, String theField) {
        return tempDF.stream()
                .map(tuple -> tuple.getString(theField))
                .collect(Collectors.toList());
    }


    /**
     * Converts a List of Strings to a List of Integers, Strings should be numbers
     * @param temps - the list of Strings
     * @return The list of Integers
     */
    private static List<Integer> getIntegerListfromStringList(List<String> temps) {
        List<Integer> tempInts = new ArrayList<>();

        for (String tempInt : temps
        ) {


            try {
                tempInts.add(Integer.parseInt(tempInt));
            } catch (NumberFormatException e) {
                System.out.println("Failed to convert " + tempInt + "" +
                        " to int");
            }


        }
        return tempInts;
    }

    public static String makeOrderedString(List<Integer> integerList, Queue<String> stringQueue){
        Map<Integer,String> theMap = new HashMap<>();
        Map<Integer,String> tempMap = new HashMap<>();
        StringBuilder theOrderedString = new StringBuilder("Rank Time Name\n");

        for (Integer ints : integerList) {
            tempMap.put(ints,stringQueue.remove());
        }

        Collections.sort(integerList);
        Queue<Integer> integerQueue = getIntQueue(integerList);

        String tempString;
        for (int i = 0; i < 5; i++) {
            if (i > integerList.size()-1){break;}

            tempString = "" + (i + 1) + (". ") + (integerQueue.peek()) + (" ") + (tempMap.get(integerQueue.remove())) + ("\n");
            theOrderedString.append(tempString);
        }





        return  theOrderedString.toString();


    }
}