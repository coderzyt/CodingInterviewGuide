package com.clay.coding.interview.guide;

public class ReverseListNode {


    private static LNode reverseLNode(LNode head) {

        LNode cur = head;

        LNode pre = null;

        LNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    public static void main(String[] args) {

    }
}
