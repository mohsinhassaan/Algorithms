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
        File inputFile = new File("assets/dijkstraData");
        var G = initializeGraph(inputFile);

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
            while ((currChar = line.charAt(i++)) != ' ' || currChar != '\t') {
                currNum *= 10;
                currNum += currChar - '0';
            }

            currPoint = currNum;
            G.putIfAbsent(currPoint, new ArrayList<Pair<Integer, Integer>>());

            // Until end of line
            while (i < line.length()) {
                currChar = line.charAt(i++);

                if (currChar == ' ' || currChar == '\t') {
                    G.get(currPoint).add(new Pair<Integer, Integer>(lastNum, currNum));
                    currNum = 0;
                } else if (currChar == ',') {
                    lastNum = currNum;
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