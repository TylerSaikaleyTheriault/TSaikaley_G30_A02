package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HangmanGameTest {

	@Test
	void testSetCurrentWord() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		assertTrue(p.game.getCurrentWordList().getLength() > 0);
	}

	@Test
	void testGetCorrectCount() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(0));
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(1));
		assertTrue(p.game.getCorrectCount() >= 2);
	}

	@Test
	void testGetFalseCount() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(0));
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(1));
		assertEquals(0, p.game.getFalseCount());
	}

	
	@Test
	void testClearLists() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(0));
		p.game.guessLetter(p.game.getCurrentWordList().getElementAt(1));
		p.game.clearLists();
		assertTrue(p.game.getCurrentWordList().getLength() < 1);
	}

}
