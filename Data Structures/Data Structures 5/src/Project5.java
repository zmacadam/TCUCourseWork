import java.util.Scanner;
import java.util.StringTokenizer;

public class Project5 {
	//********************************************************************
	// Main Method
	//
	// The main method "drives" the program by reading the System input and 
	// determining the appropriate amount of lines to read while creating
	// a directed acyclical graph.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// args			String[]	value		Array of possible arguments when program is executed.
	//
	// Local Variables
	// ---------------
	// sc			Scanner				Scanner used to get the System input.
	// numNodes		int					Number of nodes in the graph
	// dag			Graph				Graph to be created and sorted.
	// st			StringTokenizer		StringTokenizer used to created adjacency list.
	// ts			TopologicalSort		Instance of TopologicalSort class.
	// 
	//*****************************************************************
	public static void main(String[] args) {
		int numNodes = 0;
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextLine())
			numNodes = Integer.parseInt(sc.nextLine());
		Graph dag = new Graph(numNodes);
		for (int i = 0; i < numNodes; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			while (st.hasMoreTokens())
				dag.addEdge(i, st.nextToken());
		}
		TopologicalSort ts = new TopologicalSort(dag);
		ts.printList();
	}
}
