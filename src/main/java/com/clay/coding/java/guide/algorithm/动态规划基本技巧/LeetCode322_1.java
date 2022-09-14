package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.Arrays;

/**
 * @author coderclay
 * <a href="https://leetcode.cn /problems/coin-change/">...</a>
 */
public class LeetCode322_1 {

    int res = Integer.MAX_VALUE;

    int[] memory;

    public int coinChange(int[] coins, int amount) {
        memory = new int[amount + 1];
        Arrays.fill(memory, -666);
        return dp(coins, amount);
    }

    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memory[amount] != -666) {
            return memory[amount];
        }
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        memory[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memory[amount];
    }
}
