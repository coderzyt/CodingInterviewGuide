package com.clay.coding.java.guide.algorithm.动态规划基本技巧;

/**
 * @author coderclay
 */
public class LeetCode300_1 {

    public int lengthOfLIS(int[] nums) {
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
