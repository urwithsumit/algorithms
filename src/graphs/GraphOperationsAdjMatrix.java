package graphs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphOperationsAdjMatrix {
	private int[][] adjMatrix;
	private boolean[] visitArr;
	private int nodeCount;

	public GraphOperationsAdjMatrix(int nodes) {
		this.nodeCount = nodes;
		this.adjMatrix = new int[nodeCount + 1][nodeCount + 1];
		this.visitArr = new boolean[nodeCount + 1];

		for (int i = 0; i <= nodeCount; i++) {
			for (int j = 0; j <= nodeCount; j++) {
				adjMatrix[i][j] = Integer.MAX_VALUE;
				adjMatrix[j][i] = Integer.MAX_VALUE;
			}// end of for
		}// end of for
	}// end of constructor

	private int getAdjacentNodes(int node) {
		for (int i = 0; i <= nodeCount; i++) {
			if (adjMatrix[node][i] == 1 && visitArr[i] == false) {
				return i;
			}
		} // end of for
		return -1;
	}// end of getAdjacentNodes

	public void bfs(int startNode) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visitArr[startNode] = true;
		queue.add(startNode);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			System.out.print(node + " ");
			int next = -1;
			while ((next = getAdjacentNodes(node)) != -1) {
				visitArr[next] = true;
				queue.add(next);
			}// end of while 2
		}// end of while 1

	}// end of bfs

	public void dfs(int startNode) {
		visitArr[startNode] = true;
		System.out.print(startNode + " ");
		int next = -1;
		while ((next = getAdjacentNodes(startNode)) != -1) {
			visitArr[next] = true;
			dfs(next);
		}// end of while 1

	}// end of dfs

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n Enter total number of nodes : ");
		int nodeCount = scan.nextInt();

		System.out.println("\n\n Enter the number of edges :");
		int edgeCount = scan.nextInt();

		System.out.println("\n\n Is it a bi-directional Graph(true/false): ");
		boolean bidi = scan.nextBoolean();
		System.out.println("bidi: " + bidi);
		GraphOperationsAdjMatrix graph = new GraphOperationsAdjMatrix(nodeCount);

		for (int i = 0; i <= edgeCount; i++) {
			System.out.println("Enter Start Node and end Node");
			int sNode = scan.nextInt();
			int eNode = scan.nextInt();

			graph.adjMatrix[sNode][eNode] = 1;
			if (bidi) {
				graph.adjMatrix[eNode][sNode] = 1;
			}
		}// end of for

		int exit = -1;
		while (exit != 1) {
			System.out.println("\n\n Do BFS Traversal starting at node: ");
			System.out.println("\n\n Enter start node: ");
			graph.bfs(scan.nextInt());
			Arrays.fill(graph.visitArr, false);
			System.out.println("\n\n More of BFS. Press 1 to exit... :" + scan.nextInt());
			System.out.println(" ");
		}

		exit = -1;
		while (exit != 1) {
			Arrays.fill(graph.visitArr, false);
			System.out.println("\n\n Do DFS Traversal starting at node: ");
			System.out.println("\n\n Enter start node: ");
			graph.dfs(scan.nextInt());
			System.out.println("\n\n More of DFS. Press 1 to continue... :" + scan.nextInt());
			System.out.println(" ");
		}

		scan.close();

	}// end of main

}// end of class
