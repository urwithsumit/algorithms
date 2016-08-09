package multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/***
 * 
 * @author sumitkumar
 * 
 * @param <T>
 */
class Consumer<T extends Comparable<T>> implements Runnable {
	
	private BlockingQueue<T> queue;

	public Consumer(BlockingQueue<T> q) {
		this.queue = q;
	}

	public void run() {
		while (true) {
			consumeQueueItem();
		}
	}

	public void consumeQueueItem() {
		try {
			System.out.println("Queue Item: " + queue.take());
		} catch (InterruptedException e) {
			System.out.println("Interrupted Ex for Queue is Caught during Read");
		}
	}

}

/**
 * 
 * @author sumitkumar
 * 
 * @param <T>
 */
class Producer<T extends Comparable<T>> implements Runnable {

	private BlockingQueue<T> queue;

	public Producer(BlockingQueue<T> q) {
		this.queue = q;
	}

	public void run() {
		while (true) {
			produceQueueItem();
		}
	}

	@SuppressWarnings("unchecked")
	public void produceQueueItem() {

		try {
			System.out.println("Producer...");
			queue.put((T) "Sumit");
		} catch (InterruptedException e) {
			System.out.println("Producer Interrupted..");
		}

	}

}

/**
 * 
 * @author sumitkumar
 * 
 */
public class BlockingQEx {

	public static void main(String[] args) {
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(4);
		Producer<String> p = new Producer<String>(q);
		Consumer<String> c1 = new Consumer<String>(q);
		Consumer<String> c2 = new Consumer<String>(q);
		Thread tp = new Thread(p);
		Thread tc1 = new Thread(c1);
		Thread tc2 = new Thread(c2);

		tp.start();
		tc1.start();
		tc2.start();
	}

}
