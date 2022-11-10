package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreboardTest {

	
	@Test
	void testGetPlayer() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		sb.addPlayer(new Player("Txdfgdfg123"));
		sb.addPlayer(new Player("Tx234t2123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		assertEquals("Tx123", p.getPlayerName());
		
	}
	@Test
	void testAddPlayer() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		sb.addPlayer(new Player("Txdfgdfg123"));
		sb.addPlayer(new Player("Tx234t2123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		assertEquals(3, sb.getPlayerList().getLength());
		
	}
	
	
}
