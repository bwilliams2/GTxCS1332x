import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
    	List<Vertex<T>> visitedOrder = new ArrayList<Vertex<T>>();
    	
    	rBFS(start, graph, visitedOrder);
    	
    	return visitedOrder;
    }
    
    private static <T> void rBFS(Vertex<T> start, Graph<T> graph, List<Vertex<T>> visitedOrder) {
    	Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
    	LinkedList<Vertex<T>> toVisit = new LinkedList<Vertex<T>>();
    	
    	Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

    	
    	toVisit.add(start);
    	visited.add(start);
		visitedOrder.add(start);
    	
    	while (!toVisit.isEmpty()) {
    		Vertex<T> current = toVisit.remove();
    		for (VertexDistance<T> vertDist : adjList.get(current)) {
    			Vertex<T> next = vertDist.getVertex();
    			if (visited.contains(next)) {
    				continue;
    			}
        		visited.add(next);
        		visitedOrder.add(next);
    			toVisit.add(next);
    		}
    	}
    }
    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
    	List<Vertex<T>> visitedOrder = new ArrayList<Vertex<T>>();
    	Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
    	
    	rDFS(start, graph, visitedOrder, visited);
    	
    	return visitedOrder;
    }
    
    private static <T> void rDFS(Vertex<T> start, Graph<T> graph, List<Vertex<T>> visitedOrder, Set<Vertex<T>> visited) {
    	if (visited.contains(start) || visited.size() == graph.getVertices().size()) {
    		return;
    	}
    	
    	visited.add(start);
    	visitedOrder.add(start);
    	
    	Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
    	
    	for (VertexDistance<T> vertDist : adjList.get(start)) {
    		rDFS(vertDist.getVertex(), graph, visitedOrder, visited);
    	}
    	return;
    }
}