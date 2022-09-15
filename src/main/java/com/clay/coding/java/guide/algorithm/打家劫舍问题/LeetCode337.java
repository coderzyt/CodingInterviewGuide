package com.clay.coding.java.guide.algorithm.打家劫舍问题;

import java.util.HashMap;
import java.util.Map;

/**
 * @author coderclay
 * https://leetcode.cn/problems/house-robber-iii/
 */
public class LeetCode337 {

    Map<TreeNode, Integer> memo = new HashMap<>();
    
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int robRoot = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        int notRobRoot = rob(root.left) + rob(root.right);
        int res = Math.max(robRoot, notRobRoot);
        memo.put(root, res);
        return res;
    }
}
