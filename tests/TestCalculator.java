import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import eternity.Errors.DivideByZeroException;
import eternity.Euler;
import org.junit.jupiter.api.Test;
import eternity.Calculator;
import eternity.Errors.SquareRootOfNegativeException;

class TestCalculator {

  // Supporting up to 1 x 10^-12 precision error
  public static final double epsilon = 0.000000000001;

  ///////////////////////////////////////////////////////////////////////////////
  // Sin(x)
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void sin_zero() {
    assertEquals(0, Calculator.sin(0));
  }

  @Test
  public void sin_pos_int() {
    assertEquals(0.17364817766693034, Calculator.sin(10), epsilon);
  }

  @Test
  public void sin_neg_int() {
    assertEquals(-0.17364817766693034, Calculator.sin(-10), epsilon);
  }

  @Test
  public void sin_pos_double() {
    assertEquals(-0.8796957599716700, Calculator.sin(10.5), epsilon);
  }

  @Test
  public void sin_neg_double() {
    assertEquals(0.8796957599716700, Calculator.sin(-10.5), epsilon);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // x_to_the_y
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void exponential_pos_int_x_pos_int_y() {
    assertEquals(4, Calculator.x_to_the_y(2, 2));
  }

  @Test
  public void exponential_neg_int_x_pos_int_y() {
    assertEquals(4, Calculator.x_to_the_y(-2, 2));
  }

  @Test
  public void exponential_pos_double_x_pos_int_y() {
    assertEquals(6.25, Calculator.x_to_the_y(2.5, 2));
  }

  @Test
  public void exponential_neg_double_x_pos_int_y() {
    assertEquals(6.25, Calculator.x_to_the_y(-2.5, 2));
  }

  @Test
  public void exponential_pos_double_x_pos_double_y() {
    assertEquals(9.88211768802618, Calculator.x_to_the_y(2.5, 2.5), epsilon);
  }

  @Test
  public void exponential_neg_double_x_pos_double_y() {
    // TODO: This should yield a math error or an imaginary number
    assertEquals(Calculator.x_to_the_y(-2.5, 2.5), 0.101192885);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Mean Absolute Deviation
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void mad_no_input() {
    try {
      var list = makeArrayList();
      Calculator.mean_absolute_deviation(list);
    } catch (DivideByZeroException e) {
      assertTrue(true);
    } catch (Exception e) {
      assertFalse(false);
    }
  }

  @Test
  public void mad_single_int() {
    var list = makeArrayList(1);
    assertEquals(0, Calculator.mean_absolute_deviation(list));
  }

  @Test
  public void mad_mulitiple_ints() {
    var list = makeArrayList(1, 2, 3, 4, 5);
    assertEquals(1.2, Calculator.mean_absolute_deviation(list));
  }

  @Test
  public void mad_single_double() {
    var list = makeArrayList(1.5);
    assertEquals(0, Calculator.mean_absolute_deviation(list));
  }

  @Test
  public void mad_multiple_doubles() {
    var list = makeArrayList(1.5, 2.8, 3.1, 4.75, 5.9);
    assertEquals(1.372, Calculator.mean_absolute_deviation(list));
  }

  ///////////////////////////////////////////////////////////////////////////////
  // E^x, euler constant
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void e_to_the_x_0() {
    assertEquals(1, Euler.exp(0), epsilon);
  }

  @Test
  public void e_to_the_x_pos_int() {
    assertEquals(22026.465794806718, Euler.exp(10), epsilon);
  }

  @Test
  public void e_to_the_x_neg_int() {
    assertEquals(0.006737946999085467, Euler.exp(-5), epsilon);
  }

  @Test
  public void e_to_the_x_pos_double() {
    assertEquals(33.115451958692, Euler.exp(3.5), epsilon);
  }

  @Test
  public void e_to_the_x_neg_double() {
    assertEquals(0.000675538775, Euler.exp(-7.3), epsilon);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // 10^x
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void ten_to_the_x_pos_int() {
    assertEquals(100000, Calculator.ten_to_the_x(5));
  }

  @Test
  public void ten_to_the_x_neg_int() {
    assertEquals(0.001, Calculator.ten_to_the_x(-3), epsilon);
  }

  @Test
  public void ten_to_the_x_pos_double() {
    assertEquals(261658.5919399553, Calculator.ten_to_the_x(5.417735), 0.000000001);
  }

  @Test
  public void ten_to_the_x_neg_double() {
    assertEquals(0.001625086730018, Calculator.ten_to_the_x(-2.789123456), epsilon);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // mean
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void mean_single_int() {
    var list = makeArrayList(1);
    assertEquals(1, Calculator.mean(list));
  }

  @Test
  public void mean_mulitiple_ints() {
    var list = makeArrayList(1, 2, 3, 4, 5);
    assertEquals(3, Calculator.mean(list));
  }

  @Test
  public void mean_single_double() {
    var list = makeArrayList(1.5);
    assertEquals(1.5, Calculator.mean(list));
  }

  @Test
  public void mean_multiple_doubles() {
    var list = makeArrayList(1.5, 2.5, 3.5, 4.5, 5.5);
    assertEquals(3.5, Calculator.mean(list));
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Variance
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void variance_single_int() {
    var list = makeArrayList(1);
    var mean = Calculator.mean(list);
    assertEquals(0, Calculator.variance(mean, list));
  }

  @Test
  public void variance_mulitiple_ints() {
    var list = makeArrayList(1, 2, 3, 4, 5);
    var mean = Calculator.mean(list);
    assertEquals(2, Calculator.variance(mean, list));
  }

  @Test
  public void variance_single_double() {
    var list = makeArrayList(1.5);
    var mean = Calculator.mean(list);
    assertEquals(0, Calculator.variance(mean, list));
  }

  @Test
  public void variance_multiple_doubles() {
    var list = makeArrayList(1.5, 2.5, 3.5, 4.5, 5.5);
    var mean = Calculator.mean(list);
    assertEquals(2, Calculator.variance(mean, list));
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Absolute Value
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void absolute_value_pos_int() {
    assertEquals(4, Calculator.absolute_value(4));
  }

  @Test
  public void absolute_value_neg_int() {
    assertEquals(4, Calculator.absolute_value(-4));
  }

  @Test
  public void absolute_value_pos_double() {
    assertEquals(2.5, Calculator.absolute_value(2.5));
  }

  @Test
  public void absolute_value_neg_double() {
    assertEquals(2.5, Calculator.absolute_value(-2.5));
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Square root
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void square_root_exact() {
    assertEquals(2, Calculator.square_root(4));
  }

  @Test
  public void square_root_double() {
    assertEquals(1.581138830084, Calculator.square_root(2.5), epsilon);
  }

  @Test
  public void square_root_inexact() {
    assertEquals(1.414213562373, Calculator.square_root(2), epsilon * 10);
  }

  @Test
  public void square_root_negative() {
    try {
      Calculator.square_root(-1);
      assertFalse(false);
    } catch (SquareRootOfNegativeException e) {
      assertTrue(true);
    } catch (Exception e) {
      assertFalse(false);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Helpers
  ///////////////////////////////////////////////////////////////////////////////
  /*
   * Just helper methods so you don't have to do
   * var list = new ArrayList<Double>();
   * list.add(1);
   * list.add(2);
   * list.add(3);
   * ...
   * every time you write a test for something that takes a list as input
   * */
  public ArrayList<Double> makeArrayList(int... elements) {
    var list = new ArrayList<Double>();
    for (var i = 0; i < elements.length; i++) {
      list.add((double) elements[i]);
    }
    return list;
  }

  public ArrayList<Double> makeArrayList(double... elements) {
    var list = new ArrayList<Double>();
    for (var i = 0; i < elements.length; i++) {
      list.add(elements[i]);
    }
    return list;
  }
}
