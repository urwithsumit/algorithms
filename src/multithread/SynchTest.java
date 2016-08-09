package multithread;

/**
 * Question from Kathy Sierra - Java 5
 * Maintain an order between 3 threads so that threads produces a, b, c for 1000 times
 * 
 * @author sumitkumar
 * 
 */
public class SynchTest extends Thread {
	private StringBuffer buffer;
	private char letter;

	public SynchTest(StringBuffer buff, char letter) {
		this.buffer = buff;
		this.letter = letter;
	}

	@Override
	public void run() {
		synchronized (buffer) {
			for (int i = 0; i < 1000; i++) {
				buffer.append(letter);
			}
			buffer.append("\n");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		StringBuffer buffer = new StringBuffer();

		Thread t1 = new SynchTest(buffer, 'a');
		Thread t2 = new SynchTest(buffer, 'b');
		Thread t3 = new SynchTest(buffer, 'c');

		t1.start();
		t2.start();
		t3.start();

		t1.join(); // This will stop the intermittent behavior and will ensure the main thread wait for the threads
					// to complete before terminations
					// Join on either of the threads gives the desired result

		System.out.println(buffer.toString());
	}

}
