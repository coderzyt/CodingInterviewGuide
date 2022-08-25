package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode26 {

    // https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护nums[0..slow]无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
