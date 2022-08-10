package algorithm.sort.heap;

public class Heap<Key extends Comparable<Key>> {

    public void sort(Key[] arr) {
        int N = arr.length;
        for (int k = N/2; k >= 1; k--) {
            sink(arr, k, N);
        }
        while (N > 1) {
            exch(arr, 1, N--);
            sink(arr, 1, N);
        }
    }

    private void sink(Key[] arr, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(arr, j, j + 1)) {
                j++;
            }
            if (!less(arr, k, j)) {
                break;
            }
            exch(arr, j, k);
            k = j;
        }
    }

    private boolean less(Key[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exch(Key[] arr, int i, int j) {
        Key t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
