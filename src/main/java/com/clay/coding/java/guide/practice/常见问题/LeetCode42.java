package com.clay.coding.java.guide.practice.常见问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">...</a>
 */
public class LeetCode42 {

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] lmax = new int[n];
        int[] rmax = new int[n];
        lmax[0] = height[0];
        rmax[n - 1] = height[n - 1];
        int res = 0;
        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], height[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            res += Math.min(lmax[i], rmax[i]) - height[i];
        }
        return res;
    }
}
