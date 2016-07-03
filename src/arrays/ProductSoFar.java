package arrays;

/**
 * Get Product of number other than the current index
 * 
 * @author sumitkumar
 * 
 */
public class ProductSoFar {

	public static void main(String[] args) {

		int[] a = { 2, 3, 4, 5 };
		if (a.length < 1) {
			throw new IllegalArgumentException();
		}

		int previous = 1;
		int value;
		for (int i = 0; i < a.length; i++) {
			value = previous * multiply(i + 1, a.length - 1, a);
			previous *= a[i];

			System.out.print((value) + "  ");
		}

	}

	static int multiply(int low, int high, int[] a) {
		if (low == high)
			return a[low];

		int product = 1;
		for (int i = low; i <= high; i++) {
			product *= a[i];
		}

		return product;
	}

}
