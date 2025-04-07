package datastructures;

/**
 * This file impliments all of the methods in the generic MyList interface.
 * It should simulate the fuctions of javas Arraylist class.
 * 
 */
public class MyArrayList<E> implements MyList<E> {

    Object[] data;
    int size;
    private static final int DEFAULT_CAP = 5;
    private static final int EXPAND_FACTOR = 2;

    /**
     * The no arg constructor for the MyArrayList class
     * 
     * @param none
     */
    public MyArrayList() {
        data = new Object[DEFAULT_CAP];
        size = 0;
    }

    /**
     * constructor for the MyArrayList class that sets to given capacity
     * 
     * @param initialCapacity - Value to set the created arrays capacity to
     */
    public MyArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
    }

    /**
     * constructor for the MyArrayList class that sets to given given array
     * 
     * @param arr - Array to copy to the created array
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            data = new Object[DEFAULT_CAP];
            size = 0;
        } else {
            data = new Object[arr.length];
            size = arr.length;
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
        }

    }

    /**
     * Increase the capacity of underlying array if needed
     *
     * @param requiredCapacity minimum capacity after expanding
     */
    public void expandCapacity(int requiredCapacity)
            throws IllegalArgumentException {
        if (requiredCapacity < data.length) {
            throw new IllegalArgumentException();
        }
        Object[] temp;
        if (data.length == 0 && DEFAULT_CAP >= requiredCapacity) {
            temp = new Object[DEFAULT_CAP];
        } else if (data.length * EXPAND_FACTOR < requiredCapacity) {
            temp = new Object[requiredCapacity];
        } else {
            temp = new Object[data.length * EXPAND_FACTOR];
        }
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * Get the amount of elements array can hold
     *
     * @return number of elements that can be held
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Add an element at the specified index
     *
     * @param index   position to insert the element
     * @param element the element to insert
     */
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
        if (data[data.length - 1] != null) {
            this.expandCapacity(data.length + 1);
        }

        for (int i = data.length - 1; i != index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;

    }

    /**
     * Add an element to the end of the list
     *
     * @param element the element to append
     */
    public void append(E element) {
        if (data[data.length - 1] != null) {
            this.expandCapacity(data.length + 1);
        }
        int count = 0;
        for (int i = data.length - 1; i != -1; i--) {
            if (data[i] != null) {
                data[i + 1] = element;
                count++;
                break;
            }

        }
        if (count == 0) {
            data[0] = element;
        }
        size++;

    }

    /**
     * Add an element to the beginning of the list
     *
     * @param element the element to prepend
     */
    public void prepend(E element) {
        if (data[data.length - 1] != null) {
            this.expandCapacity(data.length + 1);
        }
        for (int i = data.length - 1; i != 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = element;
        size++;
    }

    /**
     * Get the element at the given index
     *
     * @param index the index at which to return the element
     * @return the element at the index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    /**
     * Replaces an element at the specified index with a new element and return
     * the original element
     *
     * @param index   the index at which to replace
     * @param element the element with which to replace
     * @return the original element
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E temp = (E) data[index];
        data[index] = element;
        return temp;
    }

    /**
     * Remove the element at the specified index and return the removed element
     *
     * @param index the index at which to remove the element
     * @return the removed element
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
        E temp = (E) data[index];

        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[data.length - 1] = null;
        size--;
        return temp;
    }

    /**
     * Get the number of elements in the list
     *
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Rotate the list by numPositions number of positions to the right
     * 
     * @param numPositions the number of positions to rotate by
     */
    public void rotate(int numPositions) throws IndexOutOfBoundsException {
        if (numPositions < 0 || numPositions >= size) {
            throw new IndexOutOfBoundsException();
        }
        E temp;

        for (int j = 0; j < numPositions; j++) {

            temp = (E) data[size - 1];
            for (int i = size - 1; i != 0; i--) {
                data[i] = data[i - 1];
            }
            data[0] = temp;
        }
    }

    /**
     * Find the element in the list and return its index
     * 
     * @param element the element to find
     * @return the index of the last occurrence of element (-1 if not found)
     */
    public int find(E element) {
        int temp = -1;
        for (int i = 0; i < data.length; i++) {
            if (element.equals((E) data[i])) {
                temp = i;
            }
        }

        return temp;
    }

}