package disjointset;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic Implementation for the Disjoint Union Set data structure with node ranking.
 * 
 * @author sumitkumar
 * 
 * @param <S>
 */
public class DisjointUnionSet<S extends Comparable<S>> {

	private Map<S, Node<S>> map = new HashMap<S, Node<S>>();

	class Node<T extends Comparable<T>> {
		T val;
		Node<T> parent;
		int rank;

		public Node(T val) {
			this.val = val;
			rank = 0;
			this.parent = this;
		}

		public String toString() {

			return String.valueOf(parent.val);

		}
	}

	/**
	 * function MakeSet(x)
	 * x.parent := x
	 * 
	 * @param val
	 */
	public void makeSet(S val) {
		Node<S> node = new Node<S>(val);
		map.put(val, node);
	}

	/**
	 * Path Compression algorithm: all nodes will connect directly to root,
	 * thus avoiding the overhead of traversal.
	 * 
	 * function Find(x)
	 * if x.parent != x
	 * x.parent := Find(x.parent)
	 * return x.parent
	 * 
	 * @param val
	 * @return
	 */
	public Node<S> find(S val) {
		Node<S> node = map.get(val);

		if (node.parent != node) {
			node.parent = find(node.parent.val);
		}

		return node.parent;
	}

	/**
	 * function Union(x, y)
	 * xRoot := Find(x)
	 * yRoot := Find(y)
	 * xRoot.parent := yRoot
	 * 
	 * if xRoot.rank < yRoot.rank
	 * xRoot.parent := yRoot
	 * else if xRoot.rank > yRoot.rank
	 * yRoot.parent := xRoot
	 * else
	 * yRoot.parent := xRoot
	 * xRoot.rank := xRoot.rank + 1
	 * 
	 * @param x
	 * @param y
	 */

	public void union(S x, S y) {

		Node<S> xNode = find(map.get(x).val);
		Node<S> yNode = find(map.get(y).val);

		// Both have same parent hence part of same set. Therefore no action required.
		if (xNode == yNode) {
			return;
		} else {
			// Merge the parents into a single rooted parent.
			if (xNode.rank < yNode.rank) {
				xNode.parent = yNode;
			} else if (xNode.rank > yNode.rank) {
				yNode.parent = xNode;
			} else {
				// Node have same rank, hence Rank will increase by 1.
				yNode.parent = xNode;
				++xNode.rank;
			}
		}
	}

	public static void main(String[] args) {
		DisjointUnionSet<Integer> ds = new DisjointUnionSet<Integer>();
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);

		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(5, 6);
		ds.union(3, 7);

		System.out.println(ds.find(1));
		System.out.println(ds.find(2));
		System.out.println(ds.find(3));
		System.out.println(ds.find(4));
		System.out.println(ds.find(5));
		System.out.println(ds.find(6));
		System.out.println(ds.find(7));
	}

}
