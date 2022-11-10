package Hangman;

import linked_data_structures.*;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Dictionary implements Serializable {
	SinglyLinkedList<String> wordsList = new SinglyLinkedList<String>();
	private File wordsFile = new File("word_db.txt");
	private int currentWordIndex;
	private transient Scanner reader;

	public Dictionary() {
		createDictionary();
		currentWordIndex = -1;

	}// Dictionary()

	public void createDictionary() {

		try {
			reader = new Scanner(wordsFile);
			while (reader.hasNext()) {
				String theWord = reader.next();
				if (theWord.length() > 0 && theWord.length() < 21) {
					wordsList.add(theWord);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("word_db.txt not found : " + e);
		}


	}// createDictionary()

	public boolean removeWord(String element) {
		boolean successful = false;
		int elementIndex = -1;

		for (int i = 0; i < wordsList.getLength(); i++) {
			if (wordsList.getElementAt(i) == element)
				elementIndex = i;
		}
		if (wordsList.remove(elementIndex) != null)
			successful = true;
		return successful;
	}

	public void removeCurrentWord() {
		wordsList.remove(currentWordIndex);
	}// removeWord(int)

	// Gets a random word from the wordslist
	public String getWord() {
		Random rand = new Random();
		String theWord = null;
		int index = rand.nextInt(0, wordsList.getLength());
		theWord = wordsList.getElementAt(index);
		currentWordIndex = index;
		return theWord;
	}
}
