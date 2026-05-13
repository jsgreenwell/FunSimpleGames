import java.util.ArrayList;
import java.util.List;

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
        }
    }


    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    public void run() {
        System.out.println("Tic Tac Toe - TBD");
    }

    /**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     * For now just changes flog so this can exit
     */
    public void checkExit() {
        keepPlaying = false;
    }
}
