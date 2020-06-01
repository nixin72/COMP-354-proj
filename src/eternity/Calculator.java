package eternity;

import java.util.ArrayList;

import eternity.Errors.SquareRootOfNegativeException;
import eternity.Errors.DivideByZeroException;

public class Calculator {
	// Functions we have to do

	// pick one
	public static double sin(double x) {
		return x;
	}

	public static double cos(double x) {
		return x;
	}

	public static double sinh(double x) {
		return x;
	}

	public static double cosh(double x) {
		return x;
	}

	public static double ten_to_the_x(double x) {
		return x;
	}

	public static double pi_to_the_x(double x) {
		return x;
	}

	public static double e_to_the_x(double x) {
		return x;
	}

	public static double a_to_the_x(double x) {
		return x;
	}

	public static double x_to_the_y(double x, double y) {
		return x;
	}

	public static double log_e_x(double x) {
		return x;
	}

	public static double log_10_x(double x) {
		return x;
	}

	/**
	 * Mean Absolute Value "MAD" of a ArrayList of Double
	 * @param x the ArrayList
	 * @return the Double Mean Absolute Value
	 */
	public static double mean_absolute_deviation(ArrayList<Double> x) {
		//find the mean of the array given
		double meanValue = mean(x);

	    //find difference between array and the mean.
		ArrayList<Double> differenceArray = diffMean(x, meanValue);
	    
	    //the Mad is the mean of the difference of the between the items and the mean.
		return mean(differenceArray);
	}
	
	/**
	 * Mean Absolute Value "MAD" of a array of Double
	 * @param x the array
	 * @return the Double Mean Absolute Value
	 */
	public static double mean_absolute_deviation(double[] x) {
		//find the mean of the array given
		double meanValue = mean(x);
	    
	    //find difference between array and the mean.
	    double[] differenceArray = diffMean(x, meanValue);
	    
	    //the Mad is the mean of the difference of the between the items and the mean.
	    return mean(differenceArray);
	}


	public static double standard_deviation(ArrayList<Double> x) {
		double mean, variance, std_dev;

		mean = Calculator.mean(x);
		variance = Calculator.variance(mean, x);
		std_dev = Calculator.square_root(variance);
		return std_dev;
	}

///////////////////////////////////////////////////////////////////////////////
	// Helper functions
///////////////////////////////////////////////////////////////////////////////
	
	/**
	* Create an array of difference between an array of double and the mean
	* */
	private static double[] diffMean(double[] args, double mean){
		//Create an array to store the difference between each item and the mean.
		double[] arrayOfDifference = new double[args.length];

		for(int index = 0; index < args.length; index++){
			//calculate the absolute value of the difference and store it in this new array.
			arrayOfDifference[index] = abs(args[index] - mean);
		}
		//return the array that contains the difference between each number in the array and the mean.
		return arrayOfDifference;
	}
	
	/**
	* Create an ArrayList of difference between an array of double and the mean
	* */
	private static ArrayList<Double> diffMean(ArrayList<Double> args, double mean){
		//Create an ArrayList to store the difference between each item and the mean.
		ArrayList<Double> arrayOfDifference = new ArrayList<Double>();

		// For Each Loop for iterating ArrayList 
        for (Double i : args) {
        	arrayOfDifference.add(abs(i - mean));
        }

		//return the array that contains the difference between each number in the array and the mean.
		return arrayOfDifference;
	}
	
	public static double absolute_value(double x) {
		if (x < 0) {
			x *= -1;
		}

		return x;
	}
	
	/**
	* return the absolute value of a double
	* */
	public static double abs(double value) {
		//If it is negative return the value * -1, else return the number.
		return (value < 0) ? (-1) * value : value;
	}
	
	public static double square_root(double x) {
		if (x < 0) {
			throw new SquareRootOfNegativeException();
		}

	    double approx = 0;
	    for (double i = x/2; x >= 0; i = approx) {
	    	approx = i - (i * i - x) / (i * 2);
	    	if (Calculator.absolute_value(i - approx) < 0.0001) {
	    		break;
	    	}
	    	i = approx;
	    }

	    return approx;
	}
	
	/**
	* Find the mean of an array of doubles
	* */
	public static double mean(double[] args) {
		//return error if length of array is 0
		if(args.length == 0)
			throw new DivideByZeroException();
		
		//initiate the total for the sum 
		double total = 0;

		// iterating over an array 
		for (double doubleNum : args) { 

			// accessing each element of array 
			total += doubleNum; 
		} 
		//mean is the total value divided by the amount of item in the array
	    return total/args.length;
	}

	public static double mean(ArrayList<Double> numbers) {
		
		if(numbers.size() == 0) {
			throw new DivideByZeroException();
		}
		
		double sum = 0;
		for (int i = 0; i < numbers.size() ; i++) {
			sum += numbers.get(i);
		}

		return sum / numbers.size();
	}

	public static double variance(double mean, ArrayList<Double> numbers) {
		for (int i = 0; i < numbers.size(); i++) {
			double diff = numbers.get(i) - mean;
			numbers.set(i, diff * diff);
		}

		return Calculator.mean(numbers);
	}

}
