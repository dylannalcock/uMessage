package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * 1. You must implement a generic chaining hashtable. You may not
 * restrict the size of the input domain (i.e., it must accept
 * any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 * shown in class!).
 * 5. HashTable should be able to resize its capacity to prime numbers for more
 * than 200,000 elements. After more than 200,000 elements, it should
 * continue to resize using some other mechanism.
 * 6. We suggest you hard code some prime numbers. You can use this
 * list: http://primes.utm.edu/lists/small/100000.txt
 * NOTE: Do NOT copy the whole list!
 * 7. When implementing your iterator, you should NOT copy every item to another
 * dictionary/list and return that dictionary/list's iterator.
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private final Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K,V>[] hashTable;
    private final int[] primeNums = {37, 101, 337, 739, 1381, 3517, 5563, 9473, 13723, 20411, 27407,
            43777, 53813, 69379, 77041, 88799, 95087, 122039,140419, 153499, 167117,
            175687, 185797};
    private int index;
    private int items;

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        index = 0;
        hashTable = new Dictionary[10];
    }


    public int size() {
        return items;
    }

    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if(loadFactor() >= 1.0) {
            this.hashTable = resize(hashTable);
        }
        int index = Math.abs(key.hashCode() % hashTable.length);
        if(index >= 0) {
            if(hashTable[index] == null) {
                hashTable[index] = newChain.get();
            }
            V oldVal = null;
            if(this.find(key) == null) {
                items++;
            } else {
                oldVal = this.find(key);
            }
            hashTable[index].insert(key, value);
            return oldVal;
        } else {
            return null;
        }
    }

    private double loadFactor() {
        return (double)(++items) / (double)hashTable.length;
    }

    @Override
    public V find(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        if(hashTable.length == 0) {
            return null;
        }
        int index = Math.abs(key.hashCode() % hashTable.length);
        if(hashTable[index] != null) {
            return hashTable[index].find(key);
        } else {
            hashTable[index] = newChain.get();
            return null;
        }
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new myIterator(this);

    }

    public class myIterator implements Iterator<Item<K, V>> {
        private int index = -1;
        private int returnedItems = 0;
        private Iterator<Item<K, V>> itr;
        private final ChainingHashTable<K, V> hashTable;

        public myIterator(ChainingHashTable<K, V> hashTable) {
            this.hashTable = hashTable;
            itr = null;
        }

        @Override
        public boolean hasNext() {
            return returnedItems != hashTable.items;
        }

        @Override
        public Item<K, V> next() {
            if(index < 0 || !itr.hasNext()) {
                index++;
                while(hashTable.hashTable[index] == null) {
                    index++;
                }
                itr = hashTable.hashTable[index].iterator();
            }
            returnedItems++;
            return itr.next();
        }
    }

    private Dictionary<K,V>[] resize(Dictionary<K, V>[] currArray) {
        if(currArray == null) {
            throw new IllegalArgumentException();
        }
        Dictionary<K,V>[] change;
        if(index > primeNums.length - 1) {
            change = new Dictionary[currArray.length * 2];
        } else {
            change = new Dictionary[primeNums[index]];
        }
        for (Dictionary<K, V> kvDictionary : currArray) {
            if (kvDictionary != null) {
                for (Item<K, V> item : kvDictionary) {
                    int index = Math.abs(item.key.hashCode() % change.length);
                    if (change[index] == null) {
                        change[index] = newChain.get();
                    }
                    change[index].insert(item.key, item.value);
                }
            }
        }
        index++;
        return change;
    }
}
