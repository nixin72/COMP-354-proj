package eternity.Tests;

import java.util.ArrayList;

class TestUtils {
	/*
	 * Just helper methods so you don't have to do 
	 * var list = new ArrayList<Double>();
	 * list.add(1);
	 * list.add(2);
	 * list.add(3);
	 * ...
	 * every time you write a test for something that takes a list as input
	 * */

	public static ArrayList<Double> makeArrayList(int... elements) {
		var list = new ArrayList<Double>();
		for (var i = 0; i < elements.length ; i++) {
			list.add((double)elements[i]);
		}

		return list;
	}

	public static ArrayList<Double> makeArrayList(double... elements) {
		var list = new ArrayList<Double>();
		for (var i = 0; i < elements.length ; i++) {
			list.add(elements[i]);
		}

		return list;
	}

	public static ArrayList<String> makeArrayList(String... elements) {
		var list = new ArrayList<String>();
		for (var i = 0; i < elements.length ; i++) {
			list.add(elements[i]);
		}

		return list;
	}

}
