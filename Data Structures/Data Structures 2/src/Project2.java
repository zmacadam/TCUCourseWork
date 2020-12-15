import java.util.Scanner;
import java.util.StringTokenizer;

//********************************************************************
//
//Zachary J Macadam
//Data Structures
//Programming Project #2: Linked Lists
//October 9, 2018
//Instructor: Dr. Michael Scherger//
//
//*******************************************************************

public class Project2 {
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
	// sc		Scanner				Scanner used to get the System input.
	// st		StringTokenizer		StringTokenizer used to separate the command, variable, and polynomial.
	// command	String				String that contains the command, used to determine the correct method call.
	//
	//*****************************************************************
	public static void main(String[] args){
		MVPolyTable table = new MVPolyTable();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			StringTokenizer st = new StringTokenizer(nextLine, " ,");
			String command = st.nextToken();
			if (command.equals("INSERT"))
				Insert(st, table);
			if (command.equals("DELETE"))
				Delete(st, table);
			if (command.equals("UPDATE"))
				Update(st, table);
			if (command.equals("SEARCH"))
				Search(st, table);
			if (command.equals("ADD"))
				Add(st, table);
			if (command.equals("SUB"))
				Sub(st, table);
			if (command.equals("MULT"))
				Mult(st, table);
			if (command.equals("QUIT"))
				Quit(sc, table);
		}
	}
	//********************************************************************
	// Insert Method
	//
	// The insert method creates a new polynomial from the given terms and 
	// attempts to insert it into the table if a valid variable name is given.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The tokens needed to create a polynomial.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// var		String			String that contains the variable name, used to check if insert is valid and name the polynomial.
	// mvpoly	MVPolynomial	MVPolynomial created using the tokens given to st from System input.
	//
	//*****************************************************************
	public static void Insert(StringTokenizer st, MVPolyTable table) {
		String var = st.nextToken();
		MVPolynomial mvpoly = new MVPolynomial();
		createMVPolynomial(st, mvpoly);
		mvpoly.setPolyName(var);
		table.CombineLike(mvpoly);
		table.Insert(var, mvpoly);
	}
	//********************************************************************
	// Delete Method
	//
	// The delete method attempts to delete a polynomial from the DLList 
	// if the variable exists in the table.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the polynomial name.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// var		String			String that contains the variable name, used to check if the variable exists to be deleted.
	//
	//*****************************************************************
	public static void Delete(StringTokenizer st, MVPolyTable table) {
		String var = st.nextToken();
		table.Delete(var);
	}
	//********************************************************************
	// Update Method
	//
	// The update method creates a new polynomial from the given terms and 
	// attempts to update the polynomial with the given variable if it already
	// exists within the table.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The tokens needed to create a polynomial.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// var		String			String that contains the variable name, used to check if the polynomial exists to update it.
	// mvpoly	MVPolynomial	MVPolynomial created using the tokens given to st from System input.
	//
	//*****************************************************************
	public static void Update(StringTokenizer st, MVPolyTable table) {
		String var = st.nextToken();
		MVPolynomial mvpoly = new MVPolynomial();
		createMVPolynomial(st, mvpoly);
		mvpoly.setPolyName(var);
		table.CombineLike(mvpoly);
		table.Update(var, mvpoly);
	}
	//********************************************************************
	// Search Method
	//
	// The search method determines if a polynomial with the given variable name
	// exists within the able.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the variable name.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// var		String			String that contains the variable name, used to check if the variable exists to be deleted.
	//
	//*****************************************************************
	public static void Search(StringTokenizer st, MVPolyTable table) {
		String var = st.nextToken();
		table.Search(var, true);
	}
	//********************************************************************
	// Add Method
	//
	// The add method attempts to add two polynomials given that both
	// polynomials exist already in the table. The resulting polynomial can create
	// a new polynomial or update an already existing one.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the variable name.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// result		String			String that contains the variable name, used to either name the new polynomial or update an existing one.
	// arg1			String			String that contains the variable name of the first polynomial to be added.
	// arg2			String			String that contains the variable name of the second polynomial to be added.
	//
	//*****************************************************************
	public static void Add(StringTokenizer st, MVPolyTable table) {
		String result = st.nextToken();
		String arg1 = st.nextToken();
		String arg2 = st.nextToken();
		table.Add(result, arg1, arg2);
	}
	//********************************************************************
	// Sub Method
	//
	// The sub method attempts to subtract two polynomials given that both
	// polynomials exist already in the table. The resulting polynomial can create
	// a new polynomial or update an already existing one.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the variable name.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// result		String			String that contains the variable name, used to either name the new polynomial or update an existing one.
	// arg1			String			String that contains the variable name of the first polynomial to be added.
	// arg2			String			String that contains the variable name of the second polynomial to be added.
	//
	//*****************************************************************
	public static void Sub(StringTokenizer st, MVPolyTable table) {
		String result = st.nextToken();
		String arg1 = st.nextToken();
		String arg2 = st.nextToken();
		table.Sub(result, arg1, arg2);
	}
	//********************************************************************
	// Mult Method
	//
	// The Mult method attempts to multiply two polynomials given that both
	// polynomials exist already in the table. The resulting polynomial can create
	// a new polynomial or update an already existing one.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the variable name.
	// table	MVPolyTable			value		The DLList that holds the polynomials.
	//
	// Local Variables
	// ---------------
	// result		String			String that contains the variable name, used to either name the new polynomial or update an existing one.
	// arg1			String			String that contains the variable name of the first polynomial to be added.
	// arg2			String			String that contains the variable name of the second polynomial to be added.
	//
	//*****************************************************************
	public static void Mult(StringTokenizer st, MVPolyTable table) {
		String result = st.nextToken();
		String arg1 = st.nextToken();
		String arg2 = st.nextToken();
		table.Mult(result, arg1, arg2);
	}
	//********************************************************************
	// Create Polynomial Method
	//
	// This method creates a new MVPolynomial using the tokens given from
	// System input.
	// 
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// st		StringTokenizer		value		The token needed to determine the variable name.
	// mvpoly	MVPolynomial		value		The MVPolynomial to be created using the tokens.
	//
	// Local Variables
	// ---------------
	// coeff	int				Integer value of the coefficient given from System input.
	// xpow		int				Integer value of the X power given from System input.
	// ypow		int				Integer value of the Y power given from System input.
	// zpow 	int				Integer value of the Z power given from System input.
	// mvterm	MVPolyTerm		MVPolyTerm created using the values obtained from System input.
	//
	//*****************************************************************
	public static void createMVPolynomial(StringTokenizer st, MVPolynomial mvpoly) {
		while(st.hasMoreTokens()) {
			int coeff, xpow, ypow, zpow;
			coeff = Integer.parseInt(st.nextToken());
			xpow = Integer.parseInt(st.nextToken());
			ypow = Integer.parseInt(st.nextToken());
			zpow = Integer.parseInt(st.nextToken());
			MVPolyTerm mvterm = new MVPolyTerm(coeff, xpow, ypow, zpow);
			mvpoly.addLast(mvterm);
		}
	}
	//********************************************************************
	// Quit Method
	//
	// The quit method closes the scanner so it cannot receive any new input,
	// deletes all the nodes within the DLList of polynomials, and exits the program.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// sc		Scanner				value		The scanner that needs to be closed.
	// table	MVPolyTable			value		The DLList that holds the polynomials which needs to be cleared.
	//
	// Local Variables
	// ---------------
	// None
	//
	//*****************************************************************
	public static void Quit(Scanner sc, MVPolyTable table) {
		sc.close();
		table.DeleteAll();
		System.exit(1);
	}
}
