package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.List;

/**
 * @author coderclay
 */
public class Node {
    public int val;

    public List<Node> children;

    public Node parent;

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
