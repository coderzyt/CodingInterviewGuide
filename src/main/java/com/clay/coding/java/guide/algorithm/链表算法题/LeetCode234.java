package com.clay.coding.java.guide.algorithm.链表算法题;

public class LeetCode234 {
    
    ListNode left = null;
    public boolean isPalindromeListNode(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (left.val == right.val);
        left = left.next;
        return res;
    }

    public boolean isPalindromeNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode right = reverse(slow);
        while (right != null) {
            if (head.val != right.val) {
                return false;
            }
            right = right.next;
            head = head.next;
        }
        return true;
    }


    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
