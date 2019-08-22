package org.clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * GraphLoader
 */
public class GraphLoader {
    public static SimpleWeightedGraph<Integer, ComparableWeightedEdge> loadGraph(File f) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(f));
        String line;

        fReader.readLine();

        SimpleWeightedGraph<Integer, ComparableWeightedEdge> G = new SimpleWeightedGraph<Integer, ComparableWeightedEdge>(
                ComparableWeightedEdge.class);

        while ((line = fReader.readLine()) != null) {
            String[] parts = line.split(" ");
            G.addVertex(Integer.parseInt(parts[0]));
            G.addVertex(Integer.parseInt(parts[1]));
            ComparableWeightedEdge edge = new ComparableWeightedEdge();
            G.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), edge);
            G.setEdgeWeight(edge, Integer.parseInt(parts[2]));
        }

        fReader.close();

        return G;
    }

    public static SimpleWeightedGraph<Integer, ComparableWeightedEdge> loadGraph2(File f) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(f));
        String line;

        line = fReader.readLine();

        SimpleWeightedGraph<Integer, ComparableWeightedEdge> G = new SimpleWeightedGraph<Integer, ComparableWeightedEdge>(
                ComparableWeightedEdge.class);

        while ((line = fReader.readLine()) != null) {
            line = line.replaceAll(" ", "");
            int v = Integer.parseInt(line, 2);
            if (!G.containsVertex(v)) {
                G.addVertex(v);
            }
        }

        fReader.close();

        return G;
    }
}