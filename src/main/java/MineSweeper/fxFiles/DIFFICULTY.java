package MineSweeper.fxFiles;

public enum DIFFICULTY {




        EASY(14,19,18),
        MEDIUM(18,23,40),
        HARD(26,33,100),
        INSANE(26, 33, 200);


    private final int row;
    private final int column;
    private final int numOfBombs;

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






