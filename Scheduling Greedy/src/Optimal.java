import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Optimal
 */
public class Optimal {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("assets/jobs.txt");
        var loader = new JobLoader(inputFile);
        var A = loader.loadJobs();

        var scheduled = schedule(A);

        int completionTime = 0;
        long weightedCompletionTime = 0;

        for (var score : scheduled) {
            int index = score.x.intValue();
            completionTime += A[index].y;
            weightedCompletionTime += completionTime * A[index].x;
        }

        System.out.println(weightedCompletionTime);
    }

    private static FPair[] schedule(Pair[] A) {
        var scores = new FPair[A.length];

        for (int i = 0; i < A.length; i++) {
            scores[i] = new FPair((double) i, (double) A[i].x / (double) A[i].y);
        }

        Arrays.sort(scores, Collections.reverseOrder());

        return scores;
    }
}