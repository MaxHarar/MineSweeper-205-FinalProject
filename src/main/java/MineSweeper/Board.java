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

    private Cell[][] cells;
    private int numBombs;
    private final int borderSize = 1;

    public Cell[][] getCells(){return cells;}

    public Board(int width, int height){

        this.cells = new Cell[width + borderSize][height + borderSize];
        initCells();
    }

    public int handleCell(int r, int c, boolean flagging){
        List<Cell> visited = new ArrayList<Cell>();
        return handleCellHelper(r,c,flagging,visited);
    }
    // Needs a lot of work, just wanted to create the general logic
    // returns 0 if everything went fine, 1 if they clicked bomb
    public int handleCellHelper(int r, int c, boolean flagging, List<Cell> visited){
        int[] offsets = {1,0,1,-1,0,-1,-1,-1,-1,0,-1,1,0,1,1,1};

        if (cells[r][c].isHasBomb()) return 1;
        cells[r][c].setVisible(true);

        if (cells[r][c].isBorder() || cells[r][c].getNeighbors() > 0) return 0;
        if (visited.contains(cells[r][c])) return 0; else visited.add(cells[r][c]);

        int sum = 0;
        for (int n = 0; n < offsets.length; n+=2){
            sum += handleCellHelper(r+offsets[n],c+offsets[n+1], flagging, visited);
        }
        return sum;
    }

    private void initCells(){
        List<Point> bombs = new ArrayList<>();

        for (int row = 0; row < this.cells.length; row++ ){
            for (int column = 0; column < this.cells[row].length; column++){

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


        }

        for (Point bomb : bombs){
            initNeighbors(bomb.x, bomb.y);
        }
    }

    private void initNeighbors(int r, int c){
        cells[r][c].setDisplayChar('*');

        cells[r+1][c].addNeighbors(1);
        cells[r+1][c].resetDisplayChar();

        cells[r+1][c+1].addNeighbors(1);
        cells[r+1][c+1].resetDisplayChar();

        cells[r+1][c-1].addNeighbors(1);
        cells[r+1][c-1].resetDisplayChar();

        cells[r-1][c].addNeighbors(1);
        cells[r-1][c].resetDisplayChar();

        cells[r-1][c+1].addNeighbors(1);
        cells[r-1][c+1].resetDisplayChar();

        cells[r-1][c-1].addNeighbors(1);
        cells[r-1][c-1].resetDisplayChar();

        cells[r][c+1].addNeighbors(1);
        cells[r][c+1].resetDisplayChar();

        cells[r][c-1].addNeighbors(1);
        cells[r][c-1].resetDisplayChar();
    }



}