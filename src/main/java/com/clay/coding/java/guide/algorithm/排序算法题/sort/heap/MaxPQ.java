package com.clay.coding.java.guide.algorithm.排序算法题.sort.heap;

import java.util.Arrays;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ(Key[] arr, int N) {
        this.pq = arr;
        this.N = N;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }
    
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(j, k);
            k = j;
        }
    }
    
    private boolean less(int i, int j) {
        System.out.println(pq[i]);
        System.out.println(pq[j]);
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        Character[] list = {'0', 'T', 'H', 'R', 'P', 'S', 'O', 'A', 'E', 'I', 'N', 'G'};
        MaxPQ<Character> pq = new MaxPQ<Character>(list, list.length);
        pq.sink(2);
        System.out.println(Arrays.toString(list));
    }
}
