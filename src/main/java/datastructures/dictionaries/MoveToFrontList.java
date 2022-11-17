package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;

import java.util.Iterator;

/**
 * 1. The list is typically not sorted.
 * 2. Add new items to the front of the list.
 * 3. Whenever find or insert is called on an existing key, move it
 * to the front of the list. This means you remove the node from its
 * current position and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 * elements to the front.  The iterator should return elements in
 * the order they are stored in the list, starting with the first
 * element in the list. When implementing your iterator, you should
 * NOT copy every item to another dictionary/list and return that
 * dictionary/list's iterator.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    private Node front;

    private class Node {
        private final Item<K, V> data;
        private Node next;
        public Node(Item<K, V> item) {
            this(item, null);
        }

        public Node(Item<K, V> item, Node next) {
            this.data = item;
            this.next = next;
        }
    }

    public MoveToFrontList() {
        this(null);
    }

    public MoveToFrontList(Item<K, V> item) {
        this.front = new Node(item);
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V val = this.find(key);
        if (val != null) {
            this.front.data.value = value;
        } else {
            this.front = new Node(new Item<>(key, value), this.front);
            this.size++;
        }
        return val;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node currNode = this.front;
        V val = null;
        if (currNode.data != null) {
            if (currNode.data.key.equals(key)) {
                return currNode.data.value;
            }

            while(currNode.next.data != null && !currNode.next.data.key.equals(key)) {
                currNode = currNode.next;
            }
            if (currNode.next.data != null) {
                val = currNode.next.data.value;
                Node nextNode = currNode.next;
                currNode.next = nextNode.next;
                nextNode.next = this.front;
                this.front = nextNode;
            }
        }
        return val;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new myIterator();
    }

    private class myIterator extends SimpleIterator<Item<K, V>> {
        private Node currNode;

        public myIterator() {
            this.currNode = MoveToFrontList.this.front;
        }
        public Item<K, V> next() {
            Item<K, V> returnItem = currNode.data;
            currNode = currNode.next;
            return returnItem;
        }
        public boolean hasNext() {
            return currNode != null && currNode.next != null;
        }
    }
}
