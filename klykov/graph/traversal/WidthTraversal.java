package klykov.graph.traversal;

import klykov.graph.*;
import java.util.*;

/**
 * A width traversal implementation.
 */
public class WidthTraversal<T> implements Traversal<T> {
    public WidthTraversal(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * @param help If a user set the flag "help" on true the method will offer some values to choose
     *             when he makes a mistake or input values which are absent in the graph.
     */
    public WidthTraversal(GraphMap<T> graph, boolean help) {
        this(graph);
        this.help = help;
    }

    /**
     * Finds the shortest way from startV to endV.
     */
    public void findPath(T startV, T endV) {
        if (graph.hasVertex(startV) && graph.hasVertex(endV) && !startV.equals(endV)) {
            que = new LinkedList<>();
            distance = new HashMap<>();
            shortestPathTree = new HashMap<>();
            T currentV;

            que.add(startV);
            distance.put(startV, 0);
            while (!que.isEmpty()) {
                currentV = que.poll();
                for (T neighbour : graph.adjacentTo(currentV)) {
                    if (neighbour.equals(endV)) {
                        shortestPathTree.put(neighbour, currentV);
                        pathTo(neighbour, startV);
                        System.out.println("\nThe distance = " + (distance.get(currentV) + 1));
                        return;
                    } else if (!distance.containsKey(neighbour)) {
                        que.offer(neighbour);
                        distance.put(neighbour, (distance.get(currentV) + 1));
                        shortestPathTree.put(neighbour, currentV);
                    }
                }
            }
            if (help) {
                offer("The path is not possible in a current graph\n" +
                        "Try again or use the list below");
            } else {
                System.out.println("The path is not possible in a current graph");
            }
        } else if (help) {
            if (!graph.hasVertex(startV) && !graph.hasVertex(endV)) {
                offer("Unfortunately, the values " + startV + " and " + endV + " are absent\n" +
                        "Choose from the list or try again on your own");
            } else if (!graph.hasVertex(startV)) {
                offer("Unfortunately, the start value " + startV + " is absent\n" +
                        "Choose from the list or try again on your own");
            } else if (!graph.hasVertex(endV)) {
                offer("Unfortunately, the end value " + endV + " is absent\n" +
                        "Choose from the list or try again on your own");
            } else {
                offer("Please, put in any different values or choose from the list below");
            }
        } else {
            if (!graph.hasVertex(startV) && !graph.hasVertex(endV)) {
                System.out.println("The values " + startV + " and " + endV + " are absent");
            } else if (!graph.hasVertex(startV)) {
                System.out.println("The start value " + startV + " is absent");
            } else if (!graph.hasVertex(endV)) {
                System.out.println("The end value " + endV + " is absent");
            } else {
                System.out.println("Please, put in any different values");
            }
        }
    }

    /**
     * If a user set the flag "help" on true the method will offer some values to choose
     * when he makes a mistake or input values which are absent in the graph.
     */
    private boolean help = false;
    /**
     * The amount of values = CONSTRAINTS will be offered to choose.
     */
    private final static int CONSTRAINS = 15;
    /**
     * If the amount of vertices less than CONSTRAINTS * 2 then it will be offered a half of vertices to choose.
     */
    private final static int DIVIDER = 2;

    private Graph<T> graph;
    /**
     * The map contains vertices and the distance from the start vertex to them.
     * Besides, the map is in charge of that one and the same vertices do not be checked and placed into the maps twice.
     */
    private Map<T, Integer> distance;
    /**
     * The queue is needed for checking vertex and its adjacent vertices and etc one by one
     * whether it equals the end vertex.
     */
    private Deque<T> que;
    /**
     * The vertices tree where each vertex has just one parent.
     * With the help of it we search reverse path from the end vertex to the start one.
     * @see klykov.graph.traversal.WidthTraversal#pathTo(Object, Object)
     */
    private Map<T, T> shortestPathTree;
    /**
     * When we use the method pathTo it outputs the vertices in a reverse way.
     * This queue is needed for correct displaying the result only.
     */
    private Deque<T> result = new LinkedList<>();

    /**
     * A recursive method works according to the principal:
     * if the current vertex is not the start value
     * then find the parent of a current vertex with the help of shortestPathTree and repeat the algorithm.
     * @param v It is a current vertex. Initially, it equals the end vertex.
     * @param startV It is the vertex which the method try to reach going from the end to the beginning of the chain.
     */
    private void pathTo(T v, T startV) {
        if (!v.equals(startV)) {
            result.push(v);
            pathTo(shortestPathTree.get(v), startV);
        } else {
            System.out.print(startV + " ");
            for (T s : result) {
                System.out.print(s + " ");
            }
        }
    }

    /**
     * Returns a random vertex.
     */
    private T randomVertex() {
        Random rand = new Random();
        List<T> list = new ArrayList<>(graph.vertices());
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * The method offers a set of random values if a user makes a mistake.
     * Values do not repeat in the set and values have to have one an adjacent vertex at least.
     * @see "DIVIDER"
     * @see "CONSTRAINTS"
     * @see klykov.graph.traversal.WidthTraversal#WidthTraversal(GraphMap, boolean)
     */
    private void offer(String massage) {
        Set<T> set = new HashSet<>();
        T vertex;

        System.out.println(massage);
        for (int i = 0; i < (graph.vertices().size() / DIVIDER) && i <= CONSTRAINS; i++) {
            vertex = randomVertex();
            if (!set.contains(vertex) && graph.adjacentTo(vertex).size() != 0) {
                set.add(vertex);
            } else {
                --i;
            }
        }
        System.out.print(set);
    }
}