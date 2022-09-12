package com.clay.coding.java.guide.algorithm.图算法;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/course-schedule/">...</a>
 * BFS算法
 */
public class LeetCode207_2 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] indegree = new int[numCourses];
        for (int[] relation : prerequisites) {
            int from = relation[1], to = relation[0];
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点i没有入度，即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                queue.offer(i);
            }
        }
        // 记录遍历的节点个数
        int count = 0;
        // 开始执行BFS循环
        while (!queue.isEmpty()) {
            // 弹出节点cur，并将它指向的节点的入度减一
            int cur = queue.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 如果入度变为0，说明next依赖的节点都已被遍历
                    queue.offer(next);
                }
            }
        }
        return count == numCourses;
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
