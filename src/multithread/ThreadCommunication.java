package multithread;

public class ThreadCommunication {

	public static void main(String[] args) {

		StringBuffer buffer = new StringBuffer();
		Thread t1 = new ThreadA(buffer);
		t1.start();

		synchronized (t1) {
			try {
				t1.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(buffer.toString());
		}
	}
}

class ThreadA extends Thread {

	private StringBuffer buf;

	public ThreadA(StringBuffer buff) {
		buf = buff;
	}

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				buf.append(i).append(" ");
			}
			notify();
		}
	}

}
