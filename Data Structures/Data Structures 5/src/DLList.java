//********************************************************************
//
//Zachary J Macadam
//Data Structures
//Programming Project #2: Linked Lists
//October 9, 2018
//Instructor: Dr. Michael Scherger//
//
//*******************************************************************


//****************************************************************
//
// Imports
//
//****************************************************************


//****************************************************************
//
// DLList Class
//
//****************************************************************
class DLList<T>{


	//****************************************************************
	//
	// DLListNode (inner) class (don't change this)
	//
	//****************************************************************
	private static class DLListNode<T>{
		//****************************************************************
		// DLList node private data members
		//****************************************************************
		private T data;
		private DLListNode<T> prev;
		private DLListNode<T> next;

		//****************************************************************
		// constructor
		//****************************************************************
		DLListNode(T value, DLListNode<T> p, DLListNode<T> n){
			data = value;
			prev = p;
			next = n;
		}

		//****************************************************************
		// Get Element
		//****************************************************************
		public T GetElement(){
			return(data);
		}

		//****************************************************************
		// Set Element
		//****************************************************************
		public boolean SetElement(T item){
			data = item;
			return(true);
		}


		//****************************************************************
		// Get Previous
		//****************************************************************
		public DLListNode<T> GetPrev(){
			return(prev);
		}


		//****************************************************************
		// Set Previous
		//****************************************************************
		public void SetPrev(DLListNode<T> p){
			prev = p;
		}


		//****************************************************************
		// Get Next
		//****************************************************************
		public DLListNode<T> GetNext(){
			return(next);
		}


		//****************************************************************
		// Set Next
		//****************************************************************
		public void SetNext(DLListNode<T> n){
			next = n;
		}
	}


	//****************************************************************
	// DLList private data members (don't change this)
	//****************************************************************
	private DLListNode<T> header;
	private DLListNode<T> trailer;
	private DLListNode<T> current;
	private int size = 0;



	//****************************************************************
	//
	// constructor
	//
	// this this the base/default constructor
	//
	//****************************************************************
	// constructors
	public DLList(){
		header = new DLListNode<>(null, null, null);
		trailer = new DLListNode<>(null, header, null);
		header.SetNext(trailer);
		current = header;
	}


	//****************************************************************
	//
	// copy constructor
	//
	// this is the copy constructor
	//
	//****************************************************************
	public DLList(DLList<T> other){
		if (!other.IsEmpty()) {
			header = new DLListNode<>(null, null, null);
			trailer = new DLListNode<>(null, header, null);
			header.SetNext(trailer);
			DLListNode<T> walk = other.header.GetNext();
			current = header;
			for (int i = 0; i < other.size; i++) {
				DLListNode<T> newest = new DLListNode<>(walk.GetElement(), current, current.GetNext());
				current.GetNext().SetPrev(newest);
				current.SetNext(newest);
				current = newest;
				walk = walk.GetNext();
				size++;
			}
		}
	}

	//****************************************************************
	//
	// clear list method
	//
	// this method "clears" the list...deletes all nodes
	//****************************************************************
	public void Clear(){
		while (!IsEmpty())
			DeleteFirst();
		current = null;
	}


	//****************************************************************
	//
	// list size method
	//
	// this method returns the size of the list...returns 0 initially
	//
	//****************************************************************
	public int Size(){
		return(size);
	}


	//****************************************************************
	//
	// list is empty method
	//
	// this method returns true/false if the list is empty...
	// returns false initially
	//
	//****************************************************************
	public boolean IsEmpty(){
		return(size == 0);
	}


	//****************************************************************
	//
	// list at first method
	//
	// this list method returns true/false if the current index is at
	// the first/front node in the list
	// initially set to false
	//
	//****************************************************************
	public boolean AtFirst(){
		if (IsEmpty()) return false;
		if (current.GetPrev() == header)
			return true;
		else
			return false;
	}


	//****************************************************************
	//
	// list at last method
	//
	// this list method returns true/false if the current index is at
	// the first/front node in the list
	// initially set to false
	//
	//****************************************************************
	public boolean AtLast(){
		if (IsEmpty()) return false;
		if (current.GetNext() == trailer)
			return true;
		else
			return false;
	}



	//****************************************************************
	//
	// list seek first node method
	//
	// this method moves the current reference to the first node
	// returns a boolean true/false if successful
	//
	//****************************************************************
	public boolean First(){
		if (IsEmpty()) return false;
		current = header.GetNext();
		return true;

	}


