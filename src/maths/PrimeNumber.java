package maths;

import java.util.Scanner;

/**
 * A number is prime if it is only divisible by 1 and itself
 * https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
 * 
 * @author sumitkumar
 * 
 */
public class PrimeNumber {

	public static boolean isPrime(int a) {
		if (a <= 1) {
			return false;
		}

		if (a == 2) {
			return true;
		}

		if (a % 2 == 0)
			return false;

		int m = (int) Math.sqrt(a);

		// division by 2 is checked above, so i can be skipped by 2
		for (int i = 3; i <= m; i += 2) {
			if (a % i == 0)
				return false;
		}

		return true;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();

		System.out.println(a + " is prime -> " + isPrime(a));

	}
}
