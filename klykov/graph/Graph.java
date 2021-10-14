package klykov.graph;

import java.util.*;

/**
 * The interface contains basic methods for creating a graph
 */
public interface Graph<T> {

    /**
     * Returns true if a graph contains the vertex.
     */
    boolean hasVertex(T vertex);

    /**
     * Returns true if a graph contains the edge with vertices vertexF and vertexS.
     */
    boolean hasEdge(T vertexF, T vertexS);

    /**
     * Returns the number of vertices in a graph.
     */
    int countVertices();

    /**
     * Returns the number of edges in a graph.
     */
    int countEdges();

    /**
     * Adds vertices to a graph.
     * @param data It is possible to put two vertices or an array of them.
     */
    void add(T... data);

    /**
     * Returns adjacent vertices if they exist.
     */
    Collection<T> adjacentTo(T vertex);

    /**
     * Returns vertices of a graph.
     */
    Collection<T> vertices();
}
