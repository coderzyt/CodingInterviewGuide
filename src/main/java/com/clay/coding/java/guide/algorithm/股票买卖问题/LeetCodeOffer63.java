package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/">...</a>
 */
public class LeetCodeOffer63 {
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
        int dpi0 = 0, dpi1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, - prices[i]);
        }
        return dpi0;
    }
}
