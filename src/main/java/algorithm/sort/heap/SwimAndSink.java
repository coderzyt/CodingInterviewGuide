package algorithm.sort.heap;

public class SwimAndSink<Key extends Comparable> {

    private Key[] pq;
    private int N = 0;

    public SwimAndSink(Key[] pq) {
        this.pq = pq;
    }

    private void swim(int k) {
        exch(k / 2, k);
        k = k / 2;
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
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
