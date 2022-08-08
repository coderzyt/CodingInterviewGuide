package algorithm.sort.selection;

import algorithm.sort.Example;

/**
 * @author coderclay
 * 2.1 选择排序
 */
public class Selection {
    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (Example.less(arr[j], arr[min])) {
                    min = j;
                }
            }
            Example.exch(arr, i, min);
        }

    }

}
