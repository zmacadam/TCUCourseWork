import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CharImage {

	private int rows;
	private int columns;
	private int connected;
	private int numAlphabets;
	private String alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private String[][] image;
	private boolean[][] visited;
	private ArrayList<Integer> table = new ArrayList<Integer>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private String imageLine;

	//********************************************************************
	// Bounds Checker Method
	//
	// This method checks to ensure that the cell attempting to be searched
	// is within the defined bounds of the 2D array.
	//
	// Return Value
	// ------------
	// boolean                         True/False if the cell is within bounds
	//
	// Function Parameters
	// -------------------
	// r		int		reference		The row number of the cell
	// c		int		reference		The columns number of the cell
	//
	// Important Variables
	// ---------------
	// rows		int 	Number of rows the 2D image array contains
	// columns	int 	Number of columns the 2D image array contains
	//	
	//*****************************************************************
	private boolean inBounds(int r, int c)
	{
		return r > -1 && r < rows && c > -1 && c < columns;
	}
	//********************************************************************
	// Image Reader Method
	//
	// This method reads the dimensions of the image and the test case image
	// from the console. It then creates a 2D array and fills it using the 
	// given values from the user using the console. It then calls on two methods,
	// the colorImage method that will have the image labeled for each unique group
	// of "*"'s and the labelAndCount method which will turn the labels into characters
	// as well as count the number of unique groups in the image.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// br				BufferedReader	Allows the input from the console to be processed.
	// imageLine		String			The current line of console input obtained by the buffered reader.
	// rows				int				Number of rows in the image.
	// columns			int 			Number of columns in the image.
	// image			String[][]		The 2D array containing the image given to the console.
	// visited			boolean[][]		A 2D array of booleans to check if the cell has been visited already.
	// st				StringTokenizer Allows the rows and columns to be read and assigned to variables separately.
	// j, a, b			int				Loop Iteration variables.
	//
	//*****************************************************************
	public void readImage() throws IOException {
		imageLine = "";
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		rows = Integer.parseInt(st.nextToken());
		columns = Integer.parseInt(st.nextToken());
		image = new String[rows][columns];
		visited = new boolean[rows][columns];
		for (int j = 0; j < rows; j++) 
			imageLine = imageLine + br.readLine();
		for (int a = 0; a < image.length; a++) 
			for (int b = 0; b < image[0].length; b++) {
				image[a][b] = imageLine.substring(0, 1);
				imageLine = imageLine.substring(1, imageLine.length());
			}
		colorImage();
		labelAndCount();
	}
	//********************************************************************
	// Image Painter Method
	//
	// This method goes through each cell of the 2D array and passes its coordinates,
	// if the coordinates contain an "*" and the cell has not previously been visited,
	// to the recursive method that finds each connected components in the 2D array.
	// This method also counts how many separate groups of connected images exist in the
	// 2D array. It also checks to see if there are more than a multiple of 62 unique groups in the image.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// connected		int		Number of connected groups in the image.
	// numAlphabets		int		Number of times the group labels will be used. (duplicates)
	// i, j				int		Loop Iteration Variables.
	//
	//*****************************************************************
	private void colorImage() {
		connected = 0;
		numAlphabets = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (image[i][j].equals("*") && !visited[i][j]) {
					if (connected % 62 == 0) {
						numAlphabets++;
					}
					findConnected(i, j, ++connected);
				}
			}
		}
	}
	//********************************************************************
	// Find Connected Components Method
	//
	// This method takes the passed in coordinates and recursively checks to
	// see if any of its neighbors are an "*". If they are, they are changed to
	// a unique number label based on the number of previously found groups and the
	// search continues to check the new current cell's neighbors. The search
	// continues until a "." is found for all surrounding cells meaning
	// that the group has been completely labeled.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// r		int		reference	Row coordinate of current cell.
	// c 		int		reference	Column coordinate of current cell.
	// l		int		value		Label for each unique group of "*"'s.
	//
	// Important Variables
	// ---------------
	// image	String[][]			The original image is now "painted" to label each group of "*"'s with a unique number.
	// visited	boolean[][]			The 2D array of booleans is called on the ensure cells are not visited and changed more than once.
	//
	//*****************************************************************
	private void findConnected(int r, int c, int l) {
		if(!inBounds(r,c) || image[r][c].equals("."))
			return;
		if (!visited[r][c]) {
			visited[r][c] = true;
			if(image[r][c].equals("*")) {
				image[r][c] = l + "";
				findConnected(r - 1, c, l);
				findConnected(r + 1, c, l);
				findConnected(r, c + 1, l);
				findConnected(r, c - 1, l);
			}
		}
		return;
	}
	//********************************************************************
	// Change and Count Labels Method 
	//
	// This method checks each cell of the 2D array to see if it contains a unique label.
	// For each group of labels found, the labels are then "painted" into the character
	// corresponding to their label number. The number of times the unique label is found
	// is recorded into an ArrayList before searching for the next unique label.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// alphabet 		String		The string of characters that is used to paint the image.
	// numAlphabets		int			The number of times the groups exceeded the length of the alphabet string.
	// table			ArrayList	The ArrayList that holds the size of each painted group in the image.
	// counter			int			Counts the size of each unique group.
	// i, j				int			Loop Iteration Variables.
	//
	//*****************************************************************
	private void labelAndCount() {
		for (int k = 0; k < connected * numAlphabets; k++) {
			String letter = Character.toString(alphabet.charAt(k % 63));
			int counter = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (image[i][j].equals(k + "")) {
						image[i][j] = letter;
						counter++;	
					}
				}
			}
			if (counter > 0) 
				table.add(counter);
		}
		if (table.size() > 1)
			bubbleSort();
	}
	//********************************************************************
	// Bubble Sort Method
	//
	// This method sorts the ArrayList of group sizes into ascending order.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// swapped		boolean			A boolean to ensure the entire list is sorted into the correct order.
	// temp			int				Temporary place holder used to swap values within the ArrayList.
	//
	//*****************************************************************
	private void bubbleSort() {
		boolean swapped = false;
		do
		{
			swapped = false;
			for (int i=0; i< table.size() - 1; i++)
			{
				if (table.get(i) > table.get(i + 1))
				{
					int temp = table.get(i);
					table.set(i, table.get(i + 1));
					table.set(i + 1, temp);
					swapped = true;
				}
			}
		} while(swapped);
	}
	//********************************************************************
	// Output Method
	//
	// This method prints the required output for each test case. It begins
	// by printing out the value of each cell in the 2D array as per the dimensions
	// given originally. It then prints the number of unique painted groups. It calls
	// a method to output the size and count of each group and finishes by printing
	// a blank line to separate test cases.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// rows			int			Number of rows in the 2D array, used to know when to start a new line when outputting painted image.
	// columns		int 		Number of columns in the 2D array, used to know how many cells to print on each line.
	// connected	int 		Number of unique connected groups.
	//
	//*****************************************************************
	public void output() {
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(image[i][j]);
			}
			System.out.println();
		}
		System.out.println(connected);
		sizeCount();
		System.out.println();
	}
	//********************************************************************
	// Count Size and Number of Groups Method
	//
	// This method searches through the ordered ArrayList and counts the number of groups
	// of the same size. When the search reaches a new value, the number of times the same
	// value has been found is printed followed by the value in the ArrayList. The method
	// handles ArrayLists of size 1.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// None
	//
	// Important Variables
	// ---------------
	// count		int			Number of times a group of the same size has been found.
	// i			int			Loop Iteration Variable.
	//
	//*****************************************************************
	private void sizeCount() {
		int count = 1;
		if (table.size() == 1)
			System.out.println(count + " " + table.get(0));
		for (int i = 0; i < table.size() - 1; i++) {
			if (table.get(i) != table.get(i + 1)) {
				System.out.println(count + " " + table.get(i));
				count = 1;
			}
			else if (table.get(i) == table.get(i + 1)) {
				table.remove(i);
				count++;
				i--;
			}
			if (i == table.size() - 2) {
				System.out.println(count + " " + table.get(i + 1));
			}
		}
	}
}
