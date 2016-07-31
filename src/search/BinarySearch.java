package search;

/**
 * O(Log n)
 * @author sumitkumar
 *
 * @param <T>
 */
public class BinarySearch<T extends Comparable<T>> {

	static Integer[] sortedArr = { 0, 1, 2, 3, 4, 5, 23, 33, 35, 67, 89, 100 };

	private int binarySearch(T[] array, int lo, int hi, T key) {

		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		}

		if (lo < hi) {
			int mid = (hi + lo) / 2;
			if (array[mid].compareTo(key) > 0)
				return binarySearch(array, lo, mid - 1, key);
			else if (array[mid].compareTo(key) < 0)
				return binarySearch(array, mid + 1, hi, key);
			else
				return mid;
		}

		return -1;
	}

	public static void main(String[] args) {
		BinarySearch<Integer> search = new BinarySearch<Integer>();
		int index = search.binarySearch(sortedArr, 0, sortedArr.length, 100);
		if (index > -1) {
			System.out.println("Found at index : " + index);
		} else {
			System.out.println("Not Found ");
		}

	}
}
