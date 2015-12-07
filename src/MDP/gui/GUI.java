package MDP.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import MDP.QTLearning;
import MDP.ValueIteration;
import MDP.entities.Initilization;
import MDP.entities.Tile;

public class GUI implements Runnable {


	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME = "GridWorld";

	public static final int SIZE =1680;
 
	public boolean running = false;
	public boolean tack = true;

	private long tickCount = 0;

	public static JFrame frame;
	public static GUIPanel panel;
	public static InterfacePanel interPanel;
	public static infoPanel infoPanel;
	
	public static int windowW = SIZE;

	public static int windowH = SIZE*10/16;

	public static float scaleW = 1;
	public static float scaleH = 1;

	int ticks = 0;
	int frames = 0;
	int steps = 0;

	public ArrayList<ArrayList<Tile>> boardMatrix;
	
	int boxSize = 100;
	Initilization init;
	
	private int size = 8;
	
	public ValueIteration valueIter;
	public QTLearning qtLearn;
	
	public GUI(){

		init = new Initilization(boxSize);
		this.boardMatrix = init.boardMatrix;
		
		
		//valueIter = new ValueIteration(boardMatrix,init.tileListToUpdate);
		
		qtLearn = new QTLearning(boardMatrix);
		
		
		
		
		
		
		
		
		
		
		
		
		int boardWidth = (int) (size*boxSize);
		int boardHeight = (int) (size*boxSize);



		panel = new GUIPanel(this,boardMatrix,boxSize-6);
		panel.setBounds(0, 0, boardWidth,boardHeight);


		
		interPanel = new InterfacePanel(this);
		interPanel.setBounds(boardWidth, 0, 180,boardHeight);
		interPanel.setBackground(Color.white);

		
		
		infoPanel = new infoPanel(this);
		infoPanel.setBounds(boardWidth+180, 0, 800, boardHeight);
		infoPanel.setBackground(Color.white);
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		
		Dimension frameSize = new Dimension(panel.getWidth()+interPanel.getWidth()+infoPanel.getWidth()+30,panel.getHeight()+50);
		frame.setSize(frameSize);
		frame.add(panel);
		frame.add(interPanel);
		frame.add(infoPanel);
		frame.setVisible(true);
		
//		XYSplineRenderer test = new XYSplineRenderer();
		
	        
        
        


	}

	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop(){
		running = false;

	}


	public void run() {
		long lastTime = System.nanoTime();
		double nanoPerTick = 1000000000D/60; //1/60 sec



		double delta = 0D;

		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1){
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			} 

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender){
				frames++;
				render();
			}

			/*if (System.currentTimeMillis()-lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println(frames+", "+ticks);
				frames = 0;
				ticks = 0;
			}*/

		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////


	public void tick(){

		tickCount++;
		

	}
	
	
	
	public void render(){
		//paint some things you already know
		panel.repaint();

	}
	
	public void updateGraph(){
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Utility convergance graph",      // chart title
                "Steps",                      // x axis label
                "Utility",                      // y axis label
                this.init.dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
        
        ChartPanel chartPanel = new ChartPanel(chart);
        infoPanel.setLayout(new java.awt.BorderLayout());
       infoPanel.add(chartPanel,BorderLayout.CENTER);
       infoPanel.validate();
	}


	public static void main(String[] args){
		GUI gui = new GUI();
		gui.start();

	}
}
