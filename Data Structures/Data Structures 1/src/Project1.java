import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//********************************************************************
//
// Zachary J Macadam
// Data Structures
// Programming Project #1: Recursion
// September 4, 2018
// Instructor: Dr. Michael Scherger//

//README
//Functionality: Program receives input through the console in the following order: number of test cases, test case dimensions, test case image.
//				If the program has more than one test case, a blank line must be entered followed by the dimensions and image for each subsequent test case.
//Project completion time: 10 hours
//Not working components: None found
//*******************************************************************
public class Project1 {
	//********************************************************************
	// Main Method
	//
	// The main method gets the number of test case images from the console
	// and creates a CharImage class for each test case. Two methods within
	// CharImage are called to read, process, and output each test case.
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
	// br			BufferedReader			BufferedReader used to get the number of test cases from the console.
	// numImages	int						Number of test case images input by the user.
	// ci			CharImage				Instance of the CharImage class used to call the methods within.
	// i			int						Loop Iteration Variable.
	//
	//*****************************************************************
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numImages = Integer.parseInt(br.readLine());
		for (int i = 0; i < numImages; i++) {
			CharImageb ci = new CharImageb();
			ci.readImage();
			ci.output();
			if (i != numImages - 1)
				br.readLine();
		}
	}
}
