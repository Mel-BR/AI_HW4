package digits.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

	// Build for TrainObservation
	public static ArrayList<TrainObservation> buildTrainObs(String imageFileName, String labelFileName){
		ArrayList<TrainObservation> ret = new ArrayList<TrainObservation>();
		ArrayList<int[][]> dig = parseDigits(imageFileName);
		ArrayList<Integer> lab = parseLabels(labelFileName);
		
		for (int i = 0; i < dig.size() && i < lab.size(); i++){
			ret.add(new TrainObservation(dig.get(i), lab.get(i)));
		}
		return ret;
	}
	
	
	// Build for TrainObservation
	public static ArrayList<TestObservation> buildTestObs(String imageFileName, String labelFileName){
		ArrayList<TestObservation> ret = new ArrayList<TestObservation>();
		ArrayList<int[][]> dig = parseDigits(imageFileName);
		ArrayList<Integer> lab = parseLabels(labelFileName);
		
		for (int i = 0; i < dig.size() && i < lab.size(); i++){
			ret.add(new TestObservation(dig.get(i), lab.get(i)));
		}
		return ret;
	}
	

	public static ArrayList<int[][]> parseDigits(String filename){
		
		ArrayList<int[][]> AllFeaturesList = new ArrayList<int[][]>();
		Scanner fileScanner = null;
		String nextLine;	
		int[][] features = new int[28][28];

        try {
        	URL res = Parser.class.getResource("/digits/inputs/"+filename);
            fileScanner = new Scanner(new BufferedReader(new FileReader(res.getPath())));
            int v =0;
            while (fileScanner.hasNextLine()) {
            	features = new int[28][28];
                for(int i=0 ; i<28 ; i++){
    				nextLine=fileScanner.nextLine();
    				char [] val = nextLine.toCharArray();
    				for (int j = 0; j<val.length; j++){
    					features[i][j] = (val[j]=='+' || val[j]=='#')?1:0;
    				}
                }
                AllFeaturesList.add(features);
            }
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
        
        return AllFeaturesList;
        
	}
	

	public static ArrayList<Integer> parseLabels(String filename){
		
		ArrayList<Integer> AllLabelsList = new ArrayList<Integer>();
		Scanner fileScanner = null;
		String nextLine;
		Integer label = -1;
		
        try {
        	URL res = Parser.class.getResource("/digits/inputs/"+filename);
            fileScanner = new Scanner(new BufferedReader(new FileReader(res.getPath())));
            while (fileScanner.hasNextLine()) {
   				nextLine=fileScanner.nextLine();
   				AllLabelsList.add(Integer.parseInt(nextLine));
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

	    } finally {
	        if (fileScanner != null) {
	            fileScanner.close();
	        }
	    }
		return AllLabelsList;
		
	}
	
}