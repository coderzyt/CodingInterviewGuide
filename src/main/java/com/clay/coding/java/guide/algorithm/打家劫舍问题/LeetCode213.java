package com.clay.coding.java.guide.algorithm.打家劫舍问题;

/**
 * @author coderclay
 * https://leetcode.cn/problems/house-robber-ii/
 */
public class LeetCode213 {
    
    /**
     * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
     * @param nums
     * @return
     */
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
        int res1 = robRange(nums, 0, n - 2, dp);
        int res2 = robRange(nums, 1, n - 1, dp);
        return Math.max(res1, res2);
    }

    int robRange(int[] nums, int start, int end, int[] dp) {
        for (int i = start; i <= end; i++) {
            if (i == start) {
                dp[i] = nums[i];
            } else if (i == start + 1) {
                dp[i] = Math.max(nums[i], nums[i - 1]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }
        return dp[end];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        LeetCode213 leetCode213 = new LeetCode213();
        System.out.println(leetCode213.rob(nums));
    }
}
