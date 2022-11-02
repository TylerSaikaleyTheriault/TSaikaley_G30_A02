package Hangman;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class HangmanFrame extends JFrame implements ActionListener {

	String[] letterArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" };
	JButton[] btnArray = new JButton[26];
	JButton[] letterBlockArray;
	JPanel wordBlockPanel, panel;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFileMenu, mnHintMenu, mnScoreMenu, mnHelpMenu;
	private JMenuItem rulesItem, newGameItem, exitGameItem, viewScoreItem, getHintItem;
	String filename = "savedGame.ser";
	HangmanGame game = null;
	Player currentPlayer = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
		panel.setBounds(101, 314, 596, 155);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 152, 22);
		contentPane.add(menuBar);

		mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);

		newGameItem = new JMenuItem("New Game");
		mnFileMenu.add(newGameItem);

		exitGameItem = new JMenuItem("Exit Game");
		mnFileMenu.add(exitGameItem);

		mnHintMenu = new JMenu("Hint");
		menuBar.add(mnHintMenu);

		getHintItem = new JMenuItem("Get Hint");
		getHintItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buttonIndex = findButtonIndex(game.getHint());
				System.out.println(buttonIndex);

				btnArray[buttonIndex].doClick();
			}
		});
		mnHintMenu.add(getHintItem);

		mnScoreMenu = new JMenu("Score");
		menuBar.add(mnScoreMenu);

		viewScoreItem = new JMenuItem("View Scoreboard");
		mnScoreMenu.add(viewScoreItem);

		mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);

		rulesItem = new JMenuItem("Rules");
		mnHelpMenu.add(rulesItem);

		wordBlockPanel = new JPanel();
		wordBlockPanel.setBounds(101, 240, 596, 63);
		contentPane.add(wordBlockPanel);
		wordBlockPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (int i = 0; i < letterArray.length; i++) {
			btnArray[i] = new JButton(letterArray[i]);
			btnArray[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
			btnArray[i].addActionListener(this);
			panel.add(btnArray[i]);
//			System.out.println(btnArray[i].getText());

		} // for

		deserialize();

	}// HangmanFrame()

	// Method to make a new game if there is nothing to de-serialize (First load)
	private void newGame() {
		Object result = JOptionPane.showInputDialog(null, "Enter player name:");

		if (result != null) {
			String theName = result.toString();
			game = new HangmanGame();
			game.sb.addPlayer(new Player(theName));
			currentPlayer = game.sb.getPlayer(0);
		} else
			System.exit(-1);

		letterBlockArray = new JButton[game.getCurrentWordList().getLength()];
		for (int i = 0; i < game.getCurrentWordList().getLength(); i++) {
			letterBlockArray[i] = new JButton("_");
			letterBlockArray[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
			letterBlockArray[i].setEnabled(false);

			wordBlockPanel.add(letterBlockArray[i]);
		}
	}// newGame()

	private void deserialize() {
		// De-serialization
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			game = (HangmanGame) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has be deserialized");
			System.out.println("a = " + game);

		} catch (IOException e) {
			System.out.println("Running first time load");
			newGame();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// deserialize

	public void startOver() {
		wordBlockPanel.removeAll();
		wordBlockPanel.repaint();
		for (int i = 0; i < letterArray.length; i++) {
			btnArray[i].setBackground(new JButton().getBackground());
			;
			btnArray[i].setEnabled(true);
//			System.out.println(btnArray[i].getText());

		} // for
	}// startOver()

	public void gameWon() {
		JOptionPane.showMessageDialog(contentPane, "Won the game!");
		currentPlayer.addWin();
		game.clearLists();
//		startOver();
	}// gameWon()

	public void gameOver() {

		for (int j = 0; j < btnArray.length; j++) {
			btnArray[j].setEnabled(false);
		}
		currentPlayer.addGamePlayed();
		JOptionPane.showMessageDialog(contentPane, "Lost the game");
		game.clearLists();
	}// gameOver()

	public void changePlayer(String name) {
		for (int i = 0; i < game.sb.getPlayerList().getLength(); i++) {
			if (game.sb.getPlayer(i).getPlayerName().equals(name))
				currentPlayer = game.sb.getPlayer(i);
		}
	}// changePlayer(String name)

	public int findButtonIndex(char c) {
		int foundIndex = -1;
		for (int i = 0; i < btnArray.length; i++)
			if(letterArray[i].charAt(0) ==c)
				foundIndex = i;
			return foundIndex;

	}// findButtonIndex()

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
						letterBlockArray[guessIndex.get(j)].setText(letterArray[i]);
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

	}// actionPerformed(e)
}// HangmanFrame
