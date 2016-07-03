package trees;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * bArray[0] is not used. bArray[1] holds the root of BST. left child is 2*K.
 * Right child is 2*k + 1
 * 
 * @author Sumit Kumar
 * 
 */
public class BinarySearchTree {

	private int[] bstArray;
	private Map<Integer, Integer> keyCounter;

	public BinarySearchTree(int size) {
		bstArray = new int[40];
		Arrays.fill(bstArray, Integer.MIN_VALUE);
		keyCounter = new LinkedHashMap<Integer, Integer>();
	}

	private void inOrderTraversal(int index) { // Left->Node>Right

		if (index > bstArray.length - 1 || bstArray[index] == Integer.MIN_VALUE)
			return;

		inOrderTraversal(2 * index);
		if (!(bstArray[index] == Integer.MIN_VALUE))
			System.out.print(bstArray[index] + " ");
		inOrderTraversal(2 * index + 1);

	}

	private void preOrderTraversal(int index) {// Node->Left->Right

		if (index > bstArray.length - 1 || bstArray[index] == Integer.MIN_VALUE)
			return;

		if (!(bstArray[index] == Integer.MIN_VALUE))
			System.out.print(bstArray[index] + " ");
		inOrderTraversal(2 * index);
		inOrderTraversal(2 * index + 1);

	}

	private void postOrderTraversal(int index) {// Left->Right->Node

		if (index > bstArray.length - 1 || bstArray[index] == Integer.MIN_VALUE)
			return;

		inOrderTraversal(2 * index);
		inOrderTraversal(2 * index + 1);
		if (!(bstArray[index] == Integer.MIN_VALUE))
			System.out.print(bstArray[index] + " ");

	}

	/**
	 * Traverse Level By Level, printing the binary tree logically
	 * 
	 * @param index
	 */
	private void levelOrderTraversal(int index) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(index);

