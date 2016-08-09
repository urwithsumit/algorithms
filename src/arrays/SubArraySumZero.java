package arrays;

public class SubArraySumZero {

	public static void main(String[] args) {

		int[] a = { 4, 2, -3, 1, 6 };
		boolean found = false;

		for (int i = 0; i < a.length; i++) {
			if (found)
				break;
			int k = i;
			int j = i + 1;
			int sum = a[k];
			while (j < a.length) {
				if ((sum += a[j]) == 0) {
					System.out.printf("SubArray Exist for Array index %d -> %d", k, j);
					found = true;
					break;
				}
				j++;
			}
		}
	}

}