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

@Getter
@Setter
public class Board {

    private Cell[][] cells;
    private int numBombs;
    private final int borderSize = 1;

    public Board(int width, int height){

        this.cells = new Cell[width + borderSize][height + borderSize];
        initCells();



    }

    public static void main(String[] args) {
        Board theBoard = new Board(8,6);

        for (int row = 0; row < theBoard.cells.length; row++ ){
            for (int column = 0; column < theBoard.cells[row].length; column++){


                System.out.print(theBoard.cells[row][column].getDisplayChar() + " ");


            }
            System.out.println();
        }

    }


    private void initCells(){

        for (int row = 0; row < this.cells.length; row++ ){
            for (int column = 0; column < this.cells[row].length; column++){

                this.cells[row][column] = new Cell();

                if (row == 0 || column == 0 || row == this.cells.length-1 || column == this.cells[row].length-1 ){


                    this.cells[row][column].setBorder(true);
                    this.cells[row][column].setDisplayChar('B');

                }else{

                    this.cells[row][column].setBorder(false);
                    this.cells[row][column].setDisplayChar('*');
                }

            }


        }


    }



}