package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testGetPlayerName() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer(0);
		p.game.setCurrentWord(p);
		assertEquals("Tx123", p.getPlayerName());
	}

	@Test
	void testGetNumberGamesPlayed() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer(0);
		p.game.setCurrentWord(p);
		p.addGamePlayed();
		p.addGamePlayed();
		p.addGamePlayed();
		assertEquals(3, p.getNumberGamesPlayed());
	}
	
	@Test
	void testGetNumberWins() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer(0);
		p.game.setCurrentWord(p);
		p.addGamePlayed();
		p.addGamePlayed();
		p.addGamePlayed();
		p.addWin();
		assertEquals(1, p.getNumberGamesWon());
	}

	

}
