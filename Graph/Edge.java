class Edge {
	Vertex vert1;
	Vertex vert2;
	int weight;
	
	public Edge(Vertex v1, Vertex v2) {
		vert1 = v1;
		vert2 = v2;
		weight = 1;
	}
	
	public Edge(Vertex v1, Vertex v2, int n) {
		vert1 = v1;
		vert2 = v2;
		weight = n;
		v1.edges.add(this);
		v2.edges.add(this);
	}
	
	public Vertex otherVertex(Vertex v) {
		if(v == vert1)
			return vert2;
		return vert1;
	}
	
}
