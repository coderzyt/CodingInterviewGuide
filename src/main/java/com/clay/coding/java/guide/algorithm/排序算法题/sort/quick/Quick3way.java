package com.clay.coding.java.guide.algorithm.排序算法题.sort.quick;

import com.clay.coding.java.guide.algorithm.排序算法题.sort.Example;

public class Quick3way {
    public static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = arr[lo];
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) {
                Example.exch(arr, lt++, i++);
            } else if (cmp > 0) {
                Example.exch(arr, i, gt--);
            } else {
                i++;
            }
        }
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }
}
