package graphs;

import java.util.LinkedList;

/**
 * 
 * @author sumitkumar
 *
 */
public class DFS {

	private LinkedList<Integer>[] graph;
	private boolean[] visited;

	public DFS(int n) {
		graph = new LinkedList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int start, int end) {
		graph[start].add(end);
	}

	public void dfs(int start) {
		visited[start] = true;
		System.out.print(start + " ");

		for (int i = 0; i < graph[start].size(); i++) {
			if (visited[graph[start].get(i)] == false) {
				dfs(graph[start].get(i));
			}
		}
	}

	public static void main(String[] args) {
		DFS g = new DFS(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		g.dfs(2);

	}

}
