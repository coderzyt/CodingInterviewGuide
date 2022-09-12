package com.clay.coding.java.guide.algorithm.图算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/course-schedule-ii/">...</a>
 */
public class LeetCode210 {

    boolean[] visited;

    boolean[] onPath;

    boolean hasCycle = false;

    List<Integer> postOrder = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        if (hasCycle) {
            return new int[]{};
        }
        Collections.reverse(postOrder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postOrder.get(i);
        }
        return res;
    }

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] relation : prerequisites) {
            int from = relation[1], to = relation[0];
            graph[from].add(to);
        }
        return graph;
    }

    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        postOrder.add(s);
        onPath[s] = false;
    }
}
