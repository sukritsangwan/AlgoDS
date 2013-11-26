import java.util.List;
import java.util.LinkedList;

class Graph {
	List<Edge> edges;
	List<Vertex> vertices;
	
	public Graph() {
		edges = new LinkedList<Edge>();
		vertices = new LinkedList<Vertex>();
	}
	
	public void addVertex(int val) {
		vertices.add(new Vertex(val));
	}
	
	public void addEdge(int id1, int id2, int w) {
		edges.add(new Edge(getVertex(id1), getVertex(id2), w));
	}
	
	public Vertex getVertex(int val) {
		for(Vertex v:vertices)
			if(val == v.data)
				return v;
		return vertices.get(0);
	}
	
	public void unvisitAll() {
		for(Vertex v:vertices)
			v.visited = false;
	}
	
	public void printGraph() {
		for(Vertex v:vertices)
			v.printVertex();
	}
	
	public List<Vertex> visitedVertices() {
		List<Vertex> visited = new LinkedList<Vertex>();
		for(Vertex v:vertices)
			if(v.visited)
				visited.add(v);
		return visited;
	}
	
	public boolean connectedVertices(int data1, int data2) {
		Vertex v1 = getVertex(data1);
		Vertex v2 = getVertex(data2);
		for(Edge e : v1.edges)
			if(e.otherVertex(v1) == v2)
				return true;
		return false;
	}
	
}
