package sortings;

import java.util.Arrays;
/**
 * Complexity - O(n Log n)
 * @author sumitkumar
 *
 */
public class MergeSort {

	public void sort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;

			sort(arr, left, mid);
			sort(arr, mid + 1, right);

			merge(arr, left, mid, right);
		}
	}

	private void merge(int[] arr, int left, int mid, int right) {
		//Identify the size for left
		int n1 = mid - left + 1; // Consider mid in Left
		
		//Identify the size for right
		int n2 = right - mid;

		int[] L = new int[n1]; // Temp Left Array
		int[] R = new int[n2]; // Temp Right Array

		//Copy the array content to temp array
		for (int i = 0; i < n1; i++) {
			L[i] = arr[left + i]; // offset l
		}

		for (int j = 0; j < n2; j++) {
			R[j] = arr[j + mid + 1]; // offset m
		}
		
		int i = 0, j = 0;

		int k = left;

		while (i < n1 && j < n2) {
			
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}

			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			k++;
			i++;
		}

		while (j < n2) {
			arr[k] = R[j];
			k++;
			j++;
		}

	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 4, 1, 2, 3, 4, 0 };
		MergeSort ms = new MergeSort();
		ms.sort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}

}
