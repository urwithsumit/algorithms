package arrays;

public class NonRepeatNumber {
	public static void main(String[] args) {

		int[] a = { 1, 1, 2, 2, 3, 4, 4, 5, 5 };
		int arrSum = 0;
		for (int i = 0; i < a.length; i++) {
			arrSum += a[i];
		}
		int n = a[a.length - 1];
		int dSum = 2 * (n * (n + 1) / 2);
		System.out.println(dSum - arrSum);
	}
}
