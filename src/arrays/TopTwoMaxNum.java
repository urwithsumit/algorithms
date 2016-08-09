package arrays;

public class TopTwoMaxNum {
	public static void main(String[] args) {

		// int[] a = { 1, 3, 4, 2, 3, 5, 9 };
		int[] a = { -1, -2, 4, 5, 90, 1 };
		int max1 = a[0]; // 1;3;4;5
		int max2 = a[0]; // 1;1;3;4
		for (int i = 1; i < a.length; i++) {
			// int k = i;
			int j = i + 1;

			while (j < a.length) {
				if (a[j] > max1) { // 3 > 1; 4 > 3;2 > 4; 3 > 4;5 > 4;9 > 5
					if (max1 > max2) { // 1 > 1; 3 > 1; 4 > 3; 5 > 4;
						max2 = max1; // m2 = 3; m2 = 4; m2 = 5
					}
					max1 = a[j]; // m1 = 3; m1 = 4;m1 = 5;m1 =9
				}

				j++; // 2,
			}
		}

		System.out.printf("Max1 is %d %nmax2 is %d", max1, max2);
	}
}
