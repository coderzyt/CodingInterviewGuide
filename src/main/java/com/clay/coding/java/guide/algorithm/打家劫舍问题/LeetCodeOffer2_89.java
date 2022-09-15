package com.clay.coding.java.guide.algorithm.打家劫舍问题;

/**
 * @author coderclay
 * https://leetcode.cn/problems/Gu0c2T/
 */
public class LeetCodeOffer2_89 {

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else if (i == 1) {
                dp[i] = Math.max(nums[i], nums[i - 1]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }
        return dp[n - 1];
    }
}
