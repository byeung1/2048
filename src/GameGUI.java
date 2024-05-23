import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame {

    private Board board;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private JPanel boardPanel;

    public GameGUI() {
        board = new Board();
        board.createNewBoard();
        board.generateTwoTiles();

        setTitle("2048 Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the score label
        JLabel scoreLabel = new JLabel("Score: " + Game.score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Create the high score label
        JLabel highScoreLabel = new JLabel("High Score: " + Game.Highscore);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Add both labels to the score panel
        scorePanel.add(scoreLabel);
        scorePanel.add(highScoreLabel);

        // Add the score panel to the north of the main panel
        add(scorePanel, BorderLayout.NORTH);

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(400, 400));
        add(boardPanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (Game.left(board)) {
                            board.generateNewTile();
                            updateGUI();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Game.right(board)) {
                            board.generateNewTile();
                            updateGUI();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (Game.up(board)) {
                            board.generateNewTile();
                            updateGUI();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (Game.down(board)) {
                            board.generateNewTile();
                            updateGUI();
                        }
                        break;
                }
                if (board.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "Game Over. Highscore: " + Game.score);
                    resetGame();
                }
            }
        });

        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateGUI() {
        scoreLabel.setText("Score: " + Game.score);
        boardPanel.repaint();
    }

    private void drawBoard(Graphics g) {
        int[][] gameBoard = Board.gameBoard;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, boardPanel.getWidth(), boardPanel.getHeight());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                drawTile(g, gameBoard[i][j], i, j);
            }
        }
    }

    private void drawTile(Graphics g, int value, int row, int col) {
        int tileSize = 100;
        int x = col * tileSize;
        int y = row * tileSize;

        g.setColor(getTileColor(value));
        g.fillRect(x, y, tileSize, tileSize);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, tileSize, tileSize);

        if (value != 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g.getFontMetrics();
            String text = String.valueOf(value);
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            g.drawString(text, x + (tileSize - textWidth) / 2, y + (tileSize + textHeight) / 2 - fm.getDescent());
        }
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(0xEEE4DA);
            case 4: return new Color(0xEDE0C8);
            case 8: return new Color(0xF2B179);
            case 16: return new Color(0xF59563);
            case 32: return new Color(0xF67C5F);
            case 64: return new Color(0xF65E3B);
            case 128: return new Color(0xEDCF72);
            case 256: return new Color(0xEDCC61);
            case 512: return new Color(0xEDC850);
            case 1024: return new Color(0xEDC53F);
            case 2048: return new Color(0xEDC22E);
            default: return new Color(0xCDC1B4);
        }
    }

    private void resetGame() {
        Game.score = 0;
        board.createNewBoard();
        board.generateTwoTiles();
        updateGUI();
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
