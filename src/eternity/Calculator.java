package eternity;

import java.util.ArrayList;

import eternity.Errors.SquareRootOfNegativeException;

public class Calculator {

  // pick one
  public static double sin(double x) {
    return x;
  }

  public static double ten_to_the_x(double x){

    final double ln10 = 2.30258509299;
    final double e = 2.718281828459;

    // TODO implement ln(10)
    // TODO implement e

    if (x == 0) {
      return 1;
    }
    if (x == 1) {
      return 10;
    }
    if (x == -1) {
      return 0.1;
    }

    double new_e_exponent = x * ln10;
    double sum = 1;

    for (int i = 1000; i > 0; --i) {
      sum = 1 + new_e_exponent * sum / (i);
    }
    if((x == (int)x) && (x > 0)){
      sum = (int)(sum * 100 + 0.5) / 100;
    }

    return sum;
  }

  /**
   * Exponential
   *
   * @param x
   * @return e^x
   */
  public static double exp(double x) {
    // TODO: Replace with our own implementation
    return Math.exp(x);
  }

  public static double a_to_the_x(double x) {
    return x;
  }

  public static double x_to_the_y(double x, double y) {
    return x;
  }

  /**
   * Natural log Author: Isaac Doré - 40043159
   *
   * <p>Leveraging Taylor series of the exp() function. The function f(y)= exp(y) - x will be zero
   * when ln(x)=y. We can use this with to converge towards the solution. Computed using Newton's
   * method : x_{n+1} = x_n - f(x_n)/f'(x_{n}) Newton's method gives us good convergence, iterating
   * 10 times gives us answer accurate to the 10^(-16) for values [x, x+10]. Note: Less precise as x
   * moves away from 1. Note: Computing 100 iteration for maximum precision over a larger domain,
   * but more expensive.
   *
   * @param x
   * @return ln(x)
   */
  public static double log_e_x(double x) {
    if (x <= 0) return Double.NEGATIVE_INFINITY; // Avoid floating point error around x=0.

    double res = x - 1; // Initial guess for Newton's Method

    for (int i = 0; i < 100; i++) res = res - 1 + x * Calculator.exp(-res);

    return res;
  }

  public static double log_10_x(double x) {
    return x;
  }

  public static double mean_absolute_deviation(ArrayList<Double> x) {
    return x.get(0);
  }

  public static double standard_deviation(ArrayList<Double> x) {
    double mean = Calculator.mean(x);
    double variance = Calculator.variance(mean, x);
    double std_dev = Calculator.square_root(variance);

    return std_dev;
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Helper functions
  ///////////////////////////////////////////////////////////////////////////////

  public static double absolute_value(double x) {
    if (x < 0) {
      x *= -1;
    }

    return x;
  }

  public static double square_root(double x) {
    if (x < 0) {
      throw new SquareRootOfNegativeException();
    }

    double approx = 0;
    for (double i = x / 2; x >= 0; i = approx) {
      approx = i - (i * i - x) / (i * 2);
      if (Calculator.absolute_value(i - approx) < 0.0001) {
        break;
      }
      i = approx;
    }

    return approx;
  }

  public static double mean(ArrayList<Double> numbers) {
    double sum = 0;
    for (int i = 0; i < numbers.size(); i++) {
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
