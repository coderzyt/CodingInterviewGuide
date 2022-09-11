package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * https://leetcode.cn/problems/uUsW3B/
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

    public static void main(String[] args) {
        LeetCodeOffer2_80 leetCodeOffer2_80 = new LeetCodeOffer2_80();
        int n = 3, k = 2;
        System.out.println(leetCodeOffer2_80.combine(3, 2));
    }
}
