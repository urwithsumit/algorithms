package arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Switches are against Columns. Given the number of times switches can be
 * flipped, find the maximum number of rows that will have no lights off.
 * 
 * @author sumitkumar
 * 
 */

class LightRow {
	private static int rows;
	private static int columns;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		String[] rowle = str.split(",");

		rows = rowle.length;
		columns = rowle[0].length();

		char[][] row = new char[rows][columns];
		for (int i = 0; i < rowle.length; i++) {
			row[i] = rowle[i].toCharArray();
		}

		int flip = Integer.parseInt(in.readLine().trim());

		while (flip > 0) {
			int index = findSwitchToFlip(row);
			if (index > -1) {
				switchLight(index, row);
				flip--;
			} else {
				break;
			}
		}

		System.out.println(totalLightedRows(row));

	}

	/**
	 * Method to toggle the light when a switch is pressed.
	 * 
	 * @param index
	 * @param row
	 */
	static void switchLight(int index, char[][] row) {
		for (int i = 0; i < rows; i++) {
			if ('N' == row[i][index]) {
				row[i][index] = 'Y';
			} else {
				row[i][index] = 'N';
			}
		}
	}

	/**
	 * Find which column has maximum number of OFF lights
	 * 
	 * @param row
	 * @return
	 */
	private static int findSwitchToFlip(char[][] row) {

		int result = 0;
		int index = -1;

		for (int i = 0; i < columns; i++) {
			int count = 0;
			for (int j = 0; j < rows; j++) {
				if ('N' == row[j][i]) {
					++count;
				}
			}

			if (result < count) {
				result = count;
				index = i;
			}
		}

		return index;
	}

	/**
	 * Finds the count of rows that have all their Lights ON
	 * 
	 * @param row
	 * @return
	 */
	private static int totalLightedRows(char[][] row) {
		int lightedRow = 0;
		for (int i = 0; i < rows; i++) {
			int count = 0;
			for (int j = 0; j < columns; j++) {
				if ('Y' == row[i][j]) {
					++count;
				}
			}

			if (count == columns)
				++lightedRow;
		}

		return lightedRow;
	}
}