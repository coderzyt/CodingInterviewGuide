package com.clay.coding.java.guide.algorithm.数组算法题;

public class LeetCodeOffer53 {
    public int search(int[] nums, int target) {
        int left = searchLeft(nums, target);
        int right = searchRight(nums, target);
        if (left == right) {
            return left == -1 ? 0 : 1;
        }
        return right - left + 1;
    }


    public int searchLeft(int[] nums, int target) {
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

    public int searchRight(int[] nums, int target) {
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
