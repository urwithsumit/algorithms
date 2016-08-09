package arrays;
/**
 * Find the exactly 1 missing number, given the maximum number in an array is equal to size of array.
 * 
 * @author sumitkumar
 * 
 */
public class MissingNumber {
	public static void main(String[] args) {

		int[] a = { 1, 3, 4, 5, 6, 7, 8 };
		int arraySum = 0;
		for (int i = 0; i < a.length; i++) {
			arraySum += a[i];
		}

		int sumOfNumber = (a[a.length - 1] * (a[a.length - 1] + 1)) / 2; // Sum of n number = n(n-1)/2

		int mNum = sumOfNumber - arraySum;

		if (mNum > -1) {
			System.out.println("Missing Number: " + mNum);
		} else {
			System.out.println("Missing Number Not found");
		}
	}

}
