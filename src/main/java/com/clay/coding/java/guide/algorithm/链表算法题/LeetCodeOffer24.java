package com.clay.coding.java.guide.algorithm.链表算法题;

public class LeetCodeOffer24 {
    
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
