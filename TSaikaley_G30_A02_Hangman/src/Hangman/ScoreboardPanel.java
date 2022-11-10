package Hangman;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

public class ScoreboardPanel extends JPanel {
	public ScoreboardPanel(Scoreboard s) {
		Scoreboard scoreboard = s;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JTextPane txtpnPlayers = new JTextPane();
		txtpnPlayers.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		txtpnPlayers.setBackground(new Color(245, 245, 245));
		txtpnPlayers.setText(s.displayScoreboard());
		txtpnPlayers.setBounds(10, 22, 468, 279);
		add(txtpnPlayers);

	}//ScoreboardFrame
}//ScoreboardFrame
