import java.util.*;

public class Game {
    
    public static int score = 0;

    public static Board board = new Board();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        board.createNewBoard();

        board.generateTwoTiles();

        board.printBoard();

        while (!board.isGameOver()) {

            switch (sc.nextLine()) {
                case "a": //for moving left
                    for (int i = 0; i < 4; i++)
                        left(i, board);
                    break;
                case "d": //for moving right
                    for (int i = 0; i < 4; i++)
                        right(i, board);
                    break;
                case "w": //for moving up
                    for (int i = 0; i < 4; i++)
                        up(i, board);
                    break;
                case "s": //for moving down
                    for (int i = 0; i < 4; i++)
                        down(i, board);
                    break;
                default:
                    break;
            }

            board.generateNewTile();
            board.printBoard();
            
            System.out.println("Score: " + score);

        }
    }

    public static void left(int row, Board board) {
        boolean[] merged = new boolean[4];
        for (int j = 1; j < 4; j++) {
            while (true) {

                if(board.getValue(row, j) == 0 || j - 1 == -1)
                    break;

                if (board.getValue(row, j - 1) == 0) {
                    board.setValue(row, j - 1, board.getValue(row, j));
                    board.setValue(row, j, 0);
                }
                else if (board.getValue(row, j - 1) == board.getValue(row, j) && !merged[j - 1] && !merged[j]) {
                    merged[j - 1] = true;
                    board.setValue(row, j - 1, 2*board.getValue(row, j - 1));
                    score += board.getValue(row, j - 1);
                    board.setValue(row, j, 0);
                }
                else {
                    break;
                }

                j--;

            }
        }
    }

    public static void right(int row, Board board) {
        boolean[] merged = new boolean[4];
        for (int j = 3; j > -1; j--) {
            while (true) {

                if(board.getValue(row, j) == 0 || j + 1 == 4)
                    break;

                if (board.getValue(row, j + 1) == 0) {
                    board.setValue(row, j + 1, board.getValue(row, j));
                    board.setValue(row, j, 0);
                }
                else if (board.getValue(row, j + 1) == board.getValue(row, j) && !merged[j + 1] && !merged[j]) {
                    merged[j + 1] = true;
                    board.setValue(row, j + 1, 2*board.getValue(row, j + 1));
                    score += board.getValue(row, j + 1);
                    board.setValue(row, j, 0);
                }
                else {
                    break;
                }

                j++;

            }
        }
    }

    public static void up(int column, Board board) {
        boolean[] merged = new boolean[4];
        for (int j = 1; j < 4; j++) {
            while (true) {

                if(board.getValue(j, column) == 0 || j - 1 == -1)
                    break;

                if (board.getValue(j - 1, column) == 0) {
                    board.setValue(j - 1, column, board.getValue(j, column));
                    board.setValue(j, column, 0);
                }
                else if (board.getValue(j - 1, column) == board.getValue(j, column) && !merged[j + 1] && !merged[j]) {
                    merged[j + 1] = true;
                    board.setValue(j - 1, column, 2*board.getValue(j - 1, column));
                    score += board.getValue(j - 1, column);
                    board.setValue(j, column, 0);
                }
                else {
                    break;
                }

                j--;

            }
        }
    }

    public static void down(int column, Board board) {
        boolean[] merged = new boolean[4];
        for (int j = 3; j > -1; j--) {
            while (true) {

                if(board.getValue(j, column) == 0 || j + 1 == 4)
                    break;

                if (board.getValue(j + 1, column) == 0) {
                    board.setValue(j + 1, column, board.getValue(j, column));
                    board.setValue(j, column, 0);
                }
                else if (board.getValue(j + 1, column) == board.getValue(j, column) && !merged[j - 1] && !merged[j]) {
                    merged[j - 1] = true;
                    board.setValue(j + 1, column, 2*board.getValue(j + 1, column));
                    score += board.getValue(j + 1, column);
                    board.setValue(j, column, 0);
                }
                else {
                    break;
                }

                j++;

            }
        }
    }
}
