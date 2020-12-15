//********************************************************************
//
//Zachary J Macadam
//Data Structures
//Programming Project #2: Linked Lists
//October 9, 2018
//Instructor: Dr. Michael Scherger//
//
//*******************************************************************

public class MVPolynomial {
	//********************************************************************
	// Private data members
	//*****************************************************************
	private String polyName;
	private DLList<MVPolyTerm> polynomial = new DLList<MVPolyTerm>();

	public MVPolynomial() {}
	//********************************************************************
	// Method to set the polynomial name with the given variable.
	//*****************************************************************
	public void setPolyName(String s) {
		polyName = s;
	}
	//********************************************************************
	// Method to get the name of the polynomial.
	//*****************************************************************
	public String getPolyName() {
		return polyName;
	}
	//********************************************************************
	// Method to move the polynomial to the first term.
	// Returns true/false if the list operation was successful.
	//*****************************************************************
	public boolean First() {
		return polynomial.First();
	}
	//********************************************************************
	// Method to move the polynomial to the last term.
	// Returns true/false if a next term exists.
	//*****************************************************************
	public boolean Next() {
		return polynomial.Next();
	}
	//********************************************************************
	// Method to check if the polynomial is at the last term.
	// Returns true/false if it is at the last term.
	//*****************************************************************
	public boolean AtLast() {
		return polynomial.AtLast();
	}
	//********************************************************************
	// Method to move the polynomial to the given location.
	//*****************************************************************
	public void Seek(int l) {
		polynomial.Seek(l);
	}
	//********************************************************************
	// Method to add a new term to the end of the polynomial.
	//*****************************************************************
	public void addLast(MVPolyTerm m) {
		polynomial.InsertLast(m);
	}
	//********************************************************************
	// Method to delete a term to the given location.
	//*****************************************************************
	public void deleteTerm(int l) {
		polynomial.Seek(l);
		polynomial.DeleteAt();
	}
	//********************************************************************
	// Method to completely empty the list.
	//*****************************************************************
	public void ClearList() {
		polynomial.Clear();
	}
	//********************************************************************
	// Method to overwrite the term at the given location with a new term.
	//*****************************************************************
	public void updatePolyTerm(MVPolyTerm m, int l) {
		polynomial.Seek(l);
		polynomial.DataWrite(m);
	}
	//********************************************************************
	// Method that returns the current term.
	//*****************************************************************
	public MVPolyTerm getTerm() {
		return polynomial.DataRead();
	}
	//********************************************************************
	// Method that multiplies all coefficients of the polynomial by -1.
	//*****************************************************************
	public void subCoeff() {
		int c = polynomial.DataRead().getCoeff();
		polynomial.DataRead().setCoeff(-c);
	}
	//********************************************************************
	// Method that returns the size of the polynomial, the number of terms.
	//*****************************************************************
	public int Size() {
		return polynomial.Size();
	}
	//********************************************************************
	// Method that converts the polynomial data into a human-readable string.
	//*****************************************************************
	public String toString() {
		String polyString = "";
		for (int i = 0; i < polynomial.Size(); i++) {
			polynomial.Seek(i);
			if (polynomial.DataRead().toString() != null) {
				if (polynomial.DataRead().getCoeff() > 0) {
					if (polyString.equals(""))
						polyString = polynomial.DataRead().toString();
					else
						polyString = polyString + " + " + polynomial.DataRead().toString();
				}
				else if (polynomial.DataRead().getCoeff() == 0) {
					if (polyString.equals(""))
						polyString = polynomial.DataRead().toString();
				}
				else {
					if (polyString.equals(""))
						polyString = "-" + polynomial.DataRead().toString();
					else
						polyString = polyString + " - " + polynomial.DataRead().toString();
				}
			}		
		}
		if (polyString.equals(""))
			return null;
		return polyString;
	}

}
