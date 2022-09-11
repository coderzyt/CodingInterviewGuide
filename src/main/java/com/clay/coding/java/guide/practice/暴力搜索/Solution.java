package com.clay.coding.java.guide.practice.暴力搜索;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class Solution {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
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
            visited[i] = true;
            track.addLast(nums[i]);
            backtrack(nums);
            track.removeLast();
            visited[i] = false;
        }

    }
}
