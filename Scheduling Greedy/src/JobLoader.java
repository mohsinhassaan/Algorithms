import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * JobLoader
 */
public class JobLoader {
    private File file;

    public JobLoader(File f) {
        file = f;
    }

    public Pair[] loadJobs() throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(fReader.readLine());
        var A = new Pair[n];
        String line;

        for (int i = 0; i < n; i++) {
            line = fReader.readLine();
            String[] entry = line.split(" ");
            A[i] = new Pair(Integer.parseInt(entry[0]), Integer.parseInt(entry[1]));
        }

        fReader.close();

        return A;
    }
}