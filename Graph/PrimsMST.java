class PrimsMST {
	/**
	 * use prim's MST algo
	 * takes O(V*E) where V is number of vertices and E is number of edges
	 * TODO: fast implementation
	 * @param inputGraph : the graph for which MST is to be found out
	 * @return the Minimum Spanning Tree
	 */
	public Graph getPrimsMST(Graph inputGraph) {
		// clear the visited flag of all vertices
		inputGraph.unvisitAll();
		Graph mst = new Graph();
		
		// add first vertex to MST and its shortest edge (along with other vertex of the edge)
		Vertex v = inputGraph.vertices.get(0);
		mst.addVertex(v.data);
		v.visited = true;
		// minE denotes the minimum weight edge between visited nodes an unvisited nodes
		Edge minE = v.edges.get(0);
		for(Edge e : v.edges)
			if(e.weight < minE.weight)
				minE = e;
		// add the vertex at the other end of the min weighted edge
		mst.addVertex(minE.otherVertex(v).data);
		mst.addEdge(v.data, minE.otherVertex(v).data, minE.weight);
		minE.otherVertex(v).visited = true;
		
		// find min weight edge b/w visited and unvisited nodes
		// add the vertex at the other end of edge
		// repeat this process until all the vertices are visited
		// this loop is repeated V times, so running time is O(V * E)
		while(mst.vertices.size() < inputGraph.vertices.size()) {
			minE = null;
			
			// each edge will be traversed twice in this nested "for" loops, so O(E)
			for(Vertex vert : inputGraph.visitedVertices()) {
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
