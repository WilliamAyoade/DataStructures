package datastructures;

/**
 * MyHashMap class, creating a hashmap data structure
 * 
 */
public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;
    private static final int HASH_CODE_MASK = 0x7fffffff;

    Node[] hashTable;
    int size;

    /**
     * Constructor to create empty hashmap of size 0
     * and capacity 5
     * 
     * 
     */
    public MyHashMap() {
        hashTable = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructor to create empty hashmap of size 0
     * and given capacity
     * 
     * @param initialCapacity the starting capacity of the created hashmap 
     * object
     */
    public MyHashMap(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        hashTable = new Node[initialCapacity];
        size = 0;
    }

    /**
     * Get data of type V mapped to given key
     * 
     * @param key- the key of the value
     * 
     * @return the value mapped to given key
     */
    public V get(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = getHash(key, hashTable.length);

        Node temp=hashTable[index];
        while(temp!=null){
            if(key.equals(temp.getKey())){
                return (V)temp.getValue();
            }
            temp=temp.getNext();
        }


        return null;
    }

    /**
     * adds key-value pair to the hash map
     * 
     * @param the key to be added
     * @param the value to be added
     * 
     * @return the value previously mapped to given key if any, null otherwise
     */
    public V put(K key, V value) throws NullPointerException {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        if (size >= hashTable.length * LOAD_FACTOR) {
            this.expandCapacity();
        }

        int index = getHash(key, hashTable.length);
        if (hashTable[index] == null) {
            Node newNode = new Node<K, V>(key, value);
            hashTable[index] = newNode;
            size++;
            return null;
        }

        if (hashTable[index].getKey().equals(key)) {
            V temp = (V) hashTable[index].getValue();
            hashTable[index].setValue(value);
            return temp;
        }
        Node tempNode = hashTable[index];
        while (tempNode.getNext() != null) {
            tempNode = tempNode.getNext();
            if (tempNode.getKey().equals(key)) {
                V temp = (V) tempNode.getValue();
                tempNode.setValue(value);
                return temp;
            }
        }
        Node newNode2 = new Node<K, V>(key, value);

        size++;
        tempNode.setNext(newNode2);
        return null;

    }

    /**
     * remove key-value pair from hashmap
     * 
     * @param the key of the map to be reomoved
     * 
     * @return the value previously mapped to given key if any, null otherwise
     */
    public V remove(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        V tempr = null;
        int index = getHash(key, hashTable.length);

        Node temp=hashTable[index];
        Node prNode=null;
        while(temp!=null){
            if(key.equals(temp.getKey())){
                tempr=(V)temp.getValue();
                if(prNode==null){
                    hashTable[index]=hashTable[index].getNext();
                    break;
                }
                else{
                    prNode.next=temp.getNext();
                    break;
                }
            }

            prNode=temp;
            temp=temp.getNext();
        }


        return tempr;
    }

    /**
     * the number of key-value pairs stored in the hashmap
     * 
     * 
     * @return the pairs counted
     */
    public int size() {
        int count = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                count++;
                Node temp = hashTable[i];
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * the number of key-value pairs that could be stored in the hashmap
     * 
     * 
     * @return the capacity of the hashmap
     */
    public int getCapacity() {
        return hashTable.length;
    }

    /**
     * removed all key-value pairs stored in hashmap
     * 
     * 
     */
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
    }

    /**
     * determines if hashmap is empty or not
     * 
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Double the capacity of the hashmap and rehash all key-value pairs into
     * the new hash map.
     * 
     * 
     */
    public void expandCapacity() {
        Node[] expand = new Node[hashTable.length * EXPAND_CAPACITY_RATIO];
        int newCap = hashTable.length * EXPAND_CAPACITY_RATIO;
        int index;
        Node hold;
        for (int i = 0; i < size; i++) {
            hold = hashTable[i];
            while (hold != null) {
                index = getHash((K) hold.getKey(), newCap);

                if (expand[index] != null) {
                    Node temp2 = expand[index];
                    while (temp2.getNext() != null) {
                        temp2 = temp2.getNext();
                    }
                    Node newNode = new Node(hold.getKey(), hold.getValue());
                    expand[index].setNext(newNode);

                } else {
                    Node newNode = new Node(hold.getKey(), hold.getValue());
                    expand[index] = newNode;

                }

                hold = hold.getNext();
            }
        }
        hashTable = expand;
    }

    /**
     * Uses the given hash function in the starter code to compute hash codes.
     * 
     * @param key-      the key to be computed
     * @param capacity- the capacity of the hash table
     * 
     * @return the hash index of given key
     */
    public int getHash(K key, int capacity)
            throws NullPointerException, IllegalArgumentException {
        if (key.equals(null)) {
            throw new NullPointerException();
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        // Hashing function is given here. DO NOT MODIFY THIS
        return (key.hashCode() & HASH_CODE_MASK) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references to the next 
     * node in the linked list
     */
    protected class Node<K, V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * 
         * @param key   key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * 
         * @param n the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * 
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * 
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * 
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * 
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * 
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
