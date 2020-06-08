package eternity;

import java.util.ArrayList;

import eternity.Errors.SquareRootOfNegativeException;

public class Calculator {

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
    final double ln10 = log_e_x(10);
    final double e = 2.718281828459;

    if (x == 0) {
      return (1);
    }
    if (x == 1) {
      return (10);
    }
    if (x == -1) {
      return (0.1);
    }

    double new_e_exponent = x * ln10;
    double sum = 1;

    for (int i = 100; i > 0; --i) {
      sum = 1 + new_e_exponent * sum / (i);
    }
    return sum;
  }

  public static double pi_to_the_x(double x) {
    return x;
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

  public static double log_e_x(double x) {
    return x;
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
