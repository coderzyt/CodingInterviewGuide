package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;

/**
 * @author coderclay
 */
public class LeetCode297 {
    /**
     * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
     */
    String SEP = ", ";
    String NULL = "null";
    StringBuffer stringBuffer = new StringBuffer();
    public String serialize(TreeNode root) {
        traverse(root);
        return stringBuffer.toString();
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            stringBuffer.append(NULL).append(SEP);
            return;
        }
        stringBuffer.append(root.val).append(SEP);
        traverse(root.left);
        traverse(root.right);
    }

    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
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
        LeetCode297 leetCode297 = new LeetCode297();
        System.out.println(leetCode297.serialize(treeNode1));
    }
}
