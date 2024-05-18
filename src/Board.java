import java.util.Random;
public class Board {

    public static int[][] gameBoard;

    /** 
     * Initializes new board with 0's
     */
    public static void createNewBoard() {
        gameBoard=new int[4][4];
    }

    /**
     * chooses between 2 and 4 with a 10% chance of being 4
     */
    public static int chooseTwoOrFour()
    {
        Random random=new Random(); 
        int a=random.nextInt(10)+1==7?4:2;
        return a;
    }
    
    /**
     * generates two random tiles
     */
    public static void generateTwoTiles() {
        Random random=new Random(); 

        //choosing 2 random numbers between 1 and 16
        int index1=random.nextInt(16) + 1;
        int index2=random.nextInt(16) + 1;
        while (index2==index1) {
            index2=random.nextInt(16) + 1;
        }

        //choosing between 2 and 4 for the two random values
        int num1=chooseTwoOrFour(),num2=chooseTwoOrFour();

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
     * generates a random new tile
     */
    public static void generateNewTile() {
        Random random=new Random(); 
        int index=random.nextInt(getEmptyTiles())+1;
        int num=1;
        for (int i=0;i<4;i++) {
            for (int k=0;k<4;k++) {
                if (gameBoard[i][k]==0){
                    if (num==index) 
                    {
                        gameBoard[i][k]=chooseTwoOrFour();
                        return;
                    }
                    else
                        num++;
                }
                    
            }
        }
    }

    /**
     * 
     * @return number of empty tiles
     */
    public static int getEmptyTiles(){
        int num=0;
        for (int i=0;i<4;i++) {
            for (int k=0;k<4;k++) {
                if (gameBoard[i][k]==0)
                    num++;
            }
        }
        return num;
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
