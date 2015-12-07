package MDP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import MDP.entities.Tile;

public class QTLearning {		
	public ArrayList<ArrayList<Tile>> permBoard;
	ArrayList<ArrayList<Node>> pastResults;
	private double probStraight = 0.8;
	private double whiteReward = -0.04;
	private double discountFac = 0.99;
	private double probExplore = 0.10;
	public double [][][] boardMatrix;

	public QTLearning(ArrayList<ArrayList<Tile>> boardMatrix){
		permBoard = boardMatrix;
		
	}


	public void iterateValues(int trials){
		System.out.println("Called");
		for (int i = 0;i < trials*100;i++){

			System.out.println("Iteration: " + i);

			Tile currTile = findTile(4,2,permBoard);
			Tile prevTile = findTile(4,2,permBoard);
			int utilityHere = 0;
			ArrayList<Node> ret = new ArrayList<Node>();

			while (prevTile.utility == 0){
				System.out.println(prevTile.utility);
				Node thisNode = new Node(currTile.col,currTile.row);
				ret.add(thisNode);
				utilityHere += currTile.utility;
				prevTile = currTile;
				currTile = explore(currTile, thisNode);		
				utilityHere+=whiteReward;
			} 
			
			for (Node n : ret){
				n.utility=utilityHere;
			}
			pastResults.add(ret);
		}
		boardMatrix = new double[6][6][4];
		for (int i = 0; i<boardMatrix.length; i++){
			for (int j = 0; j< boardMatrix[i].length; j++){
				for (int k = 0; k < boardMatrix[i][j].length; k++){
					boardMatrix[i][j][k]=getExpectedUtility(i,j,k);
				}
			}
		}

	}

	public double getExpectedUtility(int x, int y, int dir){
		int count = 0;
		double sum = 0;
		if (pastResults == null)
			return 0;
		for(ArrayList<Node> a : pastResults){
			for(Node n : a){
				if(n.posX == x && n.posY == y && n.dir == dir){
					count++;
					sum+=n.utility;
				}
			}
		}
		return (sum/count);
	}
	
	public Tile explore(Tile cur, Node n){
		Tile ret = null;
		int direction = 0;
		int best = getBestDir(cur.col, cur.row);
		if (best == 0 || Math.random()<probExplore)
			direction = randomDirection();
		else
			direction = best;

		if (direction == 0){
			System.out.println("direction choice error");
			direction = randomDirection();
		}

		n.dir=direction;
		
		if (Math.random()>probStraight){
			if (direction == 1){
				if (Math.random()<.5)
					direction = 4;
				else
					direction = 2;
			}
			else if (direction == 2){
				if (Math.random()<.5)
					direction = 1;
				else
					direction = 3;
			}
			else if (direction == 3){
				if (Math.random()<.5)
					direction = 2;
				else
					direction = 4;
			}
			else if (direction == 4){
				if (Math.random()<.5)
					direction = 3;
				else
					direction = 1;
			}
			else 
				System.out.println("direction larger than 4");
		}
		
		if (direction == 1){
			if (cur.col < 0 || cur.col > permBoard.size() || cur.row-1 < 0 || cur.row> permBoard.get(0).size() || permBoard.get(cur.col).get(cur.row).isWalkable==1)
				ret = cur;
			else
				ret = permBoard.get(cur.col).get(cur.row-1);
		}
		else if (direction == 2){
			if (cur.col < 0 || cur.col+1 > permBoard.size() || cur.row < 0 || cur.row> permBoard.get(0).size() || permBoard.get(cur.col).get(cur.row).isWalkable==1 )
				ret = cur;
			else
				ret = permBoard.get(cur.col+1).get(cur.row);
		}
		else if (direction == 3){
			if (cur.col < 0 || cur.col > permBoard.size() || cur.row < 0 || cur.row-1> permBoard.get(0).size() || permBoard.get(cur.col).get(cur.row).isWalkable==1 )
				ret = cur;
			else
				ret = permBoard.get(cur.col).get(cur.row+1);
		}
		else if (direction == 4){
			if (cur.col-1 < 0 || cur.col > permBoard.size() || cur.row < 0 || cur.row> permBoard.get(0).size() || permBoard.get(cur.col).get(cur.row).isWalkable==1 )
				ret = cur;
			else
				ret = permBoard.get(cur.col-1).get(cur.row);
		}
		else 
			System.out.println("Problem with nav");

		return ret;
	}

	public int randomDirection(){
		int rand =(int) (Math.round(Math.random()*4.+.5));
		return (rand > 4)? randomDirection() : rand;
	}

	public int getBestDir(int x, int y){
		double best = -100;
		int dir =0;
		if (pastResults == null)
			return 0;
		for (ArrayList<Node> i : pastResults){
			for(Node n : i){
				if (n.posX == x && n.posY == y && n.utility > best){
					best = n.utility;
					dir = n.dir;
				}
			}
		}
		return dir;
	}

	public Tile findTile(int x, int y, ArrayList<ArrayList<Tile>> board){
		for (ArrayList<Tile> i : board){
			for (Tile t: i){
				if (t.row == x && t.col == y)
					return t;
			}
		}
		return null;
	}

	private class Node{
		public int dir = 0; //1 is North, 2 is east, 3 is south, 4 is west
		public double utility = 0;
		int posX;
		int posY;

		public Node(int x, int y){
			posX = x;
			posY = y;
		}
	}


}
