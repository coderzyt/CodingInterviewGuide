package algorithm.sort.merge;

import algorithm.sort.Example;

public class MergeBU {
    private static Comparable[] aux;

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        aux = new Comparable[len];
        for (int sz = 1; sz < len; sz = sz + sz) {
            for (int lo = 0; lo < len - sz; lo += sz + sz) {
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, len - 1));
            }
        }
    }

    private static void merge(Comparable[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (Example.less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }
}
