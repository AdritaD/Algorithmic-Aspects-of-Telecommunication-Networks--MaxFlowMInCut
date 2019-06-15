import java.util.LinkedList;
import java.util.Queue; 

public class Assignment2 { 

	private static boolean bfs(int[][] ResidualGraph, int source, 
								int destination, int[] parent) { 

		boolean[] visited = new boolean[ResidualGraph.length];
		Queue <Integer> Queue_Path = new LinkedList<Integer>(); 
		Queue_Path.add(source); 
		visited[source] = true; 
		parent[source] = -1; 

		while (!Queue_Path.isEmpty()) { 
			int node2 = Queue_Path.poll(); 
			for (int i = 0; i < ResidualGraph.length; i++) 
			{ 
				if (ResidualGraph[node2][i] > 0 && !visited[i]) 
				{ 
					Queue_Path.offer(i); 
					visited[i] = true; 
					parent[i] = node2; 
				} 
			} 
		}
		return (visited[destination] == true); 
	} 


	private static void dfs(int[][] ResidualGraph, int source, 
								boolean[] visited) 
	{ 
		visited[source] = true; 
		for (int i = 0; i < ResidualGraph.length; i++) 
		{ 
				if (ResidualGraph[source][i] > 0 && !visited[i]) 
				{ 
					dfs(ResidualGraph, i, visited); 
				} 
		} 
	} 


	private static void GraphMinCut(int[][] OriginalGraph, int source, int destination) { 
		int node1,node2;
		int[][] ResidualGraph = new int[OriginalGraph.length][OriginalGraph.length]; 
		for (int i = 0; i < OriginalGraph.length; i++) 
		{ 
			for (int j = 0; j < OriginalGraph.length; j++) 
			{ 
				ResidualGraph[i][j] = OriginalGraph[i][j]; 
			} 
		} 

		int[] parent = new int[OriginalGraph.length]; 

		while (bfs(ResidualGraph, source, destination, parent)) 
		{ 

			int pathFlow = Integer.MAX_VALUE;		 
			for (node2 = destination; node2 != source; node2 = parent[node2]) 

			{ 
				node1 = parent[node2]; 
				pathFlow = Math.min(pathFlow, ResidualGraph[node1][node2]); 
			} 

			for (node2 = destination; node2 != source; node2 = parent[node2]) 
			{ 
				node1 = parent[node2]; 
				ResidualGraph[node1][node2] = ResidualGraph[node1][node2] - pathFlow; 
				ResidualGraph[node2][node1] = ResidualGraph[node2][node1] + pathFlow; 
			} 
		} 

		boolean[] isVisited = new boolean[OriginalGraph.length];	 
		dfs(ResidualGraph, source, isVisited); 

		for (int i = 0; i < OriginalGraph.length; i++) 
		{ 
			for (int j = 0; j < OriginalGraph.length; j++) 
			{ 
				if (OriginalGraph[i][j] > 0 && isVisited[i] && !isVisited[j]) 
				{ 
					System.out.println(i + " - " + j); 
				} 
			} 
		} 
	} 



    public static int GraphMaxFlow(int[][] OriginalGraph, int source, int destination) 
    { 
        int node1, node2;
        int[][] ResidualGraph = new int[OriginalGraph.length][OriginalGraph.length]; 

        for (int i = 0; i < OriginalGraph.length; i++) 
        { 
			for (int j = 0; j < OriginalGraph.length; j++) 
			{ 
				ResidualGraph[i][j] = OriginalGraph[i][j]; 

			}
		}
        int[] parent = new int[OriginalGraph.length]; 
  
        int max_flow = 0;

        while (bfs(ResidualGraph, source, destination, parent)) 
        { 

            int FlowPath = Integer.MAX_VALUE; 
            for (node2=destination; node2!=source; node2=parent[node2]) 
            { 
                node1 = parent[node2]; 
                FlowPath = Math.min(FlowPath, ResidualGraph[node1][node2]); 
            } 

            for (node2=destination; node2 != source; node2=parent[node2]) 
            { 
                node1 = parent[node2]; 
                ResidualGraph[node1][node2] -= FlowPath; 
                ResidualGraph[node2][node1] += FlowPath; 
            } 

            max_flow += FlowPath; 
        } 
        return max_flow;
    }


	public static void main(String args[]) 
	{ 
		

		int OriginalGraph_given[][] = {{0, 4, 7, 0, 6, 0},
				{0, 0, 0, 0, 0, 4},
				{0, 0, 0, 7, 0, 0},
				{0, 0, 0, 0, 0, 7},
				{0, 0, 0, 0, 0, 6},
				{0, 0, 0, 0, 0, 0}};

		int OriginalGraph_max[][] = {{0, 4, 7, 0, 14, 0},
				{0, 0, 0, 2, 0, 10},
				{0, 2, 0, 10, 2, 0},
				{0, 0, 0, 0, 0, 7},
				{0, 0, 0, 2, 0, 6},
				{0, 0, 0, 0, 0, 0}};
	    		
	    System.out.println(" ");
		System.out.println(" ");
	    System.out.println("The maximum possible flow for given flow is: " + GraphMaxFlow(OriginalGraph_given, 0, 5)); 
	    System.out.println("The edges in min cut for given flow are: ");
		GraphMinCut(OriginalGraph_given,0, 5); 
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("The actual maximum possible flow is: " + GraphMaxFlow(OriginalGraph_max, 0, 5)); 
		System.out.println("The edges in min cut are: ");
		GraphMinCut(OriginalGraph_max, 0, 5); 
		
  

	};
		

	
} 