		int idepth = getDepth(index);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (node < bstArray.length) {

				int cDepth = getDepth(node);
				// When Level of node changes than print in new line
				if (cDepth != idepth) {
					System.out.println(" ");
					idepth = cDepth;
				}

				if ((bstArray[node] == Integer.MIN_VALUE)) {
					System.out.print(" (" + node + ") " + " * ");
				} else {
					if (keyCounter.get(bstArray[node]) != null) {
						System.out.print(" (" + node + ") " + bstArray[node] + ":" + (keyCounter.get(bstArray[node])));
					} else {
						System.out.print(" (" + node + ") " + bstArray[node] + " ");
					}
				}
				queue.add(2 * node);
				queue.add(2 * node + 1);
			}
		}
	}

	/**
	 * Height of the resulting tree
	 * 
	 * @param index
	 * @return
	 */
	private int height(int index) {

		if (index >= bstArray.length || bstArray[index] == Integer.MIN_VALUE)
			return 0;

		return Math.max(height(2 * index), height(2 * index + 1)) + 1;
	}

	private boolean insertLeftChild(int index, int value) {
		int lIndex = 2 * index;

		if (lIndex < bstArray.length) {
			bstArray[lIndex] = value;
			System.out.println(value + " inserted at Left index " + lIndex);
			return true;
		}

		return false;
	}

	private boolean insertRightChild(int index, int value) {
		int rIndex = 2 * index + 1;

		if (rIndex < bstArray.length) {
			bstArray[rIndex] = value;
			System.out.println(value + " inserted at Right index " + rIndex);
			return true;
		}

		return false;
	}

	private boolean hasLeftChild(int index) {
		int lIndex = 2 * index;
		if (lIndex < bstArray.length) {
			if (!(bstArray[lIndex] == Integer.MIN_VALUE)) {
				return true;
			}
		}

		return false;
	}

	private boolean hasRightChild(int index) {
		int rIndex = 2 * index + 1;
		if (rIndex < bstArray.length) {
			if (!(bstArray[rIndex] == Integer.MIN_VALUE)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Does not insert duplicate values into tree. Instead breaks from loop on
	 * duplicate.
	 * 
	 * @param iNode
	 * @param value
	 * @return
	 */
	private boolean insert(int iNode, int value) {

		if (bstArray[1] == Integer.MIN_VALUE) {
			bstArray[1] = value;
			return true;
		}

		boolean insert = false;
		while (iNode < bstArray.length) {
			if (bstArray[iNode] > value) {
				if (hasLeftChild(iNode)) {
					iNode = 2 * iNode;
				} else {
					insert = insertLeftChild(iNode, value);
					break;
				}
			} else if ((bstArray[iNode] < value)) {
				if (hasRightChild(iNode)) {
					iNode = 2 * iNode + 1;
				} else {
					insert = insertRightChild(iNode, value);
					break;
				}
			} else {
				if (!keyCounter.containsKey(value)) {
					// Adding 2 occurrence. 1st occurrence is already printed.
					// the 2nd match is now found
					keyCounter.put(value, 2);
				} else {
					int count = keyCounter.get(value);
					keyCounter.put(value, (count + 1));
				}
				break;
			}
		}

		return insert;
	}

	/**
	 * Return the Array index of the key if found in BST. Otherwise throws
	 * NoSuchFieldException
	 * 
	 * @param key
	 * @return
	 * @throws NoSuchFieldException
	 */
	private int search(int key) throws NoSuchFieldException {

		if (!isBinaryTreeExist()) {
			return -1;
		}

		int root = 1;
		int index = -1;
		while (root < bstArray.length) {
			if (bstArray[root] == key) {
				index = root;
				break;
			} else if (bstArray[root] > key) {
				root = 2 * root;
			} else {
				root = 2 * root + 1;
			}
		}

		if (index == -1) {
			throw new NoSuchFieldException("No Item with value " + key);
		}

		return index;

	}

	/**
	 * Successor node is one which will replace the Array index that is been
	 * deleted. To find successor node: 1. If the deleted node is leaf i.e. no
	 * right or left child than simply delete it. 2. If the deleted node has a
	 * left child, than search the highest value in the left sub-tree. The
	 * highest value will be found in the right child of the Left Subtree. If no
	 * right child of the left subtree than the left child of the node to be
	 * deleted will be the highest value 3. If the left Subtree of the node to
	 * be deleted does not exist than traverse the right subtree and identify
	 * the highest element similarly as point 2.
	 * 
	 * this will handle all scenario of deletion: 1. Delete node is a leaf node
	 * 2. Delete node has 1 left child 3. Delete node has 1 Right child 4.
	 * Delete node has a left and right child.
	 * 
	 * @param index
	 * @return
	 */
	private int successorNode(int index) {

		if (!hasLeftChild(index)) {
			if (!hasRightChild(index)) {
				return -1;
			}
		}

		// Successor will be the largest value in the left side of tree. Set
		// left child as parent
		int successor = 2 * index;

		// If no Left node than go for Right Node
		if (!hasLeftChild(index) && hasRightChild(index)) {
			successor++;
		}

		int nextIndex = successor;
		while (true) {
			// largest value will be the right most on the left subtree of the
			// selected side(right/left)
			if (hasRightChild(nextIndex)) {
				nextIndex = (2 * nextIndex) + 1;
			} else {
				break;
			}
		}

		successor = (bstArray[successor] > bstArray[nextIndex]) ? successor : nextIndex;

		return successor;
	}

	// Delete lead node
	// Delete child with one node
	// Delete child with 2 nodes
	private boolean delete(int key) throws NoSuchFieldException {
		if (!isBinaryTreeExist()) {
			return false;
		}
		boolean isDelete = false;

		int dIndex = search(key);
		int succesor = successorNode(dIndex);

		if (dIndex == -1) {
			throw new NoSuchFieldException("No Item with value " + key);
		}

		if (succesor == -1) {
			// Leaf node deletion
			bstArray[dIndex] = Integer.MIN_VALUE;
			return true;
		} else {
			// Successor Node with either 1 or 2 child nodes
			System.out.println("Succesor Node: " + bstArray[succesor]);
			bstArray[dIndex] = bstArray[succesor];
			// Re - adjust the child nodes of the Successor node in the array to
			// maintain the linkage
			reLinkChildNode(succesor);
			bstArray[succesor] = Integer.MIN_VALUE;

			isDelete = true;

		}

		return isDelete;
	}

	/**
	 * Perform in-order traversal from the index of the successor node. (The
	 * successor of the node that is getting deleted.) This will get all the
	 * child of successor node that needs to be re-linked in the BST. As the
	 * nodes are found in the in-order traversal, the same will be re-inserted
	 * into the binary tree to maintain the BST structure. The old index will be
	 * set to null.
	 * 
	 * @param succesor
	 */
	private void reLinkChildNode(int succesor) {

		if (succesor > bstArray.length - 1 || bstArray[succesor] == Integer.MIN_VALUE)
			return;

		// In Order Traversal to re-arrange all the child nodes
		reLinkChildNode(2 * succesor); // re-link left child

		if (!(bstArray[succesor] == Integer.MIN_VALUE)) {
			int value = bstArray[succesor];// determine the key to insert
			bstArray[succesor] = Integer.MIN_VALUE;

			insert(1, value);
		}

		reLinkChildNode(2 * succesor + 1); // re-link right child

	}

	/**
	 * Count the number of leaf nodes in a BST Leaf Node has no left and right
	 * child
	 * 
	 * @param index
	 * @return
	 */
	private int countLeafNode(int index) {
		if (index > bstArray.length || bstArray[index] == Integer.MIN_VALUE)
			return 0;

		if (bstArray[2 * index] == Integer.MIN_VALUE && bstArray[2 * index + 1] == Integer.MIN_VALUE)
			return 1;

		return countLeafNode(2 * index) + countLeafNode(2 * index + 1);
	}

	/**
	 * Give the Width of the Tree. This is the maximum of the highest number of
	 * nodes contained at any given Level.
	 * 
	 * @return
	 */
	private int treeWidth() {
		int max = 0;
		int height = height(1);
		for (int w = 0; w <= height; w++) {
			int tmp = treeWidth(1, w);
			if (tmp > max)
				max = tmp;
		}

		return max;
	}

	// Recursive till the desired depth is not achieved.
	private int treeWidth(int nodeIndex, int depth) {

		if (nodeIndex > bstArray.length || bstArray[nodeIndex] == Integer.MIN_VALUE) {
			return 0;
		}

		if (depth == 0) {
			return 1;
		}

		return treeWidth(2 * nodeIndex, depth - 1) + treeWidth(2 * nodeIndex + 1, depth - 1);
	}

	/**
	 * Print all the nodes at the K distance from the root node.
	 * 
	 * @param index
	 * @param level
	 */
	private void printKDistantNodes(int index, int level) {

		if (index > bstArray.length || bstArray[index] == Integer.MIN_VALUE) {
			return;
		}

		if (level == 0) {
			System.out.print(bstArray[index] + " ");
		} else {
			printKDistantNodes(2 * index, level - 1);
			printKDistantNodes(2 * index + 1, level - 1);
		}

	}

	/**
	 * Print the summary of the node been searched for illustration purpose
	 * 
	 * @param kIndex
	 */
	private void printNodeDetail(int kIndex) {

		int i = 0;
		System.out.println("---------Details of Node : " + bstArray[kIndex] + "----------------- ");

		if (bstArray[kIndex / 2] == Integer.MIN_VALUE) {
			System.out.println(++i + " This is Root Node");
		} else {
			System.out.println(++i + " Parent Node is : " + bstArray[kIndex / 2]);
		}

		if (kIndex % 2 == 0) {// Left Node
			if (bstArray[kIndex + 1] == Integer.MIN_VALUE) {
				System.out.println(++i + " Right sibling is Empty");
			} else {
				System.out.println(++i + " Right Sibling is : " + bstArray[kIndex + 1]);
			}
		} else { // Right Node
			if (bstArray[kIndex - 1] == Integer.MIN_VALUE) {
				System.out.println(++i + " Left sibling is Empty");
			} else {
				System.out.println(++i + " Left Sibling is : " + bstArray[kIndex - 1]);
			}
		}

		if (!(hasLeftChild(kIndex) && hasRightChild(kIndex))) {
			System.out.println(++i + " This is a leaf Node");
		} else {
			if (hasLeftChild(kIndex)) {
				System.out.println(++i + " Left child node : " + bstArray[2 * kIndex]);
			} else {
				System.out.println(++i + " Left child is null");
			}

			if (hasRightChild(kIndex)) {
				System.out.println(++i + " Right child node : " + bstArray[2 * kIndex + 1]);
			} else {
				System.out.println(++i + " Right child is null");
			}
		}

		int depth = getDepth(kIndex);
		System.out.println(++i + " Depth of Node from Root Node : " + depth);
		System.out.println(++i + " Level of Node is : " + (depth + 1));
		System.out.print(++i + " All Nodes at Level " + depth + " : ");
		printKDistantNodes(1, (depth));
		System.out.println(" ");

	}

	/**
	 * Depth is the count of edges to traverse from Root to the node. Root at
	 * level 2 is at index (2^2 + K). Hence a node at index is having (index =
	 * 2^2 + k). Formula is integer portion of log2(index).
	 * 
	 * @param kIndex
	 * @return
	 */
	private int getDepth(int kIndex) {
		int depth = ((int) (Math.log10(kIndex) / Math.log10(2)));
		return depth;
	}

	// If node is set to minimum means tree is empty
	private boolean isBinaryTreeExist() {
		return !(bstArray[1] == Integer.MIN_VALUE);
	}

	private void printBSTTopView(int index, boolean lFlag, boolean rFlag) {

		if (index > bstArray.length || bstArray[index] == Integer.MIN_VALUE) {
			return;
		}

		if (lFlag) {
			printBSTTopView(2 * index, true, false);
		}

		System.out.print(bstArray[index] + " ");

		if (rFlag) {
			printBSTTopView(2 * index + 1, false, true);
		}

	}

	private void printBSTTopView(int root) {
		printBSTTopView(root, true, false);
		printBSTTopView((2 * root + 1), false, true);
	}

	/**
	 * Heap-ify Input Array
	 * 
	 * @param arr
	 */
	public void heapify(int[] arr) {
		int size = arr.length - 1;
		for (int i = size / 2; i >= 0; i--) {
			siftUp(arr, i);
		}
	}

	private void siftUp(int[] arr, int i) {
		int size = arr.length - 1;
		int left = 2 * i;
		int right = 2 * i + 1;
		int large;
		if (left <= size && arr[left] > arr[i]) {
			large = left;
		} else {
			large = i;
		}

		if (right <= size && arr[right] > arr[large]) {
			large = right;
		}

		if (large != i) {
			int tmp = arr[i];
			arr[i] = arr[large];
			arr[large] = tmp;

			siftUp(arr, large);
		}
	}

	public static void main(String[] args) throws NoSuchFieldException {

		// int[] inputArr = { 11, 6, 8, 19, 4, 10, 5, 17, 43, 49, 31, 17, 11, 8,
		// 4, 10, 10, 10, 10, 10, 10, 4, 43, 49 };
		int[] inputArr = { 11, 6, 8, 19, 4, 10, 5, 43, 49, 31, 17 };
		final BinarySearchTree tree = new BinarySearchTree(inputArr.length);

		boolean flag = false;
		for (int i = 0; i < inputArr.length; i++) {
			flag = tree.insert(1, inputArr[i]);
			if (!flag) {
				System.out.println("Insert failure for " + inputArr[i]);
			}
		}

		System.out.println("\nBinary Tree Array : " + Arrays.toString(tree.bstArray));

		tree.heapify(inputArr);
		System.out.println(" After Heapify : " + Arrays.toString(inputArr));

		System.out.println("\n----------------Properties of Binary Tree---------------\n");
		System.out.println("\nHeight of Binary Tree: " + tree.height(1));
		System.out.println("\nTotal number of Leaf Nodes : " + tree.countLeafNode(1));
		System.out.println("\nWidth of the Binary Tree is : " + tree.treeWidth());

		System.out.println("\n\nDFS Traversal : In Order Traversal : ");
		tree.inOrderTraversal(1);

		System.out.println("\n\nDFS Traversal : Pre Order Traversal : ");
		tree.preOrderTraversal(1);

		System.out.println("\n\nDFS Traversal : Post Order Traversal : ");
		tree.postOrderTraversal(1);

		System.out.println("\n\nBFS Traversal : Level Order Traversal : ");
		tree.levelOrderTraversal(1);

		System.out.println("\n\nTop View of the BST : ");
		tree.printBSTTopView(1);

		int key = 0;
		Scanner scan = new Scanner(System.in);
		while (key != -1) {
			System.out.println("\n\nEnter -1 to exit........");
			System.out.println("\n\n Search the Key to be deleted : ");
			key = scan.nextInt();

			int kIndex = tree.search(key);

			if (tree.bstArray[kIndex] != Integer.MIN_VALUE) {
				System.out.println("Search Key found at array index : " + kIndex);
				tree.printNodeDetail(kIndex);
			} else {
				System.out.println("Key not found");
			}

			boolean delete = tree.delete(key);
			System.out.println("Delete Status : " + delete);
			tree.levelOrderTraversal(1);
		}
		scan.close();

	}

}