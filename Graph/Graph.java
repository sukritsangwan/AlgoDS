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
	
	private void unvisitAll() {
		for(Vertex v:vertices)
			v.visited = false;
	}
	
	public void printGraph() {
		for(Vertex v:vertices)
			v.printVertex();
	}
	
	private List<Vertex> visitedVertices() {
		List<Vertex> visited = new LinkedList<Vertex>();
		for(Vertex v:vertices)
			if(v.visited)
				visited.add(v);
		return visited;
	}
	
	public Graph primsMST() {
		this.unvisitAll();
		Graph mst = new Graph();
		
		Vertex v = this.vertices.get(0);
		mst.addVertex(v.data);
		v.visited = true;
		Edge minE = v.edges.get(0);
		for(Edge e:v.edges)
			if(e.weight < minE.weight)
				minE = e;
		mst.addVertex(minE.otherVertex(v).data);
		mst.addEdge(v.data, minE.otherVertex(v).data, minE.weight);
		minE.otherVertex(v).visited = true;
		
		while(mst.vertices.size() < this.vertices.size()) {
			minE = null;
			for(Vertex vert:this.visitedVertices()) {
				for(Edge e:vert.edges) {
					if(!e.otherVertex(vert).visited) {
						if(null == minE || e.weight < minE.weight)
							minE = e;
					}
				}
			}
			if(minE.vert1.visited)
				v = minE.vert1;
			else
				v = minE.vert2;
			mst.addVertex(minE.otherVertex(v).data);
			mst.addEdge(v.data, minE.otherVertex(v).data, minE.weight);
			minE.otherVertex(v).visited = true;
		}
		
		return mst;
	}
	
}
