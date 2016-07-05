package trees;

public class AVLTreeOperations {

	static class Node {
		int val; // Value
		int ht; // Height
		Node left; // Left child
		Node right; // Right child
	}

	static Node leftRotation(Node root) {
		// Get Right Node as New Parent
		Node nPar = root.right;
		Node temp = nPar.left;

		nPar.left = root;
		root.right = temp;

		nPar.ht = height(nPar);
		root.ht = height(root);
		return nPar;
	}

	/**
	 * Symmetric to Left Rotation.
	 * 
	 * @param root
	 * @return
	 */
	static Node rightRotation(Node root) {
		Node nPar = root.left;
		Node temp = nPar.right;

		nPar.right = root;
		root.left = temp;

		nPar.ht = height(nPar);
		root.ht = height(root);
		return nPar;
	}

	static int balancefactor(Node root) {

		if (root == null) {
			return 0;
		}

		// -1 <= BF <= 1
		return height(root.left) - height(root.right);
	}

	static int height(Node root) {
		if (root == null)
			return -1;

		// Leaf Nodes have height Zero
		if (root.left == null && root.right == null)
			return 0;

		// Height = Max(height(left), height(right)) + 1
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	static Node insert(Node root, int val) {
		if (root == null) {
			root = new Node();
			root.val = val;
			return root;
		}

		// Node node;
		if (root.val > val) {
			root.left = insert(root.left, val);
		} else {
			root.right = insert(root.right, val);
		}

		// Calculate Height
		root.ht = height(root);
		int balance = balancefactor(root);

		if (balance > 1) { // Left inclined, Hence Right Rotations
			// left
			if (val < root.left.val) {
				// right rotation
				return rightRotation(root);
			} else {
				// double rotation
				root.left = leftRotation(root.left);
				return rightRotation(root);
			}
		}

		if (balance < -1) { // Right Inclined, Hence Left Rotation
			// right
			if (val > root.right.val) {
				// left rotation
				return leftRotation(root);
			} else {
				// double rotation
				root.right = rightRotation(root.right);
				return leftRotation(root);
			}
		}

		return root;

	}

}
