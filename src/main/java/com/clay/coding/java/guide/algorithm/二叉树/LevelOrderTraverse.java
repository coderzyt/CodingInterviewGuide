package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author coderclay
 */
public class LevelOrderTraverse {

    public void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    return;
                }
                System.out.println(cur.val);
                TreeNode left = cur.left;
                TreeNode right = cur.right;
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        LevelOrderTraverse levelOrderTraverse = new LevelOrderTraverse();
        levelOrderTraverse.levelTraverse(treeNode1);
    }
}
