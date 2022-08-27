package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCode114 {

    TreeNode dummy = new TreeNode(-1);
    TreeNode p = dummy;

    public TreeNode flatten1(TreeNode root) {
        if (root == null) {
            return null;
        }
        traverse(root);
        return p.right;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        p.right = new TreeNode(root.val);
        p = p.right;

        traverse(root.left);
        traverse(root.right);
    }

    void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        /**
         * 后序遍历位置
         * 左右子树已经拉平成一条链表
         */
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 将左子树作为右子树
        root.left  = null;
        root.right = left;

        // 将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    void flatten3(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        preorderTraversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1), cur = list.get(i);
            prev.left = null;
            prev.right = cur;
        }
    }

    void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

}
