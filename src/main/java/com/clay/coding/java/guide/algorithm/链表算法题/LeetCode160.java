package com.clay.coding.java.guide.algorithm.链表算法题;

import java.util.HashSet;
import java.util.Set;

/**
 * @author coderclay
 */
public class LeetCode160 {

    // https://leetcode.cn/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> set = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        p = headB;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;

        int count1 = 0;
        while (p1 != null) {
            p1 = p1.next;
            count1++;
        }
        int count2 = 0;
        while (p2 != null) {
            p2 = p2.next;
            count2++;
        }
        p1 = headA;
        p2 = headB;
        if (count1 > count2) {
            int temp = count1 - count2;
            for (int i = 0; i < temp; i++) {
                p1 = p1.next;
            }
        } else {
            int temp = count2 - count1;
            for (int i = 0; i < temp; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        LeetCode160 leetCode160 = new LeetCode160();
        System.out.println(leetCode160.getIntersectionNode2(l1, l2));
    }
}
