package graphs;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class DijkastrasSingleSourceShortestPathAlgoImpl {
	private PriorityQueue<Node>[] graph;
	private int[] dist; // holds the calculated distances
	private static Queue<Integer> queue; // holds the vertex that are adjacent
											// to the current vertex.
	private int INFINITY = Integer.MAX_VALUE;

	/**
	 * Node to be comparable, as Priority Queue will sort it in order of minimum
	 * weight
	 * 
	 * @author sumitkumar
	 * 
	 */
	class Node implements Comparable<Node> {
		int end;
		int wt;

		public Node(int end, int wt) {
			this.end = end;
			this.wt = wt;
		}

		@Override
		public int compareTo(Node arg0) {
			boolean flag = this.end > ((Node) arg0).end;
			return flag ? -1 : 1;
		}
	}

	@SuppressWarnings("unchecked")
	public DijkastrasSingleSourceShortestPathAlgoImpl(int vertex) {
		queue = new LinkedList<Integer>();

		this.graph = new PriorityQueue[vertex + 1];
		this.dist = new int[vertex + 1];

		for (int i = 1; i <= vertex; i++) {
			graph[i] = new PriorityQueue<Node>();
			dist[i] = INFINITY;
		}
	}

	public void dijakstra(int source) {
		dist[source] = 0;
		while (!queue.isEmpty()) {
			int element = queue.poll();
			PriorityQueue<Node> list = graph[element];
			for (Node node : list) {
				if ((dist[element] + node.wt) < dist[node.end]) {
					dist[node.end] = dist[element] + node.wt;
					queue.add(node.end); // Node to consider for next iteration
				}
			}
		}
	}

	public void addEdge(int st, int end, int wt) {
		graph[st].add(new Node(end, wt));
		graph[end].add(new Node(st, wt));
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		for (int i = 0; i < T; i++) {

			int N = scan.nextInt();
			int M = scan.nextInt();

			DijkastrasSingleSourceShortestPathAlgoImpl sol = new DijkastrasSingleSourceShortestPathAlgoImpl(N);

			for (int j = 0; j < M; j++) {
				sol.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
			}

			int source = scan.nextInt();

			queue.add(source);
			sol.dijakstra(source);

			for (int j = 1; j <= N; j++) {
				if (sol.dist[j] != 0) {
					if (sol.dist[j] == sol.INFINITY) {
						System.out.print(-1 + " ");
					} else {
						System.out.print(sol.dist[j] + " ");
					}
				}
			}
			System.out.println("");
		}
	}
}