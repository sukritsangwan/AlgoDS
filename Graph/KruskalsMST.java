import java.util.Collections;

class KruskalsMST {
	/**
	 * Sort all edges according to weight
	 * visit each node in ascending order, if it does not form cycle include in the MST
	 * 
	 * @param inputGraph : calculate MST of this graph
	 * @return min span tree using Kruskal's algo
	 */
	public Graph getKruskalsMST(Graph inputGraph) {
		inputGraph.unvisitAll();
		Graph mst = new Graph();
		
		for(Vertex v : inputGraph.vertices)
			mst.addVertex(v.data);
		
		Collections.sort(inputGraph.edges, CompareEdgeWeight);
		for(Edge e : inputGraph.edges) {
			if(!mst.connectedVertices(e.vert1.data, e.vert2.data))
				mst.addEdge(e.vert1.data, e.vert2.data);
		}
		
		return mst;
	}
	
	class CompareEdgeWeight implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			return e1.weight - e2.weight;
		}
	}
}
