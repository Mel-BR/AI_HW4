package MDP.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class infoPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GUI gui;


	public infoPanel(GUI gui){
		this.setOpaque(false);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true), "Info"));
		this.setLayout(null);
		this.gui = gui;
		
	}
}
