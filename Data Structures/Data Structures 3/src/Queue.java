
class Queue<T> {
	
	//****************************************************************
	// Default Constructor
	//****************************************************************
	SLList<T> queueList = new SLList<T>();
	
	//****************************************************************
	// Returns the number of elements in the queue.
	//****************************************************************
	public int size() {
		return queueList.size();
	}
	//****************************************************************
	// Returns if the queue is empty or not.
	//****************************************************************
	public boolean isEmpty() {
		return queueList.isEmpty();
	}
	//****************************************************************
	// Returns the front element of the queue.
	//****************************************************************
	public T first() {
		return queueList.getFirst();
	}
	//****************************************************************
	// Adds an element to the end of the queue.
	//****************************************************************
	public void enqueue(T element) {
		queueList.addLast(element);
	}
	//****************************************************************
	// Removes and returns the front element of the queue.
	//****************************************************************
	public T dequeue() {
		return queueList.returnFirst();
	}
}
