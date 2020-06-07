// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU
/**
 * A custom implementation of a Binary Search Tree with a Key, Value pair.
 * The Key needs to be comparable, while the value can be any object.
 */

import java.util.ArrayList;

/**
 * Class BinaryTree
 * Binary Search Tree data structure in which nodes have a left, right, and 
 * parent child, starting with the root node. This implementation is optimized
 * for searching.
 * @param <K> Key Object Type for BST
 * @param <V> Value Object Type for BST
 */
public class MyBST<K extends Comparable<K>, V> 
{
    /**
     * Fields for BST Class
     */

     //root node
    MyBSTNode<K, V> root;
    //number of elements in BST
    int size;

    /**
     * Node Inner Class for BinaryTree. Takes Key and Value pair, separate from
     * MyBST. Uses parent, left and right node fields.
     * @param <K> Key Object Type from outer BST Class
     * @param <V> Value Object Type from outer BST Class
     */
    static class MyBSTNode<K, V>
    {
        /**
         * Fields for Inner Node Class
         */
        K key;
        V value;

        //Internal Node fields, Parent Node, Left and Right Child nodes
        MyBSTNode<K, V> parent;
        MyBSTNode<K, V> left;
        MyBSTNode<K, V> right;

        //constructor sets up the node
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent)
        {
            setKey(key);
            setValue(value);
            setParent(parent);
            setLeft(null);
            setRight(null);
        }

        /**
         * @return key of node
         */
        public K getKey()
        {
            return this.key;
        }

        /**
         * @return value of node
         */
        public V getValue()
        {
            return this.value;
        }

        /**
         * @return parent node of node
         */
        public MyBSTNode<K, V> getParent()
        {
            return this.parent;
        }
        
        /**
         * @return left node of node
         */
        public MyBSTNode<K, V> getLeft()
        {
            return this.left;
        }
        
        /**
         * @return right node of node
         */
        public MyBSTNode<K, V> getRight()
        {
            return this.right;
        }
        
        /**
         * Sets key
         * @param newKey key to set
         */
        public void setKey(K newKey)
        {
            this.key = newKey;
        }
        
        /**
         * Sets value
         * @param newValue value to set
         */
        public void setValue(V newValue)
        {
            this.value = newValue;
        }
        
        /**
         * Sets parent node
         * @param newParent parent node to set
         */
        public void setParent(MyBSTNode<K, V> newParent)
        {
            this.parent = newParent;
        }
        
        /**
         * Sets left node
         * @param newLeft left node to set
         */
        public void setLeft(MyBSTNode<K, V> newLeft)
        {
            this.left = newLeft;
        }
        
        /**
         * Sets right node
         * @param newRight right node to set
         */
        public void setRight(MyBSTNode<K, V> newRight)
        {
            this.right = newRight;
        }
        
