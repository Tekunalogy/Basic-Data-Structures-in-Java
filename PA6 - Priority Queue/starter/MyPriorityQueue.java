//NAME: Kunal Singla
// ID: A15799385
// EMAIL: Kusingla@ucsd.edu
/**
 * A custom implementation of Priority Queue
 */

import java.util.Collection;

/**
 * Class MyPriorityQueue
 * Uses a MyMinHeap for implementation.
 */
public class MyPriorityQueue<E extends Comparable<E>>
{
    protected MyMinHeap<E> heap;

    /**
     * establishes empty priority queue
     */
    public MyPriorityQueue()
    {
        heap = new MyMinHeap<E>();
    }

    /**
     * establishes priority queue filled with elements from collection input
     * @throws NullPointerException if collection is null
     */
    public MyPriorityQueue(Collection<? extends E> collection)
    {
        heap = new MyMinHeap<E>(collection);
    }

    /**
     * Inserts element into PriorityQueue
     * @param element to insert
     * @throws NullPointerException if element is null
     */
    public void push(E element)
    {
        heap.insert(element);
    }

    /**
     * Returns front of priority queue
     * @return front of priority queue
     */
    public E peek()
    {
        return heap.getMin();
    }

    /**
     * Removes and returns element at front of priority queue
     * @return element at front of priority queue
     */
    public E pop()
    {
        return heap.remove();
    }
    
    /**
     * Returns the number of elements in priority queue
     * @return number of elements in priority queue
     */
    public int getLength()
    {
        return heap.size();
    }
    
    /**
     * Clears priority queue.
     * Empties the priority queue.
     */
    public void clear()
    {
        heap.clear();
    }
}