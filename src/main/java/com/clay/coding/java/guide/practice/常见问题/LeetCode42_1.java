package com.clay.coding.java.guide.practice.常见问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">...</a>
 */
public class LeetCode42_1 {

    public int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1, lmax = 0, rmax = 0, res = 0;
        while (left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);
            if (lmax < rmax) {
                res += lmax - height[left];
                left++;
            } else {
                res += rmax - height[right];
                right--;
            }
        }
        return res;
    }
}
