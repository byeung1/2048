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
        for (int i=0;i<4;i++){ //loops through all elements and give the generated number to chosen index
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
        int index=random.nextInt(getEmptyTiles())+1; // choose an index from empty tiles
        int num=1;
        for (int i=0;i<4;i++) { //loops through all tiles
            for (int k=0;k<4;k++) {
                if (gameBoard[i][k]==0){
                    if (num==index) 
                    {
                        gameBoard[i][k]=chooseTwoOrFour(); //generating new number for the corresponding index 
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
        for (int i=0;i<4;i++) { //looping through all tiles, add one to num when it's empty
            for (int k=0;k<4;k++) {
                if (gameBoard[i][k]==0)
                    num++;
            }
        }
        return num;
    }

    /**
     * checks if game is over: board is full and no moves can be mades
     * @return
     */
    public static boolean isGameOver() {
        if (getEmptyTiles()>0) //return false if there are still empty tiles
            return false;
        else {
            for (int i=0;i<4;i++) { //loops through all elements, return false if merges can be made
                for (int k=0;k<3;k++) {
                    if (gameBoard[i][k]==gameBoard[i][k+1])
                        return false;
                    if (gameBoard[k][i]==gameBoard[k+1][i])
                        return false;
                }
            }
            return true; //return true if no conditions above are satisfied
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
        for (int i=0;i<4;i++) //printing the board by row, separated by dashes as lines
        {
            System.out.println("---------------------");
            for (int k=0;k<4;k++) { //printing out individual elements separated by '|'
                System.out.print("|");
                if (gameBoard[i][k]==0)
                    System.out.print("    ");  //print blank if the tile is empty
                else
                    System.out.print(String.format("%4d", gameBoard[i][k])); //format all numbers to take 4 characters
            }
            System.out.println("|");
        }
        System.out.println("---------------------");
    }
}
