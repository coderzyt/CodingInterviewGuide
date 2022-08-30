package com.clay.coding.java.guide.dataStructure;

/**
 * @author coderclay
 */
public class PostOrder {

    class TreeNode {
        public TreeNode left;

        public TreeNode right;

        public String data;
    }

    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}
