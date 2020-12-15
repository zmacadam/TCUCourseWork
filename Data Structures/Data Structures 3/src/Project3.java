import java.util.Scanner;

public class Project3 {

	//********************************************************************
	// Main Method
	//
	// The main method "drives" the program by reading the System input and 
	// determining the appropriate method to call.
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
	// sc			Scanner			Scanner used to get the System input.
	// nextLine		String			Current line of the input.
	// special		String			Character denoting the type of expression.
	// expression	String			The expression to be parsed into a tree.
	//
	//*****************************************************************
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			String special = nextLine.substring(0, 1);
			String expression = nextLine.replaceAll("\\s", "").substring(1);
			if (special.equals("!")) {
				prefix(expression);
			}
			if (special.equals("@")) {
				infix(expression);
			}
			if (special.equals("#")) {
				postfix(expression);
			}
		}
	}
	//********************************************************************
	// Prefix Method
	//
	// The prefix method is called when a prefix expression is specified 
	// by the input. This method created a binary tree from the prefix
	// expression, prints the 4 traversals, and prints a graphic of the 
	// tree to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s		String		Value		Expression from System input.
	//
	// Local Variables
	// ---------------
	// bt		BTree		Binary tree created from the given expression.
	//
	//*****************************************************************
	public static void prefix(String s) {
		BTree<String> bt = new BTree<>(s, "prefix");
		bt.printTraversals();
		bt.printGraphic();
	}
	//********************************************************************
	// Postfix Method
	//
	// The postfix method is called when a postfix expression is specified 
	// by the input. This method created a binary tree from the postfix
	// expression, prints the 4 traversals, and prints a graphic of the 
	// tree to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s		String		Value		Expression from System input.
	//
	// Local Variables
	// ---------------
	// bt		BTree		Binary tree created from the given expression.
	//
	//*****************************************************************
	public static void postfix(String s) {
		BTree<String> bt = new BTree<>(s, "postfix");
		bt.printTraversals();
		bt.printGraphic();
	}
	//********************************************************************
	// Infix Method
	//
	// The infix method is called when an infix expression is specified 
	// by the input. This method created a binary tree from the infix
	// expression, prints the 4 traversals, and prints a graphic of the 
	// tree to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s		String		Value		Expression from System input.
	//
	// Local Variables
	// ---------------
	// bt		BTree		Binary tree created from the given expression.
	//
	//*****************************************************************
	public static void infix(String s) {
		BTree<String> bt = new BTree<>(s, "infix");
		bt.printTraversals();
		bt.printGraphic();
	}
}

