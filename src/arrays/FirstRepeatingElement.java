package arrays;

public class FirstRepeatingElement {
	public static void main(String[] args) {

		int[] a = { 10, 5, 3, 4, 3, 5, 6 };
		boolean flag = false;
		for (int i = 0; i < a.length; i++) {
			if (flag) {
				break;
			}

			int k = i + 1;
			while (k < a.length) {
				if (a[i] == a[k]) {
					System.out.println("Repeating Number: " + a[i]);
					flag = true;
					break;
				}

				k++;
			}
		}
	}
}
