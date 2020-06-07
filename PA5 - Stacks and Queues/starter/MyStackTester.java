// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester
{
    private MyStack<Integer> testStack;
    private MyStack<Integer> empty;

    @Before
    public void setUp()
    {
        testStack = new MyStack<>(10);
        empty = new MyStack<>(0);
    }

    @Test
    public void testConstructor()
    {
        assertEquals(10, testStack.theStack.data.length);
    }

    @Test
    public void testSanity()
    {
        assertTrue(empty.empty());
        testStack.push(2);
        assertFalse(testStack.empty());
    }

    @Test
    public void testPush()
    {
        testStack.push(2);
        assertEquals(1, testStack.theStack.size());
        assertEquals(new Integer(2), testStack.peek());
    }

    @Test
    public void testPop()
    {
        testStack.push(2);
        assertEquals(new Integer(2), testStack.pop());
        assertNull(testStack.pop());
    }

    @Test
    public void testPeek()
    {
        testStack.push(2);
        assertEquals(new Integer(2), testStack.peek());
        testStack.pop();
        assertNull(testStack.peek());
    }
    

}