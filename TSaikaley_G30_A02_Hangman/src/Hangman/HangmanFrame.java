package Hangman;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class HangmanFrame extends JFrame implements ActionListener {

	String[] letterArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" };
	JButton[] btnArray = new JButton[26];
	JButton[] letterBlockArray, playerBtnArray;
	JPanel wordBlockPanel, panel;
	Scoreboard sb;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFileMenu, mnHintMenu, mnScoreMenu, mnHelpMenu;
	private JMenuItem rulesItem, newGameItem, exitGameItem, viewScoreItem, getHintItem;
	String filename = "savedGame.ser";
	HangmanGame game = null;
	Player currentPlayer = null;
	String pictureString = "0.png";
	JLabel lblPicture;
	boolean playerChanged = false;
	private JLabel lblPlayerName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					frame.setTitle("Hangman");
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}// main

	/**
	 * Create the frame.
	 */
	public HangmanFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 541);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(101, 336, 596, 155);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 152, 22);
		contentPane.add(menuBar);

		mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);

		newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.clearLists();
				startOver();
			}
		});
		mnFileMenu.add(newGameItem);

		JMenuItem changePlayerItem = new JMenuItem("Change Player");
		changePlayerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateChangePlayers();
			}

		});

		JMenuItem newPlayerItem = new JMenuItem("New Player");
		newPlayerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPlayer();
			}
		});
		mnFileMenu.add(newPlayerItem);
		mnFileMenu.add(changePlayerItem);

		exitGameItem = new JMenuItem("Exit Game");
		mnFileMenu.add(exitGameItem);

		mnHintMenu = new JMenu("Hint");
		menuBar.add(mnHintMenu);

		getHintItem = new JMenuItem("Get Hint");
		getHintItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buttonIndex = findButtonIndex(game.getHint());

				btnArray[buttonIndex].doClick();
			}
		});
		mnHintMenu.add(getHintItem);

		mnScoreMenu = new JMenu("Score");
		menuBar.add(mnScoreMenu);

		viewScoreItem = new JMenuItem("View Scoreboard");
		viewScoreItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				game.sb.addPlayer(new Player("cusdfrt"));
