package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreboardTest {

	
	@Test
	void testAddPlayer() {
		Player p1 = new Player("tyler");
		Scoreboard sb = new Scoreboard();
		p1.addWin();
		sb.addPlayer(p1);
		assertEquals(1, sb.getPlayer(0).getNumberGamesWon(), "Add wins in scoreboard part 1 passed");
		p1.addWin();
		assertEquals(2, sb.getPlayer(0).getNumberGamesWon(), "Add wins in scoreboard part 2 passed");
	}
	
	@Test
	void testAddPlayer2() {
		HangmanGame game = new HangmanGame();
		game.sb.addPlayer(new Player("Tyler"));
		assertEquals("Tyler", game.sb.getPlayer(0).getPlayerName());
		Player currentPlayer = game.sb.getPlayer(0);
		game.sb.addPlayer(new Player("Ligma"));
		assertEquals("Tyler", game.sb.getPlayer(1).getPlayerName());
		assertEquals("Ligma", game.sb.getPlayer(0).getPlayerName());
	}
	
	@Test
	void testAddPlayer3() {
		HangmanGame game = new HangmanGame();
		game.sb.addPlayer(new Player("Tyler"));
		assertEquals("Tyler", game.sb.getPlayer(0).getPlayerName());
		Player currentPlayer = game.sb.getPlayer(0);
		game.sb.addPlayerAfter(new Player("Ligma"));
		assertEquals("Tyler", game.sb.getPlayer(0).getPlayerName());
		assertEquals("Ligma", game.sb.getPlayer(1).getPlayerName());
	}
	
	@Test
	void testAddPlayer4() {
		HangmanGame game = new HangmanGame();
		game.sb.addPlayer(new Player("Tyler"));
		assertEquals("Tyler", game.sb.getPlayer(0).getPlayerName());
		Player currentPlayer = game.sb.getPlayer(0);
		game.sb.addPlayerAfter(new Player("Ligma"));
		assertEquals(0, game.sb.getPlayer(0).getNumberGamesWon());
		currentPlayer.addWin();
		assertEquals(1, game.sb.getPlayer(0).getNumberGamesWon());
	}

}
