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

public class MineSweeperController {

    private Label[][] labels;
    private Rectangle[][] rects;
    private Cell[][] cells;

    private MineSweeperView theView;
    private Game game;
    private MineSweeperMain main;

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

    private void initBindings(){
        int rows = game.getRowCount();
        int cols = game.getColCount();

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                labels[r][c].textProperty().bind(cells[r][c].getDisplayStringProperty());
            }
        }

        theView.getTopBarRect().widthProperty().bind(theView.getRoot().widthProperty());
        theView.getTopBarRect().setHeight(40);
    }

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

    private void setLabelHandler(int finalR, int finalC, MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            onLeftClick(finalR, finalC);
        }else if(event.getButton() == MouseButton.SECONDARY){
            onRightClick(finalR, finalC);
        }
    }

    private void onRightClick(int finalR, int finalC) {
        if (cells[finalR][finalC].isVisible()  &&  !cells[finalR][finalC].isFlagged()) return;

        if (cells[finalR][finalC].isFlagged()){
            labels[finalR][finalC].getStyleClass().clear();
            labels[finalR][finalC].getStyleClass().add("initTile");
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

    private void onLeftClick(int finalR, int finalC) {
        if (!game.playerMove(finalR, finalC, false)) main.resetGame();

        for (Cell cell : game.getVisitedCells()) {
            labels[cell.getRow()][cell.getColumn()].getStyleClass().add("exploredTile");
        }

        game.printBoard(true);
    }
}
