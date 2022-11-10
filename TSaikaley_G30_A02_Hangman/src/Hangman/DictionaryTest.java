package Hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void testCreateDictionary() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		assertEquals(45, p.dict.wordsList.getLength());
	}

	@Test
	void testRemoveWord() {
		Scoreboard sb = new Scoreboard();
		sb.addPlayer(new Player("Tx123"));
		Player p = sb.getPlayer("Tx123");
		p.game.setCurrentWord(p);
		assertEquals(45, p.dict.wordsList.getLength());
		p.dict.removeCurrentWord();
		assertEquals(44, p.dict.wordsList.getLength());
	}




}
