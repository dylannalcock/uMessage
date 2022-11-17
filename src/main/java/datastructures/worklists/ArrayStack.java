package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private int size;
    private E[] array;
    private int front;


    public ArrayStack() {
        this.size = 10;
        this.array = (E[]) new Object[size];
        this.front = 0;
    }

    @Override
    public void add(E work) {
        if(this.array.length == this.front) {
            size = size * 2;
            E[] newArray = (E[]) new Object[size];

            for(int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            this.array = newArray;
        }
        array[this.front] = work;
        this.front++;
    }

    @Override
    public E peek() {
        if(this.hasWork()) {
            return array[this.front - 1];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E next() {
        if(this.hasWork()) {
            E valueToReturn = this.array[front - 1];
            array[front - 1] = null;
            this.front--;
            return valueToReturn;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return this.front;
    }

    @Override
    public void clear() {
        this.array = null;
    }
}
