package Hangman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import linked_data_structures.*;

public class HangmanGame implements Serializable {
	private SinglyLinkedList<Character> currentWordList;
	private SinglyLinkedList<Character> guessedLettersList;
	public SinglyLinkedList<Character> correctGuessedLettersList;
	public SinglyLinkedList<Character> failedGuessedLettersList;
	public char[] currentWordState;
	private boolean[] lettersGuessedArray;
	private int falseCount, correctCount;
	Scoreboard sb;
	Dictionary dict;
//	Player player;

	public HangmanGame() {
		falseCount = 0;
		correctCount = 0;
		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();
		correctGuessedLettersList = new SinglyLinkedList<Character>();
		failedGuessedLettersList = new SinglyLinkedList<Character>();
	}// HangmanGame()

	public HangmanGame(String name) {
		falseCount = 0;
		correctCount = 0;

//		player = new Player(name);

		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();
		correctGuessedLettersList = new SinglyLinkedList<Character>();
	}// HangmanGame()

	public void setCurrentWord(Player p) {
		String word = p.dict.getWord();
		lettersGuessedArray = new boolean[word.length()];
		currentWordState = new char[word.length()];
		for (int i = word.length() - 1; i >= 0; i--) {
			currentWordList.add(word.charAt(i));
			currentWordState[i] = '_';
			lettersGuessedArray[i] = false;
		} // for

//		for (int i = 0; i < currentWordList.getLength(); i++) {
//			System.out.print(currentWordList.getElementAt(i));
//
//		}
//		System.out.println();
//		for (int i = 0; i < currentWordState.length; i++) {
//			System.out.print(currentWordState[i]);
//
//		}
//		System.out.println();
	}

	/**
	 * Method used to get hints. Generates a random letter from the current word and
	 * checks the letters that have been guessed so far Uses a falsecount variable
	 * to check if the char matches none of the guessed letters
	 * 
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

				currentWordState[i] = Character.toLowerCase(c);

			} // if
		} // for
		if (indexList.size() == 0) {
			falseCount++;
			failedGuessedLettersList.add(c);
		} else
			correctGuessedLettersList.add(c);

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
		currentWordList = new SinglyLinkedList<Character>();
		guessedLettersList = new SinglyLinkedList<Character>();
		correctGuessedLettersList = new SinglyLinkedList<Character>();
		failedGuessedLettersList = new SinglyLinkedList<Character>();
		correctCount = 0;
		falseCount = 0;

	}// clearLists()

//	public void setPlayer(Player p) {
//		player = p;
//	}

}// HangmanGame
