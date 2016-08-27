package linklist;
/**
 * 
 * @author sumitkumar
 *
 */
public class CicularLinkList {

	private LinkNode<String> head = new LinkNode<String>();

	public LinkNode<String> detectCycleNode() {
		return null;
	}

	public void add(String s) {
		if (head.next == null) {
			head.next = new LinkNode<String>(s);
		}

	}

	public static void main(String[] args) {

	}

}
