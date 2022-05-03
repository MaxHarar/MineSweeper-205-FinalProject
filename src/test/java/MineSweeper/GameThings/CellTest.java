package MineSweeper.GameThings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell cell;
    @Test
    void setUp() {
        cell = new Cell(10,10);
    }

    @Test
    void setVisible() {
        cell.setVisible(true);
        assertTrue(cell.getDisplayStringProperty().getValue() != " ");
        cell.setVisible(false);
        assertTrue(cell.getDisplayStringProperty().getValue() == " ");
    }

    @Test
    void resetDisplayChar() {
    }

    @Test
    void saveDisplayCharAndUpdate() {
        char character = 'a';
        char character2 = 'b';
        cell.saveDisplayCharAndUpdate(character);
        cell.saveDisplayCharAndUpdate(character2);
        assertTrue(cell.getDisplayChar() == character2);
    }

    @Test
    void revertDisplayChar() {
        cell.revertDisplayChar();
        assertTrue(cell.getDisplayChar() == 'a');
    }
}