package MDP.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import MDP.entities.Tile;




public class GUIPanel extends JPanel{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;



	private int boxSize;
	public Dimension windowSize;

	private GUI gui;
	private ArrayList<ArrayList<Tile>> boardMatrix;
	
	Image arrowNorthImage;
	Image arrowSouthImage;
	Image arrowWestImage;
	Image arrowEastImage;


	public GUIPanel(GUI gui, ArrayList<ArrayList<Tile>> boardMatrix2, int boxSize) {

		this.boardMatrix = boardMatrix2;
		this.gui = gui;
		this.boxSize = boxSize;



		
		this.arrowNorthImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/arrownorth.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		this.arrowSouthImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/arrowsouth.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		this.arrowWestImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/arrowwest.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		this.arrowEastImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/arroweast.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);




	}




	public void tick(){


	}









	private void updateBoard(Graphics g){
		int shouldDispNr;
		for(int i = 0; i < boardMatrix.size(); i++){
			for (int j = 0; j < boardMatrix.get(i).size(); j++){
				g.drawImage(boardMatrix.get(i).get(j).tileImage, j*boxSize+boxSize/4, i*boxSize+boxSize/4, null);
				
				if(boardMatrix.get(i).get(j).arrowDir==1){
					g.drawImage(arrowNorthImage, j*boxSize+boxSize/4, i*boxSize+boxSize/4, null);
				}else if(boardMatrix.get(i).get(j).arrowDir==2){
					g.drawImage(arrowEastImage, j*boxSize+boxSize/4, i*boxSize+boxSize/4, null);
				}else if(boardMatrix.get(i).get(j).arrowDir==3){
					g.drawImage(arrowSouthImage, j*boxSize+boxSize/4, i*boxSize+boxSize/4, null);
				}else if(boardMatrix.get(i).get(j).arrowDir==4){
					g.drawImage(arrowWestImage, j*boxSize+boxSize/4, i*boxSize+boxSize/4, null);

				}
				
				setBackground(Color.white);
				setForeground(Color.white);
				Font trbi = new Font("TimesRoman", Font.BOLD, 12);
				g.setFont(trbi);
				if(boardMatrix.get(i).get(j).isWalkable==1){
				g.drawString(Double.toString(round(boardMatrix.get(i).get(j).utility,4)),j*boxSize+boxSize/2-14, i*boxSize+boxSize*3/4-14);
				}
			}
		}


	}


	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateBoard(g);

	}

	
	

}