package eternity;

import java.util.ArrayList;
import java.util.function.Function;

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
		var differenceArray = diffMean(x, meanValue);
	    
	    //the Mad is the mean of the difference of the between the items and the mean.
		return differenceArray;
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
	* Create an ArrayList of difference between an array of double and the mean
	* */
	private static double diffMean(ArrayList<Double> args, double mean){
		return sum(map(args, x->(mean-x))) / args.size();
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
	
	public static double mean(ArrayList<Double> numbers) {
		return sum(map(numbers, x->x)) / numbers.size();
	}

	public static double variance(double mean, ArrayList<Double> numbers) {
		return sum(map(numbers, x->(mean-x)*(mean-x))) / numbers.size();
	}

	public static ArrayList<Double> map(ArrayList<Double> numbers, Function<Double,Double> fn) {
		for (var i = 0; i < numbers.size() ; i++) {
			numbers.set(i, fn.apply(numbers.get(i)));
		}
	
		return numbers;
	}
	
	public static double sum(ArrayList<Double> numbers) {
		double ret = 0;
		for (var i = 0; i < numbers.size() ; i++) {
			ret += numbers.get(i);
		}
	
		return ret;
	}
}
