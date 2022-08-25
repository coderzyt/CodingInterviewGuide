package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode167 {

    // https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        LeetCode167 leetCode167 = new LeetCode167();
        int[] test = new int[] { 0, 0, 3, 4 };
        leetCode167.twoSum(test, 0);
    }
}
