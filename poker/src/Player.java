import java.util.ArrayList;
import java.util.List;

class Player {
    private static List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public static List<Card> getHand() {
        return hand;
    }






}