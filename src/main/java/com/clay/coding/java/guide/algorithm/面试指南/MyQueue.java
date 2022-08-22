package com.clay.coding.java.guide.algorithm.面试指南;

import java.util.Stack;

/**
 * MyQueue
 * @author yuntzhao
 */
public class MyQueue {

    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public MyQueue() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    public void add(Integer num) {
        stackPush.push(num);
    }

    public Integer poll() throws Exception {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new Exception();
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public Integer peek() throws Exception {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new Exception();
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
}