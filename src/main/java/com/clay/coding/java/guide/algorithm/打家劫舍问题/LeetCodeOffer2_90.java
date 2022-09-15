package com.clay.coding.java.guide.algorithm.打家劫舍问题;

/**
 * @author coderclay
 * https://leetcode.cn/problems/PzWKhm/
 */
public class LeetCodeOffer2_90 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        int robFirst = robRange(nums, dp, 0, n - 2);
        int robLast = robRange(nums, dp, 1, n - 1);
        return Math.max(robFirst, robLast);
    }

    int robRange(int[] nums, int[] dp, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i == start) {
                dp[i] = nums[start];
            } else if (i == start + 1) {
                dp[i] = Math.max(nums[i], nums[i - 1]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }
        return dp[end];
    }

    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, n - 2),
                robRange(nums, 1, n - 1));
    }

    // 仅计算闭区间 [start,end] 的最优结果
    int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
