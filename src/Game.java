import java.util.*;

public class Game {
    
    public static int score = 0;

    public static Board board = new Board();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int highScore = 0;

        quit:
        do {

            score = 0;

            board.createNewBoard();

            System.out.println("Use the w, a, s, and d keys to move. Press 'r' to restart. Press 'q' to quit.");

            board.generateTwoTiles();

            board.printBoard();

            gameLoop:
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
                        case "r": //for restarting
                            break gameLoop;
                        case "q": //for quiting
                            break quit;
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

    public static boolean left(Board board) {

        boolean moved = false;
        for (int row = 0; row < 4; row++) {
            
            boolean[] merged = new boolean[4];
            for (int j = 1; j < 4; j++) {
                while (true) {

                    if(board.getValue(row, j) == 0 || j == 0)
                        break;

                    if (board.getValue(row, j - 1) == 0) {
                        board.setValue(row, j - 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else if (board.getValue(row, j - 1) == board.getValue(row, j) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(row, j - 1, 2*board.getValue(row, j - 1));
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

    public static boolean right(Board board) {

        boolean moved = false;
        for (int row = 0; row < 4; row++) {

            boolean[] merged = new boolean[4];
            for (int j = 2; j > -1; j--) {
                while (true) {

                    if(board.getValue(row, j) == 0 || j == 3)
                        break;

                    if (board.getValue(row, j + 1) == 0) {
                        board.setValue(row, j + 1, board.getValue(row, j));
                        board.setValue(row, j, 0);
                        moved = true;
                    }
                    else if (board.getValue(row, j + 1) == board.getValue(row, j) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(row, j + 1, 2*board.getValue(row, j + 1));
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

    public static boolean up(Board board) {

        boolean moved = false;
        for (int column = 0; column < 4; column++) {

            boolean[] merged = new boolean[4];
            for (int j = 1; j < 4; j++) {
                while (true) {

                    if(board.getValue(j, column) == 0 || j == 0)
                        break;

                    if (board.getValue(j - 1, column) == 0) {
                        board.setValue(j - 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else if (board.getValue(j - 1, column) == board.getValue(j, column) && (!merged[j - 1] && !merged[j])) {
                        merged[j - 1] = true;
                        board.setValue(j - 1, column, 2*board.getValue(j - 1, column));
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

    public static boolean down(Board board) {

        boolean moved = false;
        for (int column = 0; column < 4; column++) {

            boolean[] merged = new boolean[4];
            for (int j = 2; j > -1; j--) {
                while (true) {

                    if(board.getValue(j, column) == 0 || j == 3)
                        break;

                    if (board.getValue(j + 1, column) == 0) {
                        board.setValue(j + 1, column, board.getValue(j, column));
                        board.setValue(j, column, 0);
                        moved = true;
                    }
                    else if (board.getValue(j + 1, column) == board.getValue(j, column) && (!merged[j + 1] && !merged[j])) {
                        merged[j + 1] = true;
                        board.setValue(j + 1, column, 2*board.getValue(j + 1, column));
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
