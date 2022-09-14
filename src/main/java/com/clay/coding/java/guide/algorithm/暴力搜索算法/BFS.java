package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author coderclay
 */
public class BFS {

    class Node {
        Node[] adj;
        int val;

        Node(int val) {
            this.val = val;
        }

        Node(Node[] adj) {
            this.adj = adj;
        }

        Node(int val, Node[] adj) {
            this.val = val;
            this.adj = adj;
        }
    }

    int BFS(Node start, Node target) {
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        // 将起点加入队列
        q.offer(start);
        visited.add(start);
        // 记录扩散的步数
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            /*
            将当前队列中的所有节点向四周扩散
             */
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                // 判断是否到达终点
                if (cur == target) {
                    return step;
                }
                /*
                将cur的相邻节点加入队列
                 */
                for (Node x : cur.adj) {
                    if (!visited.contains(x)) {
                        q.offer(x);
                        visited.add(x);
                    }
                }
            }
            // 更新步树
            step++;
        }
        return step;
    }
}
