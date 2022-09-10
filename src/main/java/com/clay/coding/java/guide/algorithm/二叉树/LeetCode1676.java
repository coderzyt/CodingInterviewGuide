package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.HashSet;

/**
 * @author coderclay
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 */
public class LeetCode1676 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (nodes.length == 0) {
            return null;
        }
        if (nodes.length == 1) {
            return nodes[0];
        }
        TreeNode first = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            first = find(root, first, nodes[i]);
        }
        return first;
    }

    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = find(root.left, p, q);
        TreeNode right = find(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode[] nodes) {
        HashSet<Integer> nodeVals = new HashSet<>();
        for (TreeNode node : nodes) {
            nodeVals.add(node.val);
        }
        return find(root, nodeVals);
    }

    private TreeNode find(TreeNode root, HashSet<Integer> nodeVals) {
        if (root == null) {
            return null;
        }
        if (nodeVals.contains(root.val)) {
            return root;
        }
        TreeNode left = find(root.left, nodeVals);
        TreeNode right = find(root.right, nodeVals);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
