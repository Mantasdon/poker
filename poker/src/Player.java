import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }






}