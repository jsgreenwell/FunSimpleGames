import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;

    private char[][] board;
    private List<String> moves;
    private String player1;
    private String player2;

    public TicTacToe() {
        board = new char[4][4];
        moves = new ArrayList<>();
        initialBoard();
    }

    /**
     * Establishes the board on the screen
     */
    public void initialBoard() {
        for (int row = 1; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                board[row][col] = '-';
            }
        }
    }

    /**
     * Displays the board to the players
     */
    public void displayBoard() {
        System.out.println();

        for (int row = 1; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @param row assigns a number to the row
     * @param col assigns a number to each column
     * @param symbol creates a symbol to be played
     * @return
     */
    public boolean doingMove(int row, int col, char symbol) {

        if (row < 1 || row > 3 || col < 1 || col > 3) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        board[row][col] = symbol;
        moves.add(symbol + "Played" + row + "," + col);
        return true;
    }

    /**
     *
     * @param symbol makes a character to be played
     * @return
     */
    public boolean winner(char symbol) {
        for (int row = 0; row < 3; row++) {
            if (board[row][1] == symbol &&
                    board[row][2] == symbol &&
                    board[row][3] == symbol) {
                return true;
            }
        }
        for (int col = 1; col < 4; col++) {
            if (board[1][col] == symbol &&
                    board[2][col] == symbol &&
                    board[3][col] == symbol) {
                return true;
            }
            if (board[1][1] == symbol &&
                    board[2][2] == symbol &&
                    board[3][3] == symbol) {
                return true;
            }
            if (board[1][3] == symbol &&
                    board[2][2] == symbol &&
                    board[3][1] == symbol) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return checkes to see if the board is full
     */
    public boolean isFull() {
        for (int row = 1; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * resets the board if the player wants to keep playing
     */
    public void resetBoard(){
        for (int row = 1; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                board[row][col] = '-';
            }
        }
    }


    /**
     * Runs the game
     */
    public void run() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Player 1: ");
        player1 = scan.nextLine();

        System.out.print("Player 2: ");
        player2 = scan.nextLine();

        char currentSymbol = 'X';
        String currentPlayer = player1;

        while (true) {
            displayBoard();

            System.out.println(currentPlayer + " turn.");

            int row = -1;
            int col = -1;
            try{
                System.out.print("Enter row (1-3): ");
                row = scan.nextInt();

                System.out.print("Enter column (1-3): ");
                col = scan.nextInt();

                scan.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
                continue;
            }


            boolean valid = doingMove(row, col, currentSymbol);
            if (!valid) {
                System.out.println("Invalid move.");
                continue;
            }

            if (winner(currentSymbol)) {
                displayBoard();
                System.out.println(currentPlayer + " won.");
                break;
            }
            if (isFull()) {
                displayBoard();
                System.out.println("Tie");
                break;
            }
            if (currentSymbol == 'X') {
                currentSymbol = 'O';
                currentPlayer = player2;
            } else {
                currentSymbol = 'X';
                currentPlayer = player1;
            }
        }
    }

    /**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     */
    public void checkExit() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Do you want to keep playing? (yes/no:) ");
        String choice = scan.nextLine().toLowerCase();

        if (choice.equals("yes")) {
            keepPlaying = true;
            System.out.println("Lets play again.");
            resetBoard();
        } else {
            keepPlaying = false;
        }
    }
}