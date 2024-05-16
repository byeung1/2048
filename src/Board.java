import java.util.Random;
public class Board {

    public static int[][] gameBoard;

    /** 
     * Initializes new board with 0's
     */
    public static void createNewBoard() {
        gameBoard=new int[4][4];
    }

    public static void generateTwoTiles() {
        Random random=new Random(); 

        //choosing 2 random numbers between 1 and 16
        int index1=random.nextInt(16) + 1;
        int index2=random.nextInt(16) + 1;
        while (index2==index1) {
            index2=random.nextInt(16) + 1;
        }

        //choosing between 2 and 4 for the two random values
        int num1=2,num2=2;
        if (index1<=2)
            num1=4;
        if (index2==7)
            num2=4;

        int n=1;
        for (int i=0;i<4;i++){
            for (int k=0;k<4;k++){
                if (n==index1){
                    gameBoard[i][k]=num1;
                }
                if (n==index2) {
                    gameBoard[i][k]=num2;
                }
                n++;
            }
        }
    }

    /**
     * getter for gameBoard
     * @param row
     * @param column
     * @return int value
     */
    public int getValue (int row, int column) {
        return gameBoard[row][column];
    }
    /**
     * Setter for gameBoard
     * @param row
     * @param column
     * @param value
     */
    public void setValue (int row, int column, int value) {
        gameBoard[row][column]=value;
    }
    
    /**
     * print gameBoard with grid lines formatted
     */
    public static void printBoard()
    {
        for (int i=0;i<4;i++)
        {
            System.out.println("---------------------");
            System.out.println("|"+String.format("%4d", gameBoard[i][0])+"|"+String.format("%4d", gameBoard[i][1])+
                                "|"+String.format("%4d", gameBoard[i][2])+"|"+String.format("%4d", gameBoard[i][3])+"|");
        }
        System.out.println("---------------------");
    }
}
