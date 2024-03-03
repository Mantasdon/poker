import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();
        boolean isPlayer1 = true; // Flag to determine which player to assign the card
        try {
            File myObj = new File("poker.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String cardString = myReader.next();
                if (!cardString.isBlank()) {
                    if (isPlayer1) {
                        player1Cards.add(parseCard(cardString));
                    } else {
                        player2Cards.add(parseCard(cardString));
                    }
                    isPlayer1 = !isPlayer1; // Switch player after each card
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

        Player player1 = new Player();
        Player player2 = new Player();

        // Deal cards to players
        player1Cards.forEach(player1::addCard);
        player2Cards.forEach(player2::addCard);

        // Display cards for each player
        System.out.println("Player 1 Cards: " + player1.getHand());
        System.out.println("Player 2 Cards: " + player2.getHand());
    }

    private static Card parseCard(String cardString) {
        String rank = cardString.substring(0, cardString.length() - 1);
        char suit = cardString.charAt(cardString.length() - 1);
        return new Card(rank, suit);
    }

}