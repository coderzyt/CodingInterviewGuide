package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/4sjJUc/">...</a>
 */
public class LeetCodeOffer2_82 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int trackSum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
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
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            trackSum += nums[i];
            backtrack(nums, target, i + 1);
            trackSum -= nums[i];
            track.removeLast();
        }
    }
}
