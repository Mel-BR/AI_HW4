package MDP.entities;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import MDP.gui.GUIPanel;
import sun.net.www.content.text.Generic;

public class Initilization {
	
	private Image whiteTileImage;
	private Image wallImage;
	private Image goodImage;
	private Image badImage;
	private Image startImage;
	
	private int boxSize;
	private int size = 8;
	
	

	
	public LinkedList<Tile> tileListToUpdate = new LinkedList<Tile>();
	
	
	public ArrayList<ArrayList<Tile>> boardMatrix = new ArrayList<ArrayList<Tile>>();
	public XYSeriesCollection dataset = new XYSeriesCollection();

	
	public Initilization(int boxSize){
		
		this.boxSize = boxSize;
		readImages();
		
		ArrayList<Tile> row1 = new ArrayList<Tile>();boardMatrix.add(row1);
		ArrayList<Tile> row2 = new ArrayList<Tile>();boardMatrix.add(row2);
		ArrayList<Tile> row3 = new ArrayList<Tile>();boardMatrix.add(row3);
		ArrayList<Tile> row4 = new ArrayList<Tile>();boardMatrix.add(row4);
		ArrayList<Tile> row5 = new ArrayList<Tile>();boardMatrix.add(row5);
		ArrayList<Tile> row6 = new ArrayList<Tile>();boardMatrix.add(row6);
		ArrayList<Tile> row7 = new ArrayList<Tile>();boardMatrix.add(row7);
		ArrayList<Tile> row8 = new ArrayList<Tile>();boardMatrix.add(row8);
		
		row1.add(new Tile(0,0,dataset,0,0,wallImage));
		row1.add(new Tile(0,1,dataset,0,0,wallImage));
		row1.add(new Tile(0,2,dataset,0,0,wallImage));
		row1.add(new Tile(0,3,dataset,0,0,wallImage));
		row1.add(new Tile(0,4,dataset,0,0,wallImage));
		row1.add(new Tile(0,5,dataset,0,0,wallImage));
		row1.add(new Tile(0,6,dataset,0,0,wallImage));
		row1.add(new Tile(0,7,dataset,0,0,wallImage));
		
		row2.add(new Tile(1,0,dataset,0,0,wallImage));
		row2.add(new Tile(1,1,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(1).get(1));
		row2.add(new Tile(1,2,dataset,-1,1,badImage));		
		row2.add(new Tile(1,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(1).get(3));
		row2.add(new Tile(1,4,dataset,0,1,whiteTileImage)); tileListToUpdate.add(boardMatrix.get(1).get(4));
		row2.add(new Tile(1,5,dataset,0,1,whiteTileImage)); tileListToUpdate.add(boardMatrix.get(1).get(5));
		row2.add(new Tile(1,6,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(1).get(6));
		row2.add(new Tile(1,7,dataset,0,0,wallImage));

		row3.add(new Tile(2,0,dataset,0,0,wallImage));
		row3.add(new Tile(2,1,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(2).get(1));
		row3.add(new Tile(2,2,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(2).get(2));
		row3.add(new Tile(2,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(2).get(3));
		row3.add(new Tile(2,4,dataset,0,0,wallImage));
		row3.add(new Tile(2,5,dataset,-1,1,badImage));		
		row3.add(new Tile(2,6,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(2).get(6));
		row3.add(new Tile(2,7,dataset,0,0,wallImage));		
		
		row4.add(new Tile(3,0,dataset,0,0,wallImage));
		row4.add(new Tile(3,1,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(3).get(1));
		row4.add(new Tile(3,2,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(3).get(2));
		row4.add(new Tile(3,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(3).get(3));
		row4.add(new Tile(3,4,dataset,0,0,wallImage));	
		row4.add(new Tile(3,5,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(3).get(5));
		row4.add(new Tile(3,6,dataset,3,1,goodImage));		
		row4.add(new Tile(3,7,dataset,0,0,wallImage));
		
		row5.add(new Tile(4,0,dataset,0,0,wallImage));
		row5.add(new Tile(4,1,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(4).get(1));
		row5.add(new Tile(4,2,dataset,0,1,startImage));		tileListToUpdate.add(boardMatrix.get(4).get(2));
		row5.add(new Tile(4,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(4).get(3));
		row5.add(new Tile(4,4,dataset,0,0,wallImage));
		row5.add(new Tile(4,5,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(4).get(5));
		row5.add(new Tile(4,6,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(4).get(6));
		row5.add(new Tile(4,7,dataset,0,0,wallImage));
		
		row6.add(new Tile(5,0,dataset,0,0,wallImage));
		row6.add(new Tile(5,1,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(1));
		row6.add(new Tile(5,2,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(2));
		row6.add(new Tile(5,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(3));
		row6.add(new Tile(5,4,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(4));
		row6.add(new Tile(5,5,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(5));
		row6.add(new Tile(5,6,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(5).get(6));
		row6.add(new Tile(5,7,dataset,0,0,wallImage));
		
		row7.add(new Tile(6,0,dataset,0,0,wallImage));
		row7.add(new Tile(6,1,dataset,1,1,goodImage));		
		row7.add(new Tile(6,2,dataset,-1,1,badImage));		
		row7.add(new Tile(6,3,dataset,0,1,whiteTileImage));	tileListToUpdate.add(boardMatrix.get(6).get(3));
		row7.add(new Tile(6,4,dataset,0,0,wallImage));
		row7.add(new Tile(6,5,dataset,-1,1,badImage));		
		row7.add(new Tile(6,6,dataset,-1,1,badImage));		
		row7.add(new Tile(6,7,dataset,0,0,wallImage));
		
		row8.add(new Tile(7,0,dataset,0,0,wallImage));
		row8.add(new Tile(7,1,dataset,0,0,wallImage));
		row8.add(new Tile(7,2,dataset,0,0,wallImage));
		row8.add(new Tile(7,3,dataset,0,0,wallImage));
		row8.add(new Tile(7,4,dataset,0,0,wallImage));
		row8.add(new Tile(7,5,dataset,0,0,wallImage));
		row8.add(new Tile(7,6,dataset,0,0,wallImage));
		row8.add(new Tile(7,7,dataset,0,0,wallImage));
		
//		tileListToUpdate.add(boardMatrix.get(1).get(2));
//		tileListToUpdate.add(boardMatrix.get(2).get(5));
//		tileListToUpdate.add(boardMatrix.get(3).get(6));
//		tileListToUpdate.add(boardMatrix.get(6).get(6));
//		tileListToUpdate.add(boardMatrix.get(6).get(5));
//		tileListToUpdate.add(boardMatrix.get(6).get(2));
//		tileListToUpdate.add(boardMatrix.get(6).get(1));
		
		
		
        
}
	
	
	private void readImages(){

		whiteTileImage = new ImageIcon(GUIPanel.class.getResource("/MDP/gui/res/whiteTile.jpg")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		wallImage = new ImageIcon(GUIPanel.class.getResource("/MDP/gui/res/wallTile.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		goodImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/goodTile.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		badImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/badTile.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		startImage = new ImageIcon(this.getClass().getResource("/MDP/gui/res/startTile.png")).getImage().getScaledInstance(boxSize-6, boxSize-6, Image.SCALE_SMOOTH);
		


	}
	
	

}
