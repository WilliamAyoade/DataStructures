import datastructures.*;

public class Testing {
    public static void main(String[] args) {
        System.out.println("==== Testing MyStack ====");
        MyStack<Integer> stack = new MyStack<>(10);
        stack.push(10);
        stack.push(20);
        System.out.println("Popped (expected 20): " + stack.pop());
        System.out.println("Is Empty (expected false): " + stack.empty());
        stack.pop(); // pop 10
        System.out.println("Is Empty after popping all (expected true): " + stack.empty());

        System.out.println("\n==== Testing MyQueue ====");
        MyQueue<String> queue = new MyQueue<>(5);
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        System.out.println("Dequeued (expected Alice): " + queue.dequeue());
        queue.dequeue(); // Bob
        System.out.println("Is Empty after dequeueing all (expected true): " + queue.empty());

        System.out.println("\n==== Testing MyArrayList ====");
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.append(5);
        arrayList.append(15);
        System.out.println("Element at index 1 (expected 15): " + arrayList.get(1));
        try {
            arrayList.get(10); // should throw
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Handled out-of-bounds access correctly.");
        }

        System.out.println("\n==== Testing MyLinkedList ====");
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("First");
        linkedList.add("Second");
        System.out.println("Element at index 0 (expected First): " + linkedList.get(0));

        System.out.println("\n==== Testing MyDeque ====");
        MyDeque<String> deque = new MyDeque<>(5);
        deque.addFirst("X");
        deque.addLast("Y");
        System.out.println("Removed First (expected X): " + deque.removeFirst());
        System.out.println("Removed Last (expected Y): " + deque.removeLast());

        System.out.println("\n==== Testing MyHashSet ====");
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");
        set.add("banana");
        System.out.println("duplicate, (expected false):"+set.add("apple")); // duplicate
        System.out.println("Size after duplicates (expected 2): " + set.size());


        System.out.println("\n==== Testing MyHashMap ====");
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("A", 100); // overwrite
        System.out.println("Value for 'B' (expected 2): " + map.get("B"));
        System.out.println("Value for 'A' after overwrite (expected 100): " + map.get("A"));

        System.out.println("\n==== Testing MyMinHeap ====");
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.insert(40);
        minHeap.insert(30);
        System.out.println("Min after inserts (expected 30): " + minHeap.getMin());
        minHeap.insert(20);
        System.out.println("New Min (expected 20): " + minHeap.getMin());

        System.out.println("\n==== Testing MyPriorityQueue ====");
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>();
        pq.push(5);
        pq.push(1);
        pq.push(10);
        System.out.println("Pop (expected 1): " + pq.pop());
        System.out.println("Pop (expected 5): " + pq.pop());

        System.out.println("\n==== Testing MyBST ====");
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(50, "Apple");
        bst.insert(30, "Banana");
        bst.insert(70, "Cherry");

        System.out.println("Contains key 30 (expected true): " + (bst.get(30) != null));
        System.out.println("Value for key 70 (expected Cherry): " + bst.get(70));
        System.out.println("Value for key 50 (expected Apple): " + bst.get(50));
        System.out.println("Get non-existing key 100 (expected null): " + (bst.get(100) == null));

        System.out.println("\n==== All tests completed successfully ====");
    }
}