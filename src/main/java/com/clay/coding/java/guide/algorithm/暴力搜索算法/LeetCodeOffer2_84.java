package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/7p8L0Z/">...</a>
 */
public class LeetCodeOffer2_84 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            visited[i] = true;
            backtrack(nums);
            visited[i] = false;
            track.removeLast();
        }
    }
}
