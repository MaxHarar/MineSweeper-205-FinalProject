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

import javafx.beans.property.SimpleStringProperty;
import lombok.*;
@Setter
@Getter
public class Cell {

    /** true if this cell is a bomb */
    private boolean hasBomb;

    /** true if this cell has a flag placed on it */
    private boolean isFlagged;

    /** true if this cell is currently visible to the player */
    private boolean isVisible;

    /** true if this cell is a border cell (not in play) */
    private boolean isBorder;

    /** an integer representing the number of neighboring cells that have a bomb */
    private int neighboringBombs;

    /** the character to be displayed if the cell is visible */
    private char displayChar;

    private char savedChar;

    public boolean isDarkTile() {
        return darkTile;
    }

    public void setDarkTile(boolean darkTile) {
        this.darkTile = darkTile;
    }

    public boolean darkTile;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private int row;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private int column;

    private SimpleStringProperty displayStringProperty;

    public SimpleStringProperty getDisplayStringProperty(){ return displayStringProperty; }

    public char getDisplayChar() {
        return displayChar;
    }

    public void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }

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
        if (visible){
            displayStringProperty.set(displayChar + " ");
        }else{
            displayStringProperty.set(" ");
        }
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

    public void addNeighboringBomb(int n){ neighboringBombs += n; }

    public int getNeighboringBombs(){ return neighboringBombs;}

    /**
     * resets the display char, makes sure that any changes to the neighboringBombs is
     * reflected in the displayChar
     */
    public void resetDisplayChar(){
        if (!hasBomb && !isBorder)
            displayChar = Character.forDigit(neighboringBombs,10);
        if (hasBomb)
            displayChar = '*';
    }

    public Cell(int row, int column){
        neighboringBombs = 0;
        hasBomb = false;
        isFlagged = false;
        isVisible = false;
        isBorder = false;
        displayStringProperty = new SimpleStringProperty(" ");
        this.row = row;
        this.column = column;

    }

    public void saveDisplayCharAndUpdate(char newDisplayChar){

        this.savedChar = this.displayChar;
        this.displayChar = newDisplayChar;

    }

    public void revertDisplayChar(){

        this.displayChar = this.savedChar;

    }

}