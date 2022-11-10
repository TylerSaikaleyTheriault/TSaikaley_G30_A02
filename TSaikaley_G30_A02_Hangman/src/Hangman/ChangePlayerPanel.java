package Hangman;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.*;


public class ChangePlayerPanel extends JPanel {
	public ChangePlayerPanel(JButton[] btnArray) {
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		for(int i = 0; i < btnArray.length; i++) {
//			btnArray[i].addActionListener(e -> {
//				this.setVisible(false);
//			});
			this.add(btnArray[i]);
			
		}
	}//ScoreboardFrame
}//ScoreboardFrame
