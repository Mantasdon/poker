class Card {
    private String rank;
    private char suit;

    public Card(String rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + suit;
    }
}