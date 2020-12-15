//****************************************************************
//
// YOUR NAME GOES HERE
//
// Java Templated Doubly Linked List Class
//
// Add documentation here and to each member function
//****************************************************************


//****************************************************************
//
// Imports
//
//****************************************************************


//****************************************************************
//
// TestDLList Class
//
//****************************************************************
class TestDLList{

	public static void main(String[] args){
		
		// local variables
		DLList<String> listofstrings = new DLList<String>();
		String anystring = new String();
		boolean ok;

		// store command line arguments in list of strings
		for(int ii=0; ii<args.length; ii++)
			listofstrings.InsertLast(args[ii]);

		DLList<String> copylistofstrings = new DLList<String>(listofstrings); 
		System.out.println(copylistofstrings.Size());
		// move to top of list
		ok = copylistofstrings.First();
		if(ok == true){
			while(ok == true){
				anystring = copylistofstrings.DataRead();
				if (anystring != null)
					System.out.println(">" + anystring + "<");
				ok = copylistofstrings.Next();
			}
			
		}
		else {
			System.out.println("Could not move to first node in list");
			return;
		}
	}
}