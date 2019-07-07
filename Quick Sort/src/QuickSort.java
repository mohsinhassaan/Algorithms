import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * QuickSort
 */
public class QuickSort {
    public static int comparisons;

    public static void main(String[] args) throws IOException {
        File file = new File("assets/QuickSort.txt");

        int[] arr = fileToIntArray(file);

        qsort(arr);

        System.out.println(comparisons);
    }

    public static void qsort(int[] unsorted) {
        qsort(unsorted, 0, unsorted.length);
    }

    public static void qsort(int[] unsorted, int startIndex, int endIndex) {
        if (endIndex - startIndex <= 0) {
            return;
        }

        int pivotIndex = chooseLastPivot(unsorted, startIndex, endIndex);

        swap(unsorted, startIndex, pivotIndex);
        pivotIndex = startIndex;

        int j = pivotIndex + 1, pivot = unsorted[pivotIndex];

        for (int i = pivotIndex + 1; i < endIndex; i++) {
            if (unsorted[i] < pivot) {
                swap(unsorted, i, j);
                j++;
            }
        }

        swap(unsorted, pivotIndex, j - 1);
        pivotIndex = j - 1;

        comparisons += pivotIndex - startIndex - 1;

        qsort(unsorted, startIndex, pivotIndex);

        comparisons += endIndex - pivotIndex + 1 - 1;

        qsort(unsorted, pivotIndex + 1, endIndex);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int chooseFirstPivot(int[] arr, int startIndex, int endIndex) {
        return startIndex;
    }

    public static int chooseLastPivot(int[] arr, int startIndex, int endIndex) {
        return endIndex - 1;
    }

    public static int chooseMedianPivot(int[] arr, int startIndex, int endIndex) {
        int first = arr[startIndex], last = arr[endIndex - 1],
                middle = arr[startIndex + ((endIndex - 1 - startIndex) / 2)];
        int[] temp = { first, middle, last };

        Arrays.sort(temp);

        int median = temp[1];

        if (median == arr[startIndex]) {
            return startIndex;
        }
        if (median == arr[endIndex - 1]) {
            return endIndex - 1;
        }

        return startIndex + ((endIndex - 1 - startIndex) / 2);

    }

    public static int[] fileToIntArray(File intFile) throws IOException {
        BufferedReader bufferedReader = null;

        bufferedReader = new BufferedReader(new FileReader(intFile));

        int[] arr = new int[10000];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = Integer.parseInt(bufferedReader.readLine());

        }

        bufferedReader.close();

        return arr;
    }
}