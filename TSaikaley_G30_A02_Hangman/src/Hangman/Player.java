package Hangman;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	private String playerName;
	private int numberGamesPlayed, numberGamesWon;
//private ArrayList<String> previousNames = new ArrayList<String>();

	public Player() {
		playerName = "Unknown";
		numberGamesPlayed = 0;
		numberGamesWon = 0;
	}// Player()

	public Player(String name) {
		playerName = name;
		numberGamesPlayed = 0;
		numberGamesWon = 0;
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
	}//addWin()
	
	public void addGamePlayed() {
		this.numberGamesPlayed++;
		
	}//addGamePlayed()
}
