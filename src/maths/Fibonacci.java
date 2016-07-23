package maths;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Fibonacci Series using Linear approach
 * 
 * @author sumitkumar
 * 
 */
public class Fibonacci {

	private static Map<Integer, Integer> memory = new HashMap<Integer, Integer>();
	static {
		memory.put(0, 0);
		memory.put(1, 1);
	}

	/**
	 * Linear approach of generating fibonacci series
	 * 
	 * @param limit
	 */
	private static void fibLinear(int limit) {

		int a = 0;
		int b = 1;
		int next;
		System.out.print("0 1" + " ");
		for (int i = 2; i <= limit; i++) {
			next = (a + b);
			System.out.print(next + " ");
			a = b;
			b = next;
		}

	}

	/**
	 * Recursive approach of generating fibonacci series.
	 * 
	 * @param limit
	 * @return
	 */
	private static int fibRec(int limit) {
		int next;

		if (memory.get(limit) != null) {
			next = memory.get(limit);
		} else {
			next = fibRec(limit - 1) + fibRec(limit - 2);

			// Optimization step: store the result of previously executed steps in a map
			memory.put(limit, next);
			System.out.print(next + " ");
		}

		return next;
	}

	public static void main(String[] args) {

		System.out.println("Enter the limit for the series...");
		Scanner scan = new Scanner(System.in);
		final int limit = scan.nextInt();

		if (limit <= 1) {
			System.out.print("0" + " ");
		} else if (limit == 2) {
			System.out.print("0 1" + " ");
		} else {

			final Thread t1 = new Thread(new Runnable() {
				public void run() {
					fibLinear(limit);
				}
			}, "linearThread");

			Thread t2 = new Thread(new Runnable() {
				public void run() {
					System.out.print("0 1" + " ");
					fibRec(limit);
				}
			}, "recursiveThread");

			System.out.print(t1.getName() + " => ");
			t1.start();

			try {
				// This will actually make the Thread run sequentially.
				// Thread t1 will join to the Main Thread.
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				// Currently executing thread i.e. Main Thread in this example will Sleep here.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("");
			System.out.print(t2.getName() + " => ");
			// Main thread now start the thread t2
			t2.start();
		}

	}

}
