package com.clay.coding.java.guide.algorithm.链表算法题;

/**
 * @author coderclay
 */
public class LeetCode141 {

    // 判断链表中是否有环
    // https://leetcode.cn/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
