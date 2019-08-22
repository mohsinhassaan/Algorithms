import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * TwoSum
 */
public class TwoSum {
    public static void main(String[] args) throws IOException {
        HashSet<Long> H = loadHashTable(new File("assets/2sum.txt"));
        int count = 0;

        for (int t = -10000; t <= 10000; t++) {
            for (var x : H) {
                if (x != t - x && H.contains(t - x.longValue())) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }

    private static HashSet<Long> loadHashTable(File f) throws IOException {
        BufferedReader freader = new BufferedReader(new FileReader(f));
        HashSet<Long> output = new HashSet<Long>();
        String line;

        while ((line = freader.readLine()) != null) {
            output.add(Long.parseLong(line));
        }

        freader.close();

        return output;
    }
}