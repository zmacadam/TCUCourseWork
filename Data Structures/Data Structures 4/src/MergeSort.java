import java.text.DecimalFormat;
import java.util.Arrays;

public class MergeSort {
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
	MergeSort(String[] a) {
		String[] unsorted = new String[a.length];
		System.arraycopy(a, 0, unsorted, 0, a.length);
		long startTime = System.nanoTime();
		sort(unsorted, unsorted.length);
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
		System.out.format("%-16s%-2s%-14s%-2s%-13s%-2s%-16s\n", "Merge Sort", "|", compare, "|", swaps, "|", df.format(seconds));
	}
	//********************************************************************
	// Sort Method
	//
	// The sort method recursively splits the original array into subarrays
	// until all subarrays are of size 1.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	// n		int				value		Size of the new array.
	//
	// Local Variables
	// ---------------
	// mid		int			Index of the middle of the array.
	// l		String[]	Left sub array.
	// r		String[]	Right sub array.
	// i		int			Loop iteration variable.
	//
	//*****************************************************************
	private void sort(String[] a, int n) {
		if (n < 2)
			return;
		int mid = n / 2;
		String[] l = new String[mid];
		String[] r = new String[n - mid];
		for (int i = 0; i < mid; i++) 
			l[i] = a[i];
		for (int i = mid; i < n; i++)
			r[i - mid] = a[i];
		sort(l, mid);
		sort(r, n - mid);
		
		merge(a, l, r, mid, n - mid);
	}
	//********************************************************************
	// Merge Method
	//
	// The merge method sorts and merges all sub arrays back into one sorted
	// array.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Original array to be sorted.
	// l		String[]		value		Left sub array.
	// r		String[]		value		Right sub array.
	// left		int				value		Size of the left sub array.
	// right	int				value		Size of the right sub array.
	//
	// Local Variables
	// ---------------
	// n		int			Length of the array.
	// i		int			Loop iteration variable.
	// temp		String		Data value place holder.
	// j		int			Loop iteration variable.
	//
	//*****************************************************************
	private void merge(String[] a, String[] l, String[] r, int left, int right) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			compare++;
			if (l[i].compareTo(r[j]) < 0) {
				a[k++] = l[i++];
				swaps++;
			}
			else {
				a[k++] = r[j++];
				swaps++;
			}
		}
		while (i < left) {
			a[k++] = l[i++];
			swaps++;
		}
		while (j < right) {
			a[k++] = r[j++];
			swaps++;
		}
	}
}
