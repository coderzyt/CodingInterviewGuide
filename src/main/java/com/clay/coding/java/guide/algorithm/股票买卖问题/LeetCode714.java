package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/">...</a>
 */
public class LeetCode714 {

    public int maxProfit(int[] prices, int fee) {
        /*
         dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
         dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
         */
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            }
        }
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int dpi0 = 0, dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, temp - prices[i] - fee);
        }
        return dpi0;
    }
}
