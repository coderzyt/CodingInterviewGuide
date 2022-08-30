package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCode315 {

    private class Pair {
        // 记录数组的元素值
        int val;
        // 记录元素在数组中的原始索引
        int id;
        Pair(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    /**
     * 归并排序所用的辅助数组
     */
    private Pair[] temp;

    /**
     * 记录每个元素后面比自己小的元素
      */
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];

        // 记录元素原始的索引位置，以便在count数组中更新结果
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        // 执行归并排序，本题结果被记录在count数组中
        sort(arr, 0 , n - 1);
        List<Integer> result = new LinkedList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) {
            System.arraycopy(arr, lo, temp, lo, hi + 1 - lo);
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j++];
            } else if (j == hi + 1) {
                arr[p] = temp[i++];
                // 更新count数组
                count[arr[p].id] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else {
                arr[p] = temp[i++];
                // 更新count数组
                count[arr[p].id] += j - mid - 1;
            }
        }
    }
}
