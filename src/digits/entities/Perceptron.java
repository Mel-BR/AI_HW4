package digits.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Perceptron {
	
	private ArrayList<float[]> weightVectorClass; // Array containing the weight vector for each class
	private int nbOfClass; // total number of class in the problem
	private int imageSize; // size of the image, in our example : 28
	private int bias;
	
	public Perceptron(int nbOfClass, int imageSize){
		this.nbOfClass = nbOfClass;
		this.imageSize = imageSize;
		this.weightVectorClass = new ArrayList<float[]>();
		
	}
	
	public void train(ArrayList<TrainObservation> trainList, int numberOfEpoch, int bias, int randomOrder, int randomValue, int learningRateValue,ArrayList<TestObservation> testList){
		
		int maxValue;
		int sum;
		float alpha; // Learning Rate
		int predictedClass;
		int realClass;
		int exNumber = 1;
		float[] classVector;
		int[] observationFeaturesVector;
		float[] alphaVector;
		
		if(randomOrder==1)
			Collections.shuffle(trainList);
		
		initializeWeightVectors(bias,randomValue);
		
		if(learningRateValue==0){
			alpha = 1;
		}
		
		System.out.println("EpochNumber,GeneralAccuracy");
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
					if (learningRateValue > 0) {
						alpha = ((float)learningRateValue/(learningRateValue+exNumber));
						alphaVector = multiplyConstantVectors(observationFeaturesVector, alpha);
					}
					else{
						alpha = 1;
						alphaVector = multiplyConstantVectors(observationFeaturesVector, alpha);
					}
						
					copyVectors(weightVectorClass.get(realClass), sumVectors(this.weightVectorClass.get(realClass), alphaVector));
					copyVectors(weightVectorClass.get(predictedClass), substractVectors(this.weightVectorClass.get(predictedClass), alphaVector));
				}
				exNumber++;
			}
			System.out.println(k+","+getGeneralAccuracy(test(testList))*100);
		}

	}
	
	
	/* Gives the best class given an observation */
	public int getBestClass(TestObservation testObs){
		int maxValue = Integer.MIN_VALUE;
		int sum = 0;
		int predictedClass = -1;
		float[] classVector;
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
	
	// if random = 0, we initialize every value to zero.
	// if random > 0, we initialize every value with a number between 0 and the value of the variable random
	private void initializeWeightVectors(int bias, int randomValue) {
		if(bias==0)
			for(int i=0;i<nbOfClass;i++){
				weightVectorClass.add(new float[imageSize*imageSize]);
			}
		else
			for(int i=0;i<nbOfClass;i++){
				weightVectorClass.add(new float[imageSize*imageSize+1]);
			}
		
		if(randomValue > 0){
			Random random=new Random();
			int size = weightVectorClass.get(0).length;
			for(int i=0;i<nbOfClass;i++){
				float[] vector = weightVectorClass.get(i);
				for(int j=0;j<size;j++)
					vector[j] = (random.nextInt(randomValue*2)-randomValue);
			}
		}
	}
	
	private float[] sumVectors(float[] v1, float[] v2){
		float[] res = new float[v1.length];
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
	
	private float[] substractVectors(float[] v1, float[] v2){
		float[] res = new float[v1.length];
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
	
	private float[] multiplyConstantVectors(int[] v, float alpha){
		float[] res = new float[v.length];
		for(int i=0;i<v.length;i++)
			res[i]=alpha*v[i];
		return res;
		
	}	
	
	private void copyVectors(float[] v1, float[] v2){
		if(v1.length==v2.length){
			for(int i=0;i<v1.length;i++){
				v1[i]=v2[i];
			}
		}
	}
	
	
	
	public float getGeneralAccuracy(ArrayList<TestObservation> testList){
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
}
