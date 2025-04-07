package datastructures;

/**
 * This class implements the Deque ADT using a Object array instance variable
 * called data. It also has instance variables for the front index, rear index,
 * and size
 */
public class MyDeque<E> implements DequeInterface<E> {
    private static final int EXPAND_CAPACITY_RATIO = 2;

    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor to create new MyDeque of size 0 and given capcity.
     * throws exception if given capcity is negative
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyDeque(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    /**
     * Returns the number of valid elements in the deque (non-null elements).
     *
     * @return number of valid elements in the deque.
     */
    public int size() {
        int elementCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                elementCount++;
            }
        }
        return elementCount;
    }

    /**
     * Doubles the current capacity of the deque. If the capacity is 0, set the
     * capacity to 10. Maintains size and elements in the deque.
     *
     */
    public void expandCapacity() {
        int newCapacity = 10;
        if (data.length != 0) {
            newCapacity = data.length * EXPAND_CAPACITY_RATIO;
        }
        Object tempDataArray[] = new Object[newCapacity];
        int counter = 0;
        int nullcnt=0;
        for(int i=0;i<data.length;i++){
            if(data[i]!=null){
                nullcnt++;
            }
        }
        if(nullcnt==0){
            data=tempDataArray;
            front=0;
            rear=0;
            return;
        }
        for (int i = front; i < data.length; i++) {
            tempDataArray[counter] = data[i];
            counter++;
        }
        for (int i = 0; i < front; i++) {
            tempDataArray[counter] = data[i];
            counter++;
        }
        if(data.length==0){
            rear=0;
        }
        else{
            rear = size - 1;
        }
        data = tempDataArray;
        front = 0;
        
        
    }

    /**
     * Adds the specified element to the front of the deque
     *
     * @param element the element to add to the deque
     */
    public void addFirst(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }

        if (data[front] == null) {
            data[front] = element;
            size++;
        } else {
            if (front != 0) {
                data[front - 1] = element;
                size++;
                front--;
            } else {
                data[data.length - 1] = element;
                size++;
                front = data.length - 1;
            }
        }

    }

    /**
     * Adds the specified element to the rear of the deque
     *
     * @param element the element to add to the deque
     */
    public void addLast(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }
        if (data[rear] == null) {
            data[rear] = element;
            size++;
        } else {
            if (rear == data.length - 1) {
                data[0] = element;
                size++;
                rear = 0;
            } else {
                data[rear + 1] = element;
                size++;
                rear++;
            }
        }

    }

    /**
     * Removes the element at the front of the deque
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        E removedElement;
        size--;
        if (front == data.length - 1) {
            front = 0;
            removedElement = (E) data[data.length - 1];
            data[data.length - 1] = null;
            if(size==0){
                front=rear;
            }
            return removedElement;
        } else {
            front++;
            removedElement = (E) data[front - 1];
            data[front - 1] = null;
            if(size==0){
                front=rear;
            }
            return removedElement;
        }
        
    }

    /**
     * Removes the element at the rear of the deque
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        E removedElement;
        size--;
        if (rear == 0) {
            rear = data.length - 1;
            removedElement = (E) data[0];
            data[0] = null;
            if(size==0){
                rear=front;
            }
            return removedElement;
        } else {
            rear--;
            removedElement = (E) data[rear + 1];
            data[rear + 1] = null;
            if(size==0){
                rear=front;
            }
            return removedElement;
        }

    }

    /**
     * Returns the element at the front of the deque, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return (E) data[front];
    }

    /**
     * Returns the element at the rear of the deque, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return (E) data[rear];
    }

}