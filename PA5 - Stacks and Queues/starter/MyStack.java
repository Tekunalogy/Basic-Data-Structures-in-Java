// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU
/**
 * A custom implementation of a Queue using a Deque
 */


/**
 * A Stack class that implements the Stack Interface
 * 
 * This represents a last-in-first-out (LIFO) stack of elements. 
 * It extends class Vector with five operations that allow a vector to be treated as a stack. 
 * The usual push and pop operations are provided, as well as a method to peek 
 * at the top item on the stack, a method to test for whether the stack is 
 * empty, and a method to search the stack for an item and discover how far it is from the top.
 */

 class MyStack<E> implements StackInterface<E>
 {
    MyDeque<E> theStack;

    /**
     * Constructor sets initial capacity of stack
     * @param initialCapacity initial length of stack
     */
    public MyStack(int initialCapacity)
    {
        theStack = new MyDeque<E>(initialCapacity);
    }


     /**
     * Checks whether or not the stack is empty.
     * PRECONDITION: none
     * POSTCONDITION: the StackInterface is unchanged.
     * @return True if there are no elements in the StackInterface, false 
     * otherwise.
     */
    public boolean empty()
    {
        return theStack.size == 0;
    }

    /**
     * Adds the specified element to the top of this StackInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyStack is at capacity, the capacity of this
     * container is doubled. The element is now the top element in this
     * StackInterface, none of the other elements have been changed, and
     * the size is increased by 1.
     * @param element the element to add to the stack
     * @throws NullPointerException if the specified element is null.
     */
    public void push(E element)
    {
        theStack.addLast(element);
    }

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the StackInterface's size is greater than zero.
     * POSTCONDITION: the top element in this StackInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E pop()
    {
        return theStack.removeLast();
    }

    /**
     * Returns the element at the top of this StackInterface,
     * or null if there was no such element.
     * PRECONDITION: the StackInterface's size is greater than zero.
     * POSTCONDITION: The StackInterface is unchanged.
     * @return  the element at the top, or null if the size was zero.
     */
    public E peek()
    {
        return theStack.peekLast();
    }
 }