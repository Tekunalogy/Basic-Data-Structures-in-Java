// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU
/**
 * A custom implementation of a Deque
 */



/**
 * Class MyDeque
 * A collection that supports element insertion and deletion at both ends.
 * Deque is short for double ended queue. This has no limit on the number of 
 * elements it can contain.
 * Methods are provided to insert, remove, and peek at the element on both
 * sides.
 * Insertion and deletion operations will have at worst O(1) time
 * complexity due to the implementation of a rear and front instance variable,
 * allowing dynamically updated rear and front indexes instead of shifting
 * elements.
 */

class MyDeque<E> implements DequeInterface<E>
{
    static final int DEFAULT_SIZE = 10;
    static final int EXPAND_FACTOR = 2;
    static final int ZERO_MIN = 0;
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor for initializing MyDeque.
     * @param initialCapacity initial capacity to set the Deque to.
     * @throws IllegalArgumentException If initital capacity is less than zero.
     */
    public MyDeque(int initialCapacity)
    {
        if(initialCapacity < ZERO_MIN)
            throw new IllegalArgumentException();
        
        data = new Object[initialCapacity];
    }


    /**
     * Returns the number of elements in this DequeInterface.
     * <p> PRECONDITION: none
     * <p> POSTCONDITION: the DequeInterface is unchanged.
     * @return the number of elements in this DequeInterface
     */
    public int size()
    {
        return size;
    }

    /**
     * Doubles the capacity of this DequeInterface.
     * <p> PRECONDITION: none
     * <p> POSTCONDITION: the DequeInterface's capacity is now doubled and
     * maintains the same elements. No elements have changed. If the capacity
     * is 0, set capacity to some default capacity.
     */
    public void expandCapacity()
    {
        if(data.length == 0)
            data = new Object[DEFAULT_SIZE];
        else
        {
            int tmpSize = size;
            Object[] temp = new Object[data.length * EXPAND_FACTOR];
            for(int i = 0; i < tmpSize; ++i)
            {
                temp[i] = this.removeFirst();
            }
            size = tmpSize;
            data = temp;
            front = 0;
            rear = size - 1;
        }
    }

    /**
     * Adds the specified element to the front of this DequeInterface.
     * <p>PRECONDITION: none
     * <p>POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called 
     * to double the size of this container. The element is now the front
     * element in this DequeInterface, none of the other elements have been
     * changed, and the size is increased by 1.
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    public void addFirst(E element)
    {
        if(element == null)
            throw new NullPointerException();
        if(size >= data.length)
            expandCapacity();

        if(front == 0 && size > 0)
            front = (front - 1 + data.length);
        else if(front != 0)
            --front;

        data[front] = element;
        ++size;
    }

    /**
     * Adds the specified element to the back of this DequeInterface.
     * <p>PRECONDITION: none
     * <p>POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called
     * to double the size of this container. The element is now the back element
     * in this DequeInterface, none of the other elements have been changed, and
     * the size is increased by 1.
     * @param element the element to add to the back of the attay
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    public void addLast(E element)
    {
        if(element == null)
            throw new NullPointerException();
        if(size >= data.length)
            expandCapacity();
        
        if(rear == 0 && size > 0)
            rear = (rear + 1) % data.length;
        else if(rear != 0)
            ++rear;
        
        data[rear] = element;
        ++size;
    }


    /**
     * Removes the element at the front of this DequeInterface.
     * <p> Returns the element removed, or null if there was no such element.
     * <p> PRECONDITION: the DequeInterface's size is greater than zero.
     * <p> POSTCONDITION: the front element in this DequeInterface has been
     * removed, none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return the element removed, or null if the size was zero.
     */
    public E removeFirst()
    {
        if(size == 0)
            return null;

        Object output = data[front];
        data[front] = null;

        if(size - 1 != 0)
            --size;
        
        if(front + 1 >= data.length)
            front = (front + 1) % size;
        else
            front++;
        
        return (E) output;
    }

    /**
     * Removes the element at the back of this DequeInterface.
     * <p> Returns the element removed, or null if there was no such element.
     * <p> PRECONDITION: the DequeInterface's size is greater than zero.
     * <p> POSTCONDITION: the back element in this DequeInterface has been
     * removed, none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return the element removed, or null if the size was zero.
     */
    public E removeLast()
    {
        if(size == 0)
            return null;

        Object output = data[rear];
        data[rear] = null;
        --size;
        
        if(rear - 1 < 0)
            rear = (rear - 1 + data.length) % data.length;
        else
            --rear;
        
        return (E) output;
    }

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     * <p> PRECONDITION: the DequeInterface's size is greater than zero.
     * <p> POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the front, or null if the size was zero.
     */
    public E peekFirst()
    {
        return (E) data[front];
    }

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     * <p> PRECONDITION: the DequeInterface's size is greater than zero.
     * <p> POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the back, or null if the size was zero.
     */
    public E peekLast()
    {
        return (E) data[rear];
    }

    /**
     * Prints the data using .toString method.
     */
    public void print()
    {
        System.out.print("data: [");
        for(int i = 0; i < data.length; ++i)
        {
            if(i == 0)
            {
                System.out.print(data[i]);
            }
            else
            System.out.print(", " + data[i]);
        }
        System.out.println("]");
    }
}