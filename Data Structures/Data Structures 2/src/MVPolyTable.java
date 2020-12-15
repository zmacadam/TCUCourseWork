//********************************************************************
//
//Zachary J Macadam
//Data Structures
//Programming Project #2: Linked Lists
//October 9, 2018
//Instructor: Dr. Michael Scherger//
//
//*******************************************************************

public class MVPolyTable {
	//********************************************************************
	// Private data members
	//*****************************************************************
	private DLList<MVPolynomial> table = new DLList<MVPolynomial>();
	//********************************************************************
	// Insert Method
	//
	// The insert method attempts to insert a polynomial into the DLList
	// given that the variable name does not already exist and the polynomial
	// is valid (not a 0 coefficient).
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// s		String			value		Variable name of the polynomial to be inserted.
	// m		MVPolynomial	value		Polynomial that is being inserted.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	//
	//*****************************************************************
	public boolean Insert(String s, MVPolynomial m) {
		if (Search(s, false)) {
			System.out.println("POLYNOMIAL " + s + " ALREADY INSERTED");
			return false;
		}
		if (m.toString() == null) {
			System.out.println("ZERO COEFFICIENT POLYNOMIAL " + s + " NOT INSERTED");
			return false;
		}
		table.InsertLast(m);
		table.DataRead().setPolyName(s);	
		System.out.println(s + " = " + table.DataRead().toString());
		return true;
	}
	//********************************************************************
	// Update Method
	//
	// The update method attempts to update a polynomial into the DLList
	// given that the variable name does already exist in the table and the polynomial
	// is valid (not a 0 coefficient).
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// s		String			value		Variable name of the polynomial to be updated.
	// m		MVPolynomial	value		Polynomial that is being inserted.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	//
	//*****************************************************************
	public boolean Update(String s, MVPolynomial m) {
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (s.equals(table.DataRead().getPolyName())) {
				if (m.toString() == null) {
					System.out.println("ZERO COEFFICIENT POLYNOMIAL " + s + " DELETED");
					table.DeleteAt();
					return true;
				}
				table.DataWrite(m);
				table.DataRead().setPolyName(s);
				System.out.println(s + " = " + table.DataRead().toString());
				return true;
			}
		}
		System.out.println("POLYNOMIAL " + s + " NOT FOUND");
		return false;
	}
	//********************************************************************
	// Delete Method
	//
	// The delete method attempts to delete a polynomial in the DLList
	// given that the variable name does already exist in the table.
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// s		String			value		Variable name of the polynomial to be deleted.
	// m		MVPolynomial	value		Polynomial that is being inserted.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	//
	//*****************************************************************
	public boolean Delete(String s) {
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (s.equals(table.DataRead().getPolyName())) {
				table.DeleteAt();
				System.out.println("POLYNOMIAL " + s + " SUCCESSFULLY DELETED");
				return true;
			}
		}
		System.out.println("POLYNOMIAL " + s + " DOES NOT EXIST");
		return false;
	}
	//********************************************************************
	// Delete All Method
	//
	// The delete all method deletes all nodes in the DLList to prepare for
	// program termination.
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
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	//
	//*****************************************************************
	public void DeleteAll() {
		table.Clear();
	}
	//********************************************************************
	// Search Method
	//
	// The search method attempts to search for a polynomial in the DLList
	// given that the variable name does already exist in the table.
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// s		String			value		Variable name of the polynomial to be searched for.
	// print	Boolean			value		Tells the method to print if the variable was found or not.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	//
	//*****************************************************************
	public boolean Search(String s, boolean print) {
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (s.equals(table.DataRead().getPolyName())) {
				if (print == true) {
					System.out.println((s + " = " + table.DataRead().toString()));
					return true;
				}
				else
					return true;
			}
		}
		if (print == true) {
			System.out.println("POLYNOMIAL " + s + " NOT FOUND");
			return false;
		}
		return false;
	}
	//********************************************************************
	// Add Method
	//
	// The add method attempts to add two polynomials in the DLList
	// given that the both variables correspond to existing polynomials.
	// The result can either update an existing polynomial or create and
	// insert a new polynomial to the list.
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// r		String			value		Name of the resulting polynomial.
	// a		String			value		Name of the first polynomial to be added.
	// b 		String			value		Name of the second polynomial to be added.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	// result		MVPolynomial			Resulting polynomial created by the addition operation.
	//
	//*****************************************************************
	public boolean Add(String r, String a, String b) {
		if (!Search(a, false)) {
			System.out.println("CANNOT ADD: " + a + " DOES NOT EXIST");
			return false;
		}
		if (!Search(b, false)) {
			System.out.println("CANNOT ADD: " + b + " DOES NOT EXIST");
			return false;
		}
		MVPolynomial result = new MVPolynomial();
		result = CreateResult("ADD", a, b);
		CombineLike(result);
		SortTerms(result);
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (table.DataRead().getPolyName().equals(r)) {
				Update(r, result);
				return true;
			}
		}
		return (Insert(r, result));
	}
	//********************************************************************
	// Subtract Method
	//
	// The subtract method attempts to subtract two polynomials in the DLList
	// given that the both variables correspond to existing polynomials.
	// The result can either update an existing polynomial or create and
	// insert a new polynomial to the list.
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// r		String			value		Name of the resulting polynomial.
	// a		String			value		Name of the first polynomial to be subtracted.
	// b 		String			value		Name of the second polynomial to be subtracted.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	// result		MVPolynomial			Resulting polynomial created by the subtraction operation.
	//
	//*****************************************************************
	public boolean Sub(String r, String a, String b) {
		if (!Search(a, false)) {
			System.out.println("CANNOT SUB: " + a + " DOES NOT EXIST");
			return false;
		}
		if (!Search(b, false)) {
			System.out.println("CANNOT SUB: " + b + " DOES NOT EXIST");
			return false;
		}
		MVPolynomial result = new MVPolynomial();
		result = CreateResult("SUB", a, b);
		CombineLike(result);
		SortTerms(result);
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (table.DataRead().getPolyName().equals(r)) {
				Update(r, result);
				return true;
			}
		}
		return (Insert(r, result));
	}
	//********************************************************************
	// Mult Method
	//
	// The mult method attempts to multiply two polynomials in the DLList
	// given that the both variables correspond to existing polynomials.
	// The result can either update an existing polynomial or create and
	// insert a new polynomial to the list.
	// 	
	// Return Value	
	// ------------
	// bool		True/False if the insertion is successful.
	//
	// Function Parameters
	// -------------------
	// r		String			value		Name of the resulting polynomial.
	// a		String			value		Name of the first polynomial to be multiplied.
	// b 		String			value		Name of the second polynomial to be multiplied.
	//
	// Local Variables
	// ---------------
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	// result		MVPolynomial			Resulting polynomial created by the addition operation.
	//
	//*****************************************************************
	public boolean Mult(String r, String a, String b) {
		if (!Search(a, false)) {
			System.out.println("CANNOT MULT: " + a + " DOES NOT EXIST");
			return false;
		}
		if (!Search(b, false)) {
			System.out.println("CANNOT MULT: " + b + " DOES NOT EXIST");
			return false;
		}
		MVPolynomial result = new MVPolynomial();
		result = CreateResult("MULT", a, b);
		CombineLike(result);
		SortTerms(result);
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (table.DataRead().getPolyName().equals(r)) {
				Update(r, result);
				return true;
			}
		}
		return (Insert(r, result));
	}
	//********************************************************************
	// Create Result Method
	//
	// The create result method creates the resulting polynomial by either
	// adding, subtracting, or multiplying the two given polynomials depending
	// on the operation that was passed into the method.
	// 	
	// Return Value	
	// ------------
	// MVPolynomial			Returns the resulting polynomial after the given operation was performed.
	//
	// Function Parameters
	// -------------------
	// opp		String			value		Name of the operation to be performed on the two polynomials.
	// a		String			value		Name of the first polynomial to be added.
	// b 		String			value		Name of the second polynomial to be added.
	//
	// Local Variables
	// ---------------
	// arg1			MVPolynomial			First argument for the operation.
	// arg2			MVPolynomial			Second argument for the operation.
	// result		MVPolynomial			Resulting polynomial after the operation is performed.
	// table		DLList<MVPolynomial>	Doubly linked list containing the polynomials.
	// i			Integer					Loop iteration variable.
	// j			Integer					Loop iteration variable.
	// result		MVPolynomial			Resulting polynomial created by the addition operation.
	//
	//*****************************************************************
	private MVPolynomial CreateResult(String opp, String a, String b) {
		MVPolynomial arg1 = new MVPolynomial();
		MVPolynomial arg2 = new MVPolynomial();
		MVPolynomial result = new MVPolynomial();
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (a.equals(table.DataRead().getPolyName())) {
				arg1 = table.DataRead();
			}
		}
		for (int i = 0; i < table.Size(); i++) {
			table.Seek(i);
			if (b.equals(table.DataRead().getPolyName())) {
				arg2 = table.DataRead();
			}
		}
		if (opp.equals("SUB")) {
			for (int i = 0; i < arg2.Size(); i++) {
				arg2.Seek(i);
				arg2.subCoeff();
			}
		}
		if (opp.equals("MULT")) {
			if (arg1.First()) {
				for (int i = 0; i < arg1.Size(); i++) {
					arg1.Seek(i);
					if (arg2.First()) {
						for (int j = 0; j < arg2.Size(); j++) {
							arg2.Seek(j);
							MVPolyTerm multTerm = new MVPolyTerm(arg1.getTerm(), arg2.getTerm());
							result.addLast(multTerm);
						}
					}
				}
			}
			return result;
		}
		if (arg1.First() && arg2.First()) {
			do {
				result.addLast(arg1.getTerm());

			} while (arg1.Next());
			do {
				result.addLast(arg2.getTerm());
			} while (arg2.Next());
		}
		return result;
	}
	//********************************************************************
	// Combine Like Method
	//
	// The combine like method searches through the polynomial created by
	// the previously given math operation looking for like terms. If a like
	// term is found, it is combined. This method condenses the new polynomial
	// into the proper form.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// r		MVPolynomial		value		Resulting polynomial that is searched for like terms.
	//
	// Local Variables
	// ---------------
	// arg1term		MVPolyTerm				First term to be compared for like terms.
	// arg2term		MVPolyTerm				Second term to be compared for like terms.
	// newTerm		MVPolyTerm				Polynomial term created if the two arguments have like terms.
	// i			Integer					Loop iteration variable.
	// j			Integer					Loop iteration variable.
	//
	//*****************************************************************
	public void CombineLike(MVPolynomial r) {
		if (r.First()) {
			for (int i = 0; i < r.Size(); i++) {
				r.Seek(i);
				MVPolyTerm arg1term = new MVPolyTerm(r.getTerm());
				for (int j = i + 1; j < r.Size(); j++) {
					if (!r.AtLast()) {
						r.Seek(j);
						MVPolyTerm arg2term = new MVPolyTerm(r.getTerm());
						if (arg1term.compareTerms(arg2term) == 0) {
							arg1term.setCoeff(arg1term.getCoeff() + arg2term.getCoeff());
							if (arg1term.getCoeff() == 0) {
								MVPolyTerm newTerm = new MVPolyTerm();
								arg1term = newTerm;
								r.updatePolyTerm(arg1term, i);
								r.deleteTerm(j);
							}
							else {
								r.updatePolyTerm(arg1term, i);
								r.deleteTerm(j);
							}
						}
					}
				}
			}
		}
	}
	//********************************************************************
	// Sort Terms Method
	//
	// The sort terms method sorts the resulting polynomial in order by
	// highest power of x first, highest power of y second, highest power
	// of z third, and any constant terms last.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// r		MVPolynomial		value		Resulting polynomial that is sorted.
	//
	// Local Variables
	// ---------------
	// prev			MVPolyTerm				First polynomial term that is compared to be sorted.
	// curr			MVPolyTerm				Second polynomial term that is compared to be sorted.
	// temp			MVPolyTerm				Polynomial term used to bubble sort the two terms.
	// i			Integer					Loop iteration variable.
	// j			Integer					Loop iteration variable.
	//
	//*****************************************************************
	private void SortTerms(MVPolynomial r) {
		if (r.First()) {
			for (int i = 0; i < r.Size(); i++) {
				r.Seek(i);
				MVPolyTerm prev = new MVPolyTerm(r.getTerm());
				if (!r.AtLast()) {
					for (int j = i + 1; j < r.Size(); j++) {
						r.Seek(j);
						MVPolyTerm curr = new MVPolyTerm(r.getTerm());
						if (prev.compareTerms(curr) == 2) {
							r.updatePolyTerm(curr, i);
							r.updatePolyTerm(prev, j);
							MVPolyTerm temp = new MVPolyTerm(curr);
							curr = prev;
							prev = temp;
						}
					}
				}
			}
		} 
	}

}
