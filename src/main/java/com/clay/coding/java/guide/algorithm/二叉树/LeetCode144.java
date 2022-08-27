package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCode144 {

    List<Integer> res = new LinkedList<>();

    /**
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }


    /**
     * 迭代方式
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return res;
        }
        res.add(root.val);
        res.addAll(preorderTraversal2(root.left));
        res.addAll(preorderTraversal2(root.right));
        return res;
    }
}
