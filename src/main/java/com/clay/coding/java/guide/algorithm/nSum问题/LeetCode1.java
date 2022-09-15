package com.clay.coding.java.guide.algorithm.nSum问题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/two-sum/">...</a>
 */
public class LeetCode1 {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                int index = map.get(diff);
                if (index != i) {
                    return new int[]{map.get(diff), i};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        LeetCode1 leetCode1 = new LeetCode1();
        System.out.println(leetCode1.twoSum1(nums, 6));
    }
}
