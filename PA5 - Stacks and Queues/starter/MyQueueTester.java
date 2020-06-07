// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester
{
    private MyQueue<Integer> testQueue;
    private MyQueue<Integer> empty;

    @Before
    public void setUp()
    {
        testQueue = new MyQueue<>(10);
        empty = new MyQueue<>(0);
    }

    @Test
    public void testConstructor()
    {
        assertEquals(10, testQueue.theQueue.data.length);
    }

    @Test
    public void testSanity()
    {
        assertTrue(empty.empty());
        testQueue.enqueue(1);
        assertFalse(testQueue.empty());
    }

    @Test
    public void testEnqueue()
    {
        testQueue.enqueue(2);
        assertEquals(1, testQueue.theQueue.size());
        assertEquals(new Integer(2), testQueue.peek());
    }

    @Test
    public void testDequeue()
    {
        testQueue.enqueue(2);
        assertEquals(new Integer(2), testQueue.dequeue());
        assertNull(testQueue.dequeue());
    }

    @Test
    public void testPeek()
    {
        testQueue.enqueue(2);
        assertEquals(new Integer(2), testQueue.peek());
        testQueue.dequeue();
        assertNull(testQueue.peek());
    }

}