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
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ?
                        b[1] - a[1] : a[0] - b[0];
            }
        });
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
    }

    int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;

        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) {
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }
}
