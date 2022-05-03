package MineSweeper.GameThings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell cell = new Cell(10,10);

    @Test
    void setVisible() {
        cell.setVisible(true);
        assertTrue(cell.getDisplayStringProperty().getValue() != " ");
        cell.setVisible(false);
        assertTrue(cell.getDisplayStringProperty().getValue() == " ");
    }

    @Test
    void resetDisplayChar() {
        cell.setHasBomb(true);
        cell.resetDisplayChar();
        assertTrue(cell.getDisplayChar() == '*');
    }

    @Test
    void saveDisplayCharAndUpdate() {
        cell.saveDisplayCharAndUpdate('a');
        cell.saveDisplayCharAndUpdate('b');
        assertTrue(cell.getDisplayChar() == 'b');
    }

    @Test
    void revertDisplayChar() {
        cell.saveDisplayCharAndUpdate('a');
        cell.saveDisplayCharAndUpdate('b');
        cell.revertDisplayChar();
        assertTrue(cell.getDisplayChar() == 'a');
    }
}