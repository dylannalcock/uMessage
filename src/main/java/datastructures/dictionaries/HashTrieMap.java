package datastructures.dictionaries;

import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import cse332.datastructures.containers.Item;
import datastructures.worklists.ArrayStack;
import cse332.interfaces.misc.Dictionary;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Dictionary<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<>(() -> new MoveToFrontList<>());
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            ArrayStack<Entry<A, HashTrieNode>> val = new ArrayStack<>();

            for(int i = 0; i < this.pointers.size(); i++) {
                Item<A, HashTrieNode> currentVal = pointers.iterator().next();
                val.add(new AbstractMap.SimpleEntry<>(currentVal.key, currentVal.value));
            }
            return val.iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V val;

        if(key.isEmpty()) {
            val = this.root.value;
            this.root.value = value;
        } else {
            HashTrieNode node = (HashTrieNode) this.root;
            for(A curr: key) {
                if(node.pointers.find(curr) == null) {
                    node.pointers.insert(curr, new HashTrieNode());
                }
                node = node.pointers.find(curr);
            }
            val = node.value;
            node.value = value;
        }
        if(val == null) {
            size++;
        }
        return val;
    }

    @Override
    public V find(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }

        HashTrieNode node = (HashTrieNode) this.root;

        for(A curr: key) {
            node = node.pointers.find(curr);
            if(node == null) {
                return null;
            }
        }
        return node.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode node = (HashTrieNode) this.root;
        for(A curr: key) {
            node = node.pointers.find(curr);
            if(node == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
