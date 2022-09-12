package com.clay.coding.java.guide.algorithm.图算法;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/course-schedule/">...</a>
 * DFS算法
 */
public class LeetCode207 {

    boolean[] visited;

    boolean[] onPath;

    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
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
        /*
        前序遍历代码位置
        将当前节点标记为已遍历
         */
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        /*
        后序遍历代码位置
         */
        onPath[s] = false;
    }
}
