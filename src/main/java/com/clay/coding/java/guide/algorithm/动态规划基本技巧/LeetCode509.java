package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/fibonacci-number/">...</a>
 */
public class LeetCode509 {

    public int fib(int n) {
        int[] helper = new int[n + 1];
        return fibHelper(helper, n);
    }

    int fibHelper(int[] helper, int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (helper[n] != 0) {
            return helper[n];
        }
        helper[n] = fibHelper(helper, n - 1) + fibHelper(helper, n - 2);
        return helper[n];
    }
}
