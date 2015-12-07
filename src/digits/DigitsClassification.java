package digits;

import java.text.DecimalFormat;
import java.util.ArrayList;

import digits.entities.Evaluator;
import digits.entities.Parser;
import digits.entities.Perceptron;
import digits.entities.TestObservation;


public class DigitsClassification {

	public static void main(String arg[]){
	
		System.out.println("Reading training observations...");
		ArrayList<TestObservation> trainObsList = Parser.buildTestObs("trainingimages","traininglabels");		
		
		System.out.println("Reading test observations...");
		ArrayList<TestObservation> testObsList = Parser.buildTestObs("testimages","testlabels");

		System.out.println("Done");
		
		Perceptron perceptron = new Perceptron(10, 28);
		float[] res ;
		
		
		//Tuning program, UNCOMMENT IF YOU WANT TO SEE THE VALUE FOR EACH COMBINATION OF PARAMETERS
		/*int[] learningRatePossibleValues = {0,1,10,50,100,500,1000,5000,10000};
		
		System.out.println("Bias randomOrder randomValue learningRate numberOfEpochMax MaxAccuracyOnTestSet");
		
		for(int i=0 ; i<=1;i++){ //Bias
			for(int j=0 ; j<=1;j++){ //Random Order
				for(int k=0; k<=100;k+=50){ //RandomValue
					for(int l=0; l<learningRatePossibleValues.length; l++){ // Learning rate
						res = perceptron.train(trainObsList, testObsList, 100,i,j, k, learningRatePossibleValues[l], 0);
						System.out.println(i+" "+j+" "+k+" "+learningRatePossibleValues[l]+" "+res[1]+" "+res[0]);
					}
				}
			}
		}*/
		
		// Train the perceptron with the best parameters found with the tuning programm
		int numberOfEpoch = 69;
		int bias = 1;
		int randomOrder = 0;
		// if random = 0, we initialize every value to zero.
		// if random > 0, we initialize every value with a number between 0 and the value of the variable random
		int randomValue =  0;
		int learningRateValue = 50;
		int displayAccuracy = 1;
		res = perceptron.train(trainObsList, testObsList, numberOfEpoch,bias,randomOrder, randomValue, learningRateValue, displayAccuracy);
		
		// Testing the perceptron on the test train to obtain the predicted labels
		ArrayList<TestObservation> testObsListLabeled = perceptron.test(testObsList,bias);
		
		// Instantiating the Evaluator
		Evaluator eval = new Evaluator(testObsListLabeled);
		DecimalFormat percentFormatter = new DecimalFormat("00.00%");
		
		System.out.println();
		
		// Displaying overall accuracy
		System.out.println("General accuracy on test set : "+percentFormatter.format(eval.getGeneralAccuracy()));
		
		System.out.println();
		
		// Displaying accuracy for every number
		/*		for(int i=0;i<10;i++){
			System.out.println("Accuracy for label "+i+" : "+percentFormatter.format(eval.getAccuracy(i)));
		}*/
		
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
