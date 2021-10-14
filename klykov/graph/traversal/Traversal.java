package klykov.graph.traversal;

import klykov.graph.GraphMap;

/**
 * The interface is used for creating the set of classes
 * which realize a variable behaviour of finding the path between two values.
 * Thus we encapsulate a variable part to have the opportunity to change it later without affecting a constant part.
 * The behaviour can be set dynamically with the help of {@link GraphMap#setTraversal(Traversal)}.
 */
public interface Traversal<T> {
    void findPath(T startV, T endV);
}
