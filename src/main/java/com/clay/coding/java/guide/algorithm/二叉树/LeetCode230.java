package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/">...</a>
 */
public class LeetCode230 {

    LinkedList<Integer> result = new LinkedList<>();

    int count = 0, res = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        result.add(root.val);
        traverse(root.right);
    }
}
