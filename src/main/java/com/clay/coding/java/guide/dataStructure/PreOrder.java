package com.clay.coding.java.guide.dataStructure;

/**
 * @author coderclay
 */
public class PreOrder {
    class TreeNode {
        public String data;

        public TreeNode left;

        public TreeNode right;
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
}
