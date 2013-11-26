import java.util.List;
import java.util.LinkedList;

class Vertex {
	List<Edge> edges;
	boolean visited;
	int data;
	
	public Vertex() {
		edges = new LinkedList<Edge>();
		visited = false;
		data = 0;
	}
	
	public Vertex(int val) {
		edges = new LinkedList<Edge>();
		visited = false;
		data = val;
	}
	
	public void printVertex() {
		String str = String.valueOf(data) + "\t";
		for(Edge e:edges) {
			str = str + String.valueOf(e.otherVertex(this).data) + "," + String.valueOf(e.weight) + "\t";
		}
		System.out.println(str);
	}
}
