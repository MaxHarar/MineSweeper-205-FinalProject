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

import java.util.ArrayList;
import java.util.List;

public class MineSweeperView {

    private VBox root;
    private Game game;

    private Rectangle[][] rects;
    private Label[][] labels;
    private GridPane rectGrid;

    public MineSweeperView(Game game){
        rects = new Rectangle[game.getRowCount()][game.getColCount()];
        labels = new Label[game.getRowCount()][game.getColCount()];
        rectGrid = new GridPane();
        this.game = game;
        initSceneGraph();
    }

    public GridPane getRectGrid() { return rectGrid; }
    public Rectangle[][] getRects(){ return rects; }
    public Label[][] getLabels(){ return labels; }

    private void initSceneGraph(){
        root = new VBox();
        root.setPadding(new Insets(0));
        root.setSpacing(0);
        Rectangle currRect;
        StackPane sPane;

        for (int r = 0; r < game.getRowCount(); r++){
            for (int c = 0; c < game.getColCount(); c++){
                currRect = new Rectangle(20,20);
                currRect.setFill(Color.GREY);
                rects[r][c] = (currRect);
                styleLabel(r, c);

                sPane = new StackPane(currRect, labels[r][c]);
                rectGrid.add(sPane,c,r,1,1);
            }
        }
        rectGrid.setHgap(1);
        rectGrid.setVgap(1);
        root.getChildren().add(rectGrid);
    }

    private void styleLabel(int r, int c) {
        labels[r][c] = new Label(" ");
      //  labels[r][c].setPrefWidth(20);
      //  labels[r][c].setPrefHeight(20);
      //  labels[r][c].setTextAlignment(TextAlignment.CENTER);
      //  labels[r][c].setAlignment(Pos.CENTER);
      //  labels[r][c].setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }

    public VBox getRoot(){
        return root;
    }
}