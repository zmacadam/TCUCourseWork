import java.text.DecimalFormat;
import java.util.Arrays;

public class SelectionSort {
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
	SelectionSort(String[] a) {
		String[] unsorted = new String[a.length];
		System.arraycopy(a, 0, unsorted, 0, a.length);
		long startTime = System.nanoTime();
		sort(unsorted);
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
		System.out.format("%-16s%-2s%-14s%-2s%-13s%-2s%-16s\n", "Selection Sort", "|", compare, "|", swaps, "|", df.format(seconds));
	}
	//********************************************************************
	// Sort Method
	//
	// The sort method performs selection sort on the unsorted array.
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
	// least	int			Index of the least value of the array.
	// j		int			Loop iteration variable.
	// temp		String		Data value place holder.
	//
	//*****************************************************************
	private void sort(String[] a) {
		int n = a.length;
		for (int i = 0; i < n - 1; i ++) {
			int least = i;
			for (int j = i + 1; j < n; j++) {
				compare++;
				if (a[j].compareTo(a[least]) < 0)
					least = j;
			}
			String temp = a[least];
			a[least] = a[i];
			a[i] = temp;
			swaps++;
		}
	}
}
