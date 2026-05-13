import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Framework for Black Jack game
public class BlackJack {

    Scanner scan = new Scanner(System.in);

    public boolean keepPlaying = true;

    // Card Class

    public class Card {

        public enum Suit {
            SPADES,
            HEARTS,
            DIAMONDS,
            CLUBS
        }
// String and integer values for every card. Ace default integer value is 11
        public enum Rank {
            ACE(11),
            TWO(2),
            THREE(3),
            FOUR(4),
            FIVE(5),
            SIX(6),
            SEVEN(7),
            EIGHT(8),
            NINE(9),
            TEN(10),
            JACK(10),
            QUEEN(10),
            KING(10);

            private final int value;

            Rank(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Rank getRank() {
            return rank;
        }

        public int getRankValue() {
            return rank.getValue();
        }
// Returns an emoji for every suit
        private String getSuitSymbol() {
            switch (suit) {
                case SPADES:
                    return "♠";
                case HEARTS:
                    return "♥";
                case DIAMONDS:
                    return "♦";
                case CLUBS:
                    return "♣";
                default:
                    return "?";
            }
        }
// Other symbols
        private String getRankSymbol() {
            switch (rank) {
                case ACE:
                    return "A";
                case JACK:
                    return "J";
                case QUEEN:
                    return "Q";
                case KING:
                    return "K";
                case TEN:
                    return "10";
                default:
                    return String.valueOf(rank.getValue());
            }
        }

        @Override
        public String toString() {

            String rankStr = getRankSymbol();
            String suitStr = getSuitSymbol();

            return String.format(
                    "┌─────────┐\n" +
                            "│%-2s       │\n" +
                            "│         │\n" +
                            "│    %s    │\n" +
                            "│         │\n" +
                            "│       %2s│\n" +
                            "└─────────┘",
                    rankStr,
                    suitStr,
                    rankStr
            );
        }
    }

    // Defines the deck

    public class Deck {

        private final List<Card> cards;

        public Deck() {
            cards = new ArrayList<>();

            // Create all 52 cards
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }

        public void shuffle() {
            Collections.shuffle(cards);
        }

        public Card dealCard() {
            return cards.isEmpty() ? null : cards.remove(0);
        }

        public int remainingCards() {
            return cards.size();
        }
    }

    // Calculates value of the player's hand

    public int getHandValue(List<Card> hand) {

        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {

            total += card.getRankValue();

            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
        }

        // Converts Ace from 11 to 1 if bust
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    // Saves the result of the game in a txt file (blackjack_results)
    public void saveResult(String result) {

        try (PrintWriter writer = new PrintWriter(new FileWriter("blackjack_results.txt", true))) {
            writer.println(result);
        } catch (IOException e) {
            System.out.println("Error saving result.");
        }
    }

    // Main gain loop

    public void run() {

        System.out.println("Dealing cards...");

        boolean bust = false;

        Deck newDeck = new Deck();
        newDeck.shuffle();

        // Create player's hand
        List<Card> hand = new ArrayList<>();

        List<Card> dealerHand = new ArrayList<>();

        // Deals initial cards to player and dealer
        Card cardOne = newDeck.dealCard();
        Card cardTwo = newDeck.dealCard();

        Card dealerOne = newDeck.dealCard();
        Card dealerTwo = newDeck.dealCard();

        // Add cards to hand (arraylist)
        hand.add(cardOne);
        hand.add(cardTwo);

        dealerHand.add(dealerOne);
        dealerHand.add(dealerTwo);


        // Calculate value
        int totalCardValue = getHandValue(hand);
        int dealerHandValue = getHandValue(dealerHand);

        // Prints out cards on separate lines because of graphics
        System.out.println("Dealt: ");
        System.out.println(cardOne);
        System.out.println(cardTwo);

        System.out.println("Card Value: " + totalCardValue);
        System.out.println("Dealer has: ");
        System.out.println(dealerOne);

        // while the player has not exceeded 21, continues to ask hit or stand to the player
        while (!bust) {

            System.out.println("Hit or Stand?");
            String choice = scan.nextLine().toLowerCase();

            switch (choice) {

                case "hit":
                case "h":

                    Card newCard = newDeck.dealCard();
                    hand.add(newCard);

                    totalCardValue = getHandValue(hand);

                    System.out.println("You drew: ");
                    System.out.println(newCard);
                    System.out.println("Hand value: " + totalCardValue);

                    if (totalCardValue > 21) {
                        System.out.println("Bust!");
                        bust = true;
                    }

                    break;

                case "stand":
                case "s":

                    System.out.println("Dealer's turn...");
                    System.out.println("Dealer reveals: ");
                    System.out.println(dealerTwo);
                // After the player selects stand, runs through the dealer's choices
                    while (dealerHandValue < 17) {
                        Card newDealer = newDeck.dealCard();
                        dealerHand.add(newDealer);
                        dealerHandValue = getHandValue(dealerHand);

                        System.out.println("Dealer draws: ");
                        System.out.println(newDealer);
                    }

                    System.out.println("Dealer final hand: ");
                    System.out.println(dealerHandValue);

                    // Determines results
                    String result;
                    if (dealerHandValue > 21) {
                        result = "Player win";
                        System.out.println("Dealer busts. Player wins!");
                    } else if (dealerHandValue > totalCardValue) {
                        result = "Dealer win";
                        System.out.println("Dealer wins!");
                    } else if (dealerHandValue < totalCardValue) {
                        result = "Player win";
                        System.out.println("Player wins!");
                    } else {
                        result = "Tie";
                        System.out.println("Push (tie)");
                    }
                    saveResult(result);
                    bust = true;
                    break;

                default:
                    System.out.println("Try again");
            }
        }
    }

    // Checks if the player wants to exit

    public void checkExit() {

        boolean validInput = false;
        while (!validInput) {

            System.out.println("Do you want to keep playing? (Yes or no)");
            String choice = scan.nextLine().toLowerCase();

            switch (choice) {

                case "yes":
                case "y":
                    validInput = true;
                    break;

                case "no":
                case "n":
                    System.out.println("Thanks for playing!");
                    keepPlaying = false;
                    validInput = true;
                    break;

                default:
                    System.out.println("Invalid. Please enter Yes or No");
            }
        }
    }
}
