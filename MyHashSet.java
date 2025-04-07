package datastructures;

/**
 * MyHashSet class, simulating a java hash set
 * 
 */
public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();

    MyHashMap<E, Object> hashMap;

    /**
     * Constructor to create empty hashset of size 0
     * and capacity 5
     * 
     * 
     */
    public MyHashSet() {
        hashMap = new MyHashMap<E, Object>();
    }

    /**
     * Constructor to create empty hashset of size 0
     * and given capacity
     * 
     * @param initialCapacity the starting capacity of the created hashmap object
     */
    public MyHashSet(int initialCapacity) throws IllegalArgumentException {
        hashMap = new MyHashMap<E, Object>(initialCapacity);
    }

    /**
     * Adds element into the hashset
     * 
     * @param element-the element being added
     * 
     * @return true if the set did not already contain the specified element,
     *         false otherwise.
     */
    public boolean add(E element) throws NullPointerException {
        if (element.equals(null)) {
            throw new NullPointerException();
        }

        if (this.hashMap.put(element, DEFAULT_OBJECT) == null) {
            return true;
        }
        return false;
    }

    /**
     * Removes element from the hashset
     * 
     * @param element-the element being removed
     * 
     * @return true if the set contained the specified element,
     *         false otherwise.
     */
    public boolean remove(E element) throws NullPointerException {
        if (element.equals(null)) {
            throw new NullPointerException();
        }
        if (this.hashMap.remove(element)==null) {
            return false;
        }
        return true;
    }

    /**
     * the number of elements stored in the hashset
     * 
     * 
     * @return the elements counted
     */
    public int size() {
        return this.hashMap.size();
    }

    /**
     * removes all elements stored in hashset
     * 
     * 
     */
    public void clear() {
        this.hashMap.clear();
    }

    /**
     * determines if hashset is empty or not
     * 
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.hashMap.isEmpty();
    }
}