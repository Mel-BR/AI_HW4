package MDP.entities;

import java.awt.Image;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Tile {
	public Image tileImage;
	public int isWalkable = 1;
	public double utility = 0;
	public int row;
	public int col;
	public int arrowDir = 0; //1 is North, 2 is east, 3 is south, 4 is west
    public XYSeries dataSeries;

	
	public Tile(int row, int col, XYSeriesCollection dataset){
		this.row = row;
		this.col = col;
		dataSeries = new XYSeries("("+Integer.toString(row)+","+Integer.toString(col)+")");
		dataset.addSeries(dataSeries);
	}


	public Tile(int row, int col, XYSeriesCollection dataset, int utility, int isWalkable, Image tileImage) {
		this.row = row;
		this.col = col;
		dataSeries = new XYSeries("("+Integer.toString(row)+","+Integer.toString(col)+")");
		dataset.addSeries(dataSeries);	
		
		this.tileImage = tileImage;
		this.utility = utility;
		this.isWalkable = isWalkable;
		dataSeries.add(0, utility);
	
	}

}
