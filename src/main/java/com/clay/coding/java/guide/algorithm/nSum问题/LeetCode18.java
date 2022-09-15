package com.clay.coding.java.guide.algorithm.nSum问题;

import java.util.*;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/4sum/">...</a>
 */
public class LeetCode18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 对于与target的运算，需要考虑是否大于Integer的最大值，用long型
            long diff = target - nums[i];
            List<List<Integer>> threeRes = threeSum(nums, i + 1, diff);
            for (List<Integer> list : threeRes) {
                list.add(nums[i]);
                res.add(list);
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    List<List<Integer>> threeSum(int[] nums, int start, long target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = start; i < nums.length; i++) {
            long diff = target - nums[i];
            List<List<Integer>> twoRes = twoSum(nums, i + 1, diff);
            for (List<Integer> list : twoRes) {
                list.add(nums[i]);
                res.add(list);
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    List<List<Integer>> twoSum(int[] nums, int start, long target) {
        List<List<Integer>> res = new ArrayList<>();
        int left = start, right = nums.length - 1;
        while (left < right) {
            int lo = nums[left], hi = nums[right];
            long sum = lo + hi;
            if (sum == target) {
                List<Integer> list = new ArrayList<>();
                list.add(lo);
                list.add(hi);
                res.add(list);
                while (left < right && lo == nums[left]) {
                    left++;
                }
                while (left < right && hi == nums[right]) {
                    right--;
                }
            } else if (sum > target) {
                while (left < right && hi == nums[right]) {
                    right--;
                }
            } else {
                while (left < right && lo == nums[left]) {
                    left++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1000000000, 1000000000, 1000000000, 1000000000};

        LeetCode18 leetCode18 = new LeetCode18();
        System.out.println(leetCode18.fourSum(nums, -294967296));
    }
}
