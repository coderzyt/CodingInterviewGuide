package com.clay.coding.java.guide.practice.暴力搜索;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCode47 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums);
        return res;
    }

    void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }
}
