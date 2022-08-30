package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode283 {
    // https://leetcode.cn/problems/move-zeroes/
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int slow = 0, fast = 0;
        while (fast < len) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < len) {
            nums[slow] = 0;
            slow++;
        }
    }
}
