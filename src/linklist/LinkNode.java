package linklist;

/**
 * LinkList Node class. Will use the same for single linklist and double link list implementation
 * 
 * @author sumitkumar
 * 
 * @param <T>
 */
public class LinkNode<T> {
	public T data;
	public LinkNode<T> next;
	public LinkNode<T> previous;

	public LinkNode() {

	}

	public LinkNode(T s) {
		data = s;
		next = previous = null;
	}
}
