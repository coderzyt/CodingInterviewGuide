package com.clay.coding.java.guide.algorithm.链表算法题;

/**
 * @author coderclay
 * https://leetcode.cn/problems/UHnkqh/
 */
public class LeetCodeOffer2_24 {
    
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
