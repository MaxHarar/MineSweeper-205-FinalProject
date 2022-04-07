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
    }

    private void printBoard(){
        Cell[][] cells = theBoard.getCells();
        for (int r = 0; r < cells.length; r++){
            for (int c = 0; c < cells[0].length; c++){
                System.out.print(cells[r][c].getDisplayChar());
            }
            System.out.println();
        }
    }

    public void startGame(){
        theBoard = new Board(BOARD_ROWS,BOARD_COLS);
    }

    public boolean playerMove(int x, int y){

        return false;
    }

    public static void main(String[] args) {
        new Game().printBoard();
    }
}