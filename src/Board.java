import java.util.Random;

public class Board {

    public static int[][] gameBoard;

    /**
     * Initializes new board with 0's
     */
    public void createNewBoard() {
        gameBoard = new int[4][4];
    }

    /**
     * chooses between 2 and 4 with a 10% chance of being 4
     */
    public int chooseTwoOrFour() {
        Random random = new Random();
        return random.nextInt(10) + 1 == 7 ? 4 : 2;
    }

    /**
     * generates two random tiles
     */
    public void generateTwoTiles() {
        Random random = new Random();

        // choosing 2 random numbers between 1 and 16
        int index1 = random.nextInt(16) + 1;
        int index2 = random.nextInt(16) + 1;
        while (index2 == index1) {
            index2 = random.nextInt(16) + 1;
        }

        // choosing between 2 and 4 for the two random values
        int num1 = chooseTwoOrFour(), num2 = chooseTwoOrFour();

        int n = 1;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (n == index1) {
                    gameBoard[i][k] = num1;
                }
                if (n == index2) {
                    gameBoard[i][k] = num2;
                }
                n++;
            }
        }
    }

    /**
     * generates a random new tile
     */
    public void generateNewTile() {
        Random random = new Random();
        int index = random.nextInt(getEmptyTiles()) + 1; //generating a random index according to number of empty tiles
        int num = 1;
        for (int i = 0; i < 4; i++) { //looping through all 2D index, tracking the numerical value using num
            for (int k = 0; k < 4; k++) {
                if (gameBoard[i][k] == 0) {
                    if (num == index) { //generate 2 or 4 at the correct index
                        gameBoard[i][k] = chooseTwoOrFour();
                        return;
                    } else
                        num++;
                }
            }
        }
    }

    /**
     * @return number of empty tiles
     */
    public int getEmptyTiles() {
        int num = 0;
        for (int i = 0; i < 4; i++) { //looping through all tiles and adding 1 to num if it is empty
            for (int k = 0; k < 4; k++) {
                if (gameBoard[i][k] == 0)
                    num++;
            }
        }
        return num;
    }

    /**
     * checks if game is over: board is full and no moves can be made
     * 
     * @return
     */
    public boolean isGameOver() {
        if (getEmptyTiles() > 0) //return false if there are still empty tiles on the board
            return false;
        else {
            for (int i = 0; i < 4; i++) { //loop through all elements to check if adjacent tiles are equal, return false if they are
                for (int k = 0; k < 3; k++) {
                    if (gameBoard[i][k] == gameBoard[i][k + 1])
                        return false;
                    if (gameBoard[k][i] == gameBoard[k + 1][i])
                        return false;
                }
            }
            return true; //return true if none of the above conditions are met
        }
    }

    /**
     * getter for gameBoard
     * 
     * @param row
     * @param column
     * @return int value
     */
    public int getValue(int row, int column) {
        return gameBoard[row][column];
    }

    /**
     * Setter for gameBoard
     * 
     * @param row
     * @param column
     * @param value
     */
    public void setValue(int row, int column, int value) {
        gameBoard[row][column] = value;
    }
}

