package arrays;

/**
 * Stock price of previous day. Find the maximum possible profit. Give that you
 * should purchase before you sell.
 * 
 * @author sumitkumar
 * 
 */
public class BestProfitShare {

	public static void main(String[] args) {

		int[] a = { 2, 4, 3, 6, 1, 2, 3, 9, 10, 0, 20, 3, 4, 1, 100, 99 };

		if (a.length < 2) {
			throw new IllegalArgumentException(
					"Array size less than 2. Cannot evaluate profit");
		}

		int min_price = a[0];
		int min_index = 0;
		int max_price = a[0];
		int profit = -1;

		for (int i = 0; i < a.length; i++) {
			if (a[i] < min_price) {
				profit = Math.max(profit, max_price - min_price);
				min_price = a[i];
				min_index = i;
				max_price = -1;
			}

			if (i > min_index && a[i] > min_price && a[i] > max_price) {
				max_price = a[i];
			}
		}

		System.out.println("Max Profit: "
				+ Math.max(profit, (max_price - min_price)));

	}

}
