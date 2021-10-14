package klykov;

import klykov.assistant.*;
import klykov.graph.*;
import klykov.graph.traversal.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Set<String> rowSet = Jane.getWords( 4);

        GraphMap<String> g = Handler.getGraph(rowSet.toArray(new String[0]));
        g.setTraversal(new WidthTraversal<>(g, true));
        g.findPath("talk", "door");
    }
}
