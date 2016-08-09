package arrays;

public class MinMaxArray {
	public static void main(String[] args) {

		int[] a = { 1, 2, 1, 5, 3, 8, 6, 0 };

		int aMin = a[0];
		int aMax = a[0];

		for (int i = 1; i < a.length; i++) {

			if (aMin > a[i]) {
				aMin = a[i];
			}

			if (aMax < a[i]) {
				aMax = a[i];
			}
		}

		System.out.println("Min: " + aMin + " and Max: " + aMax);

	}
}
