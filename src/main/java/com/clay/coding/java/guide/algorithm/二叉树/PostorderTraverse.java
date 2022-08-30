package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class PostorderTraverse {

    List<Integer> postorderTraverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        traverse(root, res);
        return res;
    }

    void traverse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        traverse(root.left, res);
        traverse(root.right, res);
        res.add(root.val);
    }
}
