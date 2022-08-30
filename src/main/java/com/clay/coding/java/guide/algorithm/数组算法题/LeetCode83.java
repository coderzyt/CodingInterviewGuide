package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode83 {

    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p2 != null) {
            if (p1.val != p2.val) {
                p1.next = p2;
                p1 = p1.next;
            }
            if (p2.next == null && p1.val == p2.val) {
                p1.next = null;
            }
            p2 = p2.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null) {
            if (p1.val != p2.val) {
                p1.next = p2;
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        p1.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        LeetCode83 leetCode83 = new LeetCode83();
        leetCode83.deleteDuplicates(l1);
    }
}
