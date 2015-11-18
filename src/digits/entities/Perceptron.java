package digits.entities;

import java.util.ArrayList;

public class Perceptron {
	
	private ArrayList<int[]> weightVectorClass; // Array containing the weight vector for each class
	private int nbOfClass; // total number of class in the problem
	private int imageSize; // size of the image, in our example : 28
	private int bias;
	
	public Perceptron(int nbOfClass, int imageSize){
		this.nbOfClass = nbOfClass;
		this.imageSize = imageSize;
		this.weightVectorClass = new ArrayList<int[]>();
		
	}
	
	public void train(ArrayList<TrainObservation> trainList, int numberOfEpoch, int bias){
		
		int maxValue;
		int sum;
		int predictedClass;
		int realClass;
		int[] classVector;
		int[] observationFeaturesVector;
		
		if(bias==0){
			initializeWeightVectorsNoBias();
		}
		else{
			initializeWeightVectorsBias();
		}
		
		
		for(int k=0; k<numberOfEpoch;k++){
			for(TrainObservation trainObs : trainList){
				maxValue = Integer.MIN_VALUE;
				sum = 0;
				predictedClass = -1;
				realClass = trainObs.getRealLabel();
				int limit;
				if(bias==0){
					observationFeaturesVector = trainObs.getFeaturesVectorNoBias();
					limit = imageSize*imageSize;
				}
				else {
					observationFeaturesVector = trainObs.getFeaturesVectorBias();
					limit = imageSize*imageSize+1;
				}
					
				for(int i = 0 ; i < this.nbOfClass ; i++){
					classVector = this.weightVectorClass.get(i);
					sum=0;
					
					for(int j = 0 ; j < limit ; j++){
						sum += classVector[j]*observationFeaturesVector[j];
					}
					if (sum > maxValue){
						predictedClass = i;
						maxValue = sum;
					}
				}
				if (predictedClass!=realClass){
					copyVectors(weightVectorClass.get(realClass), sumVectors(this.weightVectorClass.get(realClass), observationFeaturesVector));
					copyVectors(weightVectorClass.get(predictedClass), substractVectors(this.weightVectorClass.get(predictedClass), observationFeaturesVector));
				}
			}
		}

	}
	
	
	/* Gives the best class given an observation */
	public int getBestClass(TestObservation testObs){
		int maxValue = Integer.MIN_VALUE;
		int sum = 0;
		int predictedClass = -1;
		int[] classVector;
		int[] observationFeaturesVector = testObs.getFeaturesVectorNoBias();
		
		for(int i = 0 ; i < this.nbOfClass ; i++){
			classVector = this.weightVectorClass.get(i);
			sum=0;
			for(int j = 0 ; j < imageSize*imageSize ; j++){
				sum += classVector[j]*observationFeaturesVector[j];
			}
			if (sum > maxValue){
				predictedClass = i;
				maxValue = sum;
			}
		}
		return predictedClass;
	}
	
	/* Run the tests on a given list of test observations and assign them
	 *  the best predicted class accord to the model */
	public ArrayList<TestObservation> test(ArrayList<TestObservation> testList){
		for (TestObservation testObs : testList){
			testObs.setPredictedLabel(getBestClass(testObs));
		}
		return testList;
	}
	

	private void initializeWeightVectorsNoBias() {
		for(int i=0;i<nbOfClass;i++){
			weightVectorClass.add(new int[imageSize*imageSize]);
		}
	}
	
	private void initializeWeightVectorsBias() {
		for(int i=0;i<nbOfClass;i++){
			weightVectorClass.add(new int[imageSize*imageSize+1]);
		}
	}
	
	private int[] sumVectors(int[] v1, int[] v2){
		int[] res = new int[v1.length];
		if(v1.length!=v2.length){
			return res;
		}
		else{
			for(int i=0;i<v1.length;i++){
				res[i]=v1[i]+v2[i];
			}
			return res;
		}
	}	
	
	private int[] substractVectors(int[] v1, int[] v2){
		int[] res = new int[v1.length];
		if(v1.length!=v2.length){
			return res;
		}
		else{
			for(int i=0;i<v1.length;i++){
				res[i]=v1[i]-v2[i];
			}
			return res;
		}
	}	
	
	private void copyVectors(int[] v1, int[] v2){
		if(v1.length==v2.length){
			for(int i=0;i<v1.length;i++){
				v1[i]=v2[i];
			}
		}
	}
}
