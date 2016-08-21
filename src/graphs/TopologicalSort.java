package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Topological using DFS.
 * 
 * https://en.wikipedia.org/wiki/Topological_sorting
 * 
 * @author sumitkumar
 * 
 */
class TopologicalSort {

	private char[] visited; // 'u' = unvisited, 'v' = visited, 'p'= processing
	private ArrayList<Integer>[] adjList;
	private Stack<Integer> result = new Stack<Integer>();

	@SuppressWarnings("unchecked")
	public TopologicalSort(int node) {

		visited = new char[node + 1];
		adjList = new ArrayList[node + 1];

		for (int i = 1; i <= node; i++) {
			adjList[i] = new ArrayList<Integer>();
			visited[i] = 'u';
		}

	}

	public void addNode(int start, int end) {
		adjList[start].add(end);
	}

	public void visit(int node) {
		// If a node is in processing state, means it is not an acyclic graph. Topological sort not possible in this
		// case.
		if (visited[node] == 'p') {
			throw new IllegalArgumentException("Not a Directed Acyclic Graph");
		}

		// If node is unvisited, then visit the node and mark it as processing
		if (visited[node] == 'u') {
			visited[node] = 'p';

			// visit all the edges from the node
			List<Integer> list = adjList[node];
			for (Integer end : list) {
				visit(end);
			}

			visited[node] = 'v';

			// All dependencies visited. Add the node to result
			result.add(node);
		}
	}

	public void tSort() {
		// Visit all node with status as unvisited.
		for (int i = 1; i < visited.length; i++) {
			if (visited[i] == 'u') {
				visit(i);
			}
		}

		// Print the result
		while (!result.isEmpty()) {
			System.out.print(result.pop() + " ");
		}

	}

	public static void main(String[] args) {

		TopologicalSort sort = new TopologicalSort(3);
		sort.addNode(1, 2);
		sort.addNode(3, 1);
		sort.addNode(3, 2);

		// sort.addNode(6, 3);
		// sort.addNode(6, 1);
		// sort.addNode(5, 1);
		// sort.addNode(5, 2);
		// sort.addNode(3, 4);
		// sort.addNode(4, 2);
		// 6 5 4 2 3 1
		sort.tSort();

	}

}