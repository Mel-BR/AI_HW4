package digits.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/* This class will help to evaluate our model by
 * using a certain amount of metrics */
public class Evaluator {

	private ArrayList<TestObservation> testList;

	/* Constructor */
	public Evaluator(ArrayList<TestObservation> testList){
		this.testList = testList;
	}

	/* Sets a new test observations list */
	public void setTestList(ArrayList<TestObservation> testList){
		this.testList = testList;	
	}

	/* Returns the accuracy for a given class */
	public float getAccuracy(int label){
		int countRealLabel = 0; // The number of test observations that have the given label as RealLabel
		int countCorrectedPredictedLabel = 0; // The number of test observations that have the given label as RealLabel AND as PredictedLabel

		// We browse the list of test observation */
		for(TestObservation testObs : testList){
			if(testObs.getRealLabel() == label){
				countRealLabel++;
				if(testObs.getRealLabel() == testObs.getPredictedLabel() ){
					countCorrectedPredictedLabel++;
				}
			}
		}
		return (float)countCorrectedPredictedLabel/countRealLabel;
	}

	/* Return the general accuracy */
	public float getGeneralAccuracy(){
		int countAll= 0; // The number of test observations
		int countCorrectedPredictedLabel = 0; // The number of test observations that have the same RealLabel and PredictedLabel

		// We browse the list of test observation */
		for(TestObservation testObs : testList){
			countAll++;
			if(testObs.getRealLabel() == testObs.getPredictedLabel() ){
				countCorrectedPredictedLabel++;
			}
		}
		return (float)countCorrectedPredictedLabel/countAll;
	}

	/* Generate and return the confusion matrix */
	public float[][] generateConfMatrix(ArrayList<TestObservation> obs){
		float[][] ret = new float [10][10];
		int[] counter = new int[10];
		for (TestObservation it : obs){
			counter[it.getRealLabel()]++;
			ret[it.getPredictedLabel()][it.getRealLabel()]++;
		}
		for (int i=0; i<ret.length; i++){
			for (int j =0; j< ret[0].length; j++){
				ret[i][j]/=counter[i];
			}
		}
		return ret;
	}
}
