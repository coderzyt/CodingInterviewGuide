package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">...</a>
 */
public class LeetCode98 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        boolean isValidLeft = isValidBST(root.left, min, root);
        boolean isValidRight = isValidBST(root.right, root, max);
        return isValidLeft && isValidRight;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(10);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(20);

        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        LeetCode98 leetCode98 = new LeetCode98();
        System.out.println(leetCode98.isValidBST(treeNode2));
    }
}
