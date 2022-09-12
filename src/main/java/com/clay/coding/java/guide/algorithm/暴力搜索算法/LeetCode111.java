package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import com.clay.coding.java.guide.algorithm.二叉树.LeetCode116;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author coderclay
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 */
public class LeetCode111 {

    int step = 0;

    Queue<TreeNode> queue = new LinkedList<>();

    Set<TreeNode> visited = new HashSet<>();

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        bfs(root);
        return step;
    }

    void bfs(TreeNode start) {
        queue.offer(start);
        visited.add(start);
        step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    return;
                }
                TreeNode left = cur.left;
                TreeNode right = cur.right;
                if (left == null && right == null) {
                    return;
                }
                if (left != null && !visited.contains(left)) {
                    visited.add(left);
                    queue.offer(left);
                }

                if (right != null && !visited.contains(right)) {
                    visited.add(right);
                    queue.offer(right);
                }
            }
            step++;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        LeetCode111 leetCode111 = new LeetCode111();
        System.out.println(leetCode111.minDepth(treeNode1));
    }
}
