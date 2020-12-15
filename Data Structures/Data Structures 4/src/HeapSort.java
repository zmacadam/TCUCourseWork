import java.text.DecimalFormat;
import java.util.Arrays;

public class HeapSort {
	//****************************************************************
	// Private Data Members
	//****************************************************************
	private long compare = 0, swaps = 0;
	private double seconds;
	//********************************************************************
	// Default Constructor
	//
	// The default constructor creates a copy of the array to be sorted,
	// gets the start time, runs the sorting algorithm, gets the end time,
	// and calculates the overall time taken.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a			String[]		value		Array to be sorted.
	//
	// Local Variables
	// ---------------
	// unsorted		String[]		Copy of array to be sorted.
	// startTime	long			Time before the algorithm starts.
	// endTime		long			Time after the algorithm ends.
	// elapsedTime	long			Total time taken during algorithm.
	// seconds		double			Time taken converted to seconds.
	//
	//*****************************************************************
	HeapSort(String[] a) {
		long startTime = System.nanoTime();
		sort(a);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		seconds = (double)elapsedTime/ 1000000000.0;
	}
	//********************************************************************
	// Print Row Method
	//
	// The print row method takes the number of data comparisons,
    // number of data movements, and time taken by the algorithm
	// and prints to the console in a formatted output.
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
	// df		DecimalFormat		Decimal Format to the 10th place.
	//
	//*****************************************************************
	public void printRow() {
		DecimalFormat df = new DecimalFormat("#.0000000000");
		System.out.format("%-16s%-2s%-14s%-2s%-13s%-2s%-16s\n", "Heap Sort", "|", compare, "|", swaps, "|", df.format(seconds));
	}
	//********************************************************************
	// Sort Method
	//
	// The sort method performs heap sort on the unsorted array by first
	// building a max heap and then sorting the heap.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	//
	// Local Variables
	// ---------------
	// n		int			Length of the array.
	// i		int			Loop iteration variable.
	// temp		String		Data value place holder.
	//
	//*****************************************************************
	private void sort(String[] a) {
		int n = a.length;
		for (int i = (n/2) - 1; i >= 0; i--)
			heapify(a, n, i);
		for (int i = n-1; i >= 0; i--) {
			String temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			swaps++;
			heapify(a, i, 0);
		}
	}
	//********************************************************************
	// Heapify Method
	//
	// The heapify method creates a heap from the given array.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	// n		int				value		Size of the array.
	// i		int				value		Node index to be compared.
	//
	// Local Variables
	// ---------------
	// largest		int			Value of the largest node element.
	// left			int			Position of the left node in the array.
	// right		int			Position of the right node in the array.
	// temp			String		Data value place holder.
	//
	//*****************************************************************
	private void heapify(String[] a, int n, int i) {
		int largest = i;
		int left = (2*i) + 1;
		int right = (2*i) + 2;
		compare++;
		if (left < n && a[left].compareTo(a[largest]) > 0)
			largest = left;
		compare++;
		if (right < n && a[right].compareTo(a[largest]) > 0)
			largest = right;
		if (largest != i) {
			String temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			swaps++;
			heapify(a, n, largest);
		}
	}
}
