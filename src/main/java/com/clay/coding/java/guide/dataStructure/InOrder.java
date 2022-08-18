package com.clay.coding.java.guide.dataStructure;

/**
 * @author coderclay
 */
public class InOrder {

    class TreeNode {
        public TreeNode left;

        public TreeNode right;

        public String data;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }
}
