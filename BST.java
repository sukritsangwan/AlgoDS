import java.util.Queue;
import java.util.ArrayDeque;
import java.lang.Exception;

public class BST {
	private Node rootNode;
	
	public boolean treeIsEmpty(){
		if(rootNode == null)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void insert(int value) {
		if(rootNode == null)
			rootNode = new Node(value);
		else
			insertUnderNode(rootNode, new Node(value));
	}
	
	private void insertUnderNode(Node parent, Node child) {
		if(parent.value > child.value) {
			if(parent.left == null) {
				parent.left = child;
				child.parent = parent;
			}
			else
				insertUnderNode(parent.left, child);
		}
		else {
			if(parent.right == null) {
				parent.right = child;
				child.parent = parent;				
			}
			else
				insertUnderNode(parent.right, child);
		}
	}
	
	public void preOrderPrint() {
		if(rootNode == null)
			System.out.println("empty tree");
		printNodePre(rootNode);
	}
	
	private void printNodePre(Node current) {
		System.out.println(current.value);
		if(current.left != null)
			printNodePre(current.left);
		if(current.right != null)
			printNodePre(current.right);
	}
	
	public void printLevels() {
		Queue<Node> q1 = new ArrayDeque<Node>();
		Queue<Node> q2 = new ArrayDeque<Node>();
		if(rootNode != null)
			q1.add(rootNode);
		String s;
		while(!q1.isEmpty()) {
			s = "";
			while(!q1.isEmpty()) {
				Node n = q1.poll();
				s = s + n.value + " ";
				if(n.left != null)
					q2.add(n.left);
				if(n.right != null)
					q2.add(n.right);
			}
			System.out.println(s);
			Queue<Node> temp = q1;
			q1 = q2;
			q2 = temp;
		}
	}
	
	public int predecessor(int val) throws Exception {
		Node preNode = predecessorNode(searchUnderNode(rootNode, val));
		if(preNode == null)
			throw new Exception("this is the smallest node in the tree");
		return preNode.value;
	}
	
	private Node predecessorNode(Node thisNode) throws Exception {
		if(null == thisNode)
			throw new Exception("there is no such element in the tree");
		if(null == thisNode.left){
			// search above it
			Node temp = thisNode;
			while(temp.parent != null) {
				if(temp.parent.value < thisNode.value)
					return temp.parent;
			}
			return null;
		}
		else {
			Node temp = thisNode.left;
			while(temp.right != null)
				temp = temp.right;
			return temp;
		}
	}
	
	public int successor(int val) throws Exception {
		Node preNode = successorNode(searchUnderNode(rootNode, val));
		if(preNode == null)
			throw new Exception("this is the largest node in the tree");
		return preNode.value;
	}
	
	private Node successorNode(Node thisNode) throws Exception {
		if(null == thisNode)
			throw new Exception("there is no such element in the tree");
		if(null == thisNode.right){
			// search above it
			Node temp = thisNode;
			while(temp.parent != null) {
				if(temp.parent.value > thisNode.value)
					return temp.parent;
			}
			return null;
		}
		else {
			Node temp = thisNode.right;
			while(temp.left != null)
				temp = temp.left;
			return temp;
		}
	}
	
	public void delete(int val) {
		if(null == rootNode)
			return;
		Node toDel = searchUnderNode(rootNode, val);
		deleteNode(toDel);
	}
	
	private void deleteNode(Node n) {
		if(null == n.left) {
			if(null == n.right)
				n.parent.deleteChild(n);
			else {
				Node parent = n.parent;
				parent.deleteChild(n);
				insertUnderNode(parent, n.right);
				n.right = null;
			}
		}
		else {
			if(null == n.right) {
				Node parent = n.parent;
				parent.deleteChild(n);
				insertUnderNode(parent, n.left);
				n.left = null;
			}
			else {
				// delete this node and promote its successor
				try {
					Node left = n.left;
					Node right = n.right;
					Node parent = n.parent;
					parent.deleteChild(n);
					Node newNode = successorNode(n);
					deleteNode(newNode);
					insertUnderNode(parent, newNode);
					newNode.left = left;
					if(newNode != right)
						newNode.right = right;
					n.left = null;
					n.right = null;
				}
				catch(Exception ex) {
					System.out.println("lol");
				}
			}
		}
	}
	
	private Node searchUnderNode(Node currNode, int val) {
		if(null == currNode)
			return null;
		if(val == currNode.value)
			return currNode;
		else if(val > currNode.value)
			return searchUnderNode(currNode.right, val);
		else
			return searchUnderNode(currNode.left, val);
	}
}
