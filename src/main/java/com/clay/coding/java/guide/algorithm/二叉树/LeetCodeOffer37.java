package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;

/**
 * @author coderclay
 */
public class LeetCodeOffer37 {

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

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        LeetCodeOffer37 leetCodeOffer37 = new LeetCodeOffer37();
        String data = leetCodeOffer37.serialize(treeNode1);
        System.out.println(data);
        TreeNode root = leetCodeOffer37.deserialize(data);
        System.out.println(root);
    }
}
