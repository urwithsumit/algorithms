package multithread;

import java.util.ArrayList;
import java.util.List;

class ProducerList implements Runnable {
	private List<Integer> list;

	public ProducerList(List<Integer> i) {
		list = i;
	}

	public void run() {
		produce();
	}

	private void produce() {
		synchronized (list) {
			for (int i = 0; i < 5; i++) {
				System.out.println("Add : " + i);
				list.add(i);
			}
			
			list.notify();
		}
	}
}

class ConsumerList implements Runnable {

	private List<Integer> list;

	public ConsumerList(List<Integer> i) {
		list = i;
	}

	public void run() {
		synchronized (list) {
			try {
				list.wait();
			} catch (InterruptedException e) {
				System.out.println("Exception in Consumer");
			}
			
			consume();
		}
	}

	private void consume() {
		while (list.size() > 0) {
			System.out.println("Removing : " + list.get(0));
			list.remove(0);
		}
	}
}

public class ThreadComm {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Thread t1 = new Thread(new ProducerList(list));
		Thread t2 = new Thread(new ConsumerList(list));

		t1.start();
		t2.start();
	}

}
