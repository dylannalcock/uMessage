package datastructures.worklists;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {
    private int size;

    private E[] array;

    private int front;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.size = 0;
        this.array = (E[]) new Comparable[capacity];
        this.front = 0;
    }

    @Override
    public void add(E work) {
        if(this.isFull()) {
            throw new IllegalStateException();
        }
        array[(this.size + this.front) % this.array.length] = work;


        this.size++;
    }

    @Override
    public E peek() {
        if(!this.hasWork()){
            throw new NoSuchElementException();
        }
        return this.peek(0);
    }

    @Override
    public E peek(int i) {
        if(!this.hasWork()){
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        return this.array[(this.front + i) % this.array.length];

    }

    @Override
    public E next() {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        E frontVar = array[this.front];
        this.front = (this.front + 1) % this.array.length;
        this.size--;
        return frontVar;
    }

    @Override
    public void update(int i, E value) {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        array[(this.front + i) % this.array.length] = value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.array = (E[]) new Comparable[this.capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        int min = Math.min(this.size(), other.size());
        int cmp;
        for(int i = 0; i < min; i++) {
           cmp = this.peek(i).compareTo(other.peek(i));
           if(cmp != 0) {
               return cmp;
           }
        }
        return this.size() - other.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;
            if(this.size != other.size()) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int i = 0; i < this.size; i++) {
            hash += array[(front + i) % array.length].hashCode() * (this.front + i);
        }
        hash = hash * (this.size - this.front);
        return hash;
    }
}
