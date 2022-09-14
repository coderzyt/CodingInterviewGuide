package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/combination-sum-iii/">...</a>
 */
public class LeetCode216 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int trackSum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(1, n, k);
        return res;
    }

    void backtrack(int start, int n, int k) {
        if (track.size() == k && trackSum == n) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= 9; i++) {
            track.addLast(i);
            trackSum += i;
            backtrack(i + 1, n, k);
            trackSum -= i;
            track.removeLast();
        }
    }
}
