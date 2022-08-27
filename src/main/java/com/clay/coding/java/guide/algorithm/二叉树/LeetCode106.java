package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.HashMap;

/**
 * @author coderclay
 */
public class LeetCode106 {

    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     */
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public TreeNode build(int[] inorder, int inStart, int inEnd,
                          int[] postOrder, int postStart, int postEnd) {
        if (postEnd < postStart) {
            return null;
        }
        int rootVal = postOrder[postEnd];
        int index = valToIndex.get(rootVal);
        int rightSize = inEnd - index;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, index - 1,
                postOrder, postStart, postEnd - rightSize - 1);
        root.right = build(inorder, index + 1, inEnd,
                postOrder, postEnd - rightSize, postEnd - 1);
        return root;
    }
}
