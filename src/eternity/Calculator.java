package eternity;

import java.util.ArrayList;
import java.util.function.Function;

import eternity.Errors.SquareRootOfNegativeException;
import eternity.Errors.DivideByZeroException;
import eternity.Errors.NegativePowerOfZeroException;

public class Calculator {
  public static final double PI = 3.1415926535897932384;
  public static final double E = 2.7182818284590452354;

  public static double sin(double x) {
    double theta = x * 0.017453292519943295;
    int power = 1;
    double sinx = 0;
    for (int i = 1; i <= 10; i++) {
      double term = 0;
      if (i % 2 == 0) {
        term = -(x_to_the_y(theta, power) / factorial(power));
      } else {
        term = (x_to_the_y(theta, power) / factorial(power));
      }
      sinx = sinx + term;
      power = power + 2;
    }
    return sinx;
  }

  public static double ten_to_the_x(double x) {
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

    if ((x == (int) x) && (x > 0)) {
      sum = (int) (sum * 100 + 0.5) / 100;
    }

    return sum;
  }

  public static double x_to_the_y(double x, double y) {
    if (x == 0) {
      if (y > 0) {
        return (0);
      } else {
        throw new NegativePowerOfZeroException();
      }
    }
    if (x == 1) {
      return (1);
    }

    if (x == -1) {
      if (y != (int) y) {
        return (-1);
      } else {
        if (Calculator.CheckEvenOdd((int)y)) {
          return (1);
        } else {
          return (-1);
        }
      }
    }

    if (y == 0) {
      return (1);
    }

    if (y == 1) {
      return (x);
    }

    if (y < 1 && (y == (int) y)) {
      double result = x;
      while (y-- > 1) {
        result *= x;
      }
    }

    if (y > 1 && (y == (int) y)) {
      y *= -1;
      double result = x;
      while (y-- > 1) {
        result *= x;
      }
      result = 1 / result;
      return (result);
    }

    if (y < 1 && (y != (int) y)) {
      // TODO decimal power

    }

    if (y > 1 && (y != (int) y)) {
      // TODO decimal power

    }
    return x;
  }

  public static boolean CheckEvenOdd(int number){
    double result = number / 2;
    // true = even, false = odd
    return result == (int)result;
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

    for (int i = 0; i < 100; i++) res = res - 1 + x * Calculator.x_to_the_y(Calculator.E, -res);

    return res;
  }

  public static double log_10_x(double x) {
    return x;
  }

  /**
   * Mean Absolute Value "MAD" of a ArrayList of Double
   *
   * @param x the ArrayList
   * @return the Double Mean Absolute Value
   */
  public static double mean_absolute_deviation(ArrayList<Double> x) {
    // find the mean of the array given
    double meanValue = mean(x);

    // find difference between array and the mean.
    return diffMean(x, meanValue);
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

  /**
   * Sum of a list divided by the number of items
   *
   * @param numbers the list of numbers to find a mean.
   * @return the mean.
   */
  public static double mean(ArrayList<Double> numbers) {
    // throw divided by zero exception if no numbers are in the list
    if (numbers.size() == 0) {
      throw new DivideByZeroException();
    }

    return sum(map(numbers, x -> x)) / numbers.size();
  }

  public static double variance(double mean, ArrayList<Double> numbers) {
    // variance is (mean - x)^2/size of array
    return sum(map(numbers, x -> (mean - x) * (mean - x))) / numbers.size();
  }

  /** Create an ArrayList of difference between an array of double and the mean */
  private static double diffMean(ArrayList<Double> args, double mean) {
    return sum(map(args, x -> absolute_value(mean - x))) / args.size();
  }

  /**
   * Apply a function given by fn to all items in the list numbers.
   *
   * @param numbers the list to modify by a function fn.
   * @param fn a given function lamda.
   * @return
   */
  public static ArrayList<Double> map(ArrayList<Double> numbers, Function<Double, Double> fn) {
    // for each item in the list apply the function given by the lamda expression (e.i: x-> (mean -
    // x))
    for (var i = 0; i < numbers.size(); i++) {
      numbers.set(i, fn.apply(numbers.get(i)));
    }

    return numbers;
  }

  /**
   * Sum all items in a list.
   *
   * @param numbers the list of numbers to sum.
   * @return sum total
   */
  public static double sum(ArrayList<Double> numbers) {
    double ret = 0;
    for (var i = 0; i < numbers.size(); i++) {
      ret += numbers.get(i);
    }

    return ret;
  }

  public static int factorial(int x) {
    if (x == 0) {
      return 1;
    }

    return x * factorial(x - 1);
  }
}
