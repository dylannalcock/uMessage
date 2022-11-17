package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeapComparable<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;

    public MinFourHeapComparable() {
        this.data = (E[])new Comparable[10];
        this.size = 0;
    }

    @Override
    public boolean hasWork() {
        return super.hasWork();
    }

    @Override
    public void add(E work) {
        if(this.size == this.data.length) {
            E[] tmp = (E[]) new Comparable[this.size + 1];
            for(int i = 0; i < this.size; i++) {
                tmp[i] = this.data[i];
            }
            this.data = tmp;
        }
        this.data[this.size] = work;
        if(this.size > 0) {
            percUp((this.size - 1)/4, this.size);
        }
        this.size++;
    }

    @Override
    public E peek() {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        return this.data[0];
    }

    @Override
    public E next() {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        E val = this.data[0];
        this.data[0] = this.data[this.size - 1];
        this.size--;
        if(this.size > 0) {
            int firstChild = 1;
            percDown(0, min(firstChild));
        }
        return val;
    }

    private int min(int node) {
        int lo;
        if(node < this.size) {
            lo = node;

            for(int i = node + 1; i < node + 4; i++) {
                if(i < this.size && this.data[lo].compareTo(this.data[i]) > 0) {
                    lo = i;
                }
            }
            return lo;
        } else {
            return -1;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.data = (E[])new Comparable[10];
        this.size = 0;
    }

    private void percUp(int parent, int child) {
        while(child != 0 && this.data[parent].compareTo(this.data[child]) > 0) {
            E tmp = this.data[child];
            this.data[child] = this.data[parent];
            this.data[parent] = tmp;
            child = parent;
            parent = (child - 1)/4;
        }
    }

    private void percDown(int parent, int child) {
        while(child != -1 && this.data[parent].compareTo(this.data[child]) > 0) {
            E tmp = data[child];
            data[child] = data[parent];
            data[parent] = tmp;
            parent = child;

            int firstChild = 4 * parent + 1;
            int lo;
            if(firstChild < this.size) {
                lo = firstChild;

                for(int i = firstChild + 1; i < firstChild + 4; i++) {
                    if(i < this.size && this.data[lo].compareTo(this.data[i]) > 0) {
                        lo = i;
                    }
                }
                child = lo;
            } else {
                child = -1;
            }
        }
    }
}
