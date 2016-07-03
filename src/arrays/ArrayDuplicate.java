package arrays;

/**
 * Find exactly 1 duplicate element. Given array is sorted.
 * 
 * @author sumitkumar
 * 
 */
public class ArrayDuplicate {
	public static void main(String[] args) {

		int[] a = { 1, 1, 2, 3, 4, 5 };

		if (a.length < 2) {
			throw new IllegalArgumentException("Atleast 2 element are needed to find duplicate");
		}

		int dupSum = 0;
		for (int i = 0; i < a.length; i++) {
			dupSum += a[i];
		}

		/**
		 * Sum of N Number = N*(N+1)/2;
		 */
		int nSum = (a[a.length - 1] * (a[a.length - 1] + 1)) / 2;

		System.out.println("Dup Num: " + (dupSum - nSum));

	}
}
