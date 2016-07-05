package datastructure;

import java.util.Arrays;

/**
 * Custom Implementation of Priority Queue using Heap Data Structure.
 * 
 * @author Sumit Kumar
 * 
 */
public class CustomPriorityQueue {
	private int[] array;
	private int qDepth;

	/**
	 * Constructor with default queue size as 10
	 */
	public CustomPriorityQueue() {
		array = new int[10];
		qDepth = -1;
	} // end of constructor

	/**
	 * Constructor taking size of queue
	 * 
	 * @param size
	 */
	public CustomPriorityQueue(int size) {
		array = new int[size];
	}// end of constructor

	public boolean add(int value) throws RuntimeException {
		boolean success = false;
		if (qDepth++ > array.length - 1) {
			throw new RuntimeException("Queue is full");
		}
		array[qDepth] = value;
		success = true;
		siftUp(qDepth);
		return success;
	}// end of add

	/**
	 * Get the head of queue
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public int peek() throws RuntimeException {
		if (qDepth == -1) {
			throw new RuntimeException("Queue is empty");
		}

		return array[0];
	}// end of peek

	/**
	 * Poll the head of Queue and than re-order the queue to bring the next
	 * priority element as the head of queue
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public int poll() throws RuntimeException {

		if (qDepth == -1) {
			throw new RuntimeException("Queue is empty");
		}

		int value = array[0];
		array[0] = array[qDepth];
		array[qDepth] = 0;
		qDepth--;

		if (qDepth > 1)
			siftDown(0);

		return value;
	}// end of poll

	/**
	 * Re-order the heap after the root of heap is deleted by the sift down
	 * process.
	 * 
	 * @param i
	 */
	private void siftDown(int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int larger;
		if (left <= qDepth && array[left] > array[i]) {
			larger = left;
		} else {
			larger = i;
		}

		if (right <= qDepth && array[right] > array[larger]) {
			larger = right;
		}

		if (i != larger) {
			int temp = array[i];
			array[i] = array[larger];
			array[larger] = temp;

			siftDown(larger);
		}
	} // end of siftDown

	/**
	 * Re-order the heap on insertion
	 * 
	 * @param i
	 */
	private void siftUp(int i) {
		int parent = i / 2;
		while (i != parent && array[parent] < array[i]) {
			int temp = array[i];
			array[i] = array[parent];
			array[parent] = temp;

			siftUp(parent);
		}

	}// end of siftUp

	public void print() {
		System.out.println(Arrays.toString(array));
	}

	public static void main(String[] args) {

		CustomPriorityQueue queue = new CustomPriorityQueue();

		queue.add(1);
		queue.add(12);
		queue.add(11);
		queue.add(10);
		queue.add(19);
		queue.add(100);
		queue.add(18);
		queue.add(22);
		queue.add(26);
		queue.add(13);

		queue.print();

		for (int i = 0; i < queue.array.length; i++) {
			System.out.println(" Peek Head: " + queue.peek());
			queue.poll();
			queue.print();
		}

	}

}// end of Class