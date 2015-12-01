package MDP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import MDP.entities.Tile;

public class ValueIteration {
	
	
	public ArrayList<ArrayList<Tile>> boardMatrix;
	
	private double probStraight = 0.8;
	private double probLeftOrRight = 0.1;
	private double whiteReward = -0.04;
	private double discountFac = 0.99;
	private LinkedList<Tile> tileListToUpdate;
	
	public ValueIteration(ArrayList<ArrayList<Tile>> boardMatrix,LinkedList<Tile> tileListToUpdate){
		this.boardMatrix = boardMatrix;
		this.tileListToUpdate = tileListToUpdate;
	}
	
	
	public void iterateValues(int currStep){
		System.out.println(tileListToUpdate.size());
				for (int tileNr = 0;tileNr < tileListToUpdate.size();tileNr++){
					Tile currTile = tileListToUpdate.get(tileNr);
					double northUtility = 0;
					double southUtility = 0;
					double westUtility = 0;
					double eastUtility = 0;
					
					if (boardMatrix.get(currTile.row-1).get(currTile.col).isWalkable==1){
						northUtility = boardMatrix.get(currTile.row-1).get(currTile.col).utility;
					}else{
						northUtility = currTile.utility;
					}
					
					if (boardMatrix.get(currTile.row+1).get(currTile.col).isWalkable==1){
						southUtility = boardMatrix.get(currTile.row+1).get(currTile.col).utility;
					}else{
						southUtility = currTile.utility;
					}
					
					if (boardMatrix.get(currTile.row).get(currTile.col-1).isWalkable==1){
						westUtility = boardMatrix.get(currTile.row).get(currTile.col-1).utility;
					}else{
						westUtility = currTile.utility;
					}
					
					if (boardMatrix.get(currTile.row).get(currTile.col+1).isWalkable==1){
						eastUtility = boardMatrix.get(currTile.row).get(currTile.col+1).utility;
					}else{
						eastUtility = currTile.utility;
					}
					
					
					ArrayList<Node> calculatedUtilites = new ArrayList<Node>();
					calculatedUtilites.add(new Node(1,whiteReward+discountFac*(northUtility*probStraight+westUtility*probLeftOrRight+eastUtility*probLeftOrRight)));
					calculatedUtilites.add(new Node(3,whiteReward+discountFac*(southUtility*probStraight+westUtility*probLeftOrRight+eastUtility*probLeftOrRight)));
					calculatedUtilites.add(new Node(4,whiteReward+discountFac*(westUtility*probStraight+southUtility*probLeftOrRight+northUtility*probLeftOrRight)));
					calculatedUtilites.add(new Node(2,whiteReward+discountFac*(eastUtility*probStraight+southUtility*probLeftOrRight+northUtility*probLeftOrRight)));

					
					
					
					Collections.sort(calculatedUtilites, new Comparator<Node>() {
				        public int compare(Node p1, Node p2) {
				        	if (0>(p1.utility - p2.utility)){
				        		return 1;
				        	}else if (0<(p1.utility - p2.utility)){
				        		return -1;
				        	}else{
				            return 0; // Ascending
				            }
				        }
					});
					
					
					//These three must be edited
					
					currTile.utility = calculatedUtilites.get(0).utility;
					currTile.arrowDir = calculatedUtilites.get(0).dir;
					currTile.dataSeries.add(currStep, currTile.utility);
				}

	
	}
	
	private class Node{
		public int dir = 0; //1 is North, 2 is east, 3 is south, 4 is west
		public double utility = 0;
		
		public Node(int dir, double utility){
			this.dir = dir;
			this.utility = utility;
		}
	}
	

}