        /**
         * returns the node with the next largest key
         * @return the node with the next largest key
         */
        public MyBSTNode<K, V> successor()
        {
            MyBSTNode<K,V> curr = this;
            //if right node exists, then find the leftmost node of right node
            if(curr.getRight() != null)
            {
                curr = curr.getRight();
                while(curr.getLeft() != null)
                {
                    curr = curr.getLeft();
                }
                //return leftmost node of the right node
                return curr;
            }

            //if right node DNE, then move up to parent
            MyBSTNode<K,V> par = getParent();
            //while curr is not the root node and curr is the right node
            while(par != null && curr == par.getRight())
            {
                curr = par;
                par = par.getParent();
            }
            //return the parent node with key less than curr node.
            return par;
        }
    }

    /**
     * Constructor; always starts with an empty BST
     */
    public MyBST()
    {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the node with the next largest key of the given node
     * @param node specified node to find successor of
     * @return the node with the next largest key of the given node
     */
    protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node)
    {
        if(node == null)
            return null;
        return node.successor();
    }

    /**
     * Getter methods for the size instance variable
     * @return the number of elements in the BST
     */
    public int size()
    {
        return this.size;
    }
    
    /**
     * Inserts Node with specified key and value
     * @param key to insert
     * @param value to insert with the key
     * @return value replaced by new node, or null is nothing was replaced
     * @throws NullPointerException is key is null
     */
    public V insert(K key, V value)
    {
        //exception if key is null
        if(key == null) { throw new NullPointerException(); }

        //if BST is empty, set root node
        if(this.root == null)
        {
            this.root = (new MyBSTNode<K,V>(key, value, null));
            ++size;
            return null;
        }

        //otherwise start at root and insert
        MyBSTNode<K,V> curr = this.root;

        //iterate through BST
        while(curr != null)
        {
            //if key is greater than the current node, then
            if(key.compareTo(curr.getKey()) > 0)
            {
                //check if right node is null, if so, add node with K,V pair
                if(curr.getRight() == null)
                {
                    curr.setRight(new MyBSTNode<K,V>(key, value, curr));
                    //break loop after inserting node
                    break;
                }
                else //otherwise change the current node to right and iterate
                    curr = curr.getRight();
            }
            //if key is less than the current node, then
            else if(key.compareTo(curr.getKey()) < 0)
            {
                //check if left node is null, if son, add node with K,V pair
                if(curr.getLeft() == null)
                {
                    curr.setLeft(new MyBSTNode<K,V>(key, value, curr));
                    //break loop after inserting node
                    break;
                }
                else //otherwise change the current node to left and iterate
                    curr = curr.getLeft();
            }
            //if key is the same as the current node, replace value
            else
            {
                //replaced value is returned
                V temp = curr.getValue();
                curr.setValue(value);
                return temp;
            }
        }

        //if nothing is replaced, return null
        ++size;
        return null;
    }
    
    /**
     * Searches for a given key in a BST and returns the value associated
     * with the given key
     * @param key to search for
     * @return returns the value associated with the given key
     */
    public V search(K key)
    {
        //start at root
        MyBSTNode<K,V> curr = this.root;
        //loop while not found
        while(curr != null)
        {
            //if found, return value
            if(key.compareTo(curr.getKey()) == 0)
            {
                return curr.value;
            }
            //if key is less than currentKey then branch to left node
            else if(key.compareTo(curr.getKey()) < 0)
            {
                curr = curr.getLeft();
            }
            //otherwise branch to right node
            else
            {
                curr = curr.getRight();
            }
        }
        //return null if not found
        return null;
    }
    
    /**
     * Removes node with given key and adjusts the BST if necessary.
     * @param key of node to remove
     * @return value of removed node
     */
    public V remove(K key)
    {
        //create parent and current nodes, used to find and remove
        MyBSTNode<K,V> parent = null;
        MyBSTNode<K,V> curr = this.root;
        //value of removed node. If node not found, will stay null
        V removed = null;
        //loop while not found
        while(curr != null)
        {
            //if node found
            if(curr.getKey() == key)
            {
                //copy value of to-be-removed node
                removed = curr.getValue();
                //procedure for if curr is leaf node
                if(curr.getLeft() == null && curr.getRight() == null)
                {
                    if(parent == null) //at root node
                    {
                        this.root = (null);
                    }
                    else if(parent.getLeft() == curr)
                    {
                        parent.setLeft(null);
                    }
                    else
                    {
                        parent.setRight(null);
                    }
                }
                //procedure for if curr has 1 child (left child)
                else if(curr.getLeft() != null && curr.getRight() == null)
                {
                    if(parent == null) //if root node
                        this.root = (curr.getLeft());
                    else if(parent.getLeft() == curr)
                    {
                        parent.setLeft(curr.getLeft());
                    }
                    else
                    {
                        parent.setRight(curr.getLeft());
                    }
                }
                //procedure for if curr has 1 child (right child)
                else if(curr.getLeft() == null && curr.getRight() != null)
                {
                    if(parent == null) //if root node
                        this.root = (curr.getRight());
                    else if(parent.getLeft() == curr)
                    {
                        parent.setLeft(curr.getRight());
                    }
                    else
                    {
                        parent.setRight(curr.getRight());
                    }
                }
                //procedure for if curr has 2 children
                else
                {
                    //find successor
                    MyBSTNode<K,V> successor = curr.successor();

                    //copy data
                    K succKey = successor.getKey();
                    V succValue = successor.getValue();

                    //recursively remove successor node
                    remove(successor.getKey());

                    //assign succ data to cur data
                    curr.setKey(succKey);
                    curr.setValue(succValue);
                }
                //return removed value
                return removed;
            }
            //if key is greater than curr key, branch off to right node
            else if (curr.getKey().compareTo(key) < 0)
            {
                parent = curr;
                curr = curr.getRight();
            }
            //if key is less than curr key, branch off to left node
            else
            {
                parent = curr;
                curr = curr.getLeft();
            }
        }

        //return null if node not found
        return null;
    }
    
    /**
     * Returns an ArrayList of nodes sorted by their keys from least to greatest
     * @return an ArrayList of sorted nodes
     */
    public ArrayList<MyBSTNode<K, V>> inorder()
    {
        ArrayList<MyBSTNode<K, V>> output = new ArrayList<>();

        MyBSTNode<K,V> curr = this.root;
        while(curr.left != null)
        {
            curr = curr.getLeft();
        }
        
        while(curr != null)
        {
            // System.out.println("looping");
            output.add(curr);
            curr = curr.successor();
        }

        return output;
    }
}