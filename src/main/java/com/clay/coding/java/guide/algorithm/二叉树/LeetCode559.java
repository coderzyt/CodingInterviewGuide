package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author coderclay
 */
public class LeetCode559 {

    int maxDepth = 0;

    int depth = 0;

    /**
     * https://leetcode.cn/problems/maximum-depth-of-n-ary-tree/
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        traverse(root);
        return maxDepth;
    }

    public void traverse(Node root) {
        if (root == null) {
            return;
        }
        depth++;
        maxDepth = Math.max(maxDepth, depth);
        for (Node child : root.children) {
            traverse(child);
        }
        depth--;
    }


    /**
     * 分解问题思路
     */
    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        int subTreeMaxDepth = 0;
        for (Node child : root.children) {
            subTreeMaxDepth = Math.max(subTreeMaxDepth, maxDepth2(child));
        }
        return 1 + subTreeMaxDepth;
    }
}
