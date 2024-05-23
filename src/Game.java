import java.util.*;

public class Game {
    
    //declare and initialize a global variable score because it is modified in many methods
    public static int score = 0;

    //create a board from the Board class
    public static Board board = new Board();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //create a highScore variable to be tracked and printed out after the game ends
        int highScore = 0;

        //use a do while loop so the user can play again
        quit:
        do {

            //set score to 0, neccesary if the user decides to play again
            score = 0;

            board.createNewBoard();

            //instructions
            System.out.println("Use the w, a, s, and d keys to move. Press 'r' to restart. Press 'q' to quit.");

            //generate two tiles at the beginning of each game
            board.generateTwoTiles();

            board.printBoard();

            //for each turn, chech if the game is over, than break, or else continue the game
            gameLoop:
            while (!board.isGameOver()) {

                //for each move, used if the user inputs an illegal move, or if they input a String that isn't w, a, s, d, r, or q
                outer:
                while (true) {

                    switch (sc.nextLine()) {
                        case "a": //for moving left
                            //the method returns false if no tile has been moved, so the code goes through the switch statement again
                            //used so that no new tile is generated if no tile has been moved
                            if (!left(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "d": //for moving right
                            //the method returns false if no tile has been moved, so the code goes through the switch statement again
                            //used so that no new tile is generated if no tile has been moved
                            if (!right(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "w": //for moving up
                            //the method returns false if no tile has been moved, so the code goes through the switch statement again
                            //used so that no new tile is generated if no tile has been moved
                            if (!up(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "s": //for moving down
                            //the method returns false if no tile has been moved, so the code goes through the switch statement again
                            //used so that no new tile is generated if no tile has been moved
                            if (!down(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "r": //for restarting
                            break gameLoop; //break out of the game loop
                        case "q": //for quiting
                            break quit; //break out of the do while loop and immediately end the program
                        default: //default case if the user inputs a String that isn't w, a, s, d, r, or q
                            break;
                    }

                }

                //generate a new tile after every move
                board.generateNewTile();

                //print out the board and score after every move
                board.printBoard();
                
                System.out.println("Score: " + score);

            }

            //once the game is over, set the high score to score if it is greater than the previous high score
            if (score > highScore)
                highScore = score;

            //print out highscore and ask to play again
            System.out.println("Game Over. Highscore: " + highScore);

            System.out.println("Would you like to play again? Enter y/n");

        } while (sc.nextLine().equals("y"));

    }

    /**
     * Executes the move left
     * @param board
     * @return boolean moved, true if a tile has moved, false if it hasn't
     */
    public static boolean left(Board board) {

        // boolean to check if a tile has moved
        boolean moved = false;
        // for loop for each row
        for (int row = 0; row < 4; row++) {

            // array of booleans to keep track of which tiles have been merged
            boolean[] merged = new boolean[4];
            // for loop for each tile within the row, starting at 1 because the first tile can't move to the left
            for (int j = 1; j < 4; j++) {
                // while true loop because each tile can move more than once
                while (true) {
                    // if the current tile is 0, or if j = 0, because we don't have to consider the leftmost tile
                    if (board.getValue(row, j) == 0 || j == 0)
                        break;

                    // if the tile to the left is 0, then move the current tile to the left, and change the merged array
                    if (board.getValue(row, j - 1) == 0) {
                        board.setValue(row, j - 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                        if (merged[j]) {
                            merged[j] = false;
                            merged[j - 1] = true;
                        }
                    }
                    // if the current and previous tile can be merged, and haven't been merged before then merge them and add to the score
                    else if (board.getValue(row, j - 1) == board.getValue(row, j) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(row, j - 1, 2 * board.getValue(row, j - 1));
                        score += board.getValue(row, j - 1);
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    // if nothing happened, i.e. the tile is beside a tile of different value then break, and move onto the next tile
                    else {
                        break;
                    }

                    // run the previous tile to see if it can be moved left, after it already moved left or merged
                    j--;

                }
            }
        }

        return moved;
    }

    /**
     * Executes the move right
     * @param board
     * @return boolean moved, true if a tile has moved, false if it hasn't
     */
    public static boolean right(Board board) {

        // boolean to check if a tile has moved
        boolean moved = false;
        // for loop for each row
        for (int row = 0; row < 4; row++) {

            // array of booleans to keep track of which tiles have been merged
            boolean[] merged = new boolean[4];
            // for loop for each tile within the row, starting at 2 because the last tile can't move to the right
            for (int j = 2; j > -1; j--) {
                // while true loop because each tile can move more than once
                while (true) {
                    // if the current tile is 0, or if j = 3, because we don't have to consider the rightmost tile
                    if (board.getValue(row, j) == 0 || j == 3)
                        break;

                    // if the tile to the right is 0, then move the current tile to the right, and change the merged array
                    if (board.getValue(row, j + 1) == 0) {
                        board.setValue(row, j + 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                        if (merged[j]) {
                            merged[j] = false;
                            merged[j + 1] = true;
                        }
                    }
                    // if the current and next tile can be merged, and haven't been merged before then merge them and add to the score
                    else if (board.getValue(row, j + 1) == board.getValue(row, j) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(row, j + 1, 2 * board.getValue(row, j + 1));
                        score += board.getValue(row, j + 1);
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    // if nothing happened, i.e. the tile is beside a tile of different value then break, and move onto the next tile
                    else {
                        break;
                    }

                    // run the previous tile to see if it can be moved right, after it already moved right or merged
                    j++;

                }
            }
        }

        return moved;
    }

    /**
     * Executes the move up
     * @param board
     * @return boolean moved, true if a tile has moved, false if it hasn't
     */
    public static boolean up(Board board) {

        // boolean to check if a tile has moved
        boolean moved = false;
        // for loop for each column
        for (int column = 0; column < 4; column++) {

            // array of booleans to keep track of which tiles have been merged
            boolean[] merged = new boolean[4];
            // for loop for each tile within the column, starting at 1 because the first tile can't move up
            for (int j = 1; j < 4; j++) {
                // while true loop because each tile can move more than once
                while (true) {
                    // if the current tile is 0, or if j = 0, because we don't have to consider the topmost tile
                    if (board.getValue(j, column) == 0 || j == 0)
                        break;

                    // if the tile above is 0, then move the current tile up, and change the merged array
                    if (board.getValue(j - 1, column) == 0) {
                        board.setValue(j - 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                        if (merged[j]) {
                            merged[j] = false;
                            merged[j - 1] = true;
                        }
                    }
                    // if the current and above tile can be merged, and haven't been merged before then merge them and add to the score
                    else if (board.getValue(j - 1, column) == board.getValue(j, column) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(j - 1, column, 2 * board.getValue(j - 1, column));
                        score += board.getValue(j - 1, column);
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    // if nothing happened, i.e. the tile is beside a tile of different value then break, and move onto the next tile
                    else {
                        break;
                    }

                    // run the previous tile to see if it can be moved up, after it already moved up or merged
                    j--;

                }
            }
        }

        return moved;
    }

    /**
     * Executes the move down
     * @param board
     * @return boolean moved, true if a tile has moved, false if it hasn't
     */
    public static boolean down(Board board) {

        // boolean to check if a tile has moved
        boolean moved = false;
        // for loop for each column
        for (int column = 0; column < 4; column++) {

            // array of booleans to keep track of which tiles have been merged
            boolean[] merged = new boolean[4];
            // for loop for each tile within the column, starting at 2 because the last tile can't move down
            for (int j = 2; j > -1; j--) {
                // while true loop because each tile can move more than once
                while (true) {
                    // if the current tile is 0, or if j = 3, because we don't have to consider the bottommost tile
                    if (board.getValue(j, column) == 0 || j == 3)
                        break;

                    // if the tile below is 0, then move the current tile down, and change the merged array
                    if (board.getValue(j + 1, column) == 0) {
                        board.setValue(j + 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                        if (merged[j]) {
                            merged[j] = false;
                            merged[j + 1] = true;
                        }
                    }
                    // if the current and below tile can be merged, and haven't been merged before then merge them and add to the score
                    else if (board.getValue(j + 1, column) == board.getValue(j, column) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(j + 1, column, 2 * board.getValue(j + 1, column));
                        score += board.getValue(j + 1, column);
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    // if nothing happened, i.e. the tile is beside a tile of different value then break, and move onto the next tile
                    else {
                        break;
                    }

                    // run the previous tile to see if it can be moved down, after it already moved down or merged
                    j++;

                }
            }
        }

        return moved;
    }

}

