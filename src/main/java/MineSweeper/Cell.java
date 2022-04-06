/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Max Harar
 * Section: MWF 11am
 *Date: 4/6/22
 * Time: 1:12 PM
 *
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: Cell
 ** Description:
 *
 *
 *****************************************/
package MineSweeper;

import lombok.*;
@Setter
@Getter
public class Cell {

    public char getDisplayChar() {
        return displayChar;
    }

    public void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }

    private boolean hasBomb;

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isBorder() {
        return isBorder;
    }

    public void setBorder(boolean border) {
        isBorder = border;
    }

    private boolean isFlagged;
    private boolean isVisible;
    private boolean isBorder;
    private char displayChar;


    public Cell(){

        hasBomb = false;
        isFlagged = false;
        isVisible = false;
        isBorder = false;

    }




}