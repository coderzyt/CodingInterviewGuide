package com.clay.coding.java.guide.algorithm.二叉树;

/**
 * @author coderclay
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */
public class LeetCode1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node p1 = p;
        Node q1 = q;

        int countP = 0;
        while (p1 != null) {
            p1 = p1.parent;
            countP++;
        }
        int countQ = 0;
        while (q1 != null) {
            q1 = q1.parent;
            countQ++;
        }
        p1 = p;
        q1 = q;
        if (countP > countQ) {
            int diff = countP - countQ;
            while (diff > 0) {
                p1 = p1.parent;
                diff--;
            }
        } else if (countQ > countP) {
            int diff = countQ - countP;
            while (diff > 0) {
                q1 = q1.parent;
                diff--;
            }
        }
        while (p1 != null && q1 != null) {
            if (p1 == q1) {
                return p1;
            }
            p1 = p1.parent;
            q1 = q1.parent;
        }
        return null;
    }
}
