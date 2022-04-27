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
package MineSweeper.GameThings;


import MineSweeper.GameThings.Cell;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {

    /** all cells on the board */
    private Cell[][] cells;


    public List<Cell> getVisited() {
        return visited;
    }

    public void setVisited(List<Cell> visited) {
        this.visited = visited;
    }

    List<Cell> visited = new ArrayList<Cell>();

    /** number of bombs on the map */
    private int numBombs; // not being used yet, random bomb logic is in initCell

    /** size of empty border around the cells matrix, allows for simpler neighbor checking */
    private final int borderSize = 1;

    /** An array of row col offsets for checking neighboring cells */
    int[] offsets = {1,0,1,-1,0,-1,-1,-1,-1,0,-1,1,0,1,1,1};

    public Cell[][] getCells(){return cells;}

    public Board(int width, int height, int numBombs){
        this.numBombs = numBombs;
        this.cells = new Cell[width][height];
        instantiateCells();
    }

    public void clearVisitedCells(){
        visited = new ArrayList<Cell>();
    }

    /**
     * Called when a cell is selected, passes work onto handleCellHelper()
     * @param flagging - True if user is placing a flag
     * @param firstMove - True if this is the player's first move
     * @return a positive integer. 0 means it was successful, otherwise it was not.
     */
    public int handleCell(int r, int c, boolean flagging, boolean firstMove){
        if (firstMove)
            initCells(r, c);
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

        if (cells[r][c].isBorder() || cells[r][c].getNeighboringBombs() > 0){
            if(!cells[r][c].isBorder())
                visited.add(cells[r][c]);
            return 0;
        }
        if (visited.contains(cells[r][c])) return 0; else visited.add(cells[r][c]);

        int sum = 0;
        for (int n = 0; n < offsets.length; n+=2){
            sum += handleCellHelper(r+offsets[n],c+offsets[n+1], visited);
        }
        return sum;
    }

    public void instantiateCells(){
        for (int row = 0; row < this.cells.length; row++ ){
            for (int column = 0; column < this.cells[row].length; column++){
                cells[row][column] = new Cell(row,column);
                cells[row][column].setDisplayChar(' ');
            }
        }
    }

    /**
     * Initiates all cells, mostly involves passing each cell to initCells
     * Counts all cells' neighboring bombs
     */
    public void initCells(int r, int c){
        for (int row = 0; row < this.cells.length; row++ ){
            for (int column = 0; column < this.cells[row].length; column++){
               initCell(row, column);
            }
        }

        //why is this in here
        //numBombs = (int)(cells.length * cells[0].length * .2);
        initBombs(r, c);
    }

    /**
     * Initiates single cells. Involves instantiating and randomly selecting cells to be bombs
     */
    private void initCell(int row, int column) {
        if (row == 0 || column == 0 || row == this.cells.length-1 || column == this.cells[row].length-1 ){
            this.cells[row][column].setBorder(true);
            this.cells[row][column].setDisplayChar(' ');
            this.cells[row][column].setVisible(true);

        }else{
            this.cells[row][column].setDisplayChar(' ');
            this.cells[row][column].setBorder(false);
            this.cells[row][column].setHasBomb(false);
        }
    }

    private void initBombs(int startR, int startC){
        int bombCount = 0;
        while (bombCount < numBombs){
            int r = (int)(Math.random() * (cells.length - 2)) + 1;
            int c = (int)(Math.random() * (cells[r].length - 2)) + 1;
            if (!cells[r][c].isHasBomb() && countNeighboringBombs(r,c) < 4 && distanceTo(cells[startR][startC],cells[r][c]) > 2){
                cells[r][c].setHasBomb(true);
                initNeighbors(r,c);
                bombCount++;
                System.out.println("bomb count "+bombCount);
            }
        }
    }

    private int distanceTo(Cell cell1, Cell cell2){
        int val = (int)Math.sqrt(Math.pow(cell1.getRow() - cell2.getRow(),2) + Math.pow(cell1.getColumn() - cell2.getColumn(),2));
       // System.out.println(val);
        return val;
    }

    private int countNeighboringBombs(int r, int c){
        int count = 0;
        for (int n = 0; n < offsets.length; n+=2){
            count += (cells[r+offsets[n]][c+offsets[n+1]].isHasBomb()) ? 1 : 0;
        }
        return count;
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