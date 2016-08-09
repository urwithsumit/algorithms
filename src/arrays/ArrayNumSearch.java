package arrays;

import java.util.Arrays;

public class ArrayNumSearch {

	private static int search(int[] a, int num, int upper, int lower) {
		int index = -1;
		if (upper < lower)
			return index;

		int mid = (upper + lower) / 2;

		if (mid % 2 != 0)
			mid = mid + 1;

		System.out.println(mid);
		if (a[mid] == num) {
			index = mid;
		} else if (num > a[mid]) {
			search(a, num, upper, mid + 1);
		} else if (num < a[mid]) {
			search(a, num, mid, lower);
		}
		
		return index;
	}

	public static void main(String[] args) {

		int[] a = { 1, 3, 4, -2, -3, -1, 6, 7, -4, 8, 0, 5, 0, 3, 2 };
		System.out.println(a.length);
		// Insertion Sort
		for (int i = 0; i < a.length; i++) {
			int k = i;
			int j = i + 1;

			while (k > -1 && j < a.length) {
				if (a[j] < a[k]) { // j = 3 k = 2 ; j = 2 K = 1;
					int temp = a[k];
					a[k] = a[j]; // a[2] = 2 ; a[2] = 3
					a[j] = temp; // a[3] = 4; a[1] = 2
				}

				j--; // j = 2; j = 1
				k--; // k = 1 ; k = 0
			}
		}

		System.out.println(Arrays.toString(a));

		// Binary Search
		int index = search(a, 3, a.length, 0);

		if (index > -1)
			System.out.println("Found");
		else
			System.out.println("Not Found");

	}

}