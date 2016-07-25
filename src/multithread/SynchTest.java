package multithread;

/**
 * Question from Kathy Sierra - Java 5
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

			for (int i = 0; i < 100; i++) {
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

		t3.join(); // This will stop the intermittent behavior and will ensure the main thread wait for the last thread
					// to complete before terminations

		System.out.println(buffer.toString());

	}

}