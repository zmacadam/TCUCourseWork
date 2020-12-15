import java.util.Arrays;

public class TopologicalSort<T> {
	//****************************************************************
	// TopologicalSort private data members
	//****************************************************************
	private int numVertices;
	private DLList<T>[] adjList;
	private int time;
	private String[] startIndex;
	private String[] color;
	private String [] pi;
	private String[] startTime;
	private String[] finishTime;
	private DLList<T> sorted;
	//****************************************************************
	// Default Constructor that initializes the arrays needed
	//****************************************************************
	TopologicalSort(Graph g) {
		numVertices = g.getVertices();
		adjList = g.getAdjList();
		for (int i = 0; i < numVertices; i++) {
			startIndex = new String[numVertices];
			color = new String[numVertices];
			pi = new String[numVertices];
			startTime = new String[numVertices];
			finishTime = new String[numVertices];
			DFS(g);
		}
	}
	//********************************************************************
	// DFS
	//
	// The DFS method performs a Depth First Search on the graph.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// g			Graph			g			Graph to perform DFS on.
	//
	// Local Variables
	// ---------------
	// verName		String		Name of current node
	// i			int			Loop iteration variable.
	// u			int			Loop iteration variable.
	// 
	//*****************************************************************
	public void DFS(Graph g) {
		sorted = new DLList();
		for (int i = 0; i < numVertices; i++) {
			adjList[i].First();
			String verName = (String) adjList[i].DataRead();
			color[i] = "white";
			pi[i] = null;
			startIndex[i] = verName;
		}
		time = 0;
		for (int u = 0; u < numVertices; u++) {
			if (color[u].equals("white"))
				DFSVisit(g, u);
		}
	}
	//********************************************************************
	// DFSVisit
	//
	// The DFS Visit method checks the surrounding nodes and visits them
	// if they have not yet been visited.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// g		Graph		value		Graph that is being Depth First Searched.
	// u		int			value		Index of the current node.
	//
	// Local Variables
	// ---------------
	// vertex		String		Name of node(s) adjacent to "u".
	// v			int			Index of node adjacent to node "u".
	// i 			int			Loop iteration variable.
	// 
	//*****************************************************************
	private void DFSVisit(Graph g, int u) {
		time = time + 1;
		startTime[u] = time +"";
		color[u] = "gray";
		for (int i = 1; i <= adjList[u].Size(); i++) {
			adjList[u].Seek(i);
			String vertex = (String) adjList[u].DataRead();
			int v = Arrays.asList(startIndex).indexOf(vertex);
			if (color[v] == "white") {
				pi[v] = color[u];
				DFSVisit(g, v);
			}
		}
		color[u] = "black";
		time = time + 1;
		finishTime[u] = time +"";
		sorted.InsertFirst(adjList[u].DataRead());
	}
	//********************************************************************
	// Print List Method
	//
	// The print list method outputs a table of the sorted DAG as well as 
	// the start and finish time. The is dynamically sized based on the input.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Local Variables
	// ---------------
	// node			String		String containing sorted nodes.
	// start		String		String containing sorted start times.
	// finish		String		String containing sorted finish times.
	// filler		String		String to create a neat tabulated output.
	// i			int			Loop iteration variable.
	// index		int			Index of current node.
	// nodeName		String		Name of current node.
	// colWidth		int			Width of table column.
	// repeat		char[]		Array of characters used to make neat table.
	// startCol		int			Width of start time table column.
	// finishCol	int			Width of finish time table column.
	// numSpaces1	int			Spacing within table column.
	// numSpaces2	int			Spacing within table column.
	// startSpaces	String		Spaces needed to format table.
	// finishSpaces	String		Spaces needed to format table.
	// 	
	//*****************************************************************
	public void printList() {
		String node = "NODE        ";
		String start = "START TIME  ";
		String finish = "FINISH TIME ";
		String filler = "------------";
		sorted.First();
		for (int i = 0; i < sorted.Size(); i++) {
			int index = Arrays.asList(startIndex).indexOf(sorted.DataRead());
			String nodeName = (String) sorted.DataRead();
			nodeName.trim();
			int colWidth = nodeName.length();
			node = node + "|" + " " + sorted.DataRead() + " ";
			char[] repeat = new char[colWidth + 2];
			Arrays.fill(repeat, '-');
			filler = filler + "+" + new String(repeat);
			int startCol = String.valueOf(startTime[index]).length();
			int finishCol = String.valueOf(finishTime[index]).length();
			int numSpaces1 = colWidth - startCol + 1;
			int numSpaces2 = colWidth - finishCol + 1;
			if (numSpaces1 > 0) {
				String startSpaces = String.format("%1$"+numSpaces1+"s", "");
				start = start + "|" + " " + startTime[index] + startSpaces;
			}
			else
				start = start + "|" + startTime[index] + " ";
			if (numSpaces2 > 0) {
				String finishSpaces = String.format("%1$"+numSpaces2+"s", "");
				finish = finish  + "|" + " " + finishTime[index] + finishSpaces;
			}
			else
				finish = finish + "|" + finishTime[index]+ " ";
			sorted.Next();
		}
		System.out.println(node);
		System.out.println(filler);
		System.out.println(start);
		System.out.println(filler);
		System.out.println(finish);
	}
}
