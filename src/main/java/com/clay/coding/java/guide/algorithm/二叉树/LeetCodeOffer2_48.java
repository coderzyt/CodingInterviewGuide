package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;

/**
 * @author coderclay
 */
public class LeetCodeOffer2_48 {

    String NULL = "null";

    String SEP = ", ";

    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        traverse(root, sb);
        return sb.toString();
    }

    public void traverse(TreeNode root, StringBuffer sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        traverse(root.left, sb);
        traverse(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return traverse(nodes);
    }

    public TreeNode traverse(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = traverse(nodes);
        root.right = traverse(nodes);
        return root;
    }
}
