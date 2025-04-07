package datastructures;

import java.util.ArrayList;
import java.util.Collection;

/**
 * MyMinHeap class, creating a sorted min heap
 * 
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    protected ArrayList<E> data;
    private static final int BRANCH_FACTOR = 2;

    /**
     * Constructor to create empty minheap of size 0
     * 
     * 
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }

    /**
     * Initializes a min-heap using the elements in collection.
     * 
     * 
     */
    public MyMinHeap(Collection<? extends E> collection)
            throws NullPointerException {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        this.data = new ArrayList<E>(collection);

        for (int i = data.size() - 1; i != -1; i--) {
            percolateDown(i);
        }
    }

    /**
     * Swap the elements at from and to indices in data.
     *
     * @param from first element to be swaped
     * @param to   second element to be swaped
     */
    protected void swap(int from, int to) {
        E fromValue = data.get(from);
        data.set(from, data.get(to));
        data.set(to, fromValue);
    }

    /**
     * returns the parent index of the parameter index
     *
     * @param index the parameter index
     * 
     * @return the index of the parent element
     */
    protected static int getParentIdx(int index) {

        return (index - 1) / BRANCH_FACTOR;
    }

    /**
     * returns the left child index of the parameter index
     *
     * @param index the parameter index
     * 
     * @return the index of the left child
     */
    protected static int getLeftChildIdx(int index) {
        return BRANCH_FACTOR * index + 1;
    }

    /**
     * returns the right child index of the parameter index
     *
     * @param index the parameter index
     * 
     * @return the index of the right child
     */
    protected static int getRightChildIdx(int index) {
        return BRANCH_FACTOR * index + BRANCH_FACTOR;
    }

    /**
     * returns the child index of the smaller child
     *
     * @param index the parameter index
     * 
     * @return the index of the min child
     */
    protected int getMinChildIdx(int index) {
        if (data.size() <= BRANCH_FACTOR * index + 1) {
            return -1;
        }
        if (data.size() == BRANCH_FACTOR * index + BRANCH_FACTOR) {
            return BRANCH_FACTOR * index + 1;
        }
        // data.get(2*index+1)<=data.get(2*index+2)
        E leftChild = data.get(BRANCH_FACTOR * index + 1);
        E rightChild = data.get(BRANCH_FACTOR * index + BRANCH_FACTOR);
        if (leftChild.compareTo(rightChild) <= 0) {
            return BRANCH_FACTOR * index + 1;
        } else {
            return BRANCH_FACTOR * index + BRANCH_FACTOR;
        }
    }

    /**
     * Percolate the element at index up until heap properties are satisfied
     *
     * @param index the parameter index
     * 
     */
    protected void percolateUp(int index) {
        int currentIndex = index;
        int parentIndex = getParentIdx(index);
        // data.get(currentIndex) < data.get(parentIndex)
        while (data.get(currentIndex).compareTo(data.get(parentIndex)) < 0) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIdx(currentIndex);
        }
    }

    /**
     * Percolate the element at index down until heap properties are satisfied
     *
     * @param index the parameter index
     * 
     */
    protected void percolateDown(int index) {
        int minChildIndex = getMinChildIdx(index);
        if (minChildIndex == -1) {
            return;
        }
        if (data.get(index).compareTo(data.get(minChildIndex)) > 0) {
            swap(index, minChildIndex);
            percolateDown(minChildIndex);
        } else {
            return;
        }

    }

    /**
     * Remove the element at index from the heap and return it.
     *
     * @param index the parameter index
     * 
     * @return the deleted data
     */
    protected E deleteIndex(int index) {
        E deletedData = data.get(index);
        if (index == data.size() - 1) {
            data.remove(index);
            return deletedData;
        } else {
            swap(data.size() - 1, index);
            data.remove(data.size() - 1);
            percolateDown(index);
            percolateUp(index);
            return deletedData;
        }
    }

    /**
     * Add element into the heap, perlocate up
     *
     * @param element the element to be added
     * 
     */
    public void insert(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size() - 1);

    }

    /**
     * Return the root of the heap.
     *
     * 
     * @return the root(smallest element) of the heap.
     */
    public E getMin() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * Remove and return the root of the heap
     * 
     * @return the deleted data
     */
    public E remove() {
        if (data.size() == 0) {
            return null;
        }
        return deleteIndex(0);
    }

    /**
     * get the size of the heap
     * 
     * @return the number of elements in the heap
     */
    public int size() {
        return data.size();
    }

    /**
     * Clears the elements from the heap
     */
    public void clear() {
        data.clear();
    }
}
