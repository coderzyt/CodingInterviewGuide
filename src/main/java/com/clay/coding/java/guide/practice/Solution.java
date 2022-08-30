package com.clay.coding.java.guide.practice;

import java.util.List;

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
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

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode getInsersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        int countA = 0;
        int countB = 0;
        while (pA != null) {
            pA = pA.next;
            countA++;
        }
        while (pB != null) {
            pB = pB.next;
            countB++;
        }
        pA = headA;
        pB = headB;
        if (countA > countB) {
            int diff = countA - countB;
            for (int i = 0; i < diff; i++) {
                pA = pA.next;
            }
        }
        if (countB > countA) {
            int diff = countB - countA;
            for (int i = 0; i < diff; i++) {
                pB = pB.next;
            }
        }
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }

    public ListNode removeNthFromENode(ListNode head, int n) {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode l = p;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                l.next = l2;
                l2 = l2.next;
            } else {
                l.next = l1;
                l1 = l1.next;
            }
            l = l.next;
        }
        if (l1 == null) {
            l.next = l2;
        }
        if (l2 == null) {
            l.next = l1;
        }
        return p.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode node = null;
        for (int i = 0; i < lists.length; i++) {
            node = mergeTwoLists(node, lists[i]);
        }
        return node;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
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
        p1.next = l2.next;
        return l1.next; 
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = new ListNode(-1);
        ListNode p2 = new ListNode(-1);
        p1.next = head;
        p2.next = head;
        int count = 0;
        while (count < k) {
            p1 = p1.next;
            count++;
        }
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2;
    }

}
