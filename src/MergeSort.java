/**
 * MergeSort
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] unsorted = { 1, 6, 2, 7, 3, 4, 8, 0, 5, 123, 43, 1, 4 };
        int[] sorted = mergeSort(unsorted);

        for (int elem : unsorted) {
            System.out.print(elem + " ");
        }
        System.out.println();

        for (int elem : sorted) {
            System.out.print(elem + " ");
        }
        System.out.println();
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

        int[] sorted = merge(a, b);

        return sorted;
    }

    public static int[] merge(int[] a, int[] b) {
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
            }
        }

        return merged;
    }
}