import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short choice = 100;

        while (choice != 9) {
            // Card games are listed from easiest to program to hardest
            System.out.print("""
                    Hello and Welcome to the main menu!
                    Please select from the following options (or 9 to exit):
                        0: Boogle
                        1: A Card Game
                        2: Rock Paper Scissors
                        3: Scramble
                        4: TicTacToe
                        5: Wheel
                        6: BlackJack
                        9: Exit
                    Enter the number of your selection:
                    """);

            choice = Short.parseShort(scan.nextLine());
            switch (choice) {
                case 0:
                    Boogle boogle = new Boogle();
                    while (boogle.keepPlaying) {
                        boogle.run();
                        boogle.checkExit();
                    }
                    break;
                case 1:
                    CardGame cardGame = new CardGame();
                    while (cardGame.keepPlaying) {
                        cardGame.run();
                        cardGame.checkExit();
                    }
                    break;
                case 2:
                    RockPaperScissors rps = new RockPaperScissors();
                    while (rps.keepPlaying) {
                        rps.run();
                        rps.checkExit();
                    }
                    break;
                case 3:
                    Scramble scramble = new Scramble();
                    while (scramble.keepPlaying) {
                        scramble.run();
                        scramble.checkExit();
                    }
                    break;
                case 4:
                    TicTacToe ttt = new TicTacToe();
                    while (ttt.keepPlaying) {
                        ttt.run();
                        ttt.checkExit();
                    }
                    break;
                case 5:
                    Wheel wheel = new Wheel();
                    while (wheel.keepPlaying) {
                        wheel.run();
                        wheel.checkExit();
                    }
                    break;
                case 6:
                    BlackJack blackJack = new BlackJack();
                    while (blackJack.keepPlaying) {
                        blackJack.run();
                        blackJack.checkExit();
                    }
                    break;
                default:
                    System.out.println("Thanks for playing!\nGoodbye!");
                    choice = 9; // set to exit
            }
        }
    }
}
