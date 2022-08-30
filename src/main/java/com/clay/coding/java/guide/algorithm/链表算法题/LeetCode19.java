package com.clay.coding.java.guide.algorithm.链表算法题;

/**
 * @author coderclay
 */
public class LeetCode19 {

    // 删除倒数第 n 个结点
    // https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode p = new ListNode(-1);
        p.next = head;

        ListNode p1 = p;
        ListNode p2 = p;

        int count = 0;
        while (count < n + 1) {
            p1 = p1.next;
            count++;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;
        return p.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

        ListNode p = new ListNode(-1);
        p.next = head;
        ListNode x = getKthFromEnd(p, n + 1);
        x.next = x.next.next;
        return p.next;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        int count = 0;
        while (count < k) {
            p1 = p1.next;
            count++;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        LeetCode19 leetCode19 = new LeetCode19();
        ListNode listNode = new ListNode();
        listNode.val = 1;
        listNode.next = null;
        System.out.println(leetCode19.removeNthFromEnd2(listNode, 1));
        System.out.println(leetCode19.removeNthFromEnd(listNode, 1));
    }
}
