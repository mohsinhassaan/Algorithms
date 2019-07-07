import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MinCut
 */
public class MinCut {
    private static final int size = 200;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Integer>> edges;
        File file = new File("assets/kargerMinCut.txt");
        var points = new ArrayList<Integer>();
        edges = initializeAdjacencyList(file);

        for (int i = 0; i < size; i++) {
            points.add(i + 1);
        }

        int N = (int) (Math.pow(size, 3) * Math.log((double) size));

        for (int i = 0; i < N; i++) {
            int tempMin = getMinCut(points, edges);

            if (tempMin < min) {
                min = tempMin;
            }
        }

        System.out.println(min);
    }

    public static int getMinCut(ArrayList<Integer> points, ArrayList<ArrayList<Integer>> edges) {
        if (edges.size() == 1) {
            return 1;
        } else if (edges.size() == 2) {
            return edges.get(0).size();
        }

        int[] edge = pickEdge(points, edges);

        contract(edge[0], edge[1], edges, points);

        int currentMin = getMinCut(points, edges);

        return currentMin;
    }

    public static void contract(int a, int b, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> points) {
        var temp = new ArrayList<Integer>();

        for (int elem : edges.get(points.indexOf(b))) {
            temp.add(elem);
        }

        for (int elem : temp) {
            edges.get(points.indexOf(elem)).remove((Integer) b);
            if (elem != a) {
                edges.get(points.indexOf(elem)).add(a);
                edges.get(points.indexOf(a)).add(elem);
            }
        }

        edges.remove(points.indexOf(b));
        points.remove((Integer) b);
    }

    public static int[] pickEdge(ArrayList<Integer> points, ArrayList<ArrayList<Integer>> edges) {
        var tempPoints = new int[2];

        tempPoints[0] = points.get((int) (Math.random() * points.size()));
        tempPoints[1] = edges.get(points.indexOf(tempPoints[0]))
                .get((int) (Math.random() * edges.get(points.indexOf(tempPoints[0])).size()));

        return tempPoints;
    }

    public static ArrayList<ArrayList<Integer>> initializeAdjacencyList(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        var adjacencyList = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < size; i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }

        String line;
        int point = 0, currPoint = 0;
        char currChar;
        boolean isFirstNum;

        while ((line = reader.readLine()) != null) {
            isFirstNum = true;
            for (int i = 0; i < line.length(); i++) {
                currChar = line.charAt(i);
                if (isFirstNum) {
                    if (currChar == '\t' || currChar == ' ') {
                        isFirstNum = false;
                    }
                } else if (currChar == '\t' || currChar == ' ') {
                    if (point > 0) {
                        adjacencyList.get(currPoint).add(point);
                        point = 0;
                    }
                } else {
                    point *= 10;
                    point += currChar - '0';
                }
            }
            if (point > 0) {
                adjacencyList.get(currPoint).add(point);
                point = 0;
            }
            currPoint++;
        }

        reader.close();

        return adjacencyList;
    }
}