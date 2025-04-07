package datastructures;

import java.util.ArrayList;


/**
 * MyBST class, creating a sorted Binary Search Tree
 * 
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;


    /**
     * determines the size of the Binary Search Tree
     * 
     * 
     * @return the number of nodes stored in the Binary Search Tree
     */
    public int size() {
        return size;
    }



    /**
     * Insert a new node containing given key and value into the tree according
     * to the binary search tree properties
     * 
     * @param key the key of the node to be added
     * @param value the value of the node to be added
     * 
     * @return the value previously mapped to given key if key already exists 
     * in the tree, null otherwise
     */
    public V insert(K key, V value) throws NullPointerException{
        if(key==null){
            throw new NullPointerException();
        }
        MyBSTNode<K, V> newNode=new MyBSTNode<K,V>(key, value, null);
        MyBSTNode<K, V> currNode;
        if(root==null){
            root=newNode;
            size++;
        }
        else{
            currNode=root;
            while (currNode!=null) {
                if(newNode.getKey().compareTo(currNode.getKey())<0){
                    if(currNode.left==null){
                        currNode.left=newNode;
                        newNode.parent=currNode;
                        size++;
                        currNode=null;
                    }
                    else{
                        currNode=currNode.left;
                    }
                }
                else if(newNode.getKey().compareTo(currNode.getKey())>0){
                    if(currNode.right==null){
                        currNode.right=newNode;
                        newNode.parent=currNode;
                        size++;
                        currNode=null;
                    }
                    else{
                        currNode=currNode.right;
                    }
                }
                else{
                    V replacedValue=currNode.getValue();
                    currNode.setValue(newNode.getValue());
                    return replacedValue;
                }
            }
        }
        return null;
    }

    /**
     * Search for a node with given key and return the value associated with 
     * that node.
     * 
     * @param key the key of the node to be searched for
     * 
     * @return the value of the node that has the given key
     */
    public V get(K key) {//search
        if(key==null){
            return null;
        }
        MyBSTNode<K, V> currNode=root;
        while(currNode!=null){
            if(currNode.key.compareTo(key)==0){
                return currNode.getValue();
            }
            if(currNode.key.compareTo(key)>0){
                currNode=currNode.left;
            }
            else{
                currNode=currNode.right;
            }
            
        }
        return null;
    }

    /**
     * Search for a node with given key and return the value previosly 
     * associated with that node. Then remove the node. 
     * 
     * @param key the key of the node to be searched for
     * 
     * @return the value of the node that was removed. 
     */
    public V remove(K key) {
        if(key==null || root==null){
            return null;
        }
        MyBSTNode<K, V> currNode=root;

        if(size==1 &&currNode.key.compareTo(key)!=0){//WTF
            return null;
        }
        if(size==0){
            return null;
        }
        while(currNode!=null){
            if(currNode.key.compareTo(key)==0){
                V removedValue= currNode.getValue();
                
                if(currNode.right==null && currNode.left==null){
                    if(currNode.parent==null){
                        root=null;
                        size--;
                        return removedValue;
                    }
                    if(currNode.parent.left==currNode){
                        currNode.parent.left=null;
                    }
                    else{
                        currNode.parent.right=null;
                    }
                    currNode=null;
                    size--;
                    return removedValue;
                }
                else if(currNode.right!=null && currNode.left==null){
                    if(currNode.parent==null){
                        root=currNode.right;
                        root.setParent(null);
                        size--;
                        return removedValue;
                    }
                    if(currNode.parent.left==currNode){
                        currNode.parent.left=currNode.right;
                    }
                    else{
                        currNode.parent.right=currNode.right;
                    }
                    currNode.right.setParent(currNode.parent);
                    size--;
                    return removedValue;
                }
                else if(currNode.right==null && currNode.left!=null){
                    if(currNode.parent==null){
                        root=currNode.left;
                        root.setParent(null);
                        size--;
                        return removedValue;
                    }
                    if(currNode.parent.left==currNode){
                        currNode.parent.left=currNode.left;
                    }
                    else{
                        currNode.parent.right=currNode.left;
                    }
                    currNode.left.setParent(currNode.parent);
                    size--;
                    return removedValue;
                }
                else{
                    K newKey=currNode.successor().getKey();
                    V newValue=currNode.successor().getValue();
                    remove(currNode.successor().getKey());
                    
                    currNode.setKey(newKey);
                    currNode.setValue(newValue);
                    return removedValue;
                }

            }
            if(currNode.key.compareTo(key)>0){
                currNode=currNode.left;
            }
            if(currNode.key.compareTo(key)<0){
                currNode=currNode.right;
            }
        }
        return null;
    }

    /**
     * Do an in-order traversal of the tree, adding each node to the end of an 
     * ArrayList 
     * 
     * 
     * @return the arraylist representation of the binary search tree
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K, V>> BSTArray=new ArrayList<>();
        MyBSTNode<K, V> currNode=root;
        if(root==null){
            return BSTArray;
        }
        while(currNode.left!=null){
            currNode=currNode.left;
        }

        while(currNode!=null){
            BSTArray.add(currNode);
            currNode=currNode.successor();
        }
        
        return BSTArray;
    }
    /**
     * Helper method to recursivly copy nodes
     * 
     * @param node root node to copy
     * 
     * @return copy of node with copy of all refrences 
     */
    public MyBSTNode<K, V> copyNode(MyBSTNode<K, V> node){
        if(node==null){
            return null;
        }
        MyBSTNode<K, V> currNode=new MyBSTNode<K, V>(node.getKey(), 
            node.getValue(), node.parent);
        currNode.right=node.right;
        currNode.left=node.left;
        copyNode(currNode.right);
        copyNode(currNode.left);
        return currNode;
    }

    /**
     * Copy the BST and return a new binary search tree reference that is an 
     * exact copy of all nodes 
     * 
     * 
     * @return the copy of the binary search tree
     */
    public MyBST<K, V> copy() {
        MyBST<K, V> treeCopy = new MyBST<>();
        MyBSTNode<K, V> nodeCopy=copyNode(root);
        treeCopy.root=nodeCopy;
        treeCopy.size=this.size;
        return treeCopy;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * This method returns the node with the smallest key that is still 
         * larger than the key of the current node. If there is no larger key, 
         * return null.
         *
         * @return node with next largest key
         */
        public MyBSTNode<K, V> successor() {
            
            if(right==null){
                MyBSTNode<K, V> currNode=this;
                MyBSTNode<K, V> parentNode=parent;
                while (parentNode != null && currNode == parentNode.right) {
                    currNode=parentNode;
                    parentNode=currNode.parent;
                }
                
                return parentNode;
                
            }
            else{
                MyBSTNode<K, V> rightNode=right;
                while(rightNode.left!=null){
                    rightNode=rightNode.left;
                }
                return rightNode;
            }
            
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
