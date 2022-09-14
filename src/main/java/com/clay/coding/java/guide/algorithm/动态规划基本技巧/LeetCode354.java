package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/russian-doll-envelopes/">...</a>
 */
public class LeetCode354 {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[1] ?
                o2[1] - o2[1] : o1[0] - o2[0]);
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