	//****************************************************************
	//
	// list seek next node method
	//
	// this method moves the current reference to the next node and returns
	// true if successful
	// otherwise it returns false
	//
	//****************************************************************
	public boolean Next(){
		if (IsEmpty()) return false;
		else if (current.GetNext() == trailer)
			return false;
		else {
			current = current.GetNext();
			return true;
		}
	}


	//****************************************************************
	//
	// list seek last node method
	//
	// this method moves the current pointer to the last node in the
	// list if successful
	// otherwise it returns false
	//
	//****************************************************************
	public boolean Last(){
		if (IsEmpty()) return false;
		current = trailer.GetPrev();
		return true;

	}


	//****************************************************************
	//
	// list seek previous node method
	//
	// this method moves the current pointer to the previous node in the
	// list if successful
	// otherwise it returns false
	//
	//****************************************************************
	public boolean Previous(){
		if (IsEmpty()) return false;
		current = current.GetPrev();
		return true;

	}


	//****************************************************************
	//
	// list seek to node at location method
	//
	// this method "seeks" to the node in the list whose index is "loc"
	// example: if loc = 5, then the current reference will goto the 
	// 6th node in the list (indices start at 0)
	// returns true if successful and false otherwise
	//
	//****************************************************************
	public boolean Seek(int loc){
		if (IsEmpty()) return false;
		First();
		if (size > loc || loc <= 0) {
			for (int i = 0; i < loc; i++) 
				current = current.GetNext();
			return true;
			}
		return false;
	}


	//****************************************************************
	//
	// list data read method
	//
	// Return the content of node current.
	// Or return null if list is empty.
	//
	//****************************************************************
	public T DataRead(){
			if (IsEmpty()) return null;
			else return current.GetElement();
	}


	//****************************************************************
	//
	// list data write method
	//
	// Rewrite the content of node current if list is not empty
	//
	//****************************************************************
	public boolean DataWrite(T x){
			if (IsEmpty()) return false;
			else {
				current.SetElement(x);
				return true;
			}
	}


	//****************************************************************
	//
	// insert first method
	//
	// this method inserts "item" into a new "first" node in the list
	// current is pointing at the newly inserted first node
	// returns true if successful and false otherwise
	//
	//****************************************************************
	public boolean InsertFirst(T item){
		AddBetween(item, header, header.GetNext());
		First();
		return true;

	}


	//****************************************************************
	//
	// insert in between the given nodes
	//
	// this method inserts "item" a the "current" location in the list
	// returns true if successful and false otherwise
	//
	//****************************************************************
	private boolean AddBetween(T t, DLListNode<T> predecessor, DLListNode<T> successor){
		DLListNode<T> newest = new DLListNode<>(t, predecessor, successor);
		predecessor.SetNext(newest);
		successor.SetPrev(newest);
		size++;
		return true;

	}


	//****************************************************************
	//
	// insert last method
	//
	// this method inserts "item" into a new "last" node in the list
	// current is pointing at the newly inserted last node
	// returns true if successful and false otherwise
	//
	//****************************************************************
	public boolean InsertLast(T item){
		AddBetween(item, trailer.GetPrev(), trailer);
		Last();
		return true;

	}


	//****************************************************************
	//
	// delete first method
	//
	// this method deletes the first node in a list
	// current points to the following node or null if empty
	// returns true if delete was successful and false otherwise
	//
	//****************************************************************
	public boolean DeleteFirst(){
		if (IsEmpty()) return false;
		else {
			First();
			DeleteAt();
			if (IsEmpty())
				current = null;
			else
				First();
			return true;
		}

	}


	//****************************************************************
	//
	// delete at current position method
	//
	// Note: this should be similar to the "remove" method in our text
	//
	//****************************************************************
	public boolean DeleteAt(){
		if (IsEmpty()) return false;
		else {
			DLListNode<T> predecessor = current.GetPrev();
			DLListNode<T> successor = current.GetNext();
			predecessor.SetNext(successor);
			successor.SetPrev(predecessor);
			current.SetNext(null);
			current.SetPrev(null);
			size--;
			if (IsEmpty())
				current = null;
			return true;
		}

	}


	//****************************************************************
	//
	// delete last method
	//
	//****************************************************************
	public boolean DeleteLast(){
		if (IsEmpty()) return false;
		else {
			Last();
			DeleteAt();
			if (IsEmpty())
				current = null;
			else
				Last();
			return true;
		}

	}


	//****************************************************************
	//****************************************************************
}