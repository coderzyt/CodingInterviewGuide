package com.clay.coding.java.guide.algorithm.图算法;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/all-paths-from-source-to-target/
 */
public class LeetCode797 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        path.addLast(s);
        int n = graph.length;
        if (s == n - 1) {
            res.add(new LinkedList<>(path));
        }
        for (int val : graph[s]) {
            traverse(graph, val, path);
        }
        path.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {3}, {3}, {}};
        LeetCode797 leetCode797 = new LeetCode797();
        System.out.println(leetCode797.allPathsSourceTarget(graph));
    }
}
