/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 * Name: Sam Vickers* Section: 1
 * Date: 4/8/2022
 * Time: 1:10 AM
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: MineSweeperController
 * Description:
 ******************************************/
package MineSweeper.fxFiles;

import MineSweeper.GameThings.Cell;
import MineSweeper.GameThings.Game;
import MineSweeper.MineSweeperMain;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;


/**
 * The Mine Sweeper Controller - controls userinput and creates the bindings
 * and handlers
 */
public class MineSweeperController {

    /**2D label array */
    private final Label[][] labels;

    /**2D Cell array */
    private final Cell[][] cells;

    /**Minesweeper View*/
    private final MineSweeperView theView;

    /**The Game instance that the player is playing*/
    private final Game game;

    /**The Main class for the javafx output*/
    private final MineSweeperMain main;

    /**True if the player has made atleast one move*/
    private boolean hasClicked = false;

    private DIFFICULTY currentDifficulty;


    private int defusedCount;

    private int flaggedCount = 0;
    private StringProperty flaggedText;

    private EndGamePopUp endGamePopUp;

    private int counter;

    /**
     * MineSweeper controller
     * @param view - the view
     * @param game - the game
     * @param main - the main
     */
    public MineSweeperController(MineSweeperView view, Game game, MineSweeperMain main){
        this.theView = view;
        this.game = game;
        this.main = main;
        counter =  1;

        this.labels = theView.getLabels();

        /*2D Rectangle array - here for color */
        Rectangle[][] rects = theView.getRects();
        this.cells = game.getCells();
        this.currentDifficulty = game.getTheDifficulty();
        GameTimer gameTimer = theView.getGameTimer();



        initBindings();
        initHandlers();
    }

    /**
     * Inits the bindings for the class
     */
    private void initBindings(){
        int rows = game.getRowCount();
        int cols = game.getColCount();

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                labels[r][c].textProperty().bind(cells[r][c].getDisplayStringProperty());
            }
        }

        theView.getTopBarRect().widthProperty().bind(theView.getRectGrid().maxWidthProperty());
        theView.getTopBarRect().setHeight(40);


        flaggedText = new SimpleStringProperty();

        flaggedText.setValue("Flags Remaining: " + (this.game.getNUM_BOMBS() - flaggedCount));

        theView.getFlaggedLabel().textProperty().bind(flaggedText);



    }

    /**
     * Inits the handlers for the class
     */
    private void initHandlers(){
        int rows = game.getRowCount();
        int cols = game.getColCount();

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                int finalR = r;
                int finalC = c;
                labels[r][c].setOnMouseClicked(event -> {
                    try {
                        setLabelHandler(finalR, finalC, event);
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                });
            }
        }



        theView.getDifficultSelector().valueProperty().addListener(event -> {

            // We know this is sphagetti, but it works //////////////////////////
            String difficulty = event.toString();
            difficulty = difficulty.substring(difficulty.indexOf("value:") + 7);
            difficulty = difficulty.substring(0,difficulty.length()-1);
            /////////////////////////////////////////////////////////////////////

            switch (difficulty) {
                case "EASY" -> currentDifficulty = DIFFICULTY.EASY;
                case "MEDIUM" -> currentDifficulty = DIFFICULTY.MEDIUM;
                case "HARD" -> currentDifficulty = DIFFICULTY.HARD;
                case "INSANE" -> currentDifficulty = DIFFICULTY.INSANE;
            }
            main.resetGame(currentDifficulty);
        });

        theView.getColorMode().setOnAction(event -> {

            if (counter %2 ==0 ) {

                main.getScene().getStylesheets().clear();
                main.getScene().getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/MineSweeperStyleLight.css"))
                                .toExternalForm());
                theView.getColorMode().setText("Dark Mode");

            }else{


                main.getScene().getStylesheets().clear();
                main.getScene().getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/MineSweeperStyleDark.css"))
                                .toExternalForm());

                theView.getColorMode().setText("Standard Mode");

            }


            counter++;
        });


    }

    /**
     * Set's the label handler for when a label is clicked
     * @param finalR - the row
     * @param finalC - the column
     * @param event - MouseEvent
     */
    private void setLabelHandler(int finalR, int finalC, MouseEvent event) throws IOException, URISyntaxException {
        if(event.getButton() == MouseButton.PRIMARY) {
            onLeftClick(finalR, finalC);
            hasClicked = true;
        }else if(event.getButton() == MouseButton.SECONDARY && hasClicked){
            onRightClick(finalR, finalC);
        }
    }

    /**
     * Handles flagging for the board
     * @param finalR - row
     * @param finalC - column
     */
    private void onRightClick(int finalR, int finalC) throws IOException, URISyntaxException {
        if (cells[finalR][finalC].isVisible()  &&  !cells[finalR][finalC].isFlagged()) return;

        if (cells[finalR][finalC].isFlagged()){
            flaggedCount--;
            flaggedText.setValue("Flags Remaining: " + (this.game.getNUM_BOMBS() - flaggedCount));

            labels[finalR][finalC].getStyleClass().clear();
            if(cells[finalR][finalC].isHasBomb()){
                defusedCount--;
            }


            if (cells[finalR][finalC].isDarkTile()){

                labels[finalR][finalC].getStyleClass().add("initTileDark");

            }else{
                labels[finalR][finalC].getStyleClass().add("initTile");
            }


            cells[finalR][finalC].setFlagged(false);
            cells[finalR][finalC].setVisible(false);
            cells[finalR][finalC].revertDisplayChar();


        }else {

            if (flaggedCount >= this.game.getNUM_BOMBS()) return;

            labels[finalR][finalC].getStyleClass().add("flaggedTile");
            cells[finalR][finalC].saveDisplayCharAndUpdate('F');
            cells[finalR][finalC].setFlagged(true);
            cells[finalR][finalC].setVisible(true);
            flaggedCount++;
            flaggedText.setValue("Flags Remaining: " + (this.game.getNUM_BOMBS() - flaggedCount));

            if(cells[finalR][finalC].isHasBomb()){
                defusedCount++;
            }

            if (defusedCount == game.getNUM_BOMBS()){
                endGamePopUp = new EndGamePopUp(main,"Winner!", currentDifficulty);
                endGamePopUp.show();
            }
        }
    }

    /**
     * Handles the left click actions
     * checks to see if gameOver then calls the recursive call to
     * change the explored labels
     * @param finalR - the row
     * @param finalC - column
     */
    private void onLeftClick(int finalR, int finalC) throws IOException, URISyntaxException {
        if (cells[finalR][finalC].isFlagged()) return;

        if (!game.playerMove(finalR, finalC, false, !hasClicked)){

            endGamePopUp = new EndGamePopUp(main,"GameOver!", currentDifficulty);
            endGamePopUp.show();
        }

        for (Cell cell : game.getVisitedCells()) {

            if (cell.isDarkTile()) {
                labels[cell.getRow()][cell.getColumn()].getStyleClass().add("exploredTile");
            }else{
                labels[cell.getRow()][cell.getColumn()].getStyleClass().add("exploredTileLight");
            }
        }
    }
}
