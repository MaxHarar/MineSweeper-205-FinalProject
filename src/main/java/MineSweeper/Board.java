/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Max Harar
 * Section: MWF 11am
 *Date: 4/6/22
 * Time: 1:24 PM
 *
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: Board
 ** Description:
 *
 *
 *****************************************/
package MineSweeper;


import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {

    /** all cells on the board */
    private Cell[][] cells;

    /** number of bombs on the map */
    private int numBombs; // not being used yet, random bomb logic is in initCell

    /** size of empty border around the cells matrix, allows for simpler neighbor checking */
    private final int borderSize = 1;

    /** An array of row col offsets for checking neighboring cells */
    int[] offsets = {1,0,1,-1,0,-1,-1,-1,-1,0,-1,1,0,1,1,1};

    public Cell[][] getCells(){return cells;}

    public Board(int width, int height, boolean isGameBoard){
        int r = width + borderSize;
        int c= height + borderSize;

        this.cells = new Cell[r][c];
        if (isGameBoard) {
            initCells();
        }else{



            for (int row = 0; row < r; row++ ){
                for (int column = 0; column < c; column++){
                    this.cells[row][column] = new Cell();

                    if (row == 0 || column == 0 || row == this.cells.length-1 || column == this.cells[row].length-1 ) {
                        this.cells[row][column].setBorder(true);
                        this.cells[row][column].setDisplayChar('B');
                        this.cells[row][column].setVisible(true);
                    }else{

                        this.cells[row][column].setDisplayChar('-');
                        this.cells[row][column].setBorder(false);
                        this.cells[row][column].setVisible(true);
                    }


                }
            }

        }
    }

    /**
     * Called when a cell is selected, passes work onto handleCellHelper()
     * @param flagging - True if user is placing a flag
     * @return a positive integer. 0 means it was successful, otherwise it was not.
     */
    public int handleCell(int r, int c, boolean flagging){
        List<Cell> visited = new ArrayList<Cell>();
        return handleCellHelper(r,c,visited);
    }

    /**
     * Recurses through all empty cells, makes them all visible including non empty cells bordering the area
     * @param visited - cells that have been recursively visited already, prevents infinite loop
     * @return a positive integer. 0 means it was successful, 1 means a bomb was clicked (player loses)
     */
    public int handleCellHelper(int r, int c, List<Cell> visited){
        if (cells[r][c].isHasBomb()) return 1;
        cells[r][c].setVisible(true);

        if (cells[r][c].isBorder() || cells[r][c].getNeighboringBombs() > 0) return 0;
        if (visited.contains(cells[r][c])) return 0; else visited.add(cells[r][c]);

        int sum = 0;
        for (int n = 0; n < offsets.length; n+=2){
            sum += handleCellHelper(r+offsets[n],c+offsets[n+1], visited);
        }
        return sum;
    }

    /**
     * Initiates all cells, mostly involves passing each cell to initCells
     * Counts all cells' neighboring bombs
     */
    private void initCells(){
        List<Point> bombs = new ArrayList<>();

        for (int row = 0; row < this.cells.length; row++ ){
            for (int column = 0; column < this.cells[row].length; column++){
                initCell(bombs, row, column);
            }
        }

        for (Point bomb : bombs){
            initNeighbors(bomb.x, bomb.y);
        }
    }

    /**
     * Initiates single cells. Involves instantiating and randomly selecting cells to be bombs
     * @param bombs - list which will hold all cells selected to be bombs
     */
    private void initCell(List<Point> bombs, int row, int column) {
        this.cells[row][column] = new Cell();

        if (row == 0 || column == 0 || row == this.cells.length-1 || column == this.cells[row].length-1 ){
            this.cells[row][column].setBorder(true);
            this.cells[row][column].setDisplayChar('B');
            this.cells[row][column].setVisible(true);

        }else{
            int ran = (int)(Math.random()*10);
            if (ran == 0){
                this.cells[row][column].setHasBomb(true);
                bombs.add(new Point(row, column));
            }else{
                this.cells[row][column].setDisplayChar('-');
            }
            this.cells[row][column].setBorder(false);
        }
    }

    /**
     * Increases neighboringBomb count of each cell surrounding a bomb
     * then resets the cell's display as to correctly show this new value on the board
     */
    private void initNeighbors(int r, int c){
        cells[r][c].setDisplayChar('*');
        for (int n = 0; n < offsets.length; n+=2){
            cells[r+offsets[n]][c+offsets[n+1]].addNeighboringBomb(1);
            cells[r+offsets[n]][c+offsets[n+1]].resetDisplayChar();
        }
    }

}