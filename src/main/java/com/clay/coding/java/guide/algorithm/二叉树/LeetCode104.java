package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 */
public class LeetCode104 {

    /**
     * 记录最大深度
     */
    private int res = 0;

    /**
     * 记录遍历到的节点的深度
     */
    private int depth = 0;

    /**
     * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">...</a>
     */
    public int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    /**
     * 二叉树遍历框架
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }

        traverse(root.left);

        traverse(root.right);

        // 后序位置
        depth--;
    }

    /**
     * 深度优先搜索
     * 定义：输入根节点，返回这颗二叉树的最大深度
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth2(root.left);
            int rightHeight = maxDepth2(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        LeetCode104 leetCode104 = new LeetCode104();
        leetCode104.maxDepth(treeNode1);
    }
}
