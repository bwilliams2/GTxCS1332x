import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PrimsTest {

	@Test
	void test() {
		Set<Edge<Integer>> edges = new HashSet<Edge<Integer>>();
		
		Vertex<Integer> first = new Vertex(1);
		Vertex<Integer> second = new Vertex(2);
		Vertex<Integer> third = new Vertex(3);
		Vertex<Integer> forth = new Vertex(4);
		Vertex<Integer> fifth = new Vertex(5);

		Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
		vertices.add(first);
		vertices.add(second);
		vertices.add(third);
		vertices.add(forth);
		vertices.add(fifth);

		
		Edge<Integer> one = new Edge(first, second, 3);
		Edge<Integer> two = new Edge(second, third, 6);
		Edge<Integer> three = new Edge(first, forth, 1);
		Edge<Integer> four = new Edge(second, forth, 2);		
		Edge<Integer> five = new Edge(second, fifth, 2);
		Edge<Integer> six = new Edge(third, fifth, 1);
		Edge<Integer> one2 = new Edge(second, first, 3);
		Edge<Integer> two2 = new Edge(third, second, 6);
		Edge<Integer> three2 = new Edge(forth, first, 1);
		Edge<Integer> four2 = new Edge(forth, second, 2);		
		Edge<Integer> five2 = new Edge(fifth, second, 2);
		Edge<Integer> six2 = new Edge(fifth, third, 1);


		edges.add(one);
		edges.add(two);
		edges.add(three);
		edges.add(four);
		edges.add(five);
		edges.add(six);
		edges.add(one2);
		edges.add(two2);
		edges.add(three2);
		edges.add(four2);
		edges.add(five2);
		edges.add(six2);

		
		Graph<Integer> graph = new Graph(vertices, edges);
		
		Set<Edge<Integer>> visits = GraphAlgorithms.prims(first, graph);
		assertEquals(visits.size(), 4);
	}

}
