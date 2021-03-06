package sortings;

import java.util.Arrays;

public class QuickSort {

	private void quickSort(int[] arr, int left, int right) {

		int pivot = partition(arr, left, right);

		if (left < (pivot - 1)) {
			quickSort(arr, left, pivot - 1);
		}

		if (right > pivot) {
			quickSort(arr, pivot, right);
		}
	}

	/**
	 * @param arr
	 * @param lo
	 * @param hi
	 * @return
	 */
	private int partition(int[] arr, int lo, int hi) {
		int pivot = (hi + lo) / 2;

		int pivotValue = arr[pivot];

		while (lo <= hi) {

			while (arr[lo] < pivotValue) {
				lo++;
			}
			while (arr[hi] > pivotValue) {
				hi--;
			}

			if (lo <= hi) {

				int tmp = arr[lo];
				arr[lo] = arr[hi];
				arr[hi] = tmp;

				lo++;
				hi--;
			}

		}

		return lo;

	}

	public void sort(int[] arr) {

		if (arr.length < 1)
			return;

		quickSort(arr, 0, arr.length - 1);

	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 1, 1, 23, 5, 6, 4, 3, 2 };
		QuickSort sort = new QuickSort();
		sort.sort(arr);
		System.out.println(Arrays.toString(arr));

	}

}