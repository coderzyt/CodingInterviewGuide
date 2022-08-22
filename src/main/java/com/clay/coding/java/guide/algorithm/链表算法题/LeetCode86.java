package com.clay.coding.java.guide.algorithm.链表算法题;

/**
 * @author coderclay
 */
public class LeetCode86 {

    // 分隔链表
    // https://leetcode.cn/problems/partition-list/
    public ListNode partition(ListNode head, int x) {
        ListNode result1 = new ListNode(-1);
        ListNode result2 = new ListNode(-1);
        ListNode p1 = result1;
        ListNode p2 = result2;
        while (head != null) {
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }
        p2.next = null;
        p1.next = result2.next;
        return result1.next;
    }
}
