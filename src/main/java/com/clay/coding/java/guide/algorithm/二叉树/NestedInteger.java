package com.clay.coding.java.guide.algorithm.二叉树;

import java.util.List;

public class NestedInteger {

    private Integer val;

    private List<NestedInteger> list;

    public NestedInteger(Integer val) {
        this.val = val;
        this.list = list;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.val = null;
        this.list = list;
    }

    public boolean isInteger() {
        return val != null;
    }

    public Integer getInteger() {
        return this.val;
    }

    public List<NestedInteger> getList() {
        return this.list;
    }
}
