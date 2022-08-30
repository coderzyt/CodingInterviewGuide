package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class LeetCode543 {

    int maxDiameter = 0;

    public int diameterOfBinaryTree1(TreeNode root) {
        maxDepth1(root);
        return maxDiameter;
    }

    int maxDepth1(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        int myDiameter = leftMaxDepth + rightMaxDepth;
        maxDiameter = Math.max(maxDiameter, myDiameter);
        return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return maxDiameter;
    }

    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);

        traverse(root.left);
        traverse(root.right);
    }

    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return 1 + Math.max(leftMax, rightMax);
    }
}
