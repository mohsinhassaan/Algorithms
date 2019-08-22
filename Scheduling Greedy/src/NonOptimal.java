import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * NonOptimal
 */
public class NonOptimal {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("assets/jobs.txt");
        var loader = new JobLoader(inputFile);
        var A = loader.loadJobs();

        var scheduled = schedule(A);

        int completionTime = 0;
        long weightedCompletionTime = 0;

        for (var score : scheduled) {
            int index = score.x;
            completionTime += A[index].y;
            weightedCompletionTime += completionTime * A[index].x;
        }

        System.out.println(weightedCompletionTime);
    }

    private static Pair[] schedule(Pair[] A) {
        var scores = new Pair[A.length];

        for (int i = 0; i < A.length; i++) {
            scores[i] = new Pair(i, A[i].x - A[i].y, A[i].x);
        }

        Arrays.sort(scores, Collections.reverseOrder());

        return scores;
    }
}