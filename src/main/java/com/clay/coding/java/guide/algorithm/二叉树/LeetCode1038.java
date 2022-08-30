package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/">...</a>
 */
public class LeetCode1038 {

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
