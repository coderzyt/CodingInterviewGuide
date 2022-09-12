package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/VvJkup/">...</a>
 */
public class LeetCodeOffer2_83 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, used);
        return res;
    }

    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, track, used);
            track.removeLast();
            used[i] = false;
        }
    }
}
