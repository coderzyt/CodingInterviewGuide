package com.clay.coding.java.guide.dataStructure;

import java.util.BitSet;

/**
 * @author coderclay
 */
public class BloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 通过这个数组可以创建6个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组，数组中的元素只能是0或者1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 存放包含hash函数的类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含hash函数的类的数组，每个类中的hash函数都不一样
     */
    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }


    /**
     * 静态内部类，用于hash操作
     */
    public static class SimpleHash {

        private int cap;

        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算hash值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode())));
        }
    }

    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        BloomFilter filter = new BloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));

        Integer value3 = 13423;
        Integer value4 = 22131;
        BloomFilter filter1 = new BloomFilter();
        System.out.println(filter1.contains(value3));
        System.out.println(filter1.contains(value4));
        filter1.add(value3);
        filter1.add(value4);
        System.out.println(filter1.contains(value3));
        System.out.println(filter1.contains(value4));
    }
}
