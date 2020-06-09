package eternity.Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import eternity.Calculator;
import eternity.Errors.DivideByZeroException;
import eternity.Errors.SquareRootOfNegativeException;

class TestCalculator {
	
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
		var list = makeArrayList(1,2,3,4,5);
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
		var list = makeArrayList(1,2,3,4,5);
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
		}
		catch (SquareRootOfNegativeException e) {
			assertTrue(true);
		}
		catch (Exception e) {
			assertFalse(false);
		}
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
	// Helpers
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
		for (var i = 0; i < elements.length ; i++) {
			list.add((double)elements[i]);
		}
		return list;
	}

	public ArrayList<Double> makeArrayList(double... elements) {
		var list = new ArrayList<Double>();
		for (var i = 0; i < elements.length ; i++) {
			list.add(elements[i]);
		}
		return list;
	}
}
