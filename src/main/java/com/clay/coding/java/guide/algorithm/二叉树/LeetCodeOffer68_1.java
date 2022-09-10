package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class LeetCodeOffer68_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return find(root, min, max);
    }

    private TreeNode find(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        if (root.val > max) {
            return find(root.left, min, max);
        }
        if (root.val < min) {
            return find(root.right, min, max);
        }
        return root;
    }
}
