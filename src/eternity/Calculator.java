package eternity;

import java.util.ArrayList;
import java.util.function.Function;

import eternity.Errors.SquareRootOfNegativeException;
import eternity.Errors.DivideByZeroException;

public class Calculator {
  private static double PI = 0;
  private static double E = 0;

  public static double PI() {
    if (PI != 0) return PI;

    double Pi = 0.0;

    for (int i = 1; i < 100000; i++) {
      Pi += (mod(i, 2) == 0) ? -1 / (2 * (double) i - 1) : 1 / (2 * (double) i - 1);
    }
    PI = Pi * 4;
    return PI;
  }

  public static double E() {
    if (E != 0) return E;

    double e = 1;
    for (int i = 1; i < 32; i++) {
      e = e + 1 / (double) (factorial(i));
    }
    E = e;

    return E;
  }

  public static int mod(int base, int divider) {
    int maxDividend = base / divider;
    int result = base - (maxDividend * divider);
    return result;
  }

  public static double sin(double x) {
    double Pi = Calculator.PI();

    double theta = x * (Pi / 180); // calculate for degree

    double sinx = 0;
    for (int i = 1, power = 1; i <= 10; i++, power += 2) {
      double term = 0;
      int powerFactorial = factorial(power);
      double powerOfx = Calculator.power(theta, power);
      if (Calculator.mod(i, 2) == 0) {
        term = -powerOfx / powerFactorial;
      } else {
        term = powerOfx / powerFactorial;
      }
      sinx += term;
    }
    return sinx;
  }

  public static double ten_to_the_x(double x) {
    final double ln10 = log_e_x(10);

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
        return Double.NEGATIVE_INFINITY;
      }
    }
    if (x == 1) {
      return (1);
    }

    if (x == -1) {
      if (y != (int) y) {
        return (-1);
      } else {
        if (Calculator.CheckEvenOdd((int) y)) {
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

    if (y == (int) y) {
      if (y < 0) {
        y *= -1;
        double result = x;
        while (y-- > 1) {
          result *= x;
        }
        result = 1 / result;
        return (result);
      } else {
        double result = x;
        while (y-- > 1) {
          result *= x;
        }
        return (result);
      }
    }

    // if we reach this point y is not an int, no need to test for it.
    // find the factor of 10 that will turn y into an integer

    if (y > 1) {
      // TODO decimal power

    } else {
      // if we reach this point we know that y is negative
      // TODO decimal power
    }
    return x;
  }

  public static boolean CheckEvenOdd(int number) {
    double result = number / 2;
    // true = even, false = odd
    return result == (int) result;
  }

  public static double newton_sqrt(double initialGuess, double base) {
    double threshold = 0.00001;
    double guess = initialGuess;
    double newGuess = (guess + base / guess) / 2;
    while (absolute_value(guess - newGuess) > threshold) {
      guess = newGuess;
      newGuess = (guess + base / guess) / 2;
    }
    return newGuess;
  }

  public static double power(double base, double exponent){
    boolean basePositive = base > 0 ? true : false;
    boolean exponentPositive = exponent > 0 ? true : false;
    if(!basePositive){
      if(Calculator.mod((int)exponent, 2 ) == 0){
        basePositive = true;
        base *= -1;
      }else if(!exponentPositive){
        throw new ArithmeticException("Operation not allowed");
      }else{
        base *= -1;
      }
    }

    if(!exponentPositive){
      exponent *= -1;
    }

    double threshold = 0.00001;
    //if exponent is an integer
    if(exponent == (int) exponent){
      if(exponent < 0) {
        exponent *= -1;
        double result = base;
        while (exponent-- > 1) {
          result *= base;
        }
        return (basePositive ? 1 : -1) * (1 / result);
      } else {
        double result = base;
        while (exponent-- > 1) {
          result *= base;
        }
        return (basePositive ? 1 : -1) * result;
      }
    }

    //if exponent is a floating point number
    if(exponent >= 1){
      double temp = Calculator.power(base, exponent/2);
      return (basePositive ? 1 : -1) * (temp * temp);
    } else {
      double low = 0;
      double high = 1.0;
      double sqr = Calculator.newton_sqrt(base / 2, base);
      double acc = sqr;
      double middle = high / 2;

      while (absolute_value(middle - exponent) > threshold) {
        sqr = Calculator.newton_sqrt(sqr / 2, sqr);
        if (middle <= exponent) {
          low = middle;
          acc *= sqr;
        } else {
          high = middle;
          acc *= (1 / sqr);
        }
        middle = (low + high) / 2;
      }
      return (exponentPositive ? (basePositive ? 1 : -1) * acc : 1 / ((basePositive ? 1 : -1) * acc)) ;
    }
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

    for (int i = 0; i < 100; i++) res = res - 1 + x * Euler.exp(-res);

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
