package arrays;

public class ArrayPairSum {
	public static void main(String[] args) {

		int[] a = { 1, 3, 4, 2, 3, 5, 0, 6, -1 };
		int x = 5;

		for (int i = 0; i < a.length; i++) {
			int j = i + 1;
			while (j > 0 && j < a.length) {
				if ((a[i] + a[j]) == x) {
					System.out.printf("(%d %d) %n", a[i], a[j]);
				}
				j++;
			}
		}
	}
}
