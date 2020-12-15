import java.text.DecimalFormat;
import java.util.Arrays;

public class Quicksort {
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
	Quicksort(String[] a) {
		String[] unsorted = new String[a.length];
		System.arraycopy(a, 0, unsorted, 0, a.length);
		long startTime = System.nanoTime();
		sort(unsorted, 0, unsorted.length - 1);
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
		System.out.format("%-16s%-2s%-14s%-2s%-13s%-2s%-16s\n", "Quicksort", "|", compare, "|", swaps, "|", df.format(seconds));
	}
	//********************************************************************
	// Sort Method
	//
	// The sort method performs quicksort on the unsorted array by recursively
	// sorting and partitioning until the array can no longer be sorted.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	// int		low				value		Beginning of the partition.
	// int		high			value		End of the partition.
	//
	// Local Variables
	// ---------------
	// index	int		Value returned from the partition method.
	//
	//*****************************************************************
	private void sort(String[] a, int low, int high) {
		if (low < high) {
			compare++;
			int index = partition(a, low, high);
			sort(a, low, index - 1);
			sort(a, index + 1, high);
		}
	}
	//********************************************************************
	// Partition Method
	//
	// The partition method rearranges the array so that all elements
	// less than or equal to the pivot are to the left and all elements
	// greater than the pivot are to the right.
	// 	
	// Return Value	
	// ------------
	// int		Value of the pivot in its correct position.
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	// low		int				value		Beginning of the partition
	// high		int				value		End of the partition
	//
	// Local Variables
	// ---------------
	// pivot	String		Element to compare the array to.
	// i		int			Index place holder.
	// j 		int			Loop iteration variable.
	// temp		String		Data value place holder.
	// 
	//*****************************************************************
	private int partition(String[] a, int low, int high) {
		String pivot = a[high];
		int i  = (low - 1);
		for (int j = low; j < high; j++) {
			compare++;
			if (a[j].compareTo(pivot) <= 0) {
				i++;
				String temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				swaps++;
			}
		}
		String temp = a[i + 1];
		a[i + 1] = a[high];
		a[high] = temp;
		swaps++;
		
		return i + 1;
	}
}
