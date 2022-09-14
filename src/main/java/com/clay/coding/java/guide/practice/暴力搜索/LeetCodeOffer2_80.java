package com.clay.coding.java.guide.practice.暴力搜索;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCodeOffer2_80 {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    void backtrack(int start, int n, int k) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= n; i++) {
            track.addLast(i);
            backtrack(i + 1, n, k);
            track.removeLast();
        }
    }
}
