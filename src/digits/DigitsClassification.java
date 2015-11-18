package digits;

import java.util.ArrayList;

import digits.entities.Parser;
import digits.entities.TestObservation;
import digits.entities.TrainObservation;


public class DigitsClassification {

	public static void main(String arg[]){
	
		System.out.println("Reading training observations...");
		ArrayList<TrainObservation> trainObsList = Parser.buildTrainObs("trainingimages","traininglabels");		
		
		System.out.println("Reading test observations...");
		ArrayList<TestObservation> testObsList = Parser.buildTestObs("testimages","testlabels");

		System.out.println("Done");

		trainObsList.get(0).displayFeatures();
		
		System.out.println(trainObsList.get(0).getRealLabel());
		
		trainObsList.get(1).displayFeatures();
		
		System.out.println(trainObsList.get(1).getRealLabel());	
		
		trainObsList.get(202).displayFeatures();
		
		System.out.println(trainObsList.get(202).getRealLabel());
		
		
	}
}
