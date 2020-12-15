//********************************************************************
//
//Zachary J Macadam
//Data Structures
//Programming Project #2: Linked Lists
//October 9, 2018
//Instructor: Dr. Michael Scherger//
//
//*******************************************************************

public class MVPolyTerm {
	//********************************************************************
	// Private data members
	//*****************************************************************
	private int coeff;
	private int xpow;
	private int ypow;
	private int zpow;
	//********************************************************************
	// Default Constructor
	//*****************************************************************
	public MVPolyTerm() {
		coeff = 0;
		xpow = 0;
		ypow = 0;
		zpow = 0;
	}
	//********************************************************************
	// Constructor with given polyterm values
	//*****************************************************************
	public MVPolyTerm(int c, int x, int y, int z) {
		coeff = c;
		xpow = x;
		ypow = y;
		zpow = z;
	}
	//********************************************************************
	// Copy Constructor
	//*****************************************************************
	public MVPolyTerm(MVPolyTerm other) {
		coeff = other.coeff;
		xpow = other.xpow;
		ypow = other.ypow;
		zpow = other.zpow;
	}
	//********************************************************************
	// Multiply Constructor
	//*****************************************************************
	public MVPolyTerm(MVPolyTerm arg1, MVPolyTerm arg2) {
		coeff = arg1.getCoeff() * arg2.getCoeff();
		xpow = arg1.getXPower() + arg2.getXPower();
		ypow = arg1.getYPower() + arg2.getYPower();
		zpow = arg1.getZPower() + arg2.getZPower();
	}
	//********************************************************************
	// Method that returns the coefficient of the polyterm.
	//*****************************************************************
	public int getCoeff() {
		return coeff;
	}
	//********************************************************************
	// Method that sets the coefficient of the polyterm.
	//*****************************************************************
	public void setCoeff(int c) {
		coeff = c;
	}
	//********************************************************************
	// Method that returns the x power of the polyterm.
	//*****************************************************************
	public int getXPower() {
		return xpow;
	}
	//********************************************************************
	// Method that sets the x power of the polyterm.
	//*****************************************************************
	public void setXPower(int ix) {
		xpow = ix;
	}
	//********************************************************************
	// Method that returns the y power of the polyterm.
	//*****************************************************************
	public int getYPower() {
		return ypow;
	}
	//********************************************************************
	// Method that sets the y power of the polyterm.
	//*****************************************************************
	public void setYPower(int iy) {
		ypow = iy;
	}
	//********************************************************************
	// Method that returns the z power of the polyterm.
	//*****************************************************************
	public int getZPower() {
		return zpow;
	}
	//********************************************************************
	// Method that sets the z power of the polyterm.
	//*****************************************************************
	public void setZPower(int iz) {
		zpow = iz;
	}
	//********************************************************************
	// Method that compares the current term to a given term.
	//*****************************************************************
	public int compareTerms(MVPolyTerm arg2) {
		if (xpow > arg2.xpow)
			return 1;
		else if (xpow < arg2.xpow)
			return 2;
		else if (ypow > arg2.ypow)
			return 1;
		else if (ypow < arg2.ypow)
			return 2;
		else if (zpow > arg2.zpow)
			return 1;
		else if (zpow < arg2.zpow)
			return 2;
		else return 0;
	}
	//********************************************************************
	// Method that converts the polyterm data into a human-readable string.
	//*****************************************************************
	public String toString() {
		String mvterm = "";
		if (Math.abs(coeff) != 1 && coeff != 0)
			mvterm += Math.abs(coeff); 
		if (xpow != 0) {
			if (xpow == 1) {
				mvterm += "(x)";
			} else {
				mvterm += "(x^" + xpow + ")";
			}
		}
		if (ypow != 0) {
			if (ypow == 1) {
				mvterm += "(y)";
			} else {
				mvterm += "(y^" + ypow + ")";
			}
		}
		if (zpow != 0) {
			if (zpow == 1) {
				mvterm += "(z)";
			} else {
				mvterm += "(z^" + zpow + ")";
			}
		}
		if (Math.abs(coeff) == 1 && mvterm.equals(""))
			mvterm = "" + Math.abs(coeff);
		if (coeff == 0 && mvterm.equals(""))
			mvterm = "" + coeff;
		else if (coeff == 0 && !mvterm.equals(""))
			mvterm = null;
		return mvterm;
	}
}
