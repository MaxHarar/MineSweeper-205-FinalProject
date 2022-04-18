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
package MineSweeper;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The Mine Sweeper Controller - controls userinput and creates the bindings
 * and handlers
 */
public class MineSweeperController {

    /**2D label array */
    private Label[][] labels;

    /**2D Rectangle array - here for color */
    private Rectangle[][] rects;

    /**2D Cell array */
    private Cell[][] cells;

    /**Minesweeper View*/
    private MineSweeperView theView;

    /**The Game instance that the player is playing*/
    private Game game;

    /**The Main class for the javafx output*/
    private MineSweeperMain main;

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

        this.labels = theView.getLabels();
        this.rects = theView.getRects();
        this.cells = game.getCells();

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
        System.out.println(theView.getRectGrid().maxWidthProperty().getValue());
        theView.getTopBarRect().setHeight(40);
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
                    setLabelHandler(finalR, finalC, event);
                });
            }
        }
    }

    /**
     * Set's the label handler for when a label is clicked
     * @param finalR - the row
     * @param finalC - the column
     * @param event - MouseEvent
     */
    private void setLabelHandler(int finalR, int finalC, MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            onLeftClick(finalR, finalC);
        }else if(event.getButton() == MouseButton.SECONDARY){
            onRightClick(finalR, finalC);
        }
    }

    /**
     * Handles flagging for the board
     * @param finalR - row
     * @param finalC - column
     */
    private void onRightClick(int finalR, int finalC) {
        if (cells[finalR][finalC].isVisible()  &&  !cells[finalR][finalC].isFlagged()) return;

        if (cells[finalR][finalC].isFlagged()){
            labels[finalR][finalC].getStyleClass().clear();

            if (cells[finalR][finalC].isDarkTile()){

                labels[finalR][finalC].getStyleClass().add("initTileDark");

            }else{
                labels[finalR][finalC].getStyleClass().add("initTile");
            }


            cells[finalR][finalC].setFlagged(false);
            cells[finalR][finalC].setVisible(false);
            cells[finalR][finalC].revertDisplayChar();

        }else {
            labels[finalR][finalC].getStyleClass().add("flaggedTile");
            cells[finalR][finalC].saveDisplayCharAndUpdate('F');
            cells[finalR][finalC].setFlagged(true);
            cells[finalR][finalC].setVisible(true);
        }
    }

    /**
     * Handles the left click actions
     * checks to see if gameOver then calls the recursive call to
     * change the explored labels
     * @param finalR - the row
     * @param finalC - column
     */
    private void onLeftClick(int finalR, int finalC) {
        if (!game.playerMove(finalR, finalC, false)) main.resetGame();

        List<Cell> visited = game.getVisitedCells();
        Collections.shuffle(visited);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            int i = 0;
            int end = visited.size();

            @Override
            public void run() {
                if (i < end && i > -1){
                    Cell cell = visited.get(i);
                    if (cell != null)
                        labels[cell.getRow()][cell.getColumn()].getStyleClass().add("exploredTile");
                }else{ this.cancel(); }
                i++;
            }
        }, 0, 3);

//        for (Cell cell : game.getVisitedCells()) {
//            labels[cell.getRow()][cell.getColumn()].getStyleClass().add("exploredTile");
//        }

        game.printBoard(true);
    }

    /**
     * Pause the execution for given number of ms
     * @param ms
     */
    public static void wait(int ms)
    {
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
