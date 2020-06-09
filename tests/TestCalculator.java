import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import eternity.Calculator;
import eternity.Errors.SquareRootOfNegativeException;

class TestCalculator {
  ///////////////////////////////////////////////////////////////////////////////
  // Mean Absolute Deviation
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void mad_no_input() {
    try {
      var list = makeArrayList();
      Calculator.mean_absolute_deviation(list);
    }catch (DivideByZeroException e) {
      assertTrue(true);
    }
    catch (Exception e) {
      assertFalse(false);
    }
  }

  @Test
  public void mad_single_int() {
    var list = makeArrayList(1);
    assertEquals(Calculator.mean_absolute_deviation(list), 0);
  }

  @Test
  public void mad_mulitiple_ints() {
    var list = makeArrayList(1,2,3,4,5);
    assertEquals(Calculator.mean_absolute_deviation(list), 1.2);
  }

  @Test
  public void mad_single_double() {
    var list = makeArrayList(1.5);
    assertEquals(Calculator.mean_absolute_deviation(list), 0);
  }

  @Test
  public void mad_multiple_doubles() {
    var list = makeArrayList(1.5, 2.8, 3.1, 4.75, 5.9);
    assertEquals(Calculator.mean_absolute_deviation(list), 1.372);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // E^x, euler constant
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void e_to_the_x_0() {
    assertEquals(Calculator.exp(0), 1);
  }

  @Test
  public void e_to_the_x_pos_int() {
    assertEquals(Calculator.exp(10), 22026.46579);
  }

  @Test
  public void e_to_the_x_neg_int() {
    assertEquals(Calculator.exp(-5), 0.006737946);
  }

  @Test
  public void e_to_the_x_pos_double() {
    assertEquals(Calculator.exp(3.5), 33.11545196);
  }

  @Test
  public void e_to_the_x_neg_double() {
    assertEquals(Calculator.exp(-7.3), 0.000675538);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // 10^x
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void ten_to_the_x_pos_int() {
    assertEquals(Calculator.ten_to_the_x(5), 100000);
  }

  @Test
  public void ten_to_the_x_neg_int() {
    assertEquals(Calculator.ten_to_the_x(-3), 0.001);
  }

  @Test
  public void ten_to_the_x_pos_double() {
    assertEquals(Calculator.ten_to_the_x(5.417735), 261658.59194);
  }

  @Test
  public void ten_to_the_x_neg_double() {
    assertEquals(Calculator.ten_to_the_x(-2.789123456), 0.00162508673);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // mean
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void mean_single_int() {
    var list = makeArrayList(1);
    assertEquals(Calculator.mean(list), 1);
  }

  @Test
  public void mean_mulitiple_ints() {
    var list = makeArrayList(1, 2, 3, 4, 5);
    assertEquals(Calculator.mean(list), 3);
  }

  @Test
  public void mean_single_double() {
    var list = makeArrayList(1.5);
    assertEquals(Calculator.mean(list), 1.5);
  }

  @Test
  public void mean_multiple_doubles() {
    var list = makeArrayList(1.5, 2.5, 3.5, 4.5, 5.5);
    assertEquals(Calculator.mean(list), 3.5);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Variance
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void variance_single_int() {
    var list = makeArrayList(1);
    var mean = Calculator.mean(list);
    assertEquals(Calculator.variance(mean, list), 0);
  }

  @Test
  public void variance_mulitiple_ints() {
    var list = makeArrayList(1, 2, 3, 4, 5);
    var mean = Calculator.mean(list);
    assertEquals(Calculator.variance(mean, list), 2);
  }

  @Test
  public void variance_single_double() {
    var list = makeArrayList(1.5);
    var mean = Calculator.mean(list);
    assertEquals(Calculator.variance(mean, list), 0);
  }

  @Test
  public void variance_multiple_doubles() {
    var list = makeArrayList(1.5, 2.5, 3.5, 4.5, 5.5);
    var mean = Calculator.mean(list);
    assertEquals(Calculator.variance(mean, list), 2);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Absolute Value
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void absolute_value_pos_int() {
    assertEquals(Calculator.absolute_value(4), 4);
  }

  @Test
  public void absolute_value_neg_int() {
    assertEquals(Calculator.absolute_value(-4), 4);
  }

  @Test
  public void absolute_value_pos_double() {
    assertEquals(Calculator.absolute_value(2.5), 2.5);
  }

  @Test
  public void absolute_value_neg_double() {
    assertEquals(Calculator.absolute_value(-2.5), 2.5);
  }

  ///////////////////////////////////////////////////////////////////////////////
  // Square root
  ///////////////////////////////////////////////////////////////////////////////
  @Test
  public void square_root_exact() {
    assertEquals(Calculator.square_root(4), 2);
  }

  @Test
  public void square_root_double() {
    assertEquals(Calculator.square_root(2.5), 1.5811388300841935);
  }

  @Test
  public void square_root_inexact() {
    assertEquals(Calculator.square_root(2), 1.4142135623746899);
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
