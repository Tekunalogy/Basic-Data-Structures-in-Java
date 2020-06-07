//NAME: Kunal Singla
// ID: A15799385
// EMAIL: Kusingla@ucsd.edu

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*; 

/**
 * Testing file to test MyPriorityQueue with JUnit.
 */
public class MyPriorityQueueTester
{
    private MyPriorityQueue<Integer> queue;
    /**
     * This method runs before every method tagged with @Test. 
     * Feel free to add your own MyMinHeap variables and their 
     * initializations here. 
     */
    @Before
    public void setUp() 
    {
        queue = new MyPriorityQueue<>();
    }

    /**
     * Runs the sanity test for swap() given in the write-up. 
     */
    @Test
    public void testSanity() 
    {
        queue.push(2);
        assertEquals(queue.pop(), new Integer(2));
    }
}