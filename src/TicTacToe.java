import java.util.ArrayList;
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
        board = new char[3][3];
        moves = new ArrayList<String>();
        initialBoard();
    }

    public void initialBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    public void displayBoard() {
        System.out.println();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean doingMove(int row, int col, char symbol) {

        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        board[row][col] = symbol;
        moves.add(symbol + "Played" + row + "," + col);
        return true;
    }

    public boolean winner(char symbol){
        for (int row = 0; row < 3; row++) {
            if(board[row][0] == symbol &&
                board[row][1] == symbol &&
                board[row][2] == symbol) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol &&
                    board[1][col] == symbol &&
                    board[2][col] == symbol) {
                return true;
            }
            if (board[0][0] == symbol &&
                board[1][1] == symbol &&
                board[2][2] == symbol) {
                return true;
            }
            if (board[0][2] == symbol &&
                board[1][1] == symbol &&
                board[2][0] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    public void run() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Player 1: ");
        player1 = scan.nextLine();

        System.out.print("Player 2: ");
        player2 = scan.nextLine();

        char currentSymbol = 'X';
        String currentPlayer = player1;

        while (true){
            displayBoard();

            System.out.println(currentPlayer + "turn.");

            System.out.print("Enter row (0-2): ");
            int row = scan.nextInt();

            System.out.print("Enter column (0-2): ");
            int col = scan.nextInt();

            boolean valid = doingMove(row, col, currentSymbol);
            if (!valid) {
                System.out.println("Invalid move.");
                continue;
            }

            if (winner(currentSymbol)){
                displayBoard();
                System.out.println(currentPlayer + "won.");
                break;
            }
            if (isFull()){
                displayBoard();
                System.out.println("Tie");
                break;
            }
            if (currentSymbol == 'X') {
                currentSymbol = 'O';
                currentPlayer = player2;
            } else  {
                currentSymbol = 'X';
                currentPlayer = player1;
            }
        }
        scan.close();
    }

    /**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     * For now just changes flog so this can exit
     */
    public void checkExit() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Do you want to keep playing? (yes/no: ");
        String choice = scan.nextLine().toLowerCase();

        if (choice.equals("no")){
            keepPlaying = false;
            System.out.println("Thanks");
        } else {
        keepPlaying = true;
    }
}
