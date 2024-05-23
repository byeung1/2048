public class Game {
    
    public static int score = 0;

    public static Board board = new Board();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int highScore = 0;

        do {

            score = 0;

            board.createNewBoard();

            board.generateTwoTiles();

            board.printBoard();

            while (!board.isGameOver()) {

                outer:
                while (true) {

                    switch (sc.nextLine()) {
                        case "a": //for moving left
                            if (!left(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "d": //for moving right
                            if (!right(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "w": //for moving up
                            if (!up(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        case "s": //for moving down
                            if (!down(board)) {
                                board.printBoard();
                                System.out.println("Score: " + score);
                                break;
                            }
                            break outer;
                        default:
                            break;
                    }

                }

                board.generateNewTile();
                board.printBoard();
                
                System.out.println("Score: " + score);

            }

            if (score > highScore)
                highScore = score;

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

        boolean moved = false;
        // for loop for each row
        for (int row = 0; row < 4; row++) {
            
            boolean[] merged = new boolean[4];
            // for loop for each tile within the row, starting at 1 because the first tile can't move to the left
            for (int j = 1; j < 4; j++) {
                // while true loop because each tile can move more than once
                while (true) {

                    if(board.getValue(row, j) == 0 || j - 1 == -1)
                        break;

                    // if the tile to the left is 0, then move the current tile to the left, and change the merged array
                    if (board.getValue(row, j - 1) == 0) {
                        board.setValue(row, j - 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else if (board.getValue(row, j - 1) == board.getValue(row, j) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(row, j - 1, 2 * board.getValue(row, j - 1));
                        score += board.getValue(row, j - 1);
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else {
                        break;
                    }

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

        boolean moved = false;
        // for loop for each row
        for (int row = 0; row < 4; row++) {

            boolean[] merged = new boolean[4];
            // for loop for each tile within the row, starting at 2 because the last tile can't move to the right
            for (int j = 2; j > -1; j--) {
                // while true loop because each tile can move more than once
                while (true) {

                    if(board.getValue(row, j) == 0 || j + 1 == 4)
                        break;

                    // if the tile to the right is 0, then move the current tile to the right, and change the merged array
                    if (board.getValue(row, j + 1) == 0) {
                        board.setValue(row, j + 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else if (board.getValue(row, j + 1) == board.getValue(row, j) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(row, j + 1, 2 * board.getValue(row, j + 1));
                        score += board.getValue(row, j + 1);
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else {
                        break;
                    }

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

        boolean moved = false;
        // for loop for each column
        for (int column = 0; column < 4; column++) {

            boolean[] merged = new boolean[4];
            // for loop for each tile within the column, starting at 1 because the first tile can't move up
            for (int j = 1; j < 4; j++) {
                // while true loop because each tile can move more than once
                while (true) {

                    if(board.getValue(j, column) == 0 || j - 1 == -1)
                        break;

                    // if the tile above is 0, then move the current tile up, and change the merged array
                    if (board.getValue(j - 1, column) == 0) {
                        board.setValue(j - 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else if (board.getValue(j - 1, column) == board.getValue(j, column) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(j - 1, column, 2 * board.getValue(j - 1, column));
                        score += board.getValue(j - 1, column);
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else {
                        break;
                    }

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

        boolean moved = false;
        // for loop for each column
        for (int column = 0; column < 4; column++) {

            boolean[] merged = new boolean[4];
            // for loop for each tile within the column, starting at 2 because the last tile can't move down
            for (int j = 2; j > -1; j--) {
                // while true loop because each tile can move more than once
                while (true) {

                    if(board.getValue(j, column) == 0 || j + 1 == 4)
                        break;

                    // if the tile below is 0, then move the current tile down, and change the merged array
                    if (board.getValue(j + 1, column) == 0) {
                        board.setValue(j + 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else if (board.getValue(j + 1, column) == board.getValue(j, column) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(j + 1, column, 2 * board.getValue(j + 1, column));
                        score += board.getValue(j + 1, column);
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else {
                        break;
                    }

                    j++;
                }
            }
        }
        return moved;
    }

}

