class Card {
    private String rank;
    private char suit;

    public Card(String rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }
    public String getRank(){
        return this.rank;
    }
    public char getSuit(){
        return this.suit;
    }
    @Override
    public String toString() {
        return rank + suit;
    }
}