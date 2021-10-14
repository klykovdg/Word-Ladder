package klykov.assistant;

import klykov.graph.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The class contains the only factory method for Graph but new methods can be added potentially.
 */
public class Handler {
    /**
     * The task is to find all the words which differ one letter only from each other and create a graph.
     * The method takes the first word from the array received
     * and substitutes every letter with regex (\w) one by one searching whether there are words which match.
     * After searching, the method having put all words found into the temporary Set (tempBox) creates new vertices
     * and removes the word from array.
     * The algorithm does the same work with all the words from array.
     * The static method performs a specified task and returns an object of Graph.
     * The speed of the algorithm is satisfactory and enough for us
     * as we are constrained the amount of the words in English dictionary.
     * According to the results of tests, 5000 words are checked in 3 seconds and 10 000 in 10 seconds
     * although neither the former nor the latter are hardly possible.
     * @param rowArray For example, there are some data of all 5 letters words from the text,
     *                 which will be structured according to a specified task and added in the graph
     * @return An object of Graph
     */
    public static GraphMap<String> getGraph(String[] rowArray) {
        List<String> rowList = new ArrayList<>(Arrays.asList(rowArray));
        GraphMap<String> graph = new GraphMap<>();
        Set<String> tempBox = new HashSet<>();
        StringBuilder sb;
        String s;
        Pattern p;

        while (!rowList.isEmpty()) {
            s = rowList.get(0);
            for (int i = 0; i < s.length(); i++) {
                tempBox.clear();
                tempBox.add(s);

                sb = new StringBuilder(s);
                sb.replace(i, i + 1, "\\w");
                p = Pattern.compile(sb.toString());
                for (String ss : rowList) {
                    if (p.matcher(ss).matches() && !ss.equals(s)) {
                        tempBox.add(ss);
                    }
                }
                graph.add(tempBox.toArray(new String[0]));
            }
            rowList.remove(0);
        }
        return graph;
    }

    private Handler() {}
}
