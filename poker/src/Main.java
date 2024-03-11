
public class Main {    // Main method to initialize players, read card information, and determine the winner
    public static void main(String[] args) {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();



        // Determine the winner and print the result
        game.startGame(player1,player2);
        System.out.println(player1.getHand());
        System.out.println(player2.getHand());        // Print hands of both players
        // tests
        tests();

    }

    // Method to parse a card string and create a Card object


    //Method of getting points of each player hands

    // Method to determine the winner based on evaluated hands and high cards



    public static void tests() {
        System.out.println("tests");
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("3", 'H'));
        player1.addCard(new Card("4", 'C'));
        player1.addCard(new Card("5", 'H'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("2", 'D'));
        player2.addCard(new Card("3", 'D'));
        player2.addCard(new Card("4", 'D'));
        player2.addCard(new Card("5", 'D'));
        player2.addCard(new Card("7", 'D'));


    }

}