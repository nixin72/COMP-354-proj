package eternity;

import java.util.ArrayList;

import eternity.Errors.SquareRootOfNegativeException;

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

	// all
	public static double mean_absolute_deviation(ArrayList<Double> x) {
		return x.get(0);
	}

	public static double standard_deviation(ArrayList<Double> x) {
		double mean, variance, std_dev;

		mean = Calculator.mean(x);
		variance = Calculator.variance(mean, x);
		std_dev = Calculator.square_root(variance);
		return std_dev;
	}
	
	public static double power(double x, int y) {
		double res = 1;
		for (int i = 0; i < y; i++) {
			res = res * x;
		}
		return res;
	}
	
	public static double absolute_value(double x) {
		if (x < 0) {
			x *= -1;
		}
		return x;
	}
	
	// Helper functions
	public static double square_root(double x) {
	    double approx = 0;
	    double temp = x/2;

	    while (x >= 0.0) {
	        approx = temp - (temp * temp - x) / (2 * temp);

	        if (Calculator.absolute_value(temp - approx)< .0001) {
	            return approx;
	        }
	        else {
	        	temp = approx;
	        }
	    } 

	    return x;
//		if (x < 0) {
//			throw new SquareRootOfNegativeException();
//		}
//		double approx = 1;
//		for (int i = 0; i < 10; i++) {
//			approx = (Calculator.power(approx, (int)x) + x) / (x * approx);
//		}
//
//		return approx;
	}

	public static int fibonacci(int x) {
		int t1 = 0, t2 = 1;

		for (int i = 1; i <= x; ++i) {

			int sum = t1 + t2;
			t1 = t2;
			t2 = sum;
		}
		return t2;
	}

	public static double mean(ArrayList<Double> numbers) {
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
