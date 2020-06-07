// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;
import static org.junit.Assert.*;

public class MyDequeTester{

    private MyDeque<Integer> capZero;
    private MyDeque<Integer> empty;
    private MyDeque<Integer> oneElem;
    private MyDeque<Integer> capOne;
    private MyDeque<Integer> several;
    private MyDeque<Integer> full;
    private MyDeque<Integer> frontBehindRear;
    private final int SIZE = 7;
    private final int SEVERAL = 3;
    private final int DEFAULT_CAP = 10;
    private final int EXPAND_FACTOR = 2;

    @Before
    public void setUp(){
        capZero = new MyDeque<>(0);
        empty = new MyDeque<>(SIZE);
        oneElem = new MyDeque<>(SIZE);
        capOne = new MyDeque<>(1);
        several = new MyDeque<>(SIZE);
        full = new MyDeque<>(SIZE);
        frontBehindRear = new MyDeque<>(SIZE);

        oneElem.data[0] = 0;
        oneElem.size = 1;
        Integer[] severalArr = {0, 1, 2, null, null, null, null};
        several.data = severalArr;
        several.size = SEVERAL;
        several.rear = SEVERAL - 1;

        Integer[] fullArr = {0, 1, 2, 3, 4, 5, 6};
        full.data = fullArr;
        full.size = SIZE;
        full.rear = SIZE - 1;

        Integer[] frontBehindRearArr = {3, 4, null, null, 0, 1, 2};
        frontBehindRear.data = frontBehindRearArr;
        frontBehindRear.front = 4;
        frontBehindRear.rear = 1;
        frontBehindRear.size = 5;
    }

