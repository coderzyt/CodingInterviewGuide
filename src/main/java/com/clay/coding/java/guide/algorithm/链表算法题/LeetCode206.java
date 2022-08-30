package com.clay.coding.java.guide.algorithm.链表算法题;

import java.util.LinkedList;

public class LeetCode206 {
    
    public ListNode reverseList1(ListNode head) {
        LinkedList<ListNode> list = new LinkedList<>();
        ListNode p = new ListNode(-1);
        ListNode p1 = p;
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int len = list.size();
        for (int i = len - 1; i >= 0; i--) {
            p1.next = list.get(i);
            p1 = p1.next;
        }
        p1.next = null;
        return p.next;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = null;
        LeetCode206 leetCode206 = new LeetCode206();
        leetCode206.reverseList2(listNode1);
    }
}
