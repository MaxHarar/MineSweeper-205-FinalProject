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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class MineSweeperController {

    private Label[][] labels;
    private Rectangle[][] rects;
    private Cell[][] cells;

    private MineSweeperView theView;
    private Game game;

    public MineSweeperController(MineSweeperView view, Game game){
        this.theView = view;
        this.game = game;

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
                //rects[r][c].fillProperty().bind(cells[r][c].getColorProperty());
            }
        }
    }

    private void initHandlers(){
        int rows = game.getRowCount();
        int cols = game.getColCount();

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                int finalR = r;
                int finalC = c;
                labels[r][c].setOnMouseClicked(event -> {
                    game.playerMove(finalR, finalC,false);
                    game.printBoard(true);
                    //rects[r][c].getStyleClass().add("thing");
                });
            }
        }
    }
}