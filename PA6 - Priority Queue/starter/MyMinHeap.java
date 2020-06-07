//NAME: Kunal Singla
// ID: A15799385
// EMAIL: Kusingla@ucsd.edu
/**
 * A custom implementation of a MinHeap
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class MyMinHeap
 * A binary tree data structure in which the key at the root node must be the
 * the minimum among all other keys in the tree. This implementation has the
 * property of being a complete tree; all levels of the tree are filled from
 * left to right except possibly the last level of the tree.
 */
public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list;

    //Constructor to set an empty ArrayList
    public MyMinHeap() 
    {
        list = new ArrayList<E>();
    }

    /**
     * Constructor to 'import' a collection of elements into Min Heap
     * @param collection to import
     * @throws NullPointerException collection cannot be null
     */
    public MyMinHeap(Collection<? extends E> collection) 
    {
        if(collection == null)
            throw new NullPointerException();
        
        list = new ArrayList<E>(collection);
        
        /**works backwards from list and percolates elements down to 
        * properly setup the MinHeap
        */
        for(int i = collection.size() - 1; i >= 0; --i)
        {
            percolateDown(i);
        }
        
    }

    /**
     * Swaps to elements at given indeces
     * @param from index
     * @param to index
     */
    protected void swap(int from, int to) 
    {
        E temp = list.get(from);
        list.set(from, list.get(to));
        list.set(to, temp);
    }

    /**
     * Returns parent index of Min Heap
     * @param index of child element
     * @return parent index of given child index
     */
    protected int getParentIdx(int index) 
    {
        if(index % 2 == 1) 
            return index / 2;

        return (index - 1) / 2;
    }

    /**
     * Returns index of left child of given parent index
     * @param index of parent child
     * @return left child index
     */
    protected int getLeftChildIdx(int index) 
    {
        return index * 2 + 1;
    }

    /**
     * Returns index of right child of given parent index
     * @param index of parent child
     * @return right child index
     */
    protected int getRightChildIdx(int index) 
    {
        return index * 2 + 2;
    }

    /**
     * Returns index of child with a lower value
     * @param index of parent child
     * @return index of child with a lower value
     */
    protected int getMinChildIdx(int index) 
    {
        int output = -1;
        try 
        {
            int left = this.getLeftChildIdx(index);
            int right = this.getRightChildIdx(index);

            E max = list.get(left);
            output = left;
            //Compares left and child index
            if(max.compareTo(list.get(right)) > 0)
            {
                max = list.get(right);
                output = right;
            }
        }
        catch (Exception e)
        {
            /**
             * max will be -1 if left child does not exists, otherwise left 
             * child index if right child does not exisit.
             */ 
        }
        return output;
    }

    /**
     * Moves the element at the given index up until all heap properties are
     * satisfied.
     * @param index of element to percolate up
     */
    protected void percolateUp(int index) 
    {
        int child = index;
        int parent = this.getParentIdx(child);
        
        if(list.get(parent).compareTo(list.get(child)) > 0)
        {
            swap(child, parent);
            percolateUp(parent);
        }
    }

    /**
     * Moves the element at the given index down until all heap properties are
     * satisfied.
     * @param index of element to percolate down
     */
    protected void percolateDown(int index) 
    {
        int parent = index;
        int childToSwapWith = this.getMinChildIdx(index);
        
        if(childToSwapWith <= size() -1 && childToSwapWith >= 0 
            && list.get(parent).compareTo(list.get(childToSwapWith)) > 0)
        {
            swap(childToSwapWith, parent);
            percolateDown(childToSwapWith);
        }
    }
    
    /**
     * Deletes element at given index and readjusts Min Heap if necessary
     * @param index of element to delete
     * @return deleted element
     */
    protected E deleteIndex(int index) 
    {
        E output = list.get(index);
        if(index == size() - 1)
            return output;
        list.set(index, null);
        this.swap(index, list.size() - 1);
        list.remove(list.size() - 1);

        if(index != 0 
        && list.get(index).compareTo(list.get(this.getParentIdx(index))) < 0)
            this.percolateUp(index);
        else
            this.percolateDown(index);
        
        return output;
    }

    /**
     * Inserts element into heap and readjusts Min Heap if necessary
     * @param element to insert into heap
     * @throws NullPointerException if given element is null
     */
    public void insert(E element) 
    {
        if(element == null)
            throw new NullPointerException();
        
        list.add(element);
        percolateUp(list.size() - 1);
    }

    /**
     * Returns minimum valued element in Min Heap (the root element)
     * @return minimum valued element. Null if list is empty.
     */
    public E getMin() 
    {
        if(list.isEmpty())
            return null;
        return list.get(0);
    }

    /**
     * Removes root element in Min Heap. Readjusts Min Heap if necessary.
     * @return deleted root elemented
     */
    public E remove() 
    {
        return this.deleteIndex(0);
    }

    /**
     * Returns size of Min Heap
     * @return size of Min Heap
     */
    public int size() 
    {
        return list.size();
    }

    /**
     * Clears Min Heap by overwriting list with an empty list.
     */
    public void clear() 
    {
        list = new ArrayList<E>();
    }
}
