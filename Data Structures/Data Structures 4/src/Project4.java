import java.util.Scanner;

public class Project4 {
	//********************************************************************
	// Main Method
	//
	// The main method "drives" the program by reading the System input and 
	// determining the appropriate amount of lines to read while creating
	// the array of strings that needs to be sorted.
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
	// printSorted	boolean			Tells program to print the sorted list or not.
	// sc			Scanner			Scanner used to get the System input.
	// numCases		int				Number of strings of characters to expect
	// arrayToSort	String[]		Array of strings to be sorted.
	// curLine		String			Current line scanned from system input.
	// 
	//*****************************************************************
	public static void main(String[] args) {
		boolean printSorted = false;
		if (args.length == 1)
			if (args[0].equals("--dump")) {
				printSorted = true;
			}
		Scanner sc = new Scanner(System.in);
		int numCases = 0;
		if (sc.hasNextLine())
			numCases = Integer.parseInt(sc.nextLine());
		String[] arrayToSort = new String[numCases];
		for (int i = 0; i < numCases; i++) {
			String curLine = sc.nextLine();
			arrayToSort[i] = curLine;
		}
		sc.close();
		initSorts(arrayToSort, printSorted);
	}
	//********************************************************************
	// Init Sorts Method
	//
	// The init sorts method creates a new instance of each sort class
	// passing in the unsorted array to each sort.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// a		String[]		value		Array to be sorted.
	// b		boolean			value		Tells the program to print sorted array or not.
	//
	// Local Variables
	// ---------------
	// is		InsertionSort		Instance of the InsertionSort class.
	// ss		SelectionSort		Instance of the SelectionSort class.
	// ms 		MergeSort			Instance of the MergeSort class.
	// qs		Quicksort			Instance of the Quicksort class.
	// hs 		HeapSort			Instance of the HeapSort class.
	// 
	//*****************************************************************
	private static void initSorts(String[] a, boolean b) {
		InsertionSort is = new InsertionSort(a);
		SelectionSort ss = new SelectionSort(a);
		MergeSort ms = new MergeSort(a);
		Quicksort qs = new Quicksort(a);
		HeapSort hs = new HeapSort(a);
		if (b == true) {
			for (String item : a)
				System.out.println(item);
		}
		buildTable(is, ss, ms, qs, hs);
	}
	//********************************************************************
	// Build Table Method
	//
	// The build table method takes the results from each sorting algorithm
	// and prints them to a cleanly formatted table.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// is		InsertionSort	value		Instance of the InsertionSort class.
	// ss		SelectionSort	value		Instance of the SelectionSort class.
	// ms 		MergeSort		value		Instance of the MergeSort class.
	// qs		Quicksort		value		Instance of the Quicksort class.
	// hs 		HeapSort		value		Instance of the HeapSort class.
	//
	// Local Variables
	// ---------------
	// sc			Scanner			Scanner used to get the System input.
	// 
	//*****************************************************************
	private static void buildTable(InsertionSort is, SelectionSort ss, MergeSort ms, Quicksort qs, HeapSort hs) {
		System.out.format("%-16s%-2s%-14s%-2s%-13s%-2s%-16s\n", "ALGORITHM", "|", "Comparisons", "|", "Exchanges", "|", "Time (seconds)");
		System.out.println("================+===============+==============+================");
		is.printRow();
		System.out.println("----------------+---------------+--------------+----------------");
		ss.printRow();
		System.out.println("----------------+---------------+--------------+----------------");
		ms.printRow();
		System.out.println("----------------+---------------+--------------+----------------");
		qs.printRow();
		System.out.println("----------------+---------------+--------------+----------------");
		hs.printRow();
	}
}
