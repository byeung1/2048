import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Board board = new Board();
        board.createNewBoard();

        board.printBoard();

        while (true) {

            switch (sc.nextLine()) {
                case "A": //for moving left
                    for (int i = 0; i < 4; i++) {
                        
                    }
                    break;
            
                default:
                    break;
            }

        }
    }
}
