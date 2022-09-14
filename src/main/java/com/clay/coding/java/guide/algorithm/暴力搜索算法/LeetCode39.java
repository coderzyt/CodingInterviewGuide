package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/combination-sum/">...</a>
 */
public class LeetCode39 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int trackSum;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        backtrack(candidates, 0, target);
        return res;
    }

    void backtrack(int[] nums, int start, int target) {
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
            backtrack(nums, i, target);
            trackSum -= nums[i];
            track.removeLast();
        }
    }
}
