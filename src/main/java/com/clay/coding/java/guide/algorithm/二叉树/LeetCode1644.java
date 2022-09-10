package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class LeetCode1644 {

    boolean foundP = false, foundQ = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = LCA(root, p, q);
        if (foundP && foundQ) {
            return res;
        }
        return null;
    }

    TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (root == p || root == q) {
            if (root == p) {
                foundP = true;
            }
            if (root == q) {
                foundQ = true;
            }
            return root;
        }


        return left == null ? right : left;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        LeetCode1644 leetCode1644 = new LeetCode1644();
        System.out.println(leetCode1644.lowestCommonAncestor(treeNode1, treeNode1, treeNode3));
    }
}
