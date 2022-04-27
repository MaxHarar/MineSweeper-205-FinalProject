/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Max Harar
 * Section: MWF 11am
 *Date: 4/24/22
 * Time: 9:13 PM
 *
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: EndGamePopUp
 ** Description:
 *
 *
 *****************************************/
package MineSweeper.fxFiles;

import MineSweeper.MineSweeperMain;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class EndGamePopUp extends Stage {


    private final Button exitBtn;

    private HighScoreTracker test;

    public Button getExitBtn() {
        return exitBtn;
    }

    public Button getPlayAgainBtn() {
        return playAgainBtn;
    }

    private final Button playAgainBtn;
    private final Label gameOverMsg;
    private DIFFICULTY currentDiff;

    MineSweeperMain main;

    public EndGamePopUp(MineSweeperMain main,String message, DIFFICULTY currentDiff) throws IOException, URISyntaxException {
        super();
        this.main = main;
        this.currentDiff = currentDiff;

        this.setTitle(message);
        this.setResizable(false);

        this.initModality(Modality.APPLICATION_MODAL);




        VBox root = new VBox();
        gameOverMsg = new Label(message);
        playAgainBtn = new Button("Play again");
        exitBtn = new Button("Exit");
        this.test = new HighScoreTracker(currentDiff);


        playAgainBtn.setOnAction(event -> {


            if (this.currentDiff == null){
               this.currentDiff = DIFFICULTY.EASY;
            }

            switch(this.currentDiff){
                case EASY:
                    try {
                        main.setToEasy();
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                case MEDIUM:
                    try {
                        main.setToMedium();
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                case HARD:
                    try {
                        main.setToHard();
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;
                case INSANE:
                    try {
                        main.setToInsane();
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                    break;

            }

            main.resetGame();
            this.close();

        });


        exitBtn.setOnAction(event -> {

            Platform.exit();
            System.exit(0);

        });






        root.getChildren().addAll(gameOverMsg, test, playAgainBtn, exitBtn);







        Scene s = new Scene(root);
        this.setScene(s);
    }

    private void handleCurrentDiff(){

    }

}
