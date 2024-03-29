package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.Arrays;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/coin-change/">...</a>
 */
public class LeetCode322_2 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
