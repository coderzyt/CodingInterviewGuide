package com.clay.coding.java.guide.algorithm.链表算法题;

public class LeetCode92 {
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * 将链表的前n个节点反转（n <= 链表长度）
     * @param head
     * @param n
     * @return
     */
    ListNode successor = null; // 后驱节点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n+1 个节点
            successor = head.next;
            return head;
        }
        // 以head.next为起点，需要反转前 n-1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的head节点和后面的节点连接起来
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = null;

        LeetCode92 leetCode92 = new LeetCode92();
//        leetCode92.reverseN(listNode1, 3);
        leetCode92.reverseBetween(listNode1, 3, 5);
    }
}
