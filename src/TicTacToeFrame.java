import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    private static final int ROW = 3;
    private static final int COL = 3;
    private JButton[][] buttons = new JButton[ROW][COL];
    private String currentPlayer = "X";
    private int moveCount = 0;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                JButton button = new JButton("");
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return buttons[row][col].getText().isEmpty();
    }

    private boolean isWin(String player) {

        for (int i = 0; i < ROW; i++) {
            if (buttons[i][0].getText().equals(player) &&
                    buttons[i][1].getText().equals(player) &&
                    buttons[i][2].getText().equals(player)) {
                return true;
            }
        }

        for (int i = 0; i < COL; i++) {
            if (buttons[0][i].getText().equals(player) &&
                    buttons[1][i].getText().equals(player) &&
                    buttons[2][i].getText().equals(player)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false; // No win
    }

    private boolean isTie() {
        return moveCount == 9;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }


    private void promptForNewGame() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to play another game?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }
    private void resetGame() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }
    private void displayWin(String player) {
        JOptionPane.showMessageDialog(this, "Player " + player + " wins!");
        promptForNewGame();
    }

    private void displayTie() {
        JOptionPane.showMessageDialog(this, "It's a Tie!");
        promptForNewGame();
    }
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {

            if (isValidMove(row, col)) {
                buttons[row][col].setText(currentPlayer);
                moveCount++;

                if (moveCount >= 5 && isWin(currentPlayer)) {
                    displayWin(currentPlayer);
                } else if (moveCount >= 7 && isTie()) {
                    displayTie();
                } else {
                    switchPlayer();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
            }
        }

    }



}