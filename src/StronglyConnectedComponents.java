import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 * StronglyConnectedComponents
 */
public class StronglyConnectedComponents {
    private static HashMap<Integer, Boolean> exploredPoints;
    private static HashMap<Integer, Integer> finishingTimes, leaders;
    private static int S, t;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        var file = new File("assets/SCC.txt");

        var G = initializeAdjacencyList(file);
        var reversedG = reverseGraph(G);

        finishingTimes = new HashMap<Integer, Integer>();

        dfsLoop(reversedG, true);
        dfsLoop(G, false);

        var largest = countLargestSCCs(5);

        for (var SCC : largest) {
            System.out.print(SCC + ",");
        }
    }

    private static HashMap<Integer, ArrayList<Integer>> reverseGraph(HashMap<Integer, ArrayList<Integer>> G) {
        var reversedG = new HashMap<Integer, ArrayList<Integer>>();

        for (var u : G.keySet()) {
            for (var v : G.get(u)) {
                try {
                    reversedG.get(v).add(u);
                } catch (NullPointerException e) {
                    reversedG.put(v, new ArrayList<Integer>());
                    reversedG.get(v).add(u);
                }
            }
        }

        assert reversedG.size() == G.size();

        return reversedG;
    }

    private static void dfsLoop(HashMap<Integer, ArrayList<Integer>> G, boolean firstPass) {
        exploredPoints = new HashMap<Integer, Boolean>();
        leaders = new HashMap<Integer, Integer>();
        t = 0;

        for (var point : G.keySet()) {
            exploredPoints.put(point, false);
        }

        if (!firstPass) {
            for (int i = Collections.max(finishingTimes.keySet()); i > 0; i--) {
                Integer integer = finishingTimes.get(i);
                if (exploredPoints.containsKey(integer) && !exploredPoints.get(integer)) {
                    S = integer;
                    DFS(G, integer, false);
                }
            }
        } else {
            for (int i = Collections.max(G.keySet()); i > 0; i--) {
                if (exploredPoints.containsKey(i) && !exploredPoints.get(i)) {
                    DFS(G, i, true);
                }
            }
        }
    }

    private static void DFS(HashMap<Integer, ArrayList<Integer>> G, int i, boolean firstPass) {
        exploredPoints.put(i, true);

        stack = new Stack<Integer>();
        int currPoint = 0, nextPoint;
        boolean deadEnd = false;
        stack.push(i);

        while (!stack.empty()) {
            deadEnd = false;

            if (!firstPass) {
                if (leaders.containsKey(S)) {
                    leaders.put(S, leaders.get(S) + 1);
                } else {
                    leaders.put(S, 1);
                }
            }

            while (!deadEnd) {
                deadEnd = true;
                currPoint = stack.peek();

                for (int j = 0; j < G.get(currPoint).size(); j++) {
                    nextPoint = G.get(currPoint).get(j);
                    if (exploredPoints.containsKey(nextPoint) && !exploredPoints.get(nextPoint)) {
                        deadEnd = false;
                        exploredPoints.put(nextPoint, true);
                        stack.push(nextPoint);
                        break;
                    }
                }
            }
            currPoint = stack.pop();
            if (firstPass) {
                t++;
                finishingTimes.put(t, currPoint);
            }
        }
    }

    private static Integer[] countLargestSCCs(int n) {
        var largestSCCs = new Integer[n];

        Arrays.fill(largestSCCs, 0);

        for (var key : leaders.keySet()) {
            if (largestSCCs[n - 1] < leaders.get(key)) {
                largestSCCs[n - 1] = leaders.get(key);
                Arrays.sort(largestSCCs, Collections.reverseOrder());
            }
        }

        return largestSCCs;
    }

    private static HashMap<Integer, ArrayList<Integer>> initializeAdjacencyList(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        var adjacencyList = new HashMap<Integer, ArrayList<Integer>>();

        String line;
        char currChar;
        int currNum, currPoint;
        boolean isPoint;

        while ((line = reader.readLine()) != null) {
            currPoint = 0;
            currNum = 0;
            isPoint = true;
            for (int i = 0; i < line.length(); i++) {
                currChar = line.charAt(i);
                while (currChar != ' ' && isPoint) {
                    currPoint *= 10;
                    currPoint += line.charAt(i) - '0';

                    i++;
                    currChar = line.charAt(i);
                }
                isPoint = false;
                if (currChar >= '0' && currChar <= '9') {
                    currNum *= 10;
                    currNum += currChar - '0';
                }
            }

            if (!adjacencyList.containsKey(currPoint)) {
                adjacencyList.put(currPoint, new ArrayList<Integer>());
            }

            adjacencyList.get(currPoint).add(currNum);
        }

        reader.close();

        return adjacencyList;
    }
}