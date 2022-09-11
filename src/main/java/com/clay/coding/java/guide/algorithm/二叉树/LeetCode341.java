package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/flatten-nested-list-iterator/">...</a>
 */
public class LeetCode341 {
    public class NestedIterator implements Iterator<Integer> {

        private LinkedList<NestedInteger> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.list = new LinkedList<>(nestedList);
        }

        @Override
        public boolean hasNext() {
            while (!list.isEmpty() && !list.get(0).isInteger()) {
                List<NestedInteger> first = list.remove(0).getList();
                for (int i = first.size() - 1; i >= 0; i--) {
                    list.addFirst(first.get(i));
                }
            }
            return !list.isEmpty();
        }

        @Override
        public Integer next() {
            return list.remove(0).getInteger();
        }
    }

//    public class NestedIterator implements Iterator<Integer> {
//
//        private Iterator<Integer> iterator;
//
//        public NestedIterator(List<NestedInteger> nestedList) {
//            List<Integer> result = new LinkedList<>();
//            for (NestedInteger node : nestedList) {
//                traverse(node, result);
//            }
//            this.iterator = result.iterator();
//        }
//
//        private void traverse(NestedInteger root, List<Integer> result) {
//            if (root.isInteger()) {
//                result.add(root.getInteger());
//                return;
//            }
//            for (NestedInteger child : root.getList()) {
//                traverse(child, result);
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            return iterator.hasNext();
//        }
//
//        @Override
//        public Integer next() {
//            return iterator.next();
//        }
//    }
}
