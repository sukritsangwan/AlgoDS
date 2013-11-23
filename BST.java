import java.util.Queue;
import java.util.ArrayDeque;


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
		if(treeIsEmpty())
			rootNode = new Node(value);
		else
			InsertUnderNode(value, rootNode);
	}
	
	private void InsertUnderNode(int val, Node n) {
		if(n.value > val) {
			if(n.left == null)
				n.left = new Node(val, n);
			else
				InsertUnderNode(val, n.left);
		}
		else {
			if(n.right == null)
				n.right = new Node(val, n);
			else
				InsertUnderNode(val, n.right);
		}
	}
	
	public void preOrderPrint() {
		if(rootNode == null)
			System.out.println("empty tree");
		printNodePre(rootNode);
	}
	
	private void printNodePre(Node current) {
		System.out.println(current);
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
}
