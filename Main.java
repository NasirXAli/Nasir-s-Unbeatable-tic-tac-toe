import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
    }
}

class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn = true;
    boolean playerStarts = true; // Track if player starts

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(60, 60, 60)); // Darker background
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem playerFirst = new JMenuItem("Player Starts");
        JMenuItem computerFirst = new JMenuItem("Computer Starts");

        playerFirst.addActionListener(e -> {
            playerStarts = true;
            resetGame();
        });

        computerFirst.addActionListener(e -> {
            playerStarts = false;
            resetGame();
        });

        gameMenu.add(playerFirst);
        gameMenu.add(computerFirst);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);

        textfield.setBackground(new Color(25, 25, 112)); // Dark blue background
        textfield.setForeground(new Color(255, 255, 0)); // Yellow text
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(60, 60, 60)); // Match frame background

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(255, 255, 255)); // White background
            buttons[i].setForeground(new Color(0, 0, 0)); // Black text initially
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(25, 25, 112))); // Dark blue border
            buttons[i].addActionListener(this);

            // Add hover effect
            final int index = i;
            buttons[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (buttons[index].getText().equals("")) {
                        buttons[index].setBackground(new Color(173, 216, 230)); // Light blue when hovered
                    }
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (buttons[index].getText().equals("")) {
                        buttons[index].setBackground(new Color(255, 255, 255)); // Back to white when not hovered
                    }
                }
            });
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    if (player1_turn) {
                        buttons[i].setForeground(new Color(255, 69, 0)); // Red for X
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                        if (!player1_turn) {
                            computerMove();
                        }
                    }
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (playerStarts) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
            computerMove();
        }
    }

    public void check() {
        // check X win conditions
        if ((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X"))
                && (buttons[2].getText().equals("X"))) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X"))
                && (buttons[5].getText().equals("X"))) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X"))
                && (buttons[8].getText().equals("X"))) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X"))
                && (buttons[6].getText().equals("X"))) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X"))
                && (buttons[7].getText().equals("X"))) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X"))
                && (buttons[8].getText().equals("X"))) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X"))
                && (buttons[8].getText().equals("X"))) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X"))
                && (buttons[6].getText().equals("X"))) {
            xWins(2, 4, 6);
        }
        // check O win conditions
        if ((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O"))
                && (buttons[2].getText().equals("O"))) {
            oWins(0, 1, 2);
        }
        if ((buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O"))
                && (buttons[5].getText().equals("O"))) {
            oWins(3, 4, 5);
        }
        if ((buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O"))
                && (buttons[8].getText().equals("O"))) {
            oWins(6, 7, 8);
        }
        if ((buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O"))
                && (buttons[6].getText().equals("O"))) {
            oWins(0, 3, 6);
        }
        if ((buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O"))
                && (buttons[7].getText().equals("O"))) {
            oWins(1, 4, 7);
        }
        if ((buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O"))
                && (buttons[8].getText().equals("O"))) {
            oWins(2, 5, 8);
        }
        if ((buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O"))
                && (buttons[8].getText().equals("O"))) {
            oWins(0, 4, 8);
        }
        if ((buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O"))
                && (buttons[6].getText().equals("O"))) {
            oWins(2, 4, 6);
        }
        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            textfield.setText("Draw");
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.RED);
        buttons[b].setBackground(Color.RED);
        buttons[c].setBackground(Color.RED);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }

    public void computerMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                buttons[i].setText("O");
                int score = minimax(buttons, 0, false);
                buttons[i].setText("");
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }

        buttons[move].setText("O");
        buttons[move].setForeground(new Color(30, 144, 255)); // Dodger blue for O
        player1_turn = true;
        textfield.setText("X turn");
        check();
    }

    public int minimax(JButton[] buttons, int depth, boolean isMaximizing) {
        if (checkWinner(buttons, "O")) {
            return 1;
        } else if (checkWinner(buttons, "X")) {
            return -1;
        } else if (isDraw(buttons)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("O");
                    int score = minimax(buttons, depth + 1, false);
                    buttons[i].setText("");
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("X");
                    int score = minimax(buttons, depth + 1, true);
                    buttons[i].setText("");
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    public boolean checkWinner(JButton[] buttons, String player) {
        if ((buttons[0].getText().equals(player) && buttons[1].getText().equals(player)
                && buttons[2].getText().equals(player)) ||
                (buttons[3].getText().equals(player) && buttons[4].getText().equals(player)
                        && buttons[5].getText().equals(player))
                ||
                (buttons[6].getText().equals(player) && buttons[7].getText().equals(player)
                        && buttons[8].getText().equals(player))
                ||
                (buttons[0].getText().equals(player) && buttons[3].getText().equals(player)
                        && buttons[6].getText().equals(player))
                ||
                (buttons[1].getText().equals(player) && buttons[4].getText().equals(player)
                        && buttons[7].getText().equals(player))
                ||
                (buttons[2].getText().equals(player) && buttons[5].getText().equals(player)
                        && buttons[8].getText().equals(player))
                ||
                (buttons[0].getText().equals(player) && buttons[4].getText().equals(player)
                        && buttons[8].getText().equals(player))
                ||
                (buttons[2].getText().equals(player) && buttons[4].getText().equals(player)
                        && buttons[6].getText().equals(player))) {
            return true;
        }
        return false;
    }

    public boolean isDraw(JButton[] buttons) {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(new Color(255, 255, 255));
            button.setEnabled(true);
        }
        firstTurn();
    }
}
