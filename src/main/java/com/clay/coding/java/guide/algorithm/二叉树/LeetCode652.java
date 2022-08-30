package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 */
public class LeetCode652 {

    HashMap<String, Integer> memo = new HashMap<>();

    LinkedList<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubTrees(TreeNode root) {
        traverse(root);
        return res;
    }

    public String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + ", " + right + ", " + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        if (freq == 1) {
            res.add(root);
        }
        memo.put(subTree, freq + 1);
        return subTree;
    }
}
