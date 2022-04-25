package MineSweeper;

public enum DIFFICULTY {




        EASY(14,19,18),
        MEDIUM(18,23,40),
        HARD(26,33,100),
        INSANE(26, 33, 400);


    private int row;
    private int column;
    private int numOfBombs;


    public int getRow() {
        return row;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public int getColumn() {
        return column;
    }


    DIFFICULTY(int row, int column, int numOfBombs){
        this.row = row;
        this.column = column;
        this.numOfBombs = numOfBombs;

        }


    }






