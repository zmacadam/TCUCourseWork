
class BTree<T> {

	private static class BTNode<T> {
		private T data;
		private BTNode<T> left;
		private BTNode<T> right;
		private BTNode<T> parent;
		//****************************************************************
		// Default Constructor
		//****************************************************************
		BTNode(T value) {
			data = value;
			left = null;
			right = null;
			parent = null;
		}
		//****************************************************************
		// Get node's element
		//****************************************************************
		public T getElement() {
			return data;
		}
		//****************************************************************
		// Set node's element
		//****************************************************************
		public boolean setElement(T item) {
			data = item;
			return true;
		}	
		//****************************************************************
		// Set node's left node
		//****************************************************************
		public void setLeft(BTNode<T> l) {
			left = l;
		}
		//****************************************************************
		// Get node's left node
		//****************************************************************
		public BTNode<T> getLeft() {
			return left;
		}
		//****************************************************************
		// Set node's right node
		//****************************************************************
		public void setRight(BTNode<T> r) {
			right = r;
		}
		//****************************************************************
		// Get node's right node
		//****************************************************************
		public BTNode<T> getRight() {
			return right;
		}
	}
	private BTNode<T> root = null;
	//****************************************************************
	//
	// BTree constructor to build a binary expression tree from either
	// a postfix, prefix, or infix expression string that is given.
	// Initializes root with the element null.
	//
	//
	//****************************************************************
	public BTree(String expression, String type) {
		root = new BTNode<>(null);
		if (type.equals("postfix")) 
			buildPostfix(expression);
		if (type.equals("prefix")) 
			buildPrefix(expression);
		if (type.equals("infix"))
			buildInfix(expression);
	}
	//********************************************************************
	// Build Postfix Method
	//
	// The build postfix method creates a binary tree from the given
	// postfix expression string.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s	String		value		Postfix expression string.
	//
	// Local Variables
	// ---------------
	// nodeStack		Stack		Stack of BTNodes to create Binary Tree.
	// i				int			Loop iterator.
	// curToken			String		Current token of the expression.
	// newest			BTNode		New node that is created.
	//
	//*****************************************************************
	private void buildPostfix(String s) {
		Stack<BTNode<T>> nodeStack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			String curToken = s.substring(i, i+1);
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				BTNode<T> newest = new BTNode(curToken);
				newest.setRight(nodeStack.pop());
				newest.setLeft(nodeStack.pop());
				nodeStack.push(newest);
			}
			else {
				BTNode<T> newest = new BTNode(curToken);
				nodeStack.push(newest);
			}
		}
		root = nodeStack.pop();
	}
	//********************************************************************
	// Build Prefix Method
	//
	// The build prefix method creates a binary tree from the given
	// prefix expression string.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s	String		value		Prefix expression string.
	//
	// Local Variables
	// ---------------
	// nodeStack		Stack		Stack of BTNodes to create Binary Tree.
	// i				int			Loop iterator.
	// curToken			String		Current token of the expression.
	// newest			BTNode		New node that is created.
	//
	//*****************************************************************
	private void buildPrefix(String s) {
		Stack<BTNode<T>> nodeStack = new Stack<>();
		for (int i = s.length(); i > 0; i--) {
			String curToken = s.substring(i-1, i);
			if (!Character.isLetterOrDigit(curToken.charAt(0))) {
				BTNode<T> newest = new BTNode(curToken);
				newest.setLeft(nodeStack.pop());
				newest.setRight(nodeStack.pop());
				nodeStack.push(newest);
			}
			else {
				BTNode<T> newest = new BTNode(curToken);
				nodeStack.push(newest);
			}
		}
		root = nodeStack.pop();
	}
	//********************************************************************
	// Build Infix Method
	//
	// The build infix method creates a binary tree from the given
	// infix expression string by first converting it to postfix then
	// populate the binary tree.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// s	String		value		Infix expression string.
	//
	// Local Variables
	// ---------------
	// postfix			String		Postfix string created from infix expression.
	// postStack		Stack		Stack of BTNodes to create postfix expression.
	// i				int			Loop iterator.
	// nodeStack		Stack		Stack of BTNodes to create Binary Tree.
	// curToken			String		Current token of the expression.
	// newest			BTNode		New node that is created.
	//
	//*****************************************************************
	private void buildInfix(String s) {
		String postfix = "";
		Stack<String> postStack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			String curToken = s.substring(i, i+1);
			char tokenCheck = s.charAt(i);
			if (Character.isLetterOrDigit(tokenCheck))
				postfix += curToken;
			else if (curToken.equals("("))
				postStack.push(curToken);
			else if (curToken.equals(")")) {
				while (!postStack.isEmpty() && !postStack.top().equals("("))
					postfix += postStack.pop();
				postStack.pop();
			}
			else {
				while (!postStack.isEmpty() && getPriority(curToken) <= getPriority(postStack.top())) {
					postfix += postStack.pop();
				}
				postStack.push(curToken);
			}
		}
		while (!postStack.isEmpty())
			postfix += postStack.pop();

		Stack<BTNode<T>> nodeStack = new Stack<>();
		for (int i = 0; i < postfix.length(); i++) {
			String curToken = postfix.substring(i, i+1);
			if (!Character.isLetterOrDigit(postfix.charAt(i))) {
				BTNode<T> newest = new BTNode(curToken);
				newest.setRight(nodeStack.pop());
				newest.setLeft(nodeStack.pop());
				nodeStack.push(newest);
			}
			else {
				BTNode<T> newest = new BTNode(curToken);
				nodeStack.push(newest);
			}
		}
		root = nodeStack.pop();
	}
	//********************************************************************
	// Print Traversals Method
	//
	// The print traversals method prints all 4 traversals of the Binary Tree
	// to the console screen in the order: prefix, infix, postfix, levelorder.
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
	// None
	//
	//*****************************************************************
	public void printTraversals() {
		if (root != null) {
			printPreorder(root);
			System.out.println();
			printInorder(root);
			System.out.println();
			printPostorder(root);
			System.out.println();
			levelOrder(root);
			System.out.println();
		}
	}
	//********************************************************************
	// Print Preorder Method
	//
	// The print preorder method does a preorder traversal of the binary
	// tree from the given root node and prints it to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// node		BTNode		Reference		Root of the binary tree.
	//
	// Local Variables
	// ---------------
	// None
	//
	//*****************************************************************
	private void printPreorder(BTNode<T> node) {
		if (node == null)
			return;
		System.out.print(node.getElement() + " ");
		printPreorder(node.getLeft());
		printPreorder(node.getRight());	
	}
	//********************************************************************
	// Print Postorder Method
	//
	// The print postorder method does a postorder traversal of the binary
	// tree from the given root node and prints it to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// node		BTNode		Reference		Root of the binary tree.
	//
	// Local Variables
	// ---------------
	// None
	//
	//*****************************************************************
	private void printPostorder(BTNode<T> node) {
		if (node == null)
			return;
		printPostorder(node.getLeft());
		printPostorder(node.getRight());
		System.out.print(node.getElement() + " ");
	}
	//********************************************************************
	// Print Inorder Method
	//
	// The print inorder method does an inorder traversal of the binary
	// tree from the given root node and prints it to the console.
	// 	
	// Return Value	
	// ------------
	// None
	//
	// Function Parameters
	// -------------------
	// node		BTNode		Reference		Root of the binary tree.
	//
	// Local Variables
	// ---------------
	// None
	//
	//*****************************************************************
	private void printInorder(BTNode<T> node) {
		if (node.getLeft() == null && node.getRight() == null)
			System.out.print(node.getElement());
		else {
			System.out.print("(");
			printInorder(node.getLeft());
			System.out.print(" " + node.getElement() + " ");
			printInorder(node.getRight());
			System.out.print(")");
		}
	}
	//********************************************************************
	// Level Order Method
	//
	// The level order method traverses the binary tree in level order 
	// creating a string that is printed to the console.
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
	// levelNotation		String		Level order string being returned.
	// nodeQueue			Stack		Stack containing tree nodes.
	// newest				BTNode		Reference to current node.
	//
	//*****************************************************************
	private void levelOrder(BTNode<T> node) {
		String levelNotation = "";
		Queue<BTNode<T>> nodeQueue = new Queue<>();
		nodeQueue.enqueue(node);
		while (!nodeQueue.isEmpty()) {
			BTNode<T> newest = nodeQueue.dequeue();
			levelNotation += newest.getElement() + " ";
			if (newest.getLeft() != null) {
				nodeQueue.enqueue(newest.getLeft());
			}
			if (newest.getRight() != null) {
				nodeQueue.enqueue(newest.getRight());
			}
		}
		System.out.print(levelNotation);
	}
	//********************************************************************
	// Print Graphic Method
	//
	// The print graphic method creates a 2D array of the nodes within 
	// the binary tree and prints the 2D array to the console in the
	// shape of the tree.
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
	// depth		int			Depth of the binary tree.
	// width		int			Maximum width of the binary tree.
	// arrayWidth	int			Width of the 2D array to be printed.
	// twoDTree		Array[][]	2D array to store the binary tree.
	// nodeQueue	Queue		Queue of nodes within the binary tree.
	// i			int			Loop iterator.
	// maxNodes		int			Maximum width of current level.
	// nodeCount	int			Number of nodes on the current level.
	// j			int			Loop iterator.
	// newest		BTNode		Current node to be added to the 2D array.
	//
	//*****************************************************************
	public void printGraphic() {
		if (root.getElement() == null) {
			return;
		}
		int depth = numLevels(root);
		int width = (int) Math.pow(2, depth - 1);
		int arrayWidth = (width*2);
		String[][] twoDTree = new String[depth][arrayWidth + 1];
		Queue<BTNode<T>> nodeQueue = new Queue<>();
		nodeQueue.enqueue(root);
		for (int i = 0; i < depth; i++) {
			int maxNodes = (int) Math.pow(2, i);
			int nodeCount = nodeQueue.size();
			for (int j = 0; j < nodeCount; j++) {
				BTNode<T> newest = nodeQueue.dequeue();
				twoDTree[i][(arrayWidth/(2 * maxNodes)) + (j * (arrayWidth/maxNodes))] = (String) newest.getElement();
				if (newest.getLeft() != null) {
					nodeQueue.enqueue(newest.getLeft());
				}
				else {
					BTNode<T> leftNull = new BTNode<>(null);
					nodeQueue.enqueue(leftNull);
				}
				if(newest.getRight() != null) {
					nodeQueue.enqueue(newest.getRight());
				}
				else {
					BTNode<T> rightNull = new BTNode<>(null);
					nodeQueue.enqueue(rightNull);
				}
			}
		}


		for (String[] row : twoDTree) {
			for (String node : row) {
				if (node == null) {
					System.out.print(" ");
				} else {
					System.out.print(node);
				}
			}
			System.out.println();
		}
	}
	//********************************************************************
	// Get Priority Method
	//
	// The get priority method returns the stack priority of the string
	// given. Only operators have a priority.
	// 	
	// Return Value	
	// ------------
	// int		Priority of the string.
	//
	// Function Parameters
	// -------------------
	// x	String		value	The string whose priority needs to be compared.
	//
	// Local Variables
	// ---------------
	// None
	//
	//*****************************************************************
	private int getPriority(String x) {
		if (x.equals("+") || x.equals("-"))
			return 1;
		if (x.equals("*") || x.equals("/"))
			return 2;
		if (x.equals("^"))
			return 3;
		return -1;
	}
	//********************************************************************
	// Number of Levels Method
	//
	// The number of levels method calculates the depth of the given tree.
	// 	
	// Return Value	
	// ------------
	// int		Depth of the given tree.
	//
	// Function Parameters
	// -------------------
	// r	BTNode		Reference		Reference to the root of the given tree.
	//
	// Local Variables
	// ---------------
	// left		int		Depth of the left subtree.
	// right	int		Depth of the right subtree.
	//
	//*****************************************************************
	private int numLevels(BTNode<T> r) {
		if (r == null) {
			return 0;
		}
		else {
			int left = numLevels(r.getLeft());
			int	right = numLevels(r.getRight());
			if (left > right) {
				return (left + 1);
			}
			else 
				return (right + 1);
		}
	}
}
