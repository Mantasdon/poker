import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
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


                    } else {
                        player2.addCard(parseCard(cardString));


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

        System.out.println(isThree(player1.getHand()));

    }


    private static Card parseCard(String cardString) {
        String rank = cardString.substring(0, cardString.length() - 1);

        if(rank.equals("T")){
            rank = "11";
        }
        else if (rank.equals("Q")){
            rank = "12";
        }
        else if (rank.equals("K")){
            rank = "13";
        }
        else if (rank.equals("A")){
            rank = "14";
        }
        char suit = cardString.charAt(cardString.length() - 1);
        return new Card(rank, suit);
    }

    public static int isFlush(List<Card> hand) {

        for(int i = 1; i < hand.size() - 1; i++ ){
            if(hand.get(i - 1).getSuit() != hand.get(i).getSuit()){
                return 0;
            }
        }
        return 1;
    }
    public static int isStraight(List<Card> hand) {

        int[] ranks = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            int temp = Integer.valueOf(hand.get(i).getRank());
            ranks[i] = temp;

        }
        Arrays.sort(ranks);

        for (int i = 0; i < ranks.length - 1; i++) {
            if (ranks[i + 1] - ranks[i] != 1) {
                return 0;
            }
        }

        return 1;
    }

    public static int isThree(List<Card> hand) {

        for (int i = 0; i < hand.size(); i++) {
            int count = 1;
            String currentRank = hand.get(i).getRank();
            for (int j = i + 1; j < hand.size(); j++) {
                if (currentRank.equals(hand.get(j).getRank())) {
                    count++;
                    if (count == 3) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

}