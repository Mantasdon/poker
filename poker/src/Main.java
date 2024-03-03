import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        Player player1 = new Player();
        Player player2 = new Player();
        int count = 0;
        try {
            File myObj = new File("poker.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String cardString = myReader.next();
                if (!cardString.isBlank()) {


                    if (count < 5) {
                        player1.addCard(parseCard(cardString));
                        System.out.println(parseCard(cardString));

                    } else {
                        player2.addCard(parseCard(cardString));
                        System.out.println(parseCard(cardString));

                    }
                    count++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }


        // Deal cards to player

        // Display cards for each player
        System.out.println("Player 1 Cards: " + player1.getHand());

    }

    private static Card parseCard(String cardString) {
        String rank = cardString.substring(0, cardString.length() - 1);
        char suit = cardString.charAt(cardString.length() - 1);
        return new Card(rank, suit);
    }

    public static int isFlush(List<Card> hand) {
        System.out.println(hand);
        for(int i = 0; i < hand.size(); i++ ){
            System.out.println(hand.get(i).getRank());




        }
        return 0;
    }


}