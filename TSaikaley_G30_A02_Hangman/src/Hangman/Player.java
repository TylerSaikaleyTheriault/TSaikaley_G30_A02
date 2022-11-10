package Hangman;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private String playerName;
	private int numberGamesPlayed, numberGamesWon;
	public Dictionary dict;
	public HangmanGame game;
//private ArrayList<String> previousNames = new ArrayList<String>();

	public Player() {
		playerName = "Unknown";
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary();
		game = new HangmanGame();
	}// Player()

	public Player(String name) {
		playerName = name;
		numberGamesPlayed = 0;
		numberGamesWon = 0;
		dict = new Dictionary();
		game = new HangmanGame();
	}// Player(String name)

	public String getPlayerName() {
		return playerName;
	}
	// getPlayerName()

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}// setPlayerName(String playerName)

	public int getNumberGamesPlayed() {
		return numberGamesPlayed;
	}// getNumberGamesPlayed()

	public void setNumberGamesPlayed(int numberGamesPlayed) {
		this.numberGamesPlayed = numberGamesPlayed;
	}// setNumberGamesPlayed(int)

	public int getNumberGamesWon() {
		return numberGamesWon;
	}// getNumberGamesWon()

	public void setNumberGamesWon(int numberGamesWon) {
		this.numberGamesWon = numberGamesWon;
	}// setNumberGamesWon(int)

	public void addWin() {
		this.numberGamesWon++;
		this.numberGamesPlayed++;
		dict.removeCurrentWord();
	}// addWin()

	public void addGamePlayed() {
		this.numberGamesPlayed++;
		dict.removeCurrentWord();
		
	}// addGamePlayed()

	public int getLosses() {
		return this.numberGamesPlayed - this.numberGamesWon;
	}
}
