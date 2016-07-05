package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/***
 * Kruskal's Algorithm for finding the MST.
 * 
 * 4 6
 * 1 2 5
 * 1 3 3
 * 4 1 6
 * 2 4 7
 * 3 2 4
 * 3 4 5
 * 1
 * 
 * Output: 12
 * 
 * @author sumitkumar
 * 
 */
public class KruskalMSTAlgo {

	private Queue<Edge> queue;
	private Map<Integer, Node> map;
	private long cost = 0;

	class Node {
		int val;
		Node parent;
		int rank;
		int size;

		public Node(int val) {
			this.val = val;
			this.parent = this;
			this.rank = 0;
		}
	}

	/**
	 * Make Disjoint Set of single element.
	 * 
	 * @param val
	 */
	public void makeSet(int val) {
		Node node = new Node(val);
		map.put(val, node);
	}

	/**
	 * Find the parent of the element.
	 * Perform path compression in the same process.
	 * 
	 * @param val
	 * @return
	 */
	public Node find(int val) {
		Node node = map.get(val);
		if (node.parent != node) {
			node.parent = find(node.parent.val);
		}
		return node.parent;
	}

	/**
	 * Perform Union of the element if they are in different set. Update the rank of the parent
	 * 
	 * @param i
	 * @param j
	 */
	public void union(int i, int j) {

		Node iParent = find(i);
		Node jParent = find(j);

		// Parent are same, so no union operation required.
		if (jParent == iParent) {
			return;
		} else {
			if (jParent.rank > iParent.rank) {
				iParent.parent = jParent;
			} else if (jParent.rank < iParent.rank) {
				jParent.parent = iParent;
			} else {
				jParent.parent = iParent;
				++iParent.rank;
			}
		}
	}

	/**
	 * Stores the Edge of the Graph
	 * 
	 * @author sumitkumar
	 * 
	 */
	class Edge implements Comparable<Edge> {
		int start;
		int end;
		int wt;

		public Edge(int start, int end, int wt) {
			this.start = start;
			this.end = end;
			this.wt = wt;
		}

		@Override
		public int compareTo(Edge node) {
			boolean flag = this.wt > ((Edge) node).wt;
			return flag ? 1 : -1; // V. IMP: return in the increasing order of weight
		}
	}

	public KruskalMSTAlgo(int vertex) {
		this.queue = new PriorityQueue<Edge>();
		map = new HashMap<Integer, Node>();
	}

	/**
	 * We will add the edge directly to Priority Queue. So it is always sorted as per the Weight of edge.
	 * We will also make the disjoint set for each vertex. This is for the find and union operations to avoid repeating
	 * an edge
	 * 
	 * @param st
	 * @param end
	 * @param wt
	 */
	public void addEdge(int st, int end, int wt) {
		// Make disjoint set for start and end vertex
		makeSet(st);
		makeSet(end);

		// Add the graph edge to the Priority Queue.
		queue.add(new Edge(st, end, wt));
	}

	/**
	 * 
	 * 
	 * @param source
	 */
	public void kruksalMST(int source) {

		// Source will be added with a weight of zero and start/end would be the source itself.
		queue.add(new Edge(source, source, 0));

		while (!queue.isEmpty()) {
			Edge node = queue.poll();

			// If the start and end vertex does not belong to the same set.
			// Than such as edge will be counted for the MST
			if (find(node.start) != find(node.end)) {
				union(node.start, node.end);
				cost += node.wt;
			}
		}
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // vertices
		int M = scan.nextInt(); // edges

		KruskalMSTAlgo sol = new KruskalMSTAlgo(N);

		for (int j = 0; j < M; j++) {
			sol.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
		}

		int source = scan.nextInt();

		sol.kruksalMST(source);

		System.out.println(sol.cost);
	}

}