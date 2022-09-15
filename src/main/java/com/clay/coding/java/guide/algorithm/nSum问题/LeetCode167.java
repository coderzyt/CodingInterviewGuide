package com.clay.coding.java.guide.algorithm.nSum问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">...</a>
 */
public class LeetCode167 {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};
    }
}
