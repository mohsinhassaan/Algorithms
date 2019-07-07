import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Dijkstra
 */
public class Dijkstra {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("assets/dijkstraData.txt");
        var G = initializeGraph(inputFile);

        var A = dijkstraShortestPaths(G, 1);

        for (int v : G.keySet()) {
            A.putIfAbsent(v, 1000000);
        }

        int[] needed = { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };

        for (int vertex : needed) {
            System.out.print(A.get(vertex) + ",");
        }

    }

    private static HashMap<Integer, Integer> dijkstraShortestPaths(
            HashMap<Integer, ArrayList<Pair<Integer, Integer>>> G, int S) {
        var X = new ArrayList<Integer>();
        X.add(S);

        var A = new HashMap<Integer, Integer>();
        A.put(S, 0);

        while (X.size() != G.size()) {
            var minW = new Pair<Integer, Integer>(0, Integer.MAX_VALUE);
            int minV = 0, minGreedyCriterion = Integer.MAX_VALUE, greedyCriterion;

            for (var v : X) {
                for (var w : G.get(v)) {
                    if (!X.contains(w.x) && (greedyCriterion = w.y + A.get(v)) < minGreedyCriterion) {
                        minW = w;
                        minV = v;
                        minGreedyCriterion = greedyCriterion;
                    }
                }
            }

            X.add(minW.x);
            A.put(minW.x, A.get(minV) + minW.y);
        }

        return A;
    }

    private static HashMap<Integer, ArrayList<Pair<Integer, Integer>>> initializeGraph(File inputFile)
            throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(inputFile));
        String line;
        char currChar;
        int i, currNum, currPoint, lastNum;

        var G = new HashMap<Integer, ArrayList<Pair<Integer, Integer>>>();

        while ((line = fReader.readLine()) != null) {
            i = 0;
            currNum = 0;
            lastNum = 0;

            // For source vertex
            while ((currChar = line.charAt(i++)) != '\t') {
                currNum *= 10;
                currNum += currChar - '0';
            }

            currPoint = currNum;
            G.putIfAbsent(currPoint, new ArrayList<Pair<Integer, Integer>>());
            currNum = 0;

            // Until end of line
            while (i < line.length()) {
                currChar = line.charAt(i++);

                if (currChar == '\t') {
                    G.get(currPoint).add(new Pair<Integer, Integer>(lastNum, currNum));
                    currNum = 0;
                } else if (currChar == ',') {
                    lastNum = currNum;
                    currNum = 0;
                } else {
                    currNum *= 10;
                    currNum += currChar - '0';
                }
            }
        }

        fReader.close();

        return G;
    }
}

final class Pair<A, B> {
    public final A x;
    public final B y;

    Pair(A x, B y) {
        this.x = x;
        this.y = y;
    }
}