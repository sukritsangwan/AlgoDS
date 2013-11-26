public class TestRun {
	public static void main(String[] args) {
		Graph g = new Graph();
		for(int i = 1; i < 8; i++)
			g.addVertex(i);
		g.addEdge(1, 2, 48);
		g.addEdge(1, 3, 22);
		g.addEdge(2, 4, 8);
		g.addEdge(3, 4, 26);
		g.addEdge(3, 5, 40);
		g.addEdge(3, 6, 45);
		g.addEdge(4, 5, 18);
		g.addEdge(4, 6, 35);
		g.addEdge(4, 7, 21);
		g.addEdge(5, 6, 20);
		g.addEdge(6, 7, 25);
		g.printGraph();
		System.out.println("Prim's MST");
		g.primsMST().printGraph();
	}
}
