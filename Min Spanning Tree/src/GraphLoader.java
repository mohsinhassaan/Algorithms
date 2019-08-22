import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

/**
 * GraphLoader
 */
public class GraphLoader {

    public static int[][] load(File f) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(f));
        String line = fReader.readLine();
        final int vertices = Integer.parseInt(line.split(" ")[0]);
        final int edges = Integer.parseInt(line.split(" ")[1]);

        var G = new int[vertices][vertices];

        for (int i = 0; i < edges; i++) {
            line = fReader.readLine();
            var edge = line.split(" ");
            int v1 = Integer.parseInt(edge[0]);
            int v2 = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);

            G[v1 - 1][v2 - 1] = weight;
            G[v2 - 1][v1 - 1] = weight;
        }

        fReader.close();

        return G;
    }
}