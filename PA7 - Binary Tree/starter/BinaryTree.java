// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU
/**
 * A custom implementation of a Binary Tree
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class BinaryTree
 * Binary tree data structure in which nodes have a left and right child
 * starting with the root node. This implementation does not
 * optimize for searching. This is a complete binary tree data structure.
 */
class BinaryTree<E extends Comparable<E>>
{

    //Final integer used to calculate height of the tree.
    private static final int LOGBASE2 = 2;
    Node root;
    int size;

    /**
	 * Node Inner Class for BinaryTree
	 */
    protected class Node 
    {
		E data;
		Node left;
		Node right;

		public Node(E element)
		{
			this.data = element;
		}

		public void setRight(Node p)
		{
			this.right = p;
		}

		public void setLeft(Node n)
		{
			this.left = n;
		}

		public void setData(E e)
		{
			this.data = e;
		}

		public Node getLeft()
		{
			return this.left;
		}

		public Node getRight()
		{
			return this.right;
		} 

		public E getData()
		{
			return this.data;
		} 
    }
    
    /**
     * Constructor that sets root node to null and size to zero.
     */
    public BinaryTree()
    {
        setRoot(null);
        setSize(0);
    }

    /**
     * Constructor that adds the root node. Sets size to 1.
     * @param data for root node.
     */
    public BinaryTree(E data)
    {
        setRoot(new Node(data));
        setSize(1);
    }

    /**
     * Constructor that adds each element in the given list to the Binary Tree.
     * @param list to add elements from.
     */
    public BinaryTree(List<E> list)
    {
        //loop to add each element from list
        for(E element : list)
        {
            this.add(element);
        }
    }

    /**
     * Inserts element into binary tree
     * @param element to insert
     * @throws NullPointerException if element is null
     */
    public void add(E element)
    {
        if(element == null)
            throw new NullPointerException();

        //if list is empty, add root element and end method.
        if(getRoot() == null || getSize() == 0)
        {
            setRoot(new Node(element));
            setSize(getSize() + 1);
            return;
        }
        
        //level order traversal using a queue
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add(getRoot());

        Node temp;
        //loop through entire tree to find empty spot in the lowest level.
        while (!queue.isEmpty())
        {
            temp = queue.poll();
       
            if(temp.left == null)
            {
                temp.left = new Node(element);
                break;
            }
            else
                queue.add(temp.left);

            if(temp.right == null)
            {
                temp.right = new Node(element);
                break;
            }
            else
                queue.add(temp.right);
        }
        //increment size
        setSize(getSize() + 1);

    }

    /**
     * Removes element from binary tree and replace removed element with
     * deepest element, if possible
     * @param element to remove
     * @throws NullPointerException if element is null
     */
    public boolean remove(E element)
    {
        if(element == null)
            throw new NullPointerException();
        if(getRoot() == null || getSize() == 0)
            return false;
        //if root is the only element, then...
        if(getSize() == 1)
        {
            //if root is the element to delete, delete it
            if(getRoot().data.compareTo(element) == 0)
            {
                setRoot(null);
                setSize(getSize() - 1);
                return true;
            }
            else //otherwise deletion is not possible, returns false
                return false;
        }

        //initialize queue
        Queue<Node> queue = new LinkedList<Node>();
        //start from root node
        queue.add(getRoot());

        Node toRemove = null;
        Node temp = null;
        boolean found = false;
        /**
         * level order traversal to find deepest element and find the first
         * occurence of node with data equal to element.
         */
        while(!queue.isEmpty())
        {
            temp = queue.poll();
            /**
             * if node to remove is found, then set toRemove node and make sure
             * it is not overwritten by another node with same data.
             */
            if(temp.data.compareTo(element) == 0 && !found)
            {
                toRemove = temp;
                found = true;
            }
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
            //loop keeps running until last element in last level (deepest node)
        }

        //if element to remove was not found, return false
        if(!found)
            return false;

        //temp now is the deepest and rightmost element.
        Node deepest = temp;

        //Parent node of node to remove
        Node removeParent = null;
        //Parent node of deep node
        Node deepParent = null;

        //boolean for finding removed node parent
        boolean foundRP = false;
        //boolean for finding deep node parent
        boolean foundDP = false;

        //initialize queue again
        queue = new LinkedList<Node>();
        //start from root node again
        queue.add(getRoot());


        //another level order traversal to find parent node
        while(!queue.isEmpty())
        {
            temp = queue.poll();
            
            //if both nodes are found, exit loop
            if(foundDP && foundRP)
                break;

            //breaks out of loop if parents are found
            if(temp.left == toRemove && !foundRP)
            {
                removeParent = temp;
                foundRP = true;
            }
            else if(temp.right == toRemove && !foundRP)
            {
                removeParent = temp;
                foundRP = true;
            }
            //find deepest node parent
            if(temp.left == deepest && !foundDP)
            {
                deepParent = temp;
                foundDP = true;
            }
            else if(temp.right == deepest && !foundDP)
            {
                deepParent = temp;
                foundDP = true;
            }
            
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }

        //if removing the deepest element, then delete it
        if(deepest == toRemove)
        {
            if(removeParent.left == toRemove)
                removeParent.left = null;
            else if(removeParent.right == toRemove)
                removeParent.right = null;
        }
        else //if removing any other element, delete and replace with deepest
        {
            if(toRemove == getRoot())
                getRoot().data = temp.data;
            else if(removeParent.left == toRemove)
                removeParent.left.data = deepest.data;
            else if(removeParent.right == toRemove)
                removeParent.right.data = deepest.data;
            //delete deepest node
            if(deepParent.left == deepest)
                deepParent.left = null;
            else if(deepParent.right == deepest)
                deepParent.right = null;
        }

        //decrease size
        setSize(getSize() - 1);
        return true;
    }

    /**
     * Searches for element in binary tree
     * @param element to find
     * @throws NullPointerException if element is null
     */
    public boolean containsBFS(E element)
    {
        if(element == null)
            throw new NullPointerException();
        if(getRoot() == null || getSize() == 0)
            return false;

        //use BFS (level order traversal) to find the first occurence of element
        Queue<Node> queue = new LinkedList<Node>();
        
        queue.add(getRoot());

        while(!queue.isEmpty())
        {
            Node temp = queue.poll();
            if(temp.data.compareTo(element) == 0)
                return true;
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }
        //return false if element is not found
        return false;
    }

    /**
     * Returns height of the Binary Tree.
     * @return height of the Binary Tree.
     */
    public int getHeight()
    {
        if(getSize() <= 1)
            return 0;
        return (int)Math.floor(Math.log(getSize()) / Math.log(LOGBASE2));
    }

    /**
     * Returns the lowest valued element in the Binary Tree;
     * @return the lowest valued element in the Binary Tree;
     */
    public E minValue()
    {
        if(getRoot() == null || getSize() == 0)
            return null;
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(getRoot());
        Node min = getRoot();

        while(!queue.isEmpty())
        {
            Node temp = queue.poll();
            if(temp != min && temp.data.compareTo(min.data) < 0)
                min = temp;
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }

        return min.data;
    }

    /**
     * Prints the tree element data in level order.
     */
    public void printTree()
    {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(getRoot());

        System.out.print("\nTREE:");
        while(!queue.isEmpty())
        {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }
        System.out.println();
    }

    /**
     * Getter for root node
     * @return root node
     */
    public Node getRoot()
    {
        return this.root;
    }

    /**
     * Sets root node of tree
     * @param input node to set the root node to
     */
    public void setRoot(Node input)
    {
        this.root = input;
    }

    /**
     * Getter for Tree size
     * @return tree size
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Sets the tree size
     * @param input size to set tree to
     */
    public void setSize(int input)
    {
        this.size = input;
    }
    
}