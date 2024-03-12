
public class Main {    // Main method to initialize players, read card information, and determine the winner
    public static void main(String[] args) {
        GameTests test = new GameTests();
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();



        // Determine the winner and print the result
        game.startGame(player1,player2);
        System.out.println(" ");
        System.out.println("player 1 hand:");
        System.out.println(player1.getHand());
        System.out.println("player 2 hand:");
        System.out.println(player2.getHand());        // Print hands of both players
        //test.startTests();

    }





}