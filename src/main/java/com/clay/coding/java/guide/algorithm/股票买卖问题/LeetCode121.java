package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">...</a>
 */
public class LeetCode121 {

    public int maxProfit(int[] prices) {
        /*
        dp[i][1][0] = max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i])
        dp[i][1][1] = max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i])
        case dp[i - 1][0][0] = 0,
        dp[i][1][1] = max(dp[i - 1][1][1], -prices[i])
        case k = 1,
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
        dp[i][1] = max(dp[i - 1][1], -prices[i])
         */
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
        }
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dpi0 = 0, dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, -prices[i]);
        }
        return dpi0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        LeetCode121 leetCode121 = new LeetCode121();
        System.out.println(leetCode121.maxProfit1(nums));
    }
}
