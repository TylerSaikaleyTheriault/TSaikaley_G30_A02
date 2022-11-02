package Hangman;

import linked_data_structures.*;
import java.io.Serializable;

public class Scoreboard implements Serializable {
	private DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
	private int numPlayers;

	public Scoreboard() {
		
	}// Scoreboard()

	public void addPlayer(Player thePlayer) {
		players.add(thePlayer);
	}// addPlayer(String)
	
	public void addPlayerAfter(Player thePlayer) {
		players.add(thePlayer, players.getLength());
	}// addPlayer(String)

	public void gamePlayed(String name, boolean winOrLose) {

	}// gamePlayed(String, boolean)
	
	public DoublyLinkedList<Player> getPlayerList(){
		return players;
	}
	
	public Player getPlayer(int n) {
		Player p = null;
		
		p = players.getElementAt(n);
		
		return p;
		
	}
	
	
	//TODO
	public String displayScoreboard() {
		String s = "";
		
		return s;
	}
}
