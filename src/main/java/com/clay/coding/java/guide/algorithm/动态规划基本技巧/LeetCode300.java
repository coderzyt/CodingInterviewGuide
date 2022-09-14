package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.Arrays;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">...</a>
 */
public class LeetCode300 {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 3, 4, 2, 3};
        LeetCode300 leetCode300 = new LeetCode300();
        System.out.println(leetCode300.lengthOfLIS(nums));
    }
}
