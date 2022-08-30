package com.clay.coding.java.guide.algorithm.排序算法题;

/**
 * @author coderclay
 */
public class StackThroughNode<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first;

    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }    
}
