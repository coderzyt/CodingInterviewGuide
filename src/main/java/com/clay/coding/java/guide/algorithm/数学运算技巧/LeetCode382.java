package com.clay.coding.java.guide.algorithm.数学运算技巧;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/linked-list-random-node/">...</a>
 */
public class LeetCode382 {

    List<Integer> res;

    Random random;

    public LeetCode382(ListNode head) {
        res = new ArrayList<>();
        random = new Random();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        return res.get(random.nextInt(res.size()));
    }

    public int getRandom(ListNode head) {
        int i = 0, res = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (random.nextInt(i) == 0) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }
}
