package Hangman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import linked_data_structures.*;

public class HangmanGame implements Serializable {
	private SinglyLinkedList<Character> currentWordList;
	private SinglyLinkedList<Character> guessedLettersList;
	private boolean[] lettersGuessedArray;
	private int falseCount, correctCount;
	Scoreboard sb;
	Dictionary dict;
//	Player player;

	public HangmanGame() {
		falseCount = 0;
		correctCount = 0;
		sb = new Scoreboard();
		dict = new Dictionary();
		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();
		setCurrentWord();

	}// HangmanGame()

	public HangmanGame(String name) {
		falseCount = 0;
		correctCount = 0;
		sb = new Scoreboard();
		dict = new Dictionary();
//		player = new Player(name);

		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();
		setCurrentWord();
//		sb.addPlayer(player);

	}// HangmanGame()

	public void setCurrentWord() {

		String word = dict.getWord();
		lettersGuessedArray = new boolean[word.length()];

		for (int i = word.length() - 1; i >= 0; i--) {
			currentWordList.add(word.charAt(i));
			lettersGuessedArray[i] = false;
		} // for
//		for(int i = 0; i < currentWordList.getLength(); i++) {
//			System.out.println(currentWordList.getElementAt(i));
//		}
	}

	/**
	 * Method used to get hints. Generates a random letter from the current word and
	 * checks the letters that have been guessed so far
	 * Uses a falsecount variable to check if the char matches none of the guessed letters
	 * @return Char to be used as a hint
	 */
	public char getHint() {
		Random rand = new Random();
		char hintChar = '0';
		int falseCount = 0;
		int index = -1;
		if (guessedLettersList.getLength() != 0) {
			while (falseCount != guessedLettersList.getLength()) {
				falseCount = 0;
				index = rand.nextInt(currentWordList.getLength());
				hintChar = currentWordList.getElementAt(index);
				for (int i = 0; i < guessedLettersList.getLength(); i++) {
//					System.out.println(currentWordList.getElementAt(index) + " "
//							+ Character.toLowerCase(guessedLettersList.getElementAt(i)));
					if (currentWordList.getElementAt(index) == Character
							.toLowerCase(guessedLettersList.getElementAt(i))) {
						System.out.println("falsecount--");
						falseCount--;
					} else {
						falseCount++;
					}

					System.out.println(falseCount);
				} // for
			} // while
		} // if
		else {
			index = rand.nextInt(currentWordList.getLength());

			hintChar = currentWordList.getElementAt(index);
		}

//		System.out.println(hintChar);
		return Character.toUpperCase(hintChar);

	}// getClue()

	public ArrayList<Integer> guessLetter(char c) {
		ArrayList<Integer> indexList = new ArrayList<Integer>();

		for (int i = 0; i < currentWordList.getLength(); i++) {

			if (Character.toLowerCase(c) == currentWordList.getElementAt(i)) {
				correctCount++;
				indexList.add(i);

			} // if
		} // for
		if (indexList.size() == 0)
			falseCount++;

		guessedLettersList.add(c);
		return indexList;
	}

	public boolean[] getLettersGuessedArray() {
		return lettersGuessedArray;
	}

	public void setLettersGuessedArray(boolean[] lettersGuessedArray) {
		this.lettersGuessedArray = lettersGuessedArray;
	}

	public SinglyLinkedList<Character> getCurrentWordList() {
		return currentWordList;
	}

	public SinglyLinkedList<Character> getGuessedLettersList() {
		return guessedLettersList;
	}

	public int getFalseCount() {
		return falseCount;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void clearLists() {
		System.out.println("List pre-clear: " + currentWordList.getLength() + " " + guessedLettersList.getLength());
		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();

		System.out.println("Lists are cleared: " + currentWordList.getLength() + " " + guessedLettersList.getLength());
	}// clearLists()

//	public void setPlayer(Player p) {
//		player = p;
//	}

}// HangmanGame
