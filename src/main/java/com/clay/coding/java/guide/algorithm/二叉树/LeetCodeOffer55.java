package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class LeetCodeOffer55 {

    int maxDepth = 0;

    int depth = 0;

    /**
     * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
     */
    public int maxDepth1(TreeNode root) {
        traverse(root);
        return maxDepth;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        traverse(root.left);
        traverse(root.right);
        depth--;
    }


    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth2(root.left);
        int rightMax = maxDepth2(root.right);
        maxDepth = Math.max(leftMax, rightMax);
        return 1 + maxDepth;
    }
}
