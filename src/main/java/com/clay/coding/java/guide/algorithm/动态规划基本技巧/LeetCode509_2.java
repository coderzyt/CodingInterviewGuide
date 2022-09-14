package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/fibonacci-number/">...</a>
 */
public class LeetCode509_2 {

    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int dp1 = 1, dp2 = 0;
        for (int i = 2; i <= n; i++) {
            int dp = dp1 + dp2;
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }
}
