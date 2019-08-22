import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * MedianMaintenance
 */
public class MedianMaintenance {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("assets/Median.txt");

        ArrayList<Integer> numbers = loadNumbers(inputFile);

        int numCount = 0;

        var low = new PriorityQueue<Integer>(Collections.reverseOrder());
        var high = new PriorityQueue<Integer>();

        int medianSum = 0;
        var medians = new ArrayList<Integer>();

        while (numCount < numbers.size()) {
            int nextNum = getNextNum(numbers, ++numCount);

            if (low.size() == 0 || nextNum < low.peek()) {
                low.add(nextNum);
            } else {
                high.add(nextNum);
            }

            if (low.size() - high.size() > 1) {
                high.add(low.poll());
            } else if (high.size() - low.size() > 1) {
                low.add(high.poll());
            }

            assert Math.abs(low.size() - high.size()) <= 1;

            if (numCount % 2 == 0) {
                medianSum += low.peek();
                medians.add(low.peek());
            } else {
                if (low.size() > high.size()) {
                    medianSum += low.peek();
                    medians.add(low.peek());
                } else {
                    medianSum += high.peek();
                    medians.add(high.peek());
                }
            }
        }

        System.out.println(medianSum % 10000);
    }

    private static ArrayList<Integer> loadNumbers(File inputFile) throws IOException {
        var lines = Files.readAllLines(inputFile.toPath());
        var numbers = new ArrayList<Integer>();

        for (var line : lines) {
            numbers.add(Integer.parseInt(line));
        }

        return numbers;
    }

    private static int getNextNum(List<Integer> input, int numCount) {
        return input.get(numCount - 1);
    }
}