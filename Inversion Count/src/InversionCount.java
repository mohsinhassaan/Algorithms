import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * InversionCount
 */
public class InversionCount {
    public static long inversionCount;

    public static void main(String[] args) throws IOException {
        File intFile = new File("assets/IntegerArray.txt");
        int[] unsorted;

        unsorted = fileToIntArray(intFile);
        mergeSort(unsorted);

        System.out.println(inversionCount);
    }

    public static int[] fileToIntArray(File intFile) throws IOException {
        BufferedReader bufferedReader = null;

        bufferedReader = new BufferedReader(new FileReader(intFile));

        int[] arr = new int[100000];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = Integer.parseInt(bufferedReader.readLine());

        }

        bufferedReader.close();

        return arr;
    }

    public static int[] mergeSort(int[] unsorted) {
        if (unsorted.length <= 1) {
            return unsorted;
        }
        int[] a = new int[unsorted.length / 2];
        int[] b = new int[unsorted.length - unsorted.length / 2];

        for (int i = 0; i < a.length; i++) {
            a[i] = unsorted[i];
        }

        for (int i = 0; i < b.length; i++) {
            b[i] = unsorted[i + a.length];
        }

        a = mergeSort(a);
        b = mergeSort(b);

        int[] sorted = mergeAndCountInversion(a, b);

        return sorted;
    }

    public static int[] mergeAndCountInversion(int[] a, int[] b) {
        int i = 0, j = 0;
        int[] merged = new int[a.length + b.length];

        for (int k = 0; k < merged.length; k++) {
            if (j >= b.length) {
                merged[k] = a[i];
                i++;
            } else if (i >= a.length) {
                merged[k] = b[j];
                j++;
            } else if (a[i] < b[j]) {
                merged[k] = a[i];
                i++;
            } else {
                merged[k] = b[j];
                j++;
                inversionCount += a.length - i;
            }
        }

        return merged;
    }
}