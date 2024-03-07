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

        //System.out.println(player1.getHand());
        System.out.println(player1.getHand());
        System.out.println(player2.getHand());
        System.out.println(player1.getHighCard());
        System.out.println(player2.getHighCard());
        System.out.println(evaluate(player2));
        System.out.println(evaluate(player1));
        getWinner(player1,player2);

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
    public static int highCard(Player p) {

        List<Card> hand = p.getHand();
        int[] ranks = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            int temp = Integer.valueOf(hand.get(i).getRank());
            ranks[i] = temp;

        }
        Arrays.sort(ranks);
        int highCard = 0;
        for (int i = 0; i < 5; i++) {
            if (ranks[i] > highCard){
                highCard = ranks[i];
            }
        }
        return highCard;
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
                System.out.println(p.getHighCard());
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

    public static void getWinner(Player p1, Player p2) {
        if(evaluate(p1) == 0 && evaluate(p2) == 0){
            if(highCard(p1) > highCard(p2)){
                System.out.println("Player 1 won");
                System.out.println("Player 1 hand:");
                System.out.println((p1.getHand()));

            }

        }
      if(evaluate(p1) > evaluate(p2)){
          System.out.println("Player 1 won");
          System.out.println("Player 1 hand:");
          System.out.println((p1.getHand()));


      } else if (evaluate(p1) == evaluate(p2)) {
          int p1_high_card = Integer.valueOf(p1.getHighCard());
          int p2_high_card = Integer.valueOf(p2.getHighCard());
          if(p1_high_card == p2_high_card){
              if(highCard(p1) == highCard(p2)){
                  System.out.println("Its a tie");
                  System.out.println((p1.getHand()));
                  System.out.println((p2.getHand()));

              } else if (highCard(p1) > highCard(p2)) {
                  System.out.println("Player 1 won");
                  System.out.println("Player 1 hand:");
                  System.out.println((p1.getHand()));
              }
              else{
                  System.out.println("Player 2 won");
                  System.out.println("Player 2 hand:");
                  System.out.println((p2.getHand()));
              }
          }else if (p1_high_card > p2_high_card) {
              System.out.println("Player 1 won");
              System.out.println("Player 1 hand:");
              System.out.println((p1.getHand()));
          }
          else{
              System.out.println("Player 2 won");
              System.out.println("Player 2 hand:");
              System.out.println((p2.getHand()));
          }
      } else{
          System.out.println("Player 2 won");
          System.out.println("Player 2 hand:");
          System.out.println((p2.getHand()));
      }
    }

}