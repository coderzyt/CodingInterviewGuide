package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/">...</a>
 */
public class LeetCode123 {

    public int maxProfit(int[] prices) {

        /*
        dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
        dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
         */
        int n = prices.length, maxK = 2;
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][maxK][0];
    }

    public int maxProfit1(int[] prices) {
        /*
        dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
        dp[i][1][1] = Math.max(dp[i - 1][1][1], - prices[i]);
         */
        int n = prices.length;
        int dpi10 = 0, dpi11 = Integer.MIN_VALUE, dpi20 = 0, dpi21 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dpi20 = Math.max(dpi20, dpi21 + prices[i]);
            dpi21 = Math.max(dpi21, dpi10 - prices[i]);
            dpi10 = Math.max(dpi10, dpi11 + prices[i]);
            dpi11 = Math.max(dpi11, -prices[i]);
        }
        return dpi20;
    }
}
