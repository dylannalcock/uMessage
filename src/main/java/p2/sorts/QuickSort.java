package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        sortHelper(array, comparator, 0, array.length);
    }

    public static <E> void sortHelper(E[] array, Comparator<E> comparator, int front, int end) {
        if(end - front > 1) {
            E pivotPoint = array[end - 1];
            int div = front - 1;
            for(int i = front; i < end; i++) {
                if(comparator.compare(pivotPoint, array[i]) >= 0) {
                    div++;
                    E tmp = array[div];
                    array[div] = array[i];
                    array[i] = tmp;

                }
            }
            sortHelper(array, comparator, front, div);
            sortHelper(array, comparator, div + 1, end);
        }
    }
}
