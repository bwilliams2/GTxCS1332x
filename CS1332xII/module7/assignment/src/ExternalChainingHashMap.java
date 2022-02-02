import java.util.NoSuchElementException;
import java.lang.Math;
/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
	@SuppressWarnings("unchecked")
	public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }
    
    private int findIndex(K key, int tableLength) {
    	int hash = key.hashCode();
    	int index = Math.abs((hash % tableLength));
    	return index;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
    	if (key == null || value == null) {
    		throw new IllegalArgumentException();
    	}
    	if (( (double)(size + 1) / (double)table.length) >= MAX_LOAD_FACTOR) {
    		resizeBackingTable((table.length * 2) + 1);
    	}
    	V oldValue = addItem(key, value, table);
    	if (oldValue == null) {
    		size += 1;
    	}
    	return oldValue;
    }
    
    private V addItem(K key, V value, ExternalChainingMapEntry<K, V>[] thisTable){
    	V oldValue = null;
    	ExternalChainingMapEntry<K, V> newEntry = new ExternalChainingMapEntry<>(key, value);
    	int startInd = findIndex(key, thisTable.length);
    	ExternalChainingMapEntry<K, V> node = thisTable[startInd];
    	if (node == null) {
    		thisTable[startInd] = newEntry;
    	} else if (node.getKey().equals(key)){
    		oldValue = node.getValue();
    		node.setValue(value);
    	} else {
    		while (node.getNext() != null) {
    			node = node.getNext();
    			if (node.getKey().equals(key)) {
    				oldValue = node.getValue();
    				node.setValue(value);
    				return oldValue;
    			}
    		}
    		newEntry.setNext(thisTable[startInd]);
    		thisTable[startInd] = newEntry;
    	}
    	return oldValue;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
    	if (key == null) {
    		throw new IllegalArgumentException();
    	}
    	V oldValue = removeItem(key, table);
    	if (oldValue != null) {
    		size -= 1;
    	}
    	return oldValue;
    }
    
    private V removeItem(K key, ExternalChainingMapEntry<K, V>[] thisTable){
    	V oldValue = null;
    	int startInd = findIndex(key, thisTable.length);
    	ExternalChainingMapEntry<K, V> node = thisTable[startInd];
    	if (node == null) {
    		throw new NoSuchElementException();
    	} else if (node.getKey().equals(key)){
    		oldValue = node.getValue();
    		thisTable[startInd] = node.getNext();
    		node.setNext(null);
    	} else {
        	ExternalChainingMapEntry<K, V> oldNode = thisTable[startInd];
    		while (oldNode.getNext() != null) {
    			node = oldNode.getNext();
    			if (node.getKey().equals(key)) {
    				oldValue = node.getValue();
    				oldNode.setNext(node.getNext());
    				return oldValue;
    			} else {
    				oldNode = node;
    			}
    		}
    		throw new NoSuchElementException();
    	}
    	return oldValue;
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
    	ExternalChainingMapEntry<K, V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
    	for (int i = 0; i < table.length; i++) {
    		if (table[i] != null) {
    			ExternalChainingMapEntry<K, V> node = table[i];
    			while (node != null) {
    				addItem(node.getKey(), node.getValue(), newTable);
    				node = node.getNext();
    			}
    		}
    	}
    	table = newTable;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}