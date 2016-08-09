package arrays;

public class FirstNonRepeatNum {
	public static void main(String[] args) {

		int[] a = { 10, 5, 3, 4, 3, 5, 6 };

		boolean flag = false;
		for (int i = 0; i < a.length; i++) {
			int j = 0;
			int k = i + 1;
			if (flag)
				break;
			while (k < a.length) {
				if (a[i] == a[k]) {
					break;
				}
				j++;
				if (j == a.length) {
					System.out.println(a[i]);
					flag = true;
				}
			}
		}
	}
}
