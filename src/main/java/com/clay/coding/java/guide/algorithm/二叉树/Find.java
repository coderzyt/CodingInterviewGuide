package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class Find {

    TreeNode find(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode left = find(root.left, val);
        if (left != null) {
            return root;
        }
        TreeNode right = find(root.right, val);
        if (right != null) {
            return right;
        }
        return null;
    }
}
