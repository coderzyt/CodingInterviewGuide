package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.LinkedList;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/coin-change/">...</a>
 */
public class LeetCode322 {

    LinkedList<Integer> track = new LinkedList<>();

    int minCoinsNum = Integer.MAX_VALUE;

    int trackSum = 0;

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        backtrack(coins, 0, amount);
        return minCoinsNum == Integer.MAX_VALUE ? -1 : minCoinsNum;
    }

    void backtrack(int[] nums, int count, int target) {
        if (target == 0) {
            minCoinsNum = Math.min(minCoinsNum, count);
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            backtrack(nums, count + 1 , target - nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] coins = new int[]{411,412,413,414,415,416,417,418,419,420,421,422};
        int amount = 9864;
        LeetCode322 leetCode322 = new LeetCode322();
        System.out.println(leetCode322.coinChange(coins, amount));
    }
}
