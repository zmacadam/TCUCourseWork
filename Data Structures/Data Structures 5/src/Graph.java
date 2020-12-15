import java.util.Arrays;

public class Graph<T> {
	//****************************************************************
	// Graph private data members
	//****************************************************************
	private int numVertices;
	private DLList<T>[] adjList;
	//****************************************************************
	// Default Constructor that creates the adjacency list
	//****************************************************************
	Graph(int vertices) {
		numVertices = vertices;
		adjList = new DLList[vertices];
		for (int i = 0; i < vertices; i++)
			adjList[i] = new DLList<T>();
	}
	//****************************************************************
	// Add edge method to populate the graph
	//****************************************************************
	public void addEdge(int src, T value) {
		adjList[src].InsertLast(value);
	}
	//****************************************************************
	// Returns the number of Vertices
	//****************************************************************
	public int getVertices() {
		return numVertices;
	}
	//****************************************************************
	// Returns the adjacency list representation of the graph
	//****************************************************************
	public DLList<T>[] getAdjList(){
		return adjList;
	}
}