package com.clay.coding.java.guide.practice.常见问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/container-with-most-water/">...</a>
 */
public class LeetCode11 {

    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, cur);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
