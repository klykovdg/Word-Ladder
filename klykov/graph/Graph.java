package klykov.graph;

import klykov.graph.traversal.*;
import java.util.*;

/**
 * The class contains all basic methods needed for a graph.
 * It is possible to create a graph on your own or use the class Handler.
 * @see klykov.assistant.Handler
 * @param <T> There is a parameter for creating a graph of any type of objects or primitives.
 */
public class Graph<T> {
    public Graph() {
        graph = new HashMap<>();
    }

    /**
     * Returns the Set of adjacent vertices if they exist.
     */
    public  Set<T> adjacentTo(T vertex) {
        return graph.get(vertex);
    }

    /**
     * Returns the Set of the graph vertices.
     */
    public Set<T> vertices() {
        return graph.keySet();
    }

    /**
     * Returns true if this graph contains the vertex.
     */
    public boolean hasVertex(T vertex) {
        return graph.containsKey(vertex);
    }

    /**
     * Returns true if this graph contains the edge with vertices vertexF and vertexS.
     */
    public boolean hasEdge(T vertexF, T vertexS) {
        if (hasVertex(vertexF)) {
            return graph.get(vertexF).contains(vertexS);
        }
        return false;
    }

    /**
     * Returns the number of vertices in this graph.
     */
    public int countVertices() {
        return graph.size();
    }

    /**
     * Returns the number of edges in this graph.
     */
    public int countEdges() {
        Set<String> differentCombinations = new HashSet<>();
        for (T t : graph.keySet()) {
            for (T tt : graph.get(t)) {
                if (!differentCombinations.contains(tt + "" + t)) {
                    differentCombinations.add(t + "" + tt);
                }
            }
        }
        return differentCombinations.size();
    }

    /**
     * The method adds vertices to the map and connects them between each other.
     * Uniqueness is provided with Set.
     * @param data It is possible to put two vertices or an array of them.
     */
    public void add(T... data) {
        for (T currentV : data) {
            if (!hasVertex(currentV)) {
                graph.put(currentV, new HashSet<>());
            }
            for (T t : data) {
                if (currentV != t) {
                    graph.get(currentV).add(t);
                }
            }
        }
    }

    /**
     * Since we use the map as the representation of the graph,
     * it is one of the way to traverse the graph easy and output it onto the screen.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, Set<T>> entry : graph.entrySet()) {
            sb.append(entry.getKey())
              .append(" ")
              .append(entry.getValue())
              .append("\n");
        }
        return sb.toString();
    }

    /**
     * Sets the behaviour of graph traversal.
     * @see klykov.graph.traversal.Traversal
     */
    public void setTraversal(Traversal<T> t) {
        this.t = t;
    }

    /**
     * Finds the shortest way from startV to endV.
     */
    public void findPath(T startV, T endV) {
        t.findPath(startV, endV);
    }

    /**
     * The graph is a map stores vertices and their adjacent vertices.
     * The Set as the key of the map has been chosen because we need operations "add",
     * "contains" and auto checking of uniqueness.
     */
    private Map<T, Set<T>> graph;
    /**
     * @see klykov.graph.traversal.Traversal
     */
    private Traversal<T> t;
}
