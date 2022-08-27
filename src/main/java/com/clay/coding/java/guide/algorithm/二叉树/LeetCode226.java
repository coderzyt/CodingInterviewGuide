package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class LeetCode226 {

    /**
     * https://leetcode.cn/problems/invert-binary-tree/
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        traverse(root.left);
        traverse(root.right);
    }

    /**
     * 尝试使用分解问题的思路解决
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftInvert = invertTree2(root.left);
        TreeNode rightInvert = invertTree2(root.right);
        root.left = rightInvert;
        root.right = leftInvert;
        return root;
    }
}
