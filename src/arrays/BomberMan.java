package arrays;

/**
 * Google interview Question - Aug 2016
 * In the game of Bomberman you have a grid of blank spaces and stones, and you can place a bomb on any blank space, the
 * bomb spreads north/east/south/west all the way until an edge or a stone
 * 
 * . # . #
 * . . o .
 * . # . #
 * . . . .
 * 
 * Can you tell me the best position on a given grid so the bomb covers the most area? (return coordinates)
 * 
 * @author sumitkumar
 * 
 */

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;

	}

}

public class BomberMan {
	
	public static Pair bombPosition(int[][] arr) {

		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("The input data is not valid");
		}

		// Int size = arr.length;
		// int [][] resultArr = new int[size][size];

		/*
		 * 1 0 1 0
		 * 1 1 1 1
		 * 1 0 1 0
		 * 1 1 1 1
		 */

		int xSum = 0;

		int maxSum = 0;
		int xResult = 0;
		int yResult = 0;
		int x;
		int y;
		// O(n^2)
		for (int i = 0; i < arr.length; i++) { // 2
			for (int j = 0; j < arr.length; j++) { // 1
				x = 0;
				while (x < arr.length) { // O(1)
					xSum += arr[i][x];
					x++;
				}

				y = 0;
				while (y < arr.length) { // O(1)
					xSum += arr[y][j];
					y++;
				}

				// resultArr[i][j] = xSum;

				if (xSum > maxSum) {
					maxSum = xSum;
					xResult = i;
					yResult = j;
				}

				xSum = 0;

			}
		}

		return new Pair(xResult, yResult);

	}

}
