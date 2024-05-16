public class Board {

    public static int[][] gameBoard;

    /** 
     * Initializes new board with 0's
     */
    public static void createNewBoard() {
        gameBoard=new int[4][4];
    }

    public static void generateTwoTiles() {
        
    }

    public int getValue (int row, int column) {
        return gameBoard[row][column];
    }

    public void setValue (int row, int column, int value) {
        gameBoard[row][column]=value;
    }
    
    public static void printBoard()
    {
        for (int i=0;i<4;i++)
        {
            
        }
    }
}
