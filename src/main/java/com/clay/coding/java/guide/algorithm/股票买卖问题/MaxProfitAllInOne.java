package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 */
public class MaxProfitAllInOne {

    public int maxProfitAllInOne(int[] prices, int maxK, int coolDown, int fee) {
        int n = prices.length;
        if (n <= 0) {
            return 0;
        }
        if (maxK > n / 2) {
            return maxProfit(prices);
        }
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = - prices[i] - fee;
                    continue;
                }
                if (i - coolDown - 1 < 0) {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], - prices[i] - fee);
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - coolDown - 1][k - 1][0] - prices[i] - fee);
            }
        }
        return dp[n - 1][maxK][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dpi0 = 0, dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, temp - prices[i]);
        }
        return dpi0;
    }
}
