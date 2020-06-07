// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU
/**
 * A custom implementation of a Queue using a Deque
 */



/**
 * Class MyQueue
 * A collection that supports element insertion at the end of a queue and
 * deletion at the front.
 * Methods are provided to insert, remove, and peek at the element on both
 * sides.
 * Utilized MyDeque for underlying implementation of each function.
 */

class MyQueue<E> implements QueueInterface<E>
{
    MyDeque<E> theQueue;

    /**
     * Constructor sets initial capacity of queue
     * @param initialCapacity initial length of queue
     */
    public MyQueue(int initialCapacity)
    {
        theQueue = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks whether or not the queue is empty.
     * PRECONDITION: none
     * POSTCONDITION: the StackInterface is unchanged.
     * @return True if there are no elements in the QueueInterface, false 
     * otherwise.
     */
    public boolean empty()
    {
        return theQueue.size == 0;
    }

    /**
     * Adds the specified element to the tail of this QueueInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyQueue is at capacity, the capacity of this
     * container is doubled. The element is now the tail element in this
     * QueueInterface, none of the other elements have been changed, and
     * the size is increased by 1. 
     * @param element the element to add to the queue
     * @throws NullPointerException if the specified element is null.
     */
    public void enqueue(E element)
    {
        theQueue.addLast(element);
    }

    /**
     * Removes the element at the head of this QueueInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the QueueInterface's size is greater than zero.
     * POSTCONDITION: the head element in this QueueInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E dequeue()
    {
        return theQueue.removeFirst();
    }

    /**
     * Returns the element at the head of this QueueInterface,
     * or null if there was no such element.
     * PRECONDITION: the QueueInterface's size is greater than zero.
     * POSTCONDITION: The QueueInterface is unchanged.
     * @return  the element at the head, or null if the size was zero.
     */
    public E peek()
    {
        return theQueue.peekFirst();
    }
}