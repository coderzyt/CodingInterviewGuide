package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * https://leetcode.cn/problems/TVdhkn/
 */
public class LeetCodeOffer2_79 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
