package algorithm;

import java.util.Iterator;

/**
 * @author coderclay
 */
public class StackThroughArrays<Item> {
    private Item[] a;

    private int N;

    public StackThroughArrays(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {
        return new StackThroughArraysIterator();
    }

    public class StackThroughArraysIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return i > 0;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            return a[--i];
        }
    
        
    }
}
