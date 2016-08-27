package graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Prim's Algorithm using adjacency List and Priority Queue
 * to find the minimum spanning tree.
 * 
 * 
 * 6 6
 * 1 2 100
 * 2 3 200
 * 3 4 300
 * 4 5 400
 * 5 6 500
 * 6 1 600
 * 1
 * 
 * Output: 1500
 * 
 * Complexity : O[(|V| + |E|)log|V|]
 * 
 * V = vertices
 * E = edges
 * LogV for extract each vertex from Queue
 * Done at most for V element, means VLogV
 * 
 * LogV to decrease the key value of neighboring vertex
 * Done at most for each edge, means ELogV
 * 
 * Total => VLogV + ELogV = (V + E)LogV
 * 
 * 
 * @author sumitkumar
 * 
 */
public class PrimsAlgoMST {

	private LinkedList<Node>[] graph; // An Array of Linked List for storing the graph.
	private Queue<Node> queue;// An implementation of Priority Queue, this would help to get the lowest cost path to the
								// currently visiting vertex
	private int[] dist; // An array to maintain the minimum distance
	private boolean[] visited;// An array to track the vertices that are visited.
	private int INFINITY = Integer.MAX_VALUE;

	class Node implements Comparable<Node> {
		int end;
		int wt;

		public Node(int end, int wt) {
			this.end = end;
			this.wt = wt;
		}

		@Override
		public int compareTo(Node node) {
			boolean flag = this.wt > node.wt;
			return flag ? 1 : -1; // return in the increasing order of weight
		}

		@Override
		public String toString() {
			return "E=" + end + ":" + "W=" + wt;
		}
	}

	@SuppressWarnings("unchecked")
	public PrimsAlgoMST(int vertex) {
		this.queue = new PriorityQueue<Node>();
		this.graph = new LinkedList[vertex + 1];
		this.visited = new boolean[vertex + 1];
		this.dist = new int[vertex + 1];

		for (int i = 1; i <= vertex; i++) {
			graph[i] = new LinkedList<Node>();
			dist[i] = INFINITY;
		}
	}

	public void addEdge(int st, int end, int wt) {
		graph[st].add(new Node(end, wt));
		graph[end].add(new Node(st, wt));
	}

	public void primsMST(int source) {
		queue.add(new Node(source, 0)); // the source will be added to the queue and its distance from itself is zero

		while (!queue.isEmpty()) {

			// Since we are using Priority Queue, the cheapest route will be available on the head of the queue.
			Node n = queue.poll();
			if (!visited[n.end] && dist[n.end] > n.wt) {
				visited[n.end] = true;
				dist[n.end] = n.wt;

				// Since we are following the cheapest route, so add all the vertices adjacent to the current vertex to
				// the priority queue.
				LinkedList<Node> list = graph[n.end];
				for (Node node : list) {
					queue.add(node);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();// vertices
		int M = scan.nextInt();// edges

		PrimsAlgoMST sol = new PrimsAlgoMST(N);

		for (int j = 0; j < M; j++) {
			sol.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
		}

		int source = scan.nextInt();

		sol.primsMST(source);

		long total = 0;
		for (int j = 1; j <= N; j++) {
			if (sol.dist[j] != sol.INFINITY)
				total += sol.dist[j];
		}

		System.out.println(total);
	}
}