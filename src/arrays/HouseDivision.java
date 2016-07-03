package arrays;

import java.io.*;
import java.util.*;

/**
 * A and B want to divide a property. x = x coordinate of property y = y
 * coordinate of property rent = rent from the property
 * 
 * A & B want to draw a line so that no property lies on the line.
 * 
 * Find how, A & B should divide the property so that they difference between
 * earning is minimum
 * 
 * @author sumitkumar
 * 
 */
class HouseDivision {
	private static List<Integer> xList;
	private static List<Integer> yList;
	private static List<Integer> rentList;
	private static int[][] matrix;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();

		String[] x = str.split(",");
		xList = new ArrayList<Integer>();
		int row = 0;
		int val;
		for (int i = 0; i < x.length; i++) {
			val = Integer.parseInt(x[i].trim());
			xList.add(i, val);
			if (val > row) {
				row = val;
			}
		}

		str = in.readLine();
		String[] y = str.split(",");
		yList = new ArrayList<Integer>();
		int col = 0;
		val = 0;
		for (int i = 0; i < y.length; i++) {
			val = Integer.parseInt(y[i].trim());
			yList.add(i, val);
			if (val > col) {
				col = val;
			}
		}

		str = in.readLine();
		String[] rentArr = str.split(",");
		rentList = new ArrayList<Integer>();
		val = 0;
		for (int i = 0; i < rentArr.length; i++) {
			val = Integer.parseInt(rentArr[i].trim());
			rentList.add(i, val);
		}

		matrix = new int[row + 1][col + 1];

		int matSum = 0;
		for (int i = 0; i < rentList.size(); i++) {
			matrix[xList.get(i)][yList.get(i)] = rentList.get(i);
			matSum += rentList.get(i);
		}

		int a = 0;
		int b = 0;

		int horizontaldiff = matSum;

		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				a += matrix[i][j];
				b = Math.abs(2 * a - matSum);
				if (b < horizontaldiff) {
					horizontaldiff = b;
				}
			}
		}

		int verticalDiff = matSum;
		a = 0;
		b = 0;
		for (int i = 0; i <= col; i++) {
			for (int j = 0; j <= row; j++) {
				a += matrix[j][i];
				b = Math.abs(2 * a - matSum);
				if (b < verticalDiff) {
					verticalDiff = b;
				}
			}
		}

		System.out.println(Math.min(verticalDiff, horizontaldiff));
	}
}
