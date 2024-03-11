import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Card> hand;
    private String highCard;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }
    public void SetHighCard(String value){
        this.highCard = value;
    }
    public String getHighCard() {
        return highCard;
    }

    public List<Card> getHand() {
        return hand;
    }






}