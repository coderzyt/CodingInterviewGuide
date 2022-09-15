package com.clay.coding.java.guide.algorithm.打家劫舍问题;


/**
 * @author coderclay
 * https://leetcode.cn/problems/house-robber/
 */
public class LeetCode198 {
    
    /**
     * dp[i] = dp[i - 2] + nums[i]
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
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

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        LeetCode198 leetCode198 = new LeetCode198();
        System.out.println(leetCode198.rob(nums));
    }
}
