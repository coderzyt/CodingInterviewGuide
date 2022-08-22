package com.clay.coding.java.guide.algorithm.面试指南;

import java.util.LinkedList;

/**
 * @author yuntzhao
 */
public class WindowMaxArray {

    private static void getWindowMaxArray(int[] arr, int n, int w) {
        int[] result = new int[n-w+1];
        for (int i=1; i <= n-w+1; i++) {
            int max = getMaxOfThree(arr[i-1], arr[i], arr[i+1]);
            result[i-1]=max;
        }
        System.out.println(result);
    }

    private static int getMaxOfThree(int a, int b, int c) {
        if (a >= b) {
            return Math.max(a, c);
        } else {
            return Math.max(b, c);
        }
    }

    // example
    private static void getMaxWindow(int[] arr, int n, int w) throws Exception {

        if (arr == null || w < 1 || arr.length < w) {
            throw new Exception();
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index+1] = arr[qmax.peekFirst()];
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
//        long time1 = System.currentTimeMillis();
//        getWindowMaxArray(new int[]{4,3,5,4,3,3,6,7}, 8, 2);
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);

        long time3 = System.currentTimeMillis();
        getMaxWindow(new int[]{4,3,5,4,3,3,6,7}, 8, 2);
        long time4 = System.currentTimeMillis();
        System.out.println(time4 - time3);
    }
}
