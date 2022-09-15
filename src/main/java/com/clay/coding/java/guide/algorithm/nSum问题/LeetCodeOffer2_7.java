package com.clay.coding.java.guide.algorithm.nSum问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/1fGaJU/">...</a>
 */
public class LeetCodeOffer2_7 {
    
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> twoRes = twoSum(nums, i + 1, 0 - nums[i]);
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
        int[] nums = new int[]{0, 0, 0};
        LeetCodeOffer2_7 leetCodeOffer2_7 = new LeetCodeOffer2_7();
        System.out.println(leetCodeOffer2_7.threeSum(nums));
    }
}