//				game.sb.addPlayer(new Player("Fadfseng"));
//				game.sb.addPlayer(new Player("glizz"));
//				game.sb.getPlayer(2).addWin();
//				game.sb.getPlayer(2).addGamePlayed();

				if (sb != null)
					JOptionPane.showMessageDialog(null, new ScoreboardPanel(sb), "Leaderboard",
							JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnScoreMenu.add(viewScoreItem);

		mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);

		rulesItem = new JMenuItem("Rules");
		rulesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, new HangmanInfoPanel(), "Rules", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelpMenu.add(rulesItem);

		wordBlockPanel = new JPanel();
		wordBlockPanel.setBounds(101, 262, 596, 63);
		contentPane.add(wordBlockPanel);
		wordBlockPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblPicture = new JLabel();
		lblPicture.setIcon(new ImageIcon("0.png"));
		lblPicture.setBounds(252, 11, 241, 240);
		contentPane.add(lblPicture);
		
		lblPlayerName = new JLabel("");
		lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlayerName.setBounds(40, 60, 152, 32);
		contentPane.add(lblPlayerName);

		for (int i = 0; i < letterArray.length; i++) {
			btnArray[i] = new JButton(letterArray[i]);
			btnArray[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
			btnArray[i].addActionListener(this);
			panel.add(btnArray[i]);

		} // for

		deserialize();

	}// HangmanFrame()

	public void generateChangePlayers() {
		playerBtnArray = new JButton[sb.getPlayerList().getLength()];
		playerChanged = false;
		for (int i = 0; i < sb.getPlayerList().getLength(); i++) {
			playerBtnArray[i] = new JButton(sb.getPlayerList().getElementAt(i).getPlayerName());
			if (currentPlayer != null)
				if (currentPlayer.getPlayerName().equalsIgnoreCase(playerBtnArray[i].getText()))
					playerBtnArray[i].setEnabled(false);

			playerBtnArray[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();

					for (int i = 0; i < playerBtnArray.length; i++) {
						playerBtnArray[i].setEnabled(true);
					}
					btn.setEnabled(false);
					changePlayer(btn.getText());
					playerChanged = true;
				}
			});
		} // for

		JOptionPane.showMessageDialog(null, new ChangePlayerPanel(playerBtnArray), "Select Player",
				JOptionPane.PLAIN_MESSAGE);
		if (playerChanged)
			try {
				loadKeyboard();
				generateCurrentLetters();
				lblPlayerName.setText("Hello " + currentPlayer.getPlayerName() + "!");
				wordBlockPanel.revalidate();
				wordBlockPanel.repaint();
			} catch (NullPointerException e) {
				System.err.println("No player selected, exiting");
				System.exit(-1);
			}
	}// generateChangePlayers

	public void newPlayer() {
		Object result = JOptionPane.showInputDialog(null, "Enter player name:");
		if (result != null) {
			String theName = result.toString();
			sb.addPlayer(new Player(theName));
			currentPlayer = sb.getPlayer(theName);
			game = currentPlayer.game;
			game.setCurrentWord(currentPlayer);
			game.clearLists();
			lblPlayerName.setText("Hello " + currentPlayer.getPlayerName() + "!");
			startOver();
		} else
			System.exit(-1);

	}

	public void firstLoad() {
		Object result = JOptionPane.showInputDialog(null, "Enter player name:");
		if (result != null) {
			sb = new Scoreboard();
			String theName = result.toString();
			sb.addPlayer(new Player(theName));
			currentPlayer = sb.getPlayer(0);
			game = currentPlayer.game;
			game.setCurrentWord(currentPlayer);
		} else
			System.exit(-1);
		generateCurrentLetters();
	}// newGame()

	public void deserialize() {
		// De-serialization
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			sb = (Scoreboard) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has be deserialized");
			System.out.println("a = " + sb);
			generateChangePlayers();

		} catch (IOException e) {
			System.out.println("Running first time load");
			firstLoad();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// deserialize

	public void serialize() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(sb);
			out.close();

			System.out.println("Object has been serialized");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void startOver() {
		wordBlockPanel.removeAll();

		for (int i = 0; i < letterArray.length; i++) {
			btnArray[i].setBackground(new JButton().getBackground());
			btnArray[i].setEnabled(true);

		} // for
		game.setCurrentWord(currentPlayer);
		letterBlockArray = new JButton[game.getCurrentWordList().getLength()];
		generateCurrentLetters();
		wordBlockPanel.revalidate();
		wordBlockPanel.repaint();
	}// startOver()

	public void changePlayer(String name) {
		for (int i = 0; i < sb.getPlayerList().getLength(); i++) {
			if (sb.getPlayer(i).getPlayerName().equals(name))
				currentPlayer = sb.getPlayer(i);
		}
		currentPlayer = sb.getPlayer(name);
		game = currentPlayer.game;

		wordBlockPanel.removeAll();
		pictureString = game.getFalseCount() + ".png";
		lblPicture.setIcon(new ImageIcon(pictureString));
		lblPlayerName.setText("Hello " + name + "!");
		wordBlockPanel.revalidate();
		wordBlockPanel.repaint();
	}// changePlayer(String name)

	public void generateCurrentLetters() {

		letterBlockArray = new JButton[game.getCurrentWordList().getLength()];
		for (int i = 0; i < game.getCurrentWordList().getLength(); i++) {

			letterBlockArray[i] = new JButton(Character.toString(game.currentWordState[i]).toUpperCase());

			letterBlockArray[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
			letterBlockArray[i].setEnabled(false);
			wordBlockPanel.add(letterBlockArray[i]);
		}
		wordBlockPanel.revalidate();
		wordBlockPanel.repaint();
	}// generateCurrentLetters()

	public void gameWon() {
		JOptionPane.showMessageDialog(contentPane, "Won the game!");
		currentPlayer.addWin();
		game.clearLists();
		startOver();
	}// gameWon()

	public void gameOver() {
		pictureString = game.getFalseCount() + ".png";
		lblPicture.setIcon(new ImageIcon(pictureString));
		for (int j = 0; j < btnArray.length; j++) {
			btnArray[j].setEnabled(false);
		}
		currentPlayer.addGamePlayed();
		JOptionPane.showMessageDialog(contentPane, "Lost the game");
		game.clearLists();
		startOver();
	}// gameOver()

	public int findButtonIndex(char c) {
		int foundIndex = -1;
		for (int i = 0; i < btnArray.length; i++)
			if (letterArray[i].charAt(0) == c)
				foundIndex = i;
		return foundIndex;

	}// findButtonIndex()

	// modifies the keyboard based on the loaded games state.
	public void loadKeyboard() {
		for (int i = 0; i < letterArray.length; i++) {
			btnArray[i].setBackground(new JButton().getBackground());
			btnArray[i].setEnabled(true);
//			System.out.println(btnArray[i].getText());
		} // for

		for (int i = 0; i < game.correctGuessedLettersList.getLength(); i++) {
			char currChar = game.correctGuessedLettersList.getElementAt(i);
			for (int j = 0; j < btnArray.length; j++) {

				if (currChar == btnArray[j].getText().charAt(0)) {
					btnArray[j].setBackground(new Color(50, 150, 50));
					btnArray[j].setEnabled(false);
					btnArray[j].setForeground(Color.BLACK);
				} // if
			} // for(j) btnArray
		} // for(i) correct letters

		for (int i = 0; i < game.failedGuessedLettersList.getLength(); i++) {
			char currChar = game.failedGuessedLettersList.getElementAt(i);
			for (int j = 0; j < btnArray.length; j++) {
				if (currChar == btnArray[j].getText().charAt(0)) {
					btnArray[j].setBackground(new Color(165, 42, 42));
					btnArray[j].setEnabled(false);
				} // if
			} // for(j) btnArray
		} // for(i) correct letters
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < btnArray.length; i++) {
			if (e.getSource() == btnArray[i]) {
				ArrayList<Integer> guessIndex = game.guessLetter(letterArray[i].charAt(0));

				if (guessIndex.size() > 0) {
					btnArray[i].setBackground(new Color(50, 150, 50));
					btnArray[i].setEnabled(false);
					btnArray[i].setForeground(Color.BLACK);
					for (int j = 0; j < guessIndex.size(); j++) {
						letterBlockArray[guessIndex.get(j)].setText(letterArray[i].toUpperCase());
					}
				} else {
					btnArray[i].setBackground(new Color(165, 42, 42));
					btnArray[i].setEnabled(false);
				}
				btnArray[i].setUI(new MetalButtonUI() {
					protected Color getDisabledTextColor() {
						return Color.BLACK;
					}
				});

				if (game.getFalseCount() > 5) {
					gameOver();
					i = btnArray.length;
				} // if(gamelost)
				if (game.getCorrectCount() == game.getCurrentWordList().getLength()) {
					gameWon();
					i = btnArray.length;
				} // if(gamelost)

			} // if
		} // for
		pictureString = game.getFalseCount() + ".png";
		lblPicture.setIcon(new ImageIcon(pictureString));
		serialize();
	}// actionPerformed(e)
}// HangmanFrame
