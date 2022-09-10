package com.clay.coding.java.guide.algorithm.图算法;

public class Traverse {
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.printf("进入节点 %s", root.val);
        for (TreeNode child : root.children) {
            traverse(child);
        }
        System.out.printf("离开节点 %s", root);
    }

    // 回溯算法，关注点在树枝
    void backtrack(TreeNode root) {
        if (root == null) {
            return;
        }
        for (TreeNode child : root.children) {
            System.out.printf("从 %s 到 %s", root, child);
            backtrack(child);
            System.out.printf("从 %s 到 %s", child, root);
        }
    }
}
