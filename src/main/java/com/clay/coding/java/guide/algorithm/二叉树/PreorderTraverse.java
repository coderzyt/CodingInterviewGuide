package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class PreorderTraverse {

    List<Integer> preorderTraverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        traverse(root, res);
        return res;
    }

    void traverse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        traverse(root.left, res);
        traverse(root.right, res);
    }

    List<Integer> preorderTraverse2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return res;
        }
        res.add(root.val);
        List<Integer> left = preorderTraverse2(root.left, res);
        List<Integer> right = preorderTraverse2(root.right, res);
        res.addAll(left);
        res.addAll(right);
        return res;
    }

    // 定义：输入一棵二叉树的根节点，返回这棵树的前序遍历结果
    List<Integer> preorderTraverse3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        // 前序遍历的结果，root.val 在第一个
        res.add(root.val);
        // 利用函数定义，后面接着左子树的前序遍历结果
        res.addAll(preorderTraverse3(root.left));
        // 利用函数定义，最后接着右子树的前序遍历结果
        res.addAll(preorderTraverse3(root.right));
        return res;
    }
}
