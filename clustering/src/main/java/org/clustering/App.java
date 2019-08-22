package org.clustering;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Clustering Algorithm
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        SimpleWeightedGraph<Integer, ComparableWeightedEdge> G = GraphLoader
                .loadGraph2(new File("assets/clustering_big.txt"));

        System.out.println(cluster2(G, 2));
    }

    static double cluster(SimpleWeightedGraph<Integer, ComparableWeightedEdge> G, int k) {
        UnionFind<Integer> S = new UnionFind<Integer>(G.vertexSet());
        ArrayList<ComparableWeightedEdge> edges = new ArrayList<ComparableWeightedEdge>(G.edgeSet());
        Collections.sort(edges);

        int i = 0;

        while (S.numberOfSets() != k) {
            ComparableWeightedEdge nextEdge = edges.get(i++);
            S.union(G.getEdgeSource(nextEdge), G.getEdgeTarget(nextEdge));
        }

        double min = Double.MAX_VALUE;

        for (ComparableWeightedEdge edge : edges) {
            if (S.find(G.getEdgeSource(edge)) != S.find(G.getEdgeTarget(edge))) {
                if (G.getEdgeWeight(edge) < min) {
                    min = G.getEdgeWeight(edge);
                }
            }
        }

        return min;
    }

    static double cluster2(SimpleWeightedGraph<Integer, ComparableWeightedEdge> G, int minimum) {
        Set<Integer> vertexSet = new HashSet<>(G.vertexSet());
        UnionFind<Integer> S = new UnionFind<Integer>(vertexSet);

        int index = 1;
        for (Iterator<Integer> i = vertexSet.iterator(); i.hasNext();) {
            Integer v1 = i.next();
            System.out.println(index++);
            i.remove();
            for (Iterator<Integer> j = vertexSet.iterator(); j.hasNext();) {
                Integer v2 = j.next();
                int bitCount = Integer.bitCount(v1 ^ v2);
                if (bitCount <= minimum) {
                    S.union(v1, v2);
                }
            }
        }

        return S.numberOfSets();
    }
}
