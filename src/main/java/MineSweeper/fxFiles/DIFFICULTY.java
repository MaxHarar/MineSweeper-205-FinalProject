package MineSweeper.fxFiles;

public enum DIFFICULTY {




        EASY(14,19,18,"Easy"),
        MEDIUM(18,23,40,"Medium"),
        HARD(26,33,100,"Hard"),
        INSANE(26, 33, 400,"Insane");


    private final int row;
    private final int column;
    private final int numOfBombs;
    private final String stringName;


    public int getRow() {
        return row;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public int getColumn() {
        return column;
    }

    public String getStringName(){return stringName;}


    DIFFICULTY(int row, int column, int numOfBombs, String stringName){
        this.row = row;
        this.column = column;
        this.numOfBombs = numOfBombs;
        this.stringName = stringName;

        }


    }






