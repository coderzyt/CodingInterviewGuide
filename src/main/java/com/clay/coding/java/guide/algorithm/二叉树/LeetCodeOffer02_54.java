package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/w6cpku/">...</a>
 */
public class LeetCodeOffer02_54 {

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
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
