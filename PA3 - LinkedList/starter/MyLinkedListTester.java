import org.junit.*;	
import static org.junit.Assert.*;	
import java.util.LinkedList;	
import java.util.ListIterator;	


/**	
 *  Title: class MyLinkedListTester	
 *  @author Kunal Singla
 *  @version 3.0 05-April-2015
 *  Student ID: A15799385
 *  CSE12 Account: cs12sp20ant
 *  Date: 16-April-2020
 *	
 *  Description: TODO	
 * */	

public class MyLinkedListTester	
{	
	private MyLinkedList<Integer> empty;
	private MyLinkedList<Integer> one;	
	private MyLinkedList<Integer> several;	
	private MyLinkedList<String>  slist;	
	static final int DIM = 5;	
	static final int FIBMAX = 30;	
	private ListIterator<Integer> iterTest;	

	/**	
	 * Standard Test Fixture. An empty list, a list with one entry (0) and 	
	 * a list with several entries (0,1,2)	
	 */ 	
	@Before	
	public void setUp()	
	{	
		empty = new MyLinkedList<Integer>();	
		one = new MyLinkedList<Integer>();	
		one.add(0,new Integer(0));
		several = new MyLinkedList<Integer>() ;	
		// List: 1,2,3,...,Dim	
		for (int i = DIM; i > 0; i--)	
			several.add(0,new Integer(i));	

		// List: "First","Last"	
		slist = new MyLinkedList<String>();	
		slist.add(0,"First");
		slist.add(1,"Last");
	}	

	/** Test if first node of the lists are correct */	
	@Test	
	public void testGetFirst()	
	{	
		assertEquals("Check 0",new Integer(0),one.get(0)) ;	
		assertEquals("Check 0",new Integer(1),several.get(0)) ;	
	}	

	/** Test if size of lists are correct */	
	@Test	
	public void testListSize()	
	{	
		assertEquals("Check Empty Size",0,empty.size()) ;	
		assertEquals("Check One Size",1,one.size()) ;	
		assertEquals("Check Several Size",DIM,several.size()) ;	
	}	

	/** Test setting a specific entry */	
	@Test	
	public void testSet()	
	{	
		slist.set(1,"Final");	
		assertEquals("Setting specific value", "Final",slist.get(1));

		Integer first = several.set(0, 5);
		Integer valid = several.set(1, 4);
		Integer last  = several.set(several.nelems - 1, 3);

		assertEquals("Checking first overwritten element", new Integer(1), first);
		assertEquals("Checking valid overwritten element", new Integer(2), valid);
		assertEquals("Checking last overwritten element", new Integer(5), last);
	}	

	/** Test isEmpty */	
	@Test	
	public void testEmpty()	
	{	
		assertTrue("empty is empty", empty.isEmpty()) ;	
		assertTrue("one is not empty", !one.isEmpty()) ;	
		assertTrue("several is not empty", !several.isEmpty()) ;	
	}	

	/** Test out of bounds exception on get */	
	@Test	
	public void testGetException()	
	{	
		try 	
		{	
			empty.get(0);	
			// This is how you can test when an exception is supposed 	
			// to be thrown	
			fail("Should have generated an exception");  	
		}	
		catch(IndexOutOfBoundsException e)	
		{	
			//  normal	
		}	
	}	

	/** Test iterator on empty list and several list */	
	@Test	
	public void testIterator()	
	{	
		int counter = 0 ;	
		ListIterator<Integer> iter;	
		for (iter = empty.listIterator() ; iter.hasNext(); )	
		{	
			fail("Iterating empty list and found element") ;	
		}	
		counter = 0 ;	
		for (iter = several.listIterator() ; iter.hasNext(); iter.next())	
			counter++;	
		assertEquals("Iterator several count", counter, DIM);	
	}	
	
	@Test
	public void testRemove()
	{
		slist.add("Bebop1");
		slist.add("Bebop2");
		slist.add("Bebop3");
		assertEquals("Check if the size is correct before removal", 5, slist.nelems);
		String output = slist.remove(1);
		assertEquals("Check if the removed node data is correct", "Last", output);
		assertEquals("Check if the size is correct after removal", 4, slist.nelems);
		assertEquals("Check if the first element is correct value", "First", slist.get(0));
		String firstE = slist.remove(0);
		assertEquals("Check if the size is correct after removal", 3, slist.nelems);
		String lastE = slist.remove(slist.nelems-1);
		assertEquals("Check if the size is correct after removal", 2, slist.nelems);
		
		try 	
		{	
			slist.get(3);
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}

		try 	
		{	
			empty.remove(1);
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}
	}

	@Test
	public void testRemoveInteger()
	{
		int out = several.remove(1);
		assertEquals("Check if the value of removed element is correct", 2, out);

		try 	
		{	
			several.remove(-1);
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}

		try 	
		{	
			several.remove(several.nelems);
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}
	}

	@Test
	public void testAdd()
	{
		assertTrue("Check if element is added", slist.add("Bebop"));
		assertEquals("Check if 3rd element is correct value", "Bebop", slist.get(2));
		slist.add(0, "Bebop2");
		assertEquals("Check if first element is correct value", "Bebop2", slist.get(0));
		slist.add(slist.nelems, "Bebop3");
		assertEquals("Check if last element is correct value", "Bebop3", slist.get(slist.nelems - 1));
		assertEquals("Check if the size is correct", 5, slist.nelems);

		try 	
		{	
			slist.add(-1, "negative");
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}

	}

	@Test
	public void testGetMiddle()
	{
		slist.add("Bebop");
		slist.add("Bebop2");
		slist.add("Bebop3");
		assertEquals("Check if the size is correct after removal", 5, slist.nelems);
		assertEquals("Check if 3rd element is correct value", "Bebop", slist.get(2));	
	}

	@Test
	public void testClear()
	{
		slist.add("Bebop");
		slist.add("Bebop2");
		slist.add("Bebop3");
		slist.clear();
		assertEquals("Check if the size is correct after clear", 0, slist.nelems);
		assertEquals("Check if head node is null", null, slist.head.data);
		assertEquals("Check if head tail is null", null, slist.tail.data);
		
		assertEquals("Check if head node prev is null", null, slist.head.getPrev());
		assertEquals("Check if head node next is tail node", slist.tail, slist.head.getNext());

		assertEquals("Check if tail node next is null", null, slist.tail.getNext());
		assertEquals("Check if tail node prev is head node", slist.head, slist.tail.getPrev());
	}

	@Test
	public void testGetNth()
	{
		slist.add("Bebop");
		slist.add("Bebop2");
		slist.add("Bebop3");

		MyLinkedList.Node checkNode = slist.getNth(4);
		assertEquals("Check if checkNode is correct value", "Bebop3", checkNode.data);

		try 	
		{	
			checkNode = slist.getNth(5);
			fail("Should have generated an exception");  	
		}
		catch(IndexOutOfBoundsException e)	
		{	
			//pass
		}

	}




	//////////////////////////////////////////	
	//Begin testing on List Iterator methods//	
	/////////////////////////////////////////	


	@Test
	public void testIteratorAdd()
	{
		iterTest = several.listIterator(); 	

		iterTest.next();	
		iterTest.next();	
		iterTest.add(new Integer(69));

		assertEquals(new Integer(69), iterTest.previous());
	}

	/** Test listiterator hasnext method while it goes through the empty	
	 * and one list	
	 */	
	@Test 	
	public void testIteratorHasNext() {	

		ListIterator<Integer> iter = empty.listIterator();	
		ListIterator<Integer> iter1 = one.listIterator();	

		assertTrue(!iter.hasNext());	
		assertTrue(iter1.hasNext());							
	}	

	/** Test listiterator next method */	
	@Test	
	public void testIteratorNext() {	

		iterTest = several.listIterator();	

		assertEquals(new Integer(1),iterTest.next());	
		assertEquals(new Integer(2),iterTest.next());	
		assertEquals(new Integer(3),iterTest.next());	
		assertEquals(new Integer(4),iterTest.next());	
		assertEquals(new Integer(5),iterTest.next());						
	}	
	
	/** Test nextIndex method of list iterator */	
	@Test	
	public void testIteratorNextIndex() {	
		iterTest = several.listIterator();	

		//Test the nextIndex method at the start of and end 	
		//of the list as well as middle of the list	
		assertEquals(0, iterTest.nextIndex());

		iterTest.next();	
		iterTest.next();	
		iterTest.next();	

		assertEquals(3, iterTest.nextIndex());			

		iterTest.next();	
		iterTest.next();	

		assertEquals(5, iterTest.nextIndex());	
	}	

	/** Test the remove method of list iterator */	
	@Test 	
	public void testIteratorRemove() {	
		iterTest = several.listIterator(); 	

		//Test whether removes method work after next method		
		iterTest.next();	
		iterTest.next();	
		iterTest.remove();	

		assertEquals(new Integer(1), iterTest.previous());

		//Test whether remove method work after previous method	
		iterTest.next();	
		iterTest.next();	
		iterTest.previous();	
		iterTest.remove();	

		assertEquals(new Integer(4), iterTest.next());	
	} 			
	
	/* Add your own tests here */	

}
