package algorithm.sort.quick;

import algorithm.sort.Example;

public class Quick {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = arr[lo];
        while (true) {
            while (Example.less(arr[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (Example.less(v, arr[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            
            Example.exch(arr, i, j);
        }
        Example.exch(arr, lo, j);
        return j;
    }
    
}