    @Test
    public void testConstructorException(){
        try{
            MyDeque<Integer> illegal = new MyDeque<>(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){

        }
    }

    @Test
    public void testConstructorNormal(){
        MyDeque<Integer> normal = new MyDeque<>(SIZE);
        assertEquals(new Integer[SIZE],  normal.data);
        assertEquals(0, normal.front);
        assertEquals(0, normal.rear);
        assertEquals(0, normal.size);
    }


    @Test
    public void testAddFirstEmptyMultiple(){
        empty.addFirst(0);
        empty.addFirst(1);
        empty.addFirst(2);
        Integer[] expected = {0, null, null, null, null, 2, 1};
        assertEquals(expected,  empty.data);
        assertEquals(5, empty.front);
        assertEquals(0, empty.rear);
        assertEquals(3, empty.size);
    }


    /** When elements are in the middle of the array*/
    @Test
    public void testExpandCapacitySeveralEdge(){
        Integer[] severalEdge = {null, null, 0, 1, 2, null, null};
        several.data = severalEdge;
        several.front = 2;
        several.rear = 4;
        several.expandCapacity();
        Integer[] expanded = {0, 1, 2, null, null, null, null,
                        null, null, null, null, null, null, null};
        
        assertEquals(expanded,  several.data);
        assertEquals(0, several.front);
        assertEquals(SEVERAL - 1, several.rear);
        assertEquals(SEVERAL, several.size);
    }
    
    @Test
    public void testExpandZero()
    {
        MyDeque<Integer> test = new MyDeque<>(0);
        test.expandCapacity();
        assertEquals(10, test.data.length);
    }

    @Test
    public void testExpandOne()
    {
        MyDeque<Integer> test = new MyDeque<>(10);
        test.addFirst(1);
        test.expandCapacity();
        assertEquals(20, test.data.length);
        assertEquals(new Integer(1), test.peekFirst());
        assertEquals(new Integer(1), test.peekLast());
    }


    @Test
    public void testRemoveLastSeveralEdge(){
        Integer[] severalEdge = {1, null, null, null, null, -1, 0};
        several.data = severalEdge;
        several.front = 5;
        several.rear = 0;
        assertEquals(1, several.removeLast().intValue());
        Integer[] expected = {null, null, null, null, null, -1, 0};
        assertEquals(expected, several.data);
        assertEquals(5, several.front);
        assertEquals(6, several.rear);
        assertEquals(2, several.size);
    }

    @Test
    public void testSanity() 
    {
        MyDeque<Integer> tester = new MyDeque<Integer>(10);
        assertEquals("initialize MyDeque with capacity 10", 10, 
                                        tester.data.length);
        
        //expandCapacity with several elements at the start of the array
        tester.addLast(1);
        tester.addLast(2);
        tester.addLast(3);
        tester.expandCapacity();
        assertEquals("expand MyDeque capacity", 20, tester.data.length);

        //addFirst: deque containing several elements in the middle of the array
        Integer[] severalEdge = {null, null, 0, 1, 2, null, null};
        several.data = severalEdge;
        several.front = 2;
        several.rear = 4;
        several.addFirst(-1);
        Integer[] compare = {null, -1, 0, 1, 2, null, null};
        assertEquals(compare, several.data);

        //addLast: deque containing several elements in the middle of the array
        several.addLast(3);
        compare = new Integer[]{null, -1, 0, 1, 2, 3, null};
        assertEquals(compare, several.data);
        
        /**removeFirst: deque containing several elements in the 
         * middle of the array.
         */
        Integer output = several.removeFirst();
        compare = new Integer[]{null, null, 0, 1, 2, 3, null};
        assertEquals(compare, several.data);
        assertEquals(new Integer(-1), output);

        /**removeLast: deque containing several elements in the
         * middle of the array
         */
        output = several.removeLast();
        compare = new Integer[]{null, null, 0, 1, 2, null, null};
        assertEquals(compare, several.data);
        assertEquals(new Integer(3), output);

        
        tester = new MyDeque<Integer>(10);
        tester.addLast(1);
        tester.addLast(2);
        tester.addLast(3);
        compare = new Integer[]{1, 2, 3, null, null, null, null, null, null, null};

        /**peekFirst: deque containing several elements 
         * at the start of the array
         */
        output = tester.peekFirst();
        assertEquals(compare, tester.data);
        assertEquals(new Integer(1), output);

        /**peekLast: deque containing several elements 
         * at the start of the array
         */
        output = tester.peekLast();
        assertEquals(compare, tester.data);
        assertEquals(new Integer(3), output);
        
    }

    /** When elements are in the middle of the array*/
    @Test
    public void testAddLastMiddle() 
    {
        Integer[] severalEdge = {null, null, 0, 1, 2, null, null};
        several.data = severalEdge;

        several.front = 2;
        several.rear = 4;
        several.addLast(3);
        Integer[] addedLast = {null, null, 0, 1, 2, 3, null};
        
        assertEquals(addedLast,  several.data);
        assertEquals(5, several.rear);
        assertEquals(SEVERAL - 1, several.front);
        assertEquals(SEVERAL + 1, several.size);
    }

    @Test
    public void testSize()
    {
        assertEquals(several.size(), SEVERAL);
        assertEquals(several.size(), SEVERAL);
    }

    @Test
    public void testAddFirst()
    {
        try 
        {
            several.addFirst(null);
        }
        catch (NullPointerException e)
        {
            //pass
        }

        MyDeque<Integer> tester = new MyDeque<Integer>(1);
        tester.addFirst(1);
        assertEquals(1, tester.size());

        tester.addFirst(2);
        assertEquals(2, tester.size());

    }

    @Test
    public void testAddLast()
    {
        try 
        {
            several.addLast(null);
        }
        catch (NullPointerException e)
        {
            //pass
        }

        MyDeque<Integer> tester = new MyDeque<Integer>(1);
        tester.addLast(1);
        assertEquals(1, tester.data.length);

        tester.addLast(2);
        assertEquals(2, tester.data.length);

    }

    @Test
    public void testRemoveFirst()
    {
        assertNull(empty.removeFirst());
        MyDeque<Integer> tester = new MyDeque<Integer>(10);
        tester.addLast(1);
        assertEquals(10, tester.data.length);
        assertEquals(new Integer(1), tester.removeFirst());
    }

    @Test
    public void testRemoveLast()
    {
        assertNull(empty.removeLast());
        MyDeque<Integer> tester = new MyDeque<Integer>(10);
        tester.addLast(1);
        assertEquals(10, tester.data.length);
        assertEquals(new Integer(1), tester.removeLast());
    }

    @Test
    public void testPeekFirstEmpty()
    {
        assertNull(empty.peekFirst());
    }

    @Test
    public void testPeekLastEmpty()
    {
        assertNull(empty.peekLast());
    }






}
