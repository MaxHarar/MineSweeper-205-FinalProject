/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 * Name: Sam Vickers* Section: 1
 * Date: 4/8/2022
 * Time: 1:11 AM
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: MineSweeperView
 * Description:
 ******************************************/
package MineSweeper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MineSweeperView {

    /**The root Vbox*/
    private VBox root;
    /**The game the player is playing*/
    private Game game;
    /**the topBar HBox*/
    private HBox topBar;

    /**size of each grid element */
    public static final int gridSize = 20;
    /**Rectangle [][] used for color*/
    private Rectangle[][] rects;
    /**labels - displayed to player */
    private Label[][] labels;
    /**The rectGrid on*/
    private GridPane rectGrid;
    /**THe topNar Rectangle, used to display timer and other gameStats*/
    private Rectangle topBarRect;
    /**The Cell[][] of the cells */
    private Cell[][] cells;

    private ComboBox difficultSelector;

    private HBox gameOptions;


    /**
     * The Javafx game creation
     * @param game - Game Object
     */
    public MineSweeperView(Game game){
        rects = new Rectangle[game.getRowCount()][game.getColCount()];
        labels = new Label[game.getRowCount()][game.getColCount()];
        rectGrid = new GridPane();
        this.game = game;
        initSceneGraph();
        initStyling();
    }

    public GridPane getRectGrid() { return rectGrid; }
    public Rectangle[][] getRects(){ return rects; }
    public Label[][] getLabels(){ return labels; }
    public HBox getTopBar() { return topBar; }
    public Rectangle getTopBarRect() { return topBarRect; }
    public VBox getRoot(){return root;}

    /**
     * Initialize the Scene Graphics
     */
    private void initSceneGraph(){
        root = new VBox();
        topBar = new HBox();
        topBarRect = new Rectangle();

       gameOptions = new HBox();


        initBackgroundComponents();

        GameTimer gameTimer = new GameTimer();
        gameTimer.start();








        difficultSelector = new  ComboBox();

        difficultSelector.getItems().addAll(
                "EASY",
                "MEDIUM",
                "HARD",
                "INSANE"
        );

        gameOptions.getChildren().addAll(gameTimer,difficultSelector);

       // sPane.getChildren().add(difficultSelector);
        StackPane sPane = new StackPane(topBarRect, gameOptions);



        topBar.getChildren().add(sPane);
        root.getChildren().add(topBar);
        root.getChildren().add(rectGrid);
        this.cells = game.getCells();
    }

    /**
     * Initialize the Background
     */
    private void initBackgroundComponents() {
        StackPane sPane;
        Rectangle currRect;
        for (int r = 0; r < game.getRowCount(); r++){
            for (int c = 0; c < game.getColCount(); c++){
                currRect = new Rectangle(gridSize, gridSize);
                rects[r][c] = currRect;
                labels[r][c] = new Label(" ");

                sPane = new StackPane(currRect, labels[r][c]);
                rectGrid.add(sPane,c,r,1,1);
            }
        }
    }

    /**
     * Inits the styling of the figure
     */
    public void initStyling(){
        root.setPadding(new Insets(0));
        root.setSpacing(0);

        rectGrid.setHgap(0);
        rectGrid.setVgap(0);
        rectGrid.setMaxWidth(gridSize * rectGrid.getColumnCount());

        gameOptions.setAlignment(Pos.BASELINE_LEFT);
        topBarRect.setFill(Color.GREEN);
        createCheckerBoardPattern();
    }

    /**
     * Generates the checkerboard colors for the tiles
     */
    private void createCheckerBoardPattern() {
        int counter = 0;
        for (int r = 0; r < game.getRowCount(); r++){
            for (int c = 0; c < game.getColCount(); c++){
                if (counter %2 == 0) {
                    labels[r][c].getStyleClass().add("initTile");
                    this.cells[r][c].setDarkTile(false);
                }else{
                    labels[r][c].getStyleClass().add("initTileDark");
                    this.cells[r][c].setDarkTile(true);
                }
                counter++;
            }
        }
    }
}