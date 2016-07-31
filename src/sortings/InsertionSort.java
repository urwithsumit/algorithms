package sortings;

import java.util.Arrays;

/**
 * Worst Case O(n^2)
 * 
 * @author sumitkumar
 * 
 */
public class InsertionSort {

	public void insertSort(int[] a) {

		if (a == null || a.length == 0)
			throw new IllegalArgumentException("Array is null or empty");

		for (int i = 1; i < a.length; i++) {
			int j = i;
			while (j > 0 && a[j - 1] > a[j]) {
				int tmp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = tmp;

				j--;
			}
		}
	}

	public void insertSortOpt(int[] a) {

		if (a == null || a.length == 0)
			throw new IllegalArgumentException("Array is null or empty");

		int x;
		int j;
		for (int i = 1; i < a.length; i++) {
			x = a[i];
			j = i - 1;
			while (j >= 0 && a[j] > x) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = x;
		}

	}

	public static void main(String[] args) {

		int[] a = { 2, 4, 1, 99, 108, 34, 23, 44, 0 };
		InsertionSort sort = new InsertionSort();

		// sort.insertSort(a);
		sort.insertSortOpt(a);

		System.out.println(Arrays.toString(a));

	}

}
