package com.clay.coding.java.guide.algorithm.nSum问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/3sum/">...</a>
 */
public class LeetCode15 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int diff = 0 - nums[i];
            twoSum(nums, diff, start, i);
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    void twoSum(int[] nums, int target, int start, int i) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            int lo = nums[left], hi = nums[right];
            int sum = lo + hi;
            if (sum == target) {
                res.add(Arrays.asList(lo, hi, nums[i]));
                while (left < right && lo == nums[left]) {
                    left++;
                }
                while (right > left && hi == nums[right]) {
                    right--;
                }
            } else if (sum > target) {
                while (right > left && hi == nums[right]) {
                    right--;
                }
            } else {
                while (left < right && lo == nums[left]) {
                    left++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 0, 2, 2};
        LeetCode15 leetCode15 = new LeetCode15();
        System.out.println(leetCode15.threeSum(nums));
    }
}
