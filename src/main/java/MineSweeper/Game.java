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

import java.util.Scanner;

enum DIFFICULTY{
    EASY,
    MEDIUM,
    HARD,
    INSANE,
};

public class Game {

    private final int BOARD_ROWS = 15;
    private final int BOARD_COLS = 40;

    private DIFFICULTY difficulty;

    private Board theBoard;

    public Game(){
        startGame();
        printBoard(false);
        recursionTest();
    }

    private void printBoard(boolean checkVisibility){
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

    public void startGame(){
        theBoard = new Board(BOARD_ROWS,BOARD_COLS);
    }

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

    public boolean playerMove(int r, int c, boolean flagging){
        return theBoard.handleCell(r,c,flagging) == 0;
    }

    public static void main(String[] args) {
        new Game();
    }
}