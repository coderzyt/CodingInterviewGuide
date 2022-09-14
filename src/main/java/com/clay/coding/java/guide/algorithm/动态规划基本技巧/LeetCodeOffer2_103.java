package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.Arrays;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/gaM7Ch/">...</a>
 */
public class LeetCodeOffer2_103 {

    public int coinChange(int[] coins, int amount) {
        // dp[i]的含义是当amount = i时，最少的硬币数量
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        LeetCodeOffer2_103 leetCodeOffer2_103 = new LeetCodeOffer2_103();
        System.out.println(leetCodeOffer2_103.coinChange(coins, amount));
    }
}
