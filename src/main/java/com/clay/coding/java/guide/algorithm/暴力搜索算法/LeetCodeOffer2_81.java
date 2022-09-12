package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/Ygoe9J/">...</a>
 */
public class LeetCodeOffer2_81 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0);
        return res;
    }

    void backtrack(int[] nums, int target, int start) {
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            trackSum += nums[i];
            backtrack(nums, target, i);
            trackSum -= nums[i];
            track.removeLast();
        }
    }
}
