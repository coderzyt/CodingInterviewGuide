package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/delete-node-in-a-bst/">...</a>
 */
public class LeetCode450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // 情况1：删除的节点恰好是末端节点，两个子节点都为空。
            if (root.left == null && root.right == null) {
                return null;
            }
            // 情况2：删除的节点只有一个非空子节点。
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 情况3：删除的节点有两个非空子节点，
            // 获得右子树的最小节点
            TreeNode minNode = getMin(root.right);
            // 删除右子树的最小节点
            root.right = deleteNode(root.right, minNode.val);
            // 用右子树最小节点替换root节点
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode5.left = treeNode2;
        treeNode5.right = treeNode6;

        treeNode6.right = treeNode7;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode4;
        treeNode4.left = treeNode3;

        LeetCode450 leetCode450 = new LeetCode450();
        leetCode450.deleteNode(treeNode5, 2);
    }
}
