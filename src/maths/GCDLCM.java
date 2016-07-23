package maths;

import java.util.Scanner;

/**
 * Greatest Common Divisor of 2 Number
 * The greatest common divisor (GCD) of two numbers a and b is the greatest number that divides evenly both a and b
 * 
 * @author sumitkumar
 * 
 */
public class GCDLCM {
	/**
	 * Euclid’s algorithm for GCD :
	 * https://www.topcoder.com/community/data-science/data-science-tutorials/mathematics-for-topcoders/
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;

		return gcd(b, a % b);
	}
	
	public static int LCM(int a, int b)
	{
	   return (b*a)/gcd(a,b);
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		int b = scan.nextInt();

		System.out.println("GCD of " + a + " and " + b + " is : " + gcd(Math.max(a, b), Math.min(a, b)));
		
		System.out.println("LCM of " + a + " and " + b + " is : " + LCM(Math.max(a, b), Math.min(a, b)));

		// System.out.println(gcd(4, 2));
		// System.out.println(gcd(2336, 1314));
	}
}
