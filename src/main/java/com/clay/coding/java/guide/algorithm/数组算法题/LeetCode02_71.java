package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.Random;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/cuyjEf/">...</a>
 */
public class LeetCode02_71 {

    int[] preSum;

    Random random;

    public LeetCode02_71(int[] w) {
        random = new Random();
        int len = w.length;
        preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int rand = random.nextInt(preSum[preSum.length - 1]) + 1;
        return getLeftRange(preSum, rand) - 1;
    }

    private int getLeftRange(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }
}
