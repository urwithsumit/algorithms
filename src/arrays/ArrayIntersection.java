package arrays;

import java.util.Arrays;

public class ArrayIntersection {

	public static void main(String[] args) {

		int[] a = { 21, 34, 41, 22, 35 };
		int[] b = { 61, 34, 45, 21, 11 };
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

		System.out.println(Arrays.toString(c));

	}

}
