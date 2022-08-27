package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.HashMap;

/**
 * @author coderclay
 */
public class LeetCode889 {

    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
     */
    HashMap<Integer, Integer> valToIndexPost = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIndexPost.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] postorder, int postStart, int postEnd) {
        if (preEnd < preStart) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        int rootVal = preorder[preStart];
        int leftRootVal = preorder[preStart + 1];
        int leftIndex = valToIndexPost.get(leftRootVal);
        int leftSize = leftIndex - postStart + 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, leftIndex);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, leftIndex + 1, postEnd - 1);
        return root;
    }
}
