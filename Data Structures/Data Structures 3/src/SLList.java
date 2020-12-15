
class SLList<T> {

	private static class SLListNode<T> {
		private T data;
		private SLListNode<T> next;

		//****************************************************************
		// Default Node Constructor
		//****************************************************************
		SLListNode(T value, SLListNode<T> n){
			data = value;
			next = n;
		}
		//****************************************************************
		// Returns the element of a node.
		//****************************************************************
		public T GetElement(){
			return data;
		}
		//****************************************************************
		// Sets the element of a node.
		//****************************************************************
		public boolean SetElement(T item){
			data = item;
			return(true);
		}
		//****************************************************************
		// Returns the next node in the list.
		//****************************************************************
		public SLListNode<T> GetNext(){
			return next;
		}
		//****************************************************************
		// Sets the next node in the list
		//****************************************************************
		public void SetNext(SLListNode<T> n){
			next = n;
		}
	}
	private SLListNode<T> head;
	private SLListNode<T> tail;
	private int size = 0;

	//****************************************************************
	// Default Constructor
	//****************************************************************
	public SLList() {
		head = new SLListNode<>(null, null);
		tail = new SLListNode<>(null, null);
	}
	//****************************************************************
	// Returns the number of nodes in the list.
	//****************************************************************
	public int size() {
		return size;
	}
	//****************************************************************
	// Returns if the list is empty or not.
	//****************************************************************
	public boolean isEmpty() {
		return (size == 0);
	}
	//****************************************************************
	// Removes and returns the first node in the list.
	//****************************************************************
	public T returnFirst() {
		T temp = getFirst();
		head = head.GetNext();
		size--;
		if (size <= 1)
			tail = head;
		return temp;
	}
	//****************************************************************
	// Returns the first node in the list.
	//****************************************************************
	public T getFirst() {
		if (isEmpty())
			return null;
		return head.GetElement();
	}
	//****************************************************************
	// Adds a new node to the front of the list.
	//****************************************************************
	public boolean addFirst(T item) {
		SLListNode<T> newest = new SLListNode<>(item, null);
		if (!isEmpty())
			newest.SetNext(head);
		else 
			tail = newest;
		head = newest;
		size++;
		return true;
	}
	//****************************************************************
	// Adds a new node to the end of the list.
	//****************************************************************
	public boolean addLast(T item) {
		SLListNode<T> newest = new SLListNode<>(item, null);
		if (!isEmpty())
			tail.SetNext(newest);
		else
			head = newest;
		tail = newest;
		size++;
		return true;
	}
}
