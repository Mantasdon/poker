import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PokerGame {

    public static void startGame(Player p1, Player p2){
        int count = 0;
        try { // Read card information from a file named "poker.txt"

            File myObj = new File("poker.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String cardString = myReader.next();
                if (!cardString.isBlank()) { // Fill hands for player 1 and player 2



                    if (count < 5) {
                        p1.addCard(parseCard(cardString));


                    } else {
                        p2.addCard(parseCard(cardString));


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
        getWinner(p1, p2);

    }

    public static int getWinner(Player p1, Player p2) {
        // Evaluate hands of both players
        if(evaluate(p1) == 0 && evaluate(p2) == 0){
            if(highCard(p1,p2) == 0){
                System.out.println("Its a tie");
                System.out.println((p1.getHand()));
                System.out.println((p2.getHand()));
                return 0;

            } else if (highCard(p1,p2) == 1) {
                System.out.println("Player 1 won");
                System.out.println("Player 1 hand:");
                System.out.println((p1.getHand()));
                return 1;
            }
            else{
                System.out.println("Player 2 won");
                System.out.println("Player 2 hand:");
                System.out.println((p2.getHand()));
                return 2;
            }

        } else if (evaluate(p1) > evaluate(p2)){
            System.out.println("Player 1 won");
            System.out.println("Player 1 hand:");
            System.out.println((p1.getHand()));
            return 1;


        } else if (evaluate(p1) == evaluate(p2)) {
            int p1_high_card = Integer.valueOf(p1.getHighCard());
            int p2_high_card = Integer.valueOf(p2.getHighCard());
            if(p1_high_card == p2_high_card){
                if(highCard(p1,p2) == 0){
                    System.out.println("Its a tie");
                    System.out.println((p1.getHand()));
                    System.out.println((p2.getHand()));
                    return 0;


                } else if (highCard(p1,p2) == 1) {
                    System.out.println("Player 1 won");
                    System.out.println("Player 1 hand:");
                    System.out.println((p1.getHand()));
                    return 1;
                }
                else{
                    System.out.println("Player 2 won");
                    System.out.println("Player 2 hand:");
                    System.out.println((p2.getHand()));
                    return 2;
                }
            }else if (p1_high_card > p2_high_card) {
                System.out.println("Player 1 won");
                System.out.println("Player 1 hand:");
                System.out.println((p1.getHand()));
                return 1;
            }
            else{
                System.out.println("Player 2 won");
                System.out.println("Player 2 hand:");
                System.out.println((p2.getHand()));
                return 2;
            }
        } else{
            System.out.println("Player 2 won");
            System.out.println("Player 2 hand:");
            System.out.println((p2.getHand()));
            return 2;
        }

    }

    public static int evaluate(Player p) {
        List<Card> hand = p.getHand();

        if(isRoyalFLush(hand) == 1){
            return 9;
        } else if (isStraightFlush(hand) == 1) {
            return 8;
        }else if (isFourPairs(hand) == 1) {
            return 7;
        }else if (isFullHouse(p) == 1) {
            return 6;
        }else if (isFlush(hand) == 1) {
            return 5;
        }else if (isStraight(hand) == 1) {
            return 4;
        }else if (isThreePairs(p) == 1) {
            return 3;
        }else if (isTwoPair(p) == 1) {
            return 2;
        }else if (isOnePair(p) == 1) {
            return 1;
        }
        return 0;
    }

    private static Card parseCard(String cardString) {
        String rank = cardString.substring(0, cardString.length() - 1);

        if(rank.equals("J")){
            rank = "11";
        }
        else if (rank.equals("Q")){
            rank = "12";
        }
        else if (rank.equals("T")){
            rank = "10";
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
    public static int highCard(Player p1, Player p2) {

        List<Card> hand = p1.getHand();
        List<Card> hand2 = p2.getHand();
        int[] ranks = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            int temp = Integer.valueOf(hand.get(i).getRank());
            ranks[i] = temp;

        }
        int[] ranks2 = new int[hand2.size()];
        for (int i = 0; i < hand2.size(); i++) {
            int temp = Integer.valueOf(hand2.get(i).getRank());
            ranks2[i] = temp;

        }
        Arrays.sort(ranks);
        Arrays.sort(ranks2);
        for (int i = ranks.length - 1; i >= 0; i--) {
            if (ranks[i] > ranks2[i]) {
                return 1;
            } else if (ranks[i] < ranks2[i]) {
                return 2;
            }
        }

        return 0;
    }
    public static int isOnePair(Player p) {
        List<Card> hand = p.getHand();
        for (int i = 0; i < hand.size() - 1; i++) {
            String currentRank = hand.get(i).getRank();
            for (int j = i + 1; j < hand.size(); j++) {
                if (currentRank.equals(hand.get(j).getRank())) {
                    p.SetHighCard(currentRank);
                    return 1;
                }
            }
        }
        return 0;
    }
    public static int isTwoPair(Player p) {
        List<Card> hand = p.getHand();

        for (int i = 0; i < hand.size(); i++) {
            int count = 1;
            String currentRank = hand.get(i).getRank();
            for (int j = i + 1; j < hand.size(); j++) {
                if (currentRank.equals(hand.get(j).getRank())) {
                    count++;
                    if (count == 2) {
                        p.SetHighCard(currentRank);
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    public static int isThreePairs(Player p) {
        List<Card> hand = p.getHand();
        for (int i = 0; i < hand.size(); i++) {
            int count = 1;
            String currentRank = hand.get(i).getRank();
            for (int j = i + 1; j < hand.size(); j++) {
                if (currentRank.equals(hand.get(j).getRank())) {
                    count++;
                    if (count == 3) {
                        p.SetHighCard(currentRank);
                        return 1;
                    }
                }
            }
        }
        return 0;
    }    public static int isStraight(List<Card> hand) {

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
    public static int isFlush(List<Card> hand) {

        for(int i = 1; i < hand.size(); i++ ){
            if(hand.get(i - 1).getSuit() != hand.get(i).getSuit()){

                return 0;
            }
        }
        return 1;
    }
    public static int isFullHouse(Player p) {
        List<Card> hand = p.getHand();
        int rankCount = 0;
        String rank = "";
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                rankCount++;
            } else {
                rankCount = 1;
                rank = card.getRank();
            }
            if (rankCount == 3) {
                p.SetHighCard(rank);
                break;
            }
        }

        if (rankCount != 3) {
            return 0;
        }

        List<Card> remainingCards = new ArrayList<>();
        for (Card card : hand) {
            if (!card.getRank().equals(rank)) {
                remainingCards.add(card);
            }
        }

        for (int i = 0; i < remainingCards.size() - 1; i++) {
            String currentRank = remainingCards.get(i).getRank();
            for (int j = i + 1; j < remainingCards.size(); j++) {
                if (currentRank.equals(remainingCards.get(j).getRank())) {

                    return 1;
                }
            }
        }

        return 0;
    }


    public static int isFourPairs(List<Card> hand) {

        for (int i = 0; i < hand.size(); i++) {
            int count = 1;
            String currentRank = hand.get(i).getRank();
            for (int j = i + 1; j < hand.size(); j++) {
                if (currentRank.equals(hand.get(j).getRank())) {
                    count++;
                    if (count == 4) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }






    public static int isStraightFlush(List<Card> hand) {
        if(isStraight(hand) == 1 && isFlush(hand) == 1){
            return 1;

        }
        return 0;
    }
    public static int isRoyalFLush(List<Card> hand) {
        if(isFlush(hand) == 1){

            int[] ranks = new int[hand.size()];
            for (int i = 0; i < hand.size(); i++) {
                int temp = Integer.valueOf(hand.get(i).getRank());
                ranks[i] = temp;

            }
            Arrays.sort(ranks);
            if(ranks[0] == 10 && isStraight(hand) == 1) {
                return 1;

            }
        }
        return 0;
    }
}
