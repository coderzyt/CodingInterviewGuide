package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.HashMap;

/**
 * @author coderclay
 */
public class LeetCode105 {

    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     */
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int index = valToIndex.get(rootVal);
        int lefSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + lefSize,
                inorder, inStart, index - 1);
        root.right = build(preorder, preStart + lefSize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }
}
