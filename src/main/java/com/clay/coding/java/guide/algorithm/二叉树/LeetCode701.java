package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/insert-into-a-binary-search-tree/">...</a>
 */
public class LeetCode701 {


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            insertIntoBST(root.left, val);
        }
        return root;
    }

    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode temp = root;
        traverse(temp, val);
        return root;
    }

    private void traverse(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return;
            } else {
                traverse(root.right, val);
            }
        }
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return;
            } else {
                traverse(root.left, val);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode4.left = treeNode2;
        treeNode4.right = treeNode7;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;

        LeetCode701 leetCode701 = new LeetCode701();
        leetCode701.insertIntoBST(treeNode4, 5);
    }
}
