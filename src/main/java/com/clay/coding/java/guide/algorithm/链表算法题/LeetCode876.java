package com.clay.coding.java.guide.algorithm.链表算法题;

/**
 * @author coderclay
 */
public class LeetCode876 {

    // 寻找链表的中间结点
    // https://leetcode.cn/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
