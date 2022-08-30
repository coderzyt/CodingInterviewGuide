package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class LeetCode700 {

    TreeNode res;

    public TreeNode searchBST(TreeNode root, int val) {
        traverse(root, val);
        return res;
    }

    private void traverse(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (root.val == val) {
            res = root;
            return;
        } else if (root.val > val) {
            traverse(root.left, val);
        } else {
            traverse(root.right, val);
        }
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            return searchBST2(root.left, val);
        }
        if (root.val < val) {
            return searchBST2(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        LeetCode700 leetCode700 = new LeetCode700();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode4.left = treeNode2;
        treeNode4.right = treeNode7;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        leetCode700.searchBST2(treeNode4, 3);
    }
}
