package com.clay.coding.java.guide.algorithm.排序算法题.sort.insertion;

import com.clay.coding.java.guide.algorithm.排序算法题.sort.Example;

/**
 * @author coderclay
 * 插入排序
 */
public class Insertion {
    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && Example.less(arr[j], arr[j - 1]); j--) {
                Example.exch(arr, j, j - 1);
            }
        }
    }
}
