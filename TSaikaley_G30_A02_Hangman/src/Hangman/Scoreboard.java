package Hangman;

import linked_data_structures.*;
import java.io.Serializable;

public class Scoreboard implements Serializable {
	private DoublyLinkedList<Player> players;
	private int numPlayers;

	public Scoreboard() {
		players = new DoublyLinkedList<Player>();
	}// Scoreboard()

	// adds players to the list while simultaneously sorting them.
	public void addPlayer(Player thePlayer) {
		boolean playerAdded = false;
		int smallestIndex = -1;
		if (players.getLength() == 0) {
			players.add(thePlayer);
			playerAdded = true;
		} else
			for (int i = players.getLength() - 1; i >= 0; i--) {
				if (thePlayer.getPlayerName().compareTo(players.getElementAt(i).getPlayerName()) > 0) {
					players.add(thePlayer, i + 1);
					playerAdded = true;
					i = -1;
				} else if (thePlayer.getPlayerName().compareTo(players.getElementAt(i).getPlayerName()) < 0)
					smallestIndex = i;
			}
		if (!playerAdded)
			players.add(thePlayer, smallestIndex);
	}// addPlayer(String)

	public void addPlayerAfter(Player thePlayer) {
		players.add(thePlayer, players.getLength());
	}// addPlayer(String)

	public void gamePlayed(String name, boolean winOrLose) {

	}// gamePlayed(String, boolean)

	public DoublyLinkedList<Player> getPlayerList() {
		return players;
	}

	public Player getPlayer(int n) {
		Player p = null;

		p = players.getElementAt(n);

		return p;

	}

	public Player getPlayer(String name) {
		Player p = null;

		for (int i = 0; i < players.getLength(); i++) {
			if (players.getElementAt(i).getPlayerName().equals(name))
				p = players.getElementAt(i);
		}

		return p;

	}

	// Formats and returns the scoreboard.
	public String displayScoreboard() {
		Player[] sortedPlayers = new Player[players.getLength()];
		String s = "";
		for (int i = 0; i < players.getLength(); i++) {
			Player thePlayer = players.getElementAt(i);
//			s += thePlayer.getPlayerName() + " Wins:" + thePlayer.getNumberGamesWon() + " Losses:"
//					+ (thePlayer.getNumberGamesPlayed() - thePlayer.getNumberGamesWon()) + "\r";
			s += String.format("%-20s \t %20s%d%10s%d \r", thePlayer.getPlayerName(), " Wins:",
					thePlayer.getNumberGamesWon(), " Losses:", thePlayer.getLosses());
			s += String.format("%-50s\r", "---------------------------------------------------------------");
		}
		return s;
	}
}
