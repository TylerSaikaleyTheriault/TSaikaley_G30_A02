package Hangman;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

public class HangmanInfoPanel extends JPanel {
//Taken from my 2nd Semester prog 2 battleship assignment.
	public HangmanInfoPanel() {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Hangman Instructions");
		lblNewLabel.setBounds(202, 5, 157, 14);
		add(lblNewLabel);

		JTextPane txtpnTheRulesTo = new JTextPane();
		txtpnTheRulesTo.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		txtpnTheRulesTo.setBackground(new Color(245, 245, 245));
		txtpnTheRulesTo.setText(
				"The rules to Hangman are simple.\r\n\r\nYou must guess a word by pressing one of the letter buttons.\r\n\r\n You have 6 tries to succeed lest the unfortunate chap be hung.");
		txtpnTheRulesTo.setBounds(10, 22, 468, 279);
		add(txtpnTheRulesTo);

	}
}
