package digits;

import java.text.DecimalFormat;
import java.util.ArrayList;

import digits.entities.Evaluator;
import digits.entities.Parser;
import digits.entities.Perceptron;
import digits.entities.TestObservation;
import digits.entities.TrainObservation;


public class DigitsClassification {

	public static void main(String arg[]){
	
		System.out.println("Reading training observations...");
		ArrayList<TrainObservation> trainObsList = Parser.buildTrainObs("trainingimages","traininglabels");		
		
		System.out.println("Reading test observations...");
		ArrayList<TestObservation> testObsList = Parser.buildTestObs("testimages","testlabels");

		System.out.println("Done");
		
		Perceptron perceptron = new Perceptron(10, 28);
		perceptron.train(trainObsList,1,1);
		ArrayList<TestObservation> testObsListLabeled = perceptron.test(testObsList);
		
		/*
		for (int i = 0; i < 8; i++) {
			testObsListLabeled.get(i).displayFeatures();
			System.out.println(testObsListLabeled.get(i).getPredictedLabel()+" "+testObsListLabeled.get(i).getRealLabel());
		}
		*/
		
		Evaluator eval = new Evaluator(testObsListLabeled);
		DecimalFormat percentFormatter = new DecimalFormat("00.00%");
		
		System.out.println();
		
		// Displaying overall accuracy
		System.out.println("General accuracy : "+percentFormatter.format(eval.getGeneralAccuracy()));
		
		System.out.println();
		
		// Displaying accuracy for every number
		for(int i=0;i<10;i++){
			System.out.println("Accuracy for label "+i+" : "+percentFormatter.format(eval.getAccuracy(i)));
		}
		
		System.out.println();
		
		// Displaying Confusion Matrix
		System.out.println("Confusion matrix:");
		float[][] confMat = eval.generateConfMatrix(testObsListLabeled);
		DecimalFormat formatter = new DecimalFormat("00.00");
		for(int i=0;i<10;i++){		
			for(int j=0;j<10;j++){
				System.out.printf(formatter.format(confMat[i][j]*100));
				System.out.print("  ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		
	}
}
