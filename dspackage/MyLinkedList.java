package datastructures;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * MyLinkedList class, simulating javas built in linked list methods
 * 
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * returns generic ListIterator
     * 
     * @return MyListIterator
     */
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    /**
     * returns generic Iterator
     * 
     * @return MyListIterator
     */
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * 
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * 
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * 
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * 
         * @param element new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * 
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    /**
     * Constructor to create empty Linked list of size 0
     * 
     * 
     */
    public MyLinkedList() {
        /* Add your implementation here */
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;

    }

    /**
     * find the number of nodes in the linked list, excluding the sentinal
     * head and tail nodes
     * 
     * 
     * @return the linkedlist size
     */
    @Override
    public int size() {

        Node currNode = head;
        size = 0;
        while (currNode.getNext() != null) {
            size++;
            currNode = currNode.getNext();
        }

        size--;
        return size;

    }

    /**
     * Get data of type E within the node at position index.
     * 
     * @param index index of node element you want returned
     * 
     * @return the element of the node at the given index
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        for (int i = 0; i < index + 1; i++) {
            currNode = currNode.getNext();
        }

        return (E) currNode.getElement(); // TODO
    }

    /**
     * Adds a node into this list by given index and stored data
     * 
     * 
     * @param index index of node you are adding at
     * @param data  the data that is being stored in the new node
     * 
     */
    @Override
    public void add(int index, E data)
            throws NullPointerException, IndexOutOfBoundsException {
        if (data.equals(null)) {
            throw new NullPointerException();
        }
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        Node nextNode = currNode.getNext();
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
            nextNode = currNode.getNext();
        }
        Node newNode = new Node(data);
        currNode.setNext(newNode);
        nextNode.setPrev(newNode);

        newNode.setNext(nextNode);
        newNode.setPrev(currNode);
        size++;

    }

    /**
     * adds node storing given data at the end of the index
     * 
     * @param data he data that is being stored in the new node
     * 
     * @return true if item is added
     */
    @Override
    public boolean add(E data) throws NullPointerException {
        if (data.equals(null)) {
            throw new NullPointerException();
        }
        Node currNode = head;
        Node nextNode = currNode.getNext();
        for (int i = 0; i < this.size(); i++) {
            currNode = currNode.getNext();
            nextNode = currNode.getNext();
        }
        Node newNode = new Node(data);
        currNode.setNext(newNode);
        nextNode.setPrev(newNode);

        newNode.setNext(nextNode);
        newNode.setPrev(currNode);
        size++;
        return true; // TODO
    }

    /**
     * Set the element for the node at the specified
     * index to data and return the old data
     * 
     * @param index index of node you are setting at
     * @param data  the data that is being stored in the node
     * 
     * @return element that was previously stored in the node
     */
    @Override
    public E set(int index, E data)
            throws NullPointerException, IndexOutOfBoundsException {
        if (data.equals(null)) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        for (int i = 0; i < index + 1; i++) {
            currNode = currNode.getNext();
        }
        E store = currNode.getElement();
        currNode.setElement(data);
        return store;

    }

    /**
     * remove the node at the specified index
     * 
     * @param index index of node you are removing
     * 
     * @return element that was previously stored in the node
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        for (int i = 0; i < index + 1; i++) {
            currNode = currNode.getNext();
        }
        E store = currNode.getElement();
        Node next = currNode.getNext();
        Node prev = currNode.getPrev();
        next.setPrev(prev);
        prev.setNext(next);
        size--;
        return store;
    }

    /**
     * remove all nodes from the linked list.
     * leaves sentinel nodes.
     * 
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Determines if the list is empty.
     * 
     * 
     * @return true if list is empty,false otherwise
     */
    @Override
    public boolean isEmpty() {
        /* Add your implementation here */
        return head.getNext() == tail; // TODO
    }

    /**
     * returns the Node at a specified index
     * 
     * @param index index of node you are finding
     * 
     * @return node at the index
     */
    protected Node getNth(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        for (int i = 0; i < index + 1; i++) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    /**
     * Determine if this list contains the element data between the specified
     * start and end indicies
     * 
     * @param data  the data that is being searching for
     * @param start index you are starting to search from (inclusve)
     * @param end   index you are ending the search from (exclusve)
     * 
     * @return true if data is found,false otherwise
     */
    public boolean contains(E data, int start, int end)
            throws IndexOutOfBoundsException {
        if (start < 0 || start >= this.size() || end < 0 || end > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (end <= start) {
            return false;
        }

        for (int i = start; i < end; i++) {
            if (getNth(i).getElement().equals(data)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return the index at which the given element data
     * exists in the linked list.
     * 
     * @param data the data that is being searching for
     * 
     * @return index that data was found at, return -1 if nothing was found
     */
    public int indexOfElement(E data) throws NullPointerException {
        if (data.equals(null)) {
            throw new NullPointerException();
        }
        Node currNode = head;
        int count = 0;
        while (currNode.getNext() != null) {
            currNode = currNode.getNext();
            if (currNode.getElement() == null) {
                continue;
            }
            if (currNode.getElement().equals(data)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     * MyListIterator class, implimeting methods for traversing Linked lists
     * 
     */
    protected class MyListIterator implements ListIterator<E> {
        Node left, right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * Constructor to create MyListIterator object
         * 
         * 
         */
        public MyListIterator() {
            left = head;
            right = left.getNext();
            canRemoveOrSet = false;
            forward = true;
            idx = 0;
        }

        /**
         * determines if the ListIterator has a node when going forward
         * does not count sentinal tail.
         * 
         * @return true if there is a node ahead, false otherwise.
         */
        public boolean hasNext() {
            if (this.right == null) {
                return false;
            }
            if (this.right.getElement() == null) {
                return false;
            }
            return true;
        }

        /**
         * Return the next element in the list when going forward, and moves
         * the iterator forward by one node.
         * 
         * @return the element of the node passed.
         */
        public E next() throws NoSuchElementException {
            if (this.right.getElement() == null) {
                throw new NoSuchElementException();
            }
            canRemoveOrSet = true;
            idx += 1;
            forward = true;
            Node temp = this.right;
            this.right = temp.getNext();
            this.left = temp;
            return temp.getElement();
        }

        /**
         * determines if the ListIterator has a node when going backwards
         * does not count sentinal head.
         * 
         * @return true if there is a node previous, false otherwise.
         */
        public boolean hasPrevious() {
            if (this.left == null) {
                return false;
            }
            if (this.left.getElement() == null) {
                return false;
            }
            return true;
        }

        /**
         * Return the next element in the list when going backwards, and moves
         * the iterator backwards by one node.
         * 
         * @return the element of the node passed.
         */
        public E previous() throws NoSuchElementException {
            if (this.left.getElement() == null) {
                throw new NoSuchElementException();
            }
            canRemoveOrSet = true;
            idx -= 1;
            forward = false;
            Node temp = this.left;
            this.left = temp.getPrev();
            this.right = temp;
            return temp.getElement();
        }

        /**
         * Return the index of the node that is pointed to by right.
         * rerurns the size of list if ListIterator is at end
         * 
         * @return the index of the right node
         */
        public int nextIndex() {
            return idx;
        }

        /**
         * Return the index of the node that is pointed to by left.
         * rerurns -1 if ListIterator is at beginning
         * 
         * @return the index of the left node
         */
        public int previousIndex() {
            return idx - 1;
        }

        /**
         * For the node returned by the most recent next/previous call,
         * replace its value with element
         * 
         * @param element element to be set into list
         * 
         */
        public void set(E element)
                throws NullPointerException, IllegalStateException {
            if (element == null) {
                throw new NullPointerException();
            }
            if (canRemoveOrSet == false) {
                throw new IllegalStateException();
            }
            if (forward) {
                this.left.setElement(element);
            } else {
                this.right.setElement(element);
            }
        }







        /**
         * Insert the element into the list immediately before the right node
         * 
         * @param element element to be added to list
         * 
         */
        public void add(E element) throws NullPointerException {
            if (element == null) {
                throw new NullPointerException();
            }
            idx += 1;
            canRemoveOrSet = false;
            Node newNode = new Node(element);
            // if(this.right.getElement()==null && this.left.getElement()==null){
            //     this.right=newNode;
            //     this.left.next=newNode;
            //     return;
            // }
            // if(this.right.getElement()==null  && idx!=0){
            //     this.left.next=newNode;
            //     this.left = newNode;
            //     this.right.prev = newNode;
            //     return;
            // }

            this.left.next=newNode;
            
            this.right.prev = newNode;
            newNode.next=right;
            newNode.prev=left;
            this.left = newNode;
            size++;

        }

        

        /**
         * For the node returned by the most recent next/previous call, remove
         * it from the list
         * 
         * 
         */
        public void remove() throws IllegalStateException {
            if (canRemoveOrSet == false) {
                throw new IllegalStateException();
            }
            canRemoveOrSet = false;
            if (forward) {
                this.left = this.left.prev;
                this.right.prev = this.left;
                this.left.next=this.right;
                idx -= 1;
            } else {
                this.right = this.right.next;
                this.left.next = this.right;
                this.right.prev=this.left;
            }
            //forward=false;
            size--;

        }

    }
}