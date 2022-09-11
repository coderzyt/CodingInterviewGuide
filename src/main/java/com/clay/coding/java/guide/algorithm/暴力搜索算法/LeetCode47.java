package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * https://leetcode.cn/problems/permutations-ii/
 */
public class LeetCode47 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
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
            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
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

    public static void main(String[] args) {
        LeetCode47 leetCode47 = new LeetCode47();
        int[] nums = new int[]{1, 2, 2};
        System.out.println(leetCode47.permuteUnique(nums));
    }
}
