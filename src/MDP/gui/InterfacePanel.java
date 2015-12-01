package MDP.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InterfacePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] buttons;
	GUI gui;
	
	public InterfacePanel(GUI gui){
		
		this.setOpaque(false);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true), "Buttons"));
		this.setLayout(null);
		this.gui = gui;
 
		makeButtons();
		
	}
	
	private void makeButtons(){
		buttons = new JButton[5];
		for (int i = 0; i < 3 ; i++){
			buttons[i] = new JButton();
			buttons[i].setBounds(15, 20+45*i, 150, 40);
			this.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		buttons[0].setText("Reset");
		buttons[1].setText("Step");
		buttons[2].setText("Exit");
		
	
	}
	
	
	

	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == buttons[0]){

		}else if( e.getSource() == buttons[1]){
			gui.steps++;
			
			
			//Change this line
			gui.valueIter.iterateValues(gui.steps);
			
			
			gui.updateGraph();
	
		}else if( e.getSource() == buttons[2]){
			System.exit(0);
			
		}

	}
}
