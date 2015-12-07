package MDP;

import java.util.ArrayList;

import MDP.entities.Tile;

public class QTLearning {		
	public ArrayList<ArrayList<Tile>> permBoard;
	ArrayList<ArrayList<Node>> pastResults = new ArrayList<ArrayList<Node>>();
	private double probStraight = 0.8;
	private double whiteReward = -0.04;
	private double discountFac = 0.99;
	private double probExplore = 0.25;
	public double [][] boardMatrix;

	public double[][][] estimatedUtility = new double[][][]{
		{{1.6664,3},{-1,0},{1.8122,2},{1.8359, 2},{1.9095,2},{2.4828,3}},
		{{2.0712,3},{2.1405,3},{2.21,3},{0,0},{-1,0},{2.4828,3}},
		{{2.1392,2},{2.218,2},{2.2971,3},{0,0},{2.7439,3},{3,0}},
		{{2.197,3},{2.2906,2},{2.38565,3},{0,0},{2.797,2},{2.9,1}},
		{{2.1319,1},{2.2306,1},{2.4792,2},{2.6293,2},{2.7131,1},{2.8029,1}},
		{{1,0},{-1,0},{2.025,1},{0,0},{-1,0},{-1,0}},
	};


	public QTLearning(ArrayList<ArrayList<Tile>> boardMatrix){
		System.out.println("Exploring: " +probExplore*100+"%");
		permBoard = boardMatrix;
		
		/*for (int i = 0; i<estimatedUtility.length; i++){
			for (int j = 0; j< estimatedUtility[i].length; j++){					//System.out.println(estimatedUtility[i][j][k]);
				System.out.print(estimatedUtility[i][j][0] + "	" +'\t');
			}
			System.out.println();
		}*/

	}


	public void iterateValues(int trials){
		//System.out.println("Called");
		for (int i = 0;i < trials*500;i++){

			//System.out.println("Iteration: " + i);

			Tile currTile = findTile(4,2,permBoard);
			Tile prevTile = findTile(4,2,permBoard);
			int utilityHere = 0;
			ArrayList<Node> ret = new ArrayList<Node>();

			while (prevTile.utility == 0){
				//System.out.println(prevTile.utility);
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
		boardMatrix = new double[6][6];
		for (int i = 0; i<boardMatrix.length; i++){
			for (int j = 0; j< boardMatrix[i].length; j++){
					//boardMatrix[i][j][k]=getExpectedUtility(i,j,k);
					boardMatrix[i][j]=getBestDir(1,j)[1];
			}
		}

		double RMSval = 0;

		for (int i = 0; i<boardMatrix.length; i++){
			for (int j = 0; j< boardMatrix[i].length; j++){
				int k=(int) estimatedUtility[i][j][1];
				if (k!=0){
					//System.out.println(i+ " "+ j+ " "+Math.pow((estimatedUtility[i][j][0]-boardMatrix[i][j][k]),2.0));
					RMSval+=Math.pow(estimatedUtility[i][j][0]-boardMatrix[i][j],2);	
				}
			}
		}
		//System.out.println("RMS val= " + RMSval);
		RMSval = Math.sqrt(RMSval/(trials*500));

		System.out.println(trials*500 + " iterations and RMS error=" + RMSval);


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
		return (count==0)?-10:(sum/count);
	}

	public Tile explore(Tile cur, Node n){
		Tile ret = null;
		int direction = 0;
		int best = (int) getBestDir(cur.col, cur.row)[0];
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
			if (cur.row-1 < 0 || permBoard.get(cur.col).get(cur.row-1).isWalkable==0)
				ret = cur;
			else
				ret = permBoard.get(cur.col).get(cur.row-1);
		}
		else if (direction == 2){
			if (cur.col+1 > permBoard.size() || permBoard.get(cur.col+1).get(cur.row).isWalkable==0 )
				ret = cur;
			else
				ret = permBoard.get(cur.col+1).get(cur.row);
		}
		else if (direction == 3){
			if (cur.row+1> permBoard.get(0).size() || permBoard.get(cur.col).get(cur.row+1).isWalkable==0 )
				ret = cur;
			else
				ret = permBoard.get(cur.col).get(cur.row+1);
		}
		else if (direction == 4){
			if (cur.col-1 < 0 || permBoard.get(cur.col-1).get(cur.row).isWalkable==0 )
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

	public double[] getBestDir(int x, int y){
		double best = -5;
		int dir =0;
		if (pastResults == null)
			return new double[]{0,0};
		for (ArrayList<Node> i : pastResults){
			for(Node n : i){
				if (n.posX == x && n.posY == y && n.utility > best){
					best = n.utility;
					dir = n.dir;
				}
			}
		}
		return new double[]{dir,best};
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
