import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 3x3 grid initialization for the board
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        char currentPlayer = 'X';
        int moves = 0;
        boolean hasWon = false;

        System.out.println("=== TIC TAC TOE ===");

        // Main game loop
        while (!hasWon && moves < 9) {

            displayBoard(board);

            System.out.println("\nPlayer " + currentPlayer);

            System.out.print("Enter row (0 to 2): ");
            int row = scanner.nextInt();

            System.out.print("Enter column (0 to 2): ");
            int column = scanner.nextInt();

            // Input validation: bounds check
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Invalid position! Try again.");
                continue;
            }

            // Input validation: availability check
            if (board[row][column] != ' ') {
                System.out.println("Position already occupied! Try again.");
                continue;
            }

            // Make the move
            board[row][column] = currentPlayer;
            moves++;

            // Check match status
            if (checkWin(board, currentPlayer)) {
                hasWon = true;
            } else {
                // Switch player using ternary operator
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        displayBoard(board);

        // Game over results
        if (hasWon) {
            System.out.println("\nCongratulations! Player " + currentPlayer + " wins!");
        } else {
            System.out.println("\nIt's a draw!");
        }

        scanner.close();
    }

    // Renders the current state of the board in the terminal
    public static void displayBoard(char[][] board) {
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);

            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    // Scans rows, columns, and diagonals for 3 matching symbols
    public static boolean checkWin(char[][] board, char player) {

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player) {
                return true;
            }
        }

        // Check main diagonal
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
                return true;
        }

        // Check anti-diagonal
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
                return true;
        }

        return false;
    }
}
