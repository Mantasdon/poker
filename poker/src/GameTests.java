import org.junit.Test;


import static org.junit.Assert.*;

public class GameTests {
    @Test

    public void startTests(){
        HigherCardWins();
        royalFlushWins();
        straightFlushWins();
        fourPairWins();
        threePairWins();
        twoPairWins();
        onePairWins();
        onePairWithHigherHighCardWins();
        fullHouseWin();
        gameTie();

    }

    @Test

    public void HigherCardWins() {
        PokerGame game = new PokerGame();
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

        // In this case, player 2 has a higher card in their hand (7 of Diamonds)
        assertEquals(2, game.getWinner(player1, player2));
    }
    @Test

    public void royalFlushWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("10", 'D'));
        player1.addCard(new Card("11", 'D'));
        player1.addCard(new Card("12", 'D'));
        player1.addCard(new Card("13", 'D'));
        player1.addCard(new Card("14", 'D'));

        player2.addCard(new Card("2", 'H'));
        player2.addCard(new Card("3", 'H'));
        player2.addCard(new Card("4", 'C'));
        player2.addCard(new Card("5", 'H'));
        player2.addCard(new Card("6", 'H'));

        // In this case, player 1 has a royal flush
        assertEquals(1, game.getWinner(player1, player2));
    }

    @Test
    public void straightFlushWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("3", 'D'));
        player1.addCard(new Card("4", 'D'));
        player1.addCard(new Card("5", 'D'));
        player1.addCard(new Card("6", 'D'));

        player2.addCard(new Card("2", 'H'));
        player2.addCard(new Card("3", 'H'));
        player2.addCard(new Card("4", 'C'));
        player2.addCard(new Card("5", 'H'));
        player2.addCard(new Card("6", 'H'));

        // In this case, player 1 has a straight flush
        assertEquals(1, game.getWinner(player1, player2));
    }
    @Test

    public void fourPairWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("2", 'C'));
        player1.addCard(new Card("2", 'S'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("3", 'H'));
        player2.addCard(new Card("3", 'D'));
        player2.addCard(new Card("3", 'C'));
        player2.addCard(new Card("4", 'S'));
        player2.addCard(new Card("7", 'D'));

        // In this case, player 1 has a four of a kind (4 twos), which should win
        assertEquals(1, game.getWinner(player1, player2));
    }


    @Test
    public void threePairWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("2", 'C'));
        player1.addCard(new Card("3", 'S'));
        player1.addCard(new Card("3", 'H'));

        player2.addCard(new Card("4", 'H'));
        player2.addCard(new Card("4", 'D'));
        player2.addCard(new Card("4", 'C'));
        player2.addCard(new Card("5", 'S'));
        player2.addCard(new Card("6", 'D'));

        // In this case, player 1 has three pairs (2s and 3s), which should win
        assertEquals(1, game.getWinner(player1, player2));
    }

    @Test
    public void twoPairWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("3", 'C'));
        player1.addCard(new Card("3", 'S'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("4", 'H'));
        player2.addCard(new Card("4", 'D'));
        player2.addCard(new Card("5", 'C'));
        player2.addCard(new Card("5", 'S'));
        player2.addCard(new Card("7", 'D'));

        // In this case, player 1 has two pairs (2s and 3s), which should win over player 2's two pairs (4s and 5s)
        assertEquals(2, game.getWinner(player1, player2));
    }

    @Test
    public void onePairWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("3", 'C'));
        player1.addCard(new Card("4", 'S'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("4", 'H'));
        player2.addCard(new Card("4", 'D'));
        player2.addCard(new Card("5", 'C'));
        player2.addCard(new Card("6", 'S'));
        player2.addCard(new Card("7", 'D'));

        // In this case, player 1 has a pair of 2s, while player 2 has a pair of 4s. Player 1 should win.
        assertEquals(2, game.getWinner(player1, player2));
    }

    @Test
    public void onePairWithHigherHighCardWins() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("3", 'C'));
        player1.addCard(new Card("4", 'S'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("2", 'S')); // Player 2 also has a pair of 2s
        player2.addCard(new Card("2", 'C'));
        player2.addCard(new Card("5", 'D')); // Player 2 has a higher high card (5)
        player2.addCard(new Card("6", 'S'));
        player2.addCard(new Card("7", 'D'));

        // In this case, both players have a pair of 2s, but player 2 has a higher high card (5), so player 2 should win.
        assertEquals(2, game.getWinner(player1, player2));
    }

        @Test
        public void fullHouseWin() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("2", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("4", 'C'));
        player1.addCard(new Card("4", 'S'));
        player1.addCard(new Card("4", 'H'));

        player2.addCard(new Card("2", 'S')); // Player 2 also has a pair of 2s
        player2.addCard(new Card("2", 'C'));
        player2.addCard(new Card("3", 'D')); // Player 2 has a higher high card (5)
        player2.addCard(new Card("3", 'S'));
        player2.addCard(new Card("3", 'D'));

        // In this case, both players have a pair of 2s, but player 2 has a higher high card (5), so player 2 should win.
        assertEquals(1, game.getWinner(player1, player2));
    }

    @Test
    public void gameTie() {
        PokerGame game = new PokerGame();
        Player player1 = new Player();
        Player player2 = new Player();

        // Set up hands for player 1 and player 2
        player1.addCard(new Card("1", 'H'));
        player1.addCard(new Card("2", 'D'));
        player1.addCard(new Card("3", 'C'));
        player1.addCard(new Card("4", 'S'));
        player1.addCard(new Card("6", 'H'));

        player2.addCard(new Card("1", 'S')); // Player 2 also has a pair of 2s
        player2.addCard(new Card("2", 'C'));
        player2.addCard(new Card("3", 'D')); // Player 2 has a higher high card (5)
        player2.addCard(new Card("4", 'S'));
        player2.addCard(new Card("6", 'D'));

        // In this case, both players have a pair of 2s, but player 2 has a higher high card (5), so player 2 should win.
        assertEquals(0, game.getWinner(player1, player2));
    }

}
