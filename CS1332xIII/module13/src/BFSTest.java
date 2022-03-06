import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BFSTest {

	@Test
	void test() {
		Set<Edge<Integer>> edges = new HashSet<Edge<Integer>>();
		
		Vertex<Integer> first = new Vertex(1);
		Vertex<Integer> second = new Vertex(2);
		Vertex<Integer> third = new Vertex(3);
		Vertex<Integer> forth = new Vertex(4);

		Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
		vertices.add(first);
		vertices.add(second);
		vertices.add(third);
		vertices.add(forth);
		
		Edge<Integer> one = new Edge(first, second, 1);
		Edge<Integer> two = new Edge(second, third, 1);
		Edge<Integer> three = new Edge(first, forth, 1);
		Edge<Integer> four = new Edge(second, forth, 1);

		edges.add(one);
		edges.add(two);
		edges.add(three);
		edges.add(four);
		
		Graph<Integer> graph = new Graph(vertices, edges);
		
		List<Vertex<Integer>> visits = GraphAlgorithms.bfs(first, graph);
		assertEquals(visits.size(), 4);
	}

}
