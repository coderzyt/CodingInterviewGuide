package com.clay.coding.java.guide.algorithm.股票买卖问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/">...</a>
 */
public class LeetCode188 {

    public int maxProfit(int k, int[] prices) {
        /*
        dp[i][k][0 or 1]
        0 <= i <= n - 1, 1 <= k <= K
        n为天数，大K为交易数上限，0 和 1 代表是否持有股票
        此问题共 n * K * 2种状态，全部穷举就能搞定
        for 0 <= i <= n:
            for 1 <= k <= K
                for s in {0, 1}:
                    dp[i][k][s] = max(buy, sell, rest)
        dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
                      max( 今天选择rest，           今天选择 sell       )
        dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
                      max( 今天选择rest，            今天选择 buy           )
         */
        int n = prices.length;
        if (n <= 0) {
            return 0;
        }
        if (k > n / 2) {
            return maxProfit(prices);
        }
        /*
        base case :
        dp[-1][...][0] = dp[...][0][0] = 0;
        dp[-1][...][1] = dp[...][0][1] = -infinity;
         */
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
            dp[i][0][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i -1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][k][0];
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
