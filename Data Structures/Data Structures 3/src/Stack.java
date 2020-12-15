
class Stack<T> {
	
	//****************************************************************
	// Default Constructor
	//****************************************************************
	SLList<T> stackList = new SLList<T>();
	
	//****************************************************************
	// Returns the number of elements in the stack.
	//****************************************************************
	public int size() {
		return stackList.size();
	}
	//****************************************************************
	// Returns if the stack is empty or not.
	//****************************************************************
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
	//****************************************************************
	// Returns the top element of the stack.
	//****************************************************************
	public T top() {
		return stackList.getFirst();
	}
	//****************************************************************
	// Pushes a new element to the top of the stack.
	//****************************************************************
	public void push(T element) {
		stackList.addFirst(element);
	}
	//****************************************************************
	// Removes and returns the top element of the stack.
	//****************************************************************
	public T pop() {
		return stackList.returnFirst();
	}
}
