/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 * Name: Sam Vickers* Section: 1
 * Date: 4/7/2022
 * Time: 10:11 AM
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: Game
 * Description:
 ******************************************/
package MineSweeper;

import java.util.List;
import java.util.Scanner;

enum DIFFICULTY{
    EASY,
    MEDIUM,
    HARD,
    INSANE,
};

public class Game {

    /** size of the board in rows */
    private final int BOARD_ROWS = 18;

    /** size of the board in columns */
    private final int BOARD_COLS = 25;

    /** currently selected difficulty */
    private DIFFICULTY difficulty; //not being used yet

    /** instance of the Board class */
    private Board theBoard;

    public Game(){
        startGame();
        printBoard(false);
        //recursionTest();
    }

    public int getRowCount(){ return BOARD_ROWS; }
    public int getColCount(){ return BOARD_COLS; }
    public Cell[][] getCells(){ return theBoard.getCells(); }

    /**
     * Just prints out the current state of the board
     * - : Empty cell
     * * : Bomb
     * 0-9 : Number of neighboring bombs
     * B : Border cell
     * @param checkVisibility - if true, cells will only be printed out if they are visible
     */
    public void printBoard(boolean checkVisibility){
        char disp;
        int row;
        Cell[][] cells = theBoard.getCells();
        for (int r = 0; r < cells.length+2; r++){
            if (r < 2)
                System.out.print("    ");
            for (int c = 0; c < cells[0].length; c++){
                if (r < 2){
                    if (r == 0)
                        System.out.print(c % 10 + " ");
                    else
                        System.out.print("~ ");
                }else{
                    row = r - 2;
                    if (c == 0)
                        System.out.print(String.format("%4s",row + "~ "));
                    disp = (cells[row][c].isVisible() || !checkVisibility) ? cells[row][c].getDisplayChar() : ' ';
                    System.out.print(disp + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Start the minesweeper game
     */
    public void startGame(){
        theBoard = new Board(BOARD_ROWS,BOARD_COLS);
    }

    /**
     * simple test, mainly for recursion but works as a main test
     */
    private void recursionTest(){
        Scanner scnr = new Scanner(System.in);
        int r,c;
        while (true){
            System.out.println("Enter row:");
            r = Integer.parseInt(scnr.next());
            System.out.println("Enter col:");
            c = Integer.parseInt(scnr.next());

            playerMove(r,c,false);

            printBoard(true);
        }
    }

    /**
     * Makes a move as defined by the players' actions
     * @param r
     * @param c
     * @param flagging - true if the player is placing a flag
     * @return true if succesfull, false if they have now lost the game
     */
    public boolean playerMove(int r, int c, boolean flagging){
        return theBoard.handleCell(r,c,flagging) == 0;
    }

    public List<Cell> getVisitedCells(){
        return theBoard.getVisited();
    }
    public void clearVisitedCells() { theBoard.clearVisitedCells(); }

    public static void main(String[] args) {
        new Game();
    }
}