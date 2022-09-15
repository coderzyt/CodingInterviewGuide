package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/">...</a>
 */
public class LeetCode309 {

    public int maxProfit(int[] prices) {
        /*
         case k == infinity,
         dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
         dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i])
         */
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
            } else if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int dpi0 = 0, dpi1 = Integer.MIN_VALUE;
        int dpPre0 = 0;
        for (int i = 0; i < n; i++) {
            int temp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, dpPre0 - prices[i]);
            dpPre0 = temp;
        }
        return dpi0;
    }
}
