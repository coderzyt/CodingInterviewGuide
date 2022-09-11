package com.clay.coding.java.guide.algorithm.图算法;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/course-schedule-ii/">...</a>
 */
public class LeetCode210_2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] indegree = new int[numCourses];
        for (int[] relation : prerequisites) {
            int to = relation[0];
            indegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count] = cur;
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (count != numCourses) {
            return new int[]{};
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
}
