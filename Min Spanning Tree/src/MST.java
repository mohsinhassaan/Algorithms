import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MST
 */
public class MST {
    public static void main(String[] args) throws IOException {
        // Adjacency Matrix
        var G = GraphLoader.load(new File("assets/edges.txt"));
        var X = new ArrayList<Integer>();
        X.ensureCapacity(G.length);
        X.add(1);
        var T = new ArrayList<Edge>();

        Edge minE = null;
        int minlen = 0;

        while (X.size() != G.length) {
            minE = null;
            minlen = Integer.MAX_VALUE;

            // Choose the min edge with one seen vertex and one unseen vertex
            // For all seen vertices
            for (int v1 : X) {
                // For all unseen vertices
                for (int v2 = 1; v2 <= G.length; v2++) {
                    if (!X.contains(v2)) {
                        int len = G[v1 - 1][v2 - 1];
                        // If it is the cheapest edge seen
                        if (len != 0 && len < minlen) {
                            minlen = len;
                            minE = new Edge(v1, v2, len);
                        }
                    }
                }
            }
            // add the edge to MST
            T.add(minE);
            // add the unseen vertex to the seen vertices
            X.add(minE.v2);
        }

        int ans = 0;

        // add up all weights of edges in MST
        for (var edge : T) {
            ans += edge.weight;
        }

        // Print them
        System.out.println(ans);
    }
}

class Edge {
    public final int v1;
    public final int v2;
    public final int weight;

    public Edge(int a, int b, int w) {
        v1 = a;
        v2 = b;
        weight = w;
    }
}