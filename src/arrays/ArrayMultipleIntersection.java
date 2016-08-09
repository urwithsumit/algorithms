package arrays;

import java.util.Arrays;

public class ArrayMultipleIntersection {

	public static void main(String[] args) {

		int[] a = { 1, 5, 10, 20, 40, 80 };
		int[] b = { 6, 7, 20, 80, 100 };
		int[] c = { 3, 4, 15, 20, 30, 70, 80, 120 };

		int[] result = intersect(intersect(a, b), c);

		System.out.println(Arrays.toString(result));

	}

	private static int[] intersect(int[] a, int[] b) {
		int[] c = new int[a.length];

		int k = -1;

		Arrays.sort(b);

		for (int i = 0; i < a.length; i++) {
			int index = Arrays.binarySearch(b, a[i]);

			System.out.println(index);
			if (index > -1) {
				c[++k] = a[i];
			}
		}

		return c;
	}
}