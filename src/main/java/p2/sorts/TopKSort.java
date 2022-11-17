package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

import java.util.Comparator;
import datastructures.worklists.MinFourHeap;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    /**
     * Behaviour is undefined when k > array.length
     */
    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        if(k > array.length) {
            k = array.length;
        }
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        for(int i = 0; i < k; i++) {
            heap.add(array[i]);
        }

        for(int i = k; i < array.length; i++) {
            if(comparator.compare(array[i], heap.peek()) > 0) {
                heap.next();
                heap.add(array[i]);
            }
            array[i] = null;
        }
        for(int i = 0; i < k; i++) {
            array[i] = heap.next();
        }
    }
}
