package MineSweeper;

public enum DIFFICULTY {




        EASY(18,25,15),
        MEDIUM(22,29,25),
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






