package sortings;
import java.util.Arrays;

public class HeapSort {

	private void maxHeap(int[] input, int node, int arrSize) {

		int left = 2 * node;
		int right = 2 * node + 1;

		int large;

		if (left <= arrSize && input[left] > input[node]) {
			large = left;
		} else {
			large = node;
		}// end of if-else

		if (right <= arrSize && input[right] > input[large]) {
			large = right;
		}// end if

		if (large != node) {
			swap(input, node, large);
			maxHeap(input, large, arrSize);
		}// end if

	}// end of maxHeap

	private void swap(int[] input, int node, int large) {
		int temp = input[node];
		input[node] = input[large];
		input[large] = temp;
	} // end swap

	public void sort(int[] input) {

		int size = input.length - 1;

		for (int i = size / 2; i >= 0; i--) {
			maxHeap(input, i, size);
		}// end of for

		for (int i = size; i > 0; i--) {
			swap(input, 0, i);
			size--;
			maxHeap(input, 0, size);
		}

	} // end of hSort

	public static void main(String[] args) {

		int[] input = { 3, 2, 4, 1, 2, 3, 4, 0 };

		System.out.println("Input: " + Arrays.toString(input));

		HeapSort hSort = new HeapSort();
		hSort.sort(input);

		System.out.println("Output: " + Arrays.toString(input));

	}// end of main

}// end of class