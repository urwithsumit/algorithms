package graphs;

/**
 * the Floyd–Warshall algorithm is an algorithm for finding shortest paths in a weighted graph with positive or negative
 * edge weights (but with no negative cycles). A single execution of the algorithm will find the lengths (summed
 * weights) of the shortest paths between all pairs of vertices, though it does not return details of the paths
 * themselves
 * 
 * https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm
 * 
 * @author sumitkumar
 * 
 */
public class FloydWarshalAlgo {
	private int[][] graph;
	private int vertices;
	private Integer[][] next; // For path construction
	private int INFINITY = 99999;

	public FloydWarshalAlgo(int v) {
		vertices = v;
		graph = new int[v + 1][v + 1];
		next = new Integer[v + 1][v + 1];

		for (int i = 0; i <= vertices; i++) {
			for (int j = 0; j <= vertices; j++) {
				graph[i][j] = INFINITY;
				next[i][j] = null;
				// graph[j][i] = Integer.MAX_VALUE;
			}
		}
	}

	public void addEdgeWt(int u, int v, int wt) {
		graph[u][v] = wt;
		// graph[c][r] = wt;
		next[u][v] = v;
	}

	/**
	 * Algorithm implementation
	 * Complexity : O(n^3)
	 */
	public void floydWarshalAlgo() {

		for (int k = 1; k <= vertices; k++) {
			for (int i = 1; i <= vertices; i++) {
				for (int j = 1; j <= vertices; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
	}

	/**
	 * one can inspect the diagonal of the path matrix, and the presence of a negative number indicates that the graph
	 * contains at least one negative cycle.
	 * 
	 * @return
	 */
	public boolean negativeCycleTest() {

		for (int i = 1; i <= vertices; i++) {
			if (graph[i][i] < 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Prints the Graph in matrix format.
	 */
	public void printDistanceMatrix() {
		for (int i = 1; i <= vertices; i++) {
			for (int j = 1; j <= vertices; j++) {
				if (graph[i][j] >= INFINITY) {
					System.out.print("I" + " ");
				} else {
					System.out.print(graph[i][j] + " ");
				}

			}
			System.out.println(" ");
		}
	}

	/**
	 * To print the shortest path Matrix.
	 */
	public void printPathMatrix() {
		for (int i = 1; i <= vertices; i++) {
			for (int j = 1; j <= vertices; j++) {
				System.out.print(next[i][j] + " ");
			}

			System.out.println(" ");
		}
	}

	/**
	 * Prints the Shortest path between 2 vertices.
	 */
	public void printShortestPath() {
		for (int i = 1; i <= vertices; i++) {
			for (int j = 1; j <= vertices; j++) {
				pathPrint(i, j);
				System.out.println(" ");
			}

			System.out.println(" ");
		}
	}

	/**
	 * Prints the path between vertices u & v
	 * 
	 * @param u
	 * @param v
	 */
	public void pathPrint(int u, int v) {
		System.out.print("Shortest Path Between " + u + " & " + v + " : ");

		if (next[u][v] == null)
			return;

		StringBuilder result = new StringBuilder();
		result.append(u);
		while (u != v) {
			result.append("->");
			u = next[u][v];
			result.append(u);
		}

		System.out.print(result.toString());
	}

	public static void main(String[] args) {

		FloydWarshalAlgo fwalgo = new FloydWarshalAlgo(5);

		fwalgo.addEdgeWt(1, 3, 6);
		fwalgo.addEdgeWt(3, 4, 2);
		fwalgo.addEdgeWt(4, 3, 1);
		fwalgo.addEdgeWt(1, 4, 3);
		// fwalgo.addEdgeWt(1, 4, -5); // For negative cycle test
		fwalgo.addEdgeWt(2, 1, 3);
		fwalgo.addEdgeWt(4, 2, 1);
		fwalgo.addEdgeWt(5, 4, 2);
		fwalgo.addEdgeWt(5, 2, 4);

		System.out.println("INPUT.....");
		fwalgo.printDistanceMatrix();
		System.out.println("");

		fwalgo.floydWarshalAlgo();

		System.out.println("OUTPUT.....");
		fwalgo.printDistanceMatrix();
		System.out.println("");

		System.out.println("Does Graph has a negative Cycle: " + fwalgo.negativeCycleTest());

		System.out.println("");
		System.out.println("Resultant Path Matrix....");
		fwalgo.printPathMatrix();
		System.out.println("");

		System.out.println("Print Path: ");
		fwalgo.printShortestPath();

	}

}
