package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/convert-bst-to-greater-tree/">...</a>
 */
public class LeetCode538 {

    LinkedList<Integer> result = new LinkedList<>();
    int count = 0;

    // 超出时间限制
    public TreeNode convertBST1(TreeNode root) {
        traverse1(root);
        convert(root);
        return root;
    }

    private void traverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse1(root.left);
        result.add(root.val);
        traverse1(root.right);
    }

    private void convert(TreeNode root) {
        if (root == null) {
            return;
        }
        convert(root.left);
        count++;
        root.val = sum(result, count - 1);
        convert(root.right);
    }

    private int sum(LinkedList<Integer> result, int index) {
        int sum = 0;
        for (int i = index; i < result.size(); i++) {
            sum += result.get(i);
        }
        return sum;
    }

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }

    public static void main(String[] args) {
        LeetCode538 leetCode538 = new LeetCode538();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);

        node4.left = node1;
        node4.right = node6;
        node1.left = node0;
        node1.right = node2;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        node7.right = node8;

        leetCode538.convertBST(node4);
    }
}
