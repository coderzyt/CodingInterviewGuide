package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * LeetCode34
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class LeetCode34 {

    public int[] searchRange(int[] nums, int target) {
        int left = searchLeftRange(nums, target);
        int right = searchRightRange(nums, target);
        return new int[]{left, right};
    }

    public int searchLeftRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int searchRightRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left - 1 < 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }
}