package com.clay.coding.java.guide.algorithm.排序算法题.sort.insertion;

import com.clay.coding.java.guide.algorithm.排序算法题.sort.Example;

public class Shell {
    public static void sort(Comparable[] arr) {
        int len = arr.length;
        int h = 1;
        while (h < len/3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093...
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && Example.less(arr[j], arr[j - h]); j -= h) {
                    Example.exch(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void selectionSort(Comparable[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (Example.less(array[j], array[min])) {
                    min = j;
                }
            }
            Example.exch(array, i, min);
        }
    }

    public static void insertionSort(Comparable[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && Example.less(array[j], array[j - 1]); j--) {
                Example.exch(array, j, j - 1);
            }
        }
    }
}
