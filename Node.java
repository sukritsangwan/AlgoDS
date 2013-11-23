public class Node {
	Node left;
	Node right;
	Node parent;
	int value;
	
	public Node(int v) {
		value = v;
		left = null;
		right = null;
		parent = null;
	}
	
	public Node(int v, Node p) {
		value = v;
		parent = p;
		left = null;
		right = null;
	}

	
}