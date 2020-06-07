//NAME: KUNAL SINGLA
//ID: A15799385
//EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class MyHashTableTester {

	private MyHashTable hashTable1;

	@Before
	public void setUp()
	{
		hashTable1 = new MyHashTable(1);
	}
	@Test
	public void testInsert()
	{
		assertEquals("Checking insert", true, hashTable1.insert("abc"));
		assertEquals("Checking contains after insert", true,
		hashTable1.contains("abc"));
	}

	@Test
	public void testInsertNullPointerException(){
		try{
			hashTable1.insert(null);
			fail("Expected an NullPointerException to be thrown");
		}catch(NullPointerException e){
			assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
		}
	}


	@Test
	public void testDelete()
	{
		hashTable1.insert("abc");
		assertEquals("Checking delete", true, hashTable1.delete("abc"));
		assertEquals("Checking contains after delete", false,
		hashTable1.contains("abc"));
	}
	@Test
	public void testGetSize()
	{
		assertEquals("Checking getSize", 0, hashTable1.getSize());

		hashTable1.insert("abc");
		hashTable1.insert("pqr");
		hashTable1.insert("xyz");

		assertEquals("Checking getSize", Integer.valueOf(3),
		Integer.valueOf(hashTable1.getSize()));
	}

	@Test
	public void testHash()
	{
		int one = hashTable1.hashString("hello");
		int two = hashTable1.hashString("hello");
	}

	/***************************CUSTOM STUDENT TESTS***************************/
	@Test
	public void sanityCheck()
	{
		MyHashTable sanityTable = new MyHashTable(10);
		assertEquals("Check hashtable size is equal to 10", 10, sanityTable.array.length);
		
		String toInsert = "First Value";

		assertTrue("Element Inserted", sanityTable.insert(toInsert));
		assertTrue("Check hashtable contains correct element", sanityTable.contains(toInsert));
		assertEquals("Check hashtable size after insertion", 1, sanityTable.getSize());

		sanityTable.delete(toInsert);
		assertTrue("Check hashtable does not contain element", !sanityTable.contains(toInsert));
		assertEquals("Check hashtable size after deletion", 0, sanityTable.getSize());

		sanityTable.rehash();
		assertEquals("Check hashtable length after rehash", 23, sanityTable.array.length);
		
		String string1 = "CSE12 Rocks!";
		String copyString1 = "CSE12 Rocks!";
		assertTrue("Check if identical strings are hashed to the same value", 
		sanityTable.hashString(string1) == sanityTable.hashString(copyString1));
	}

	@Test
	public void testInvalidConstructors()
	{
		MyHashTable table;

		//test table constructor with size less than 0
		try
		{
			table = new MyHashTable(-1);
		}
		catch(IllegalArgumentException e)
		{
			//pass
		}

		//test table constructor with size less than 0 and valid string
		try
		{
			table = new MyHashTable(-1, "FileName");
		}
		catch(IllegalArgumentException e)
		{
			//pass
		}

		//test table constructor with valid size and invalid string
		try
		{
			table = new MyHashTable(5, null);
		}
		catch(NullPointerException e)
		{
			//pass
		}

		//test table constructor with invalid size and invalid string
		try
		{
			table = new MyHashTable(-1, null);
		}
		/**
		 * Constructor checks size first, so illegal arg exception would be
		 * thrown first.
		 **/
		catch(IllegalArgumentException e)
		{
			//pass
		}

	}

	@Test
	public void testValidConstructor()
	{
		MyHashTable table;

		table = new MyHashTable(11);
		assertEquals("Verify table size after valid constructor", 0, table.getSize());
		assertEquals("Verify nelems instance variable", 0, table.nelems);
		assertEquals("Verify expand instance variable", 0, table.expand);
		assertEquals("Verify collision instance variable", 0, table.collision);
		assertEquals("Verify statsFileName instance variable", null, table.statsFileName);
		assertEquals("Verify printStats instance variable", false, table.printStats);

		String fileName = "FileName";
		table = new MyHashTable(11, fileName);
		assertEquals("Verify table size after valid constructor", 0, table.getSize());
		assertEquals("Verify nelems instance variable", 0, table.nelems);
		assertEquals("Verify expand instance variable", 0, table.expand);
		assertEquals("Verify collision instance variable", 0, table.collision);
		assertEquals("Verify statsFileName instance variable", fileName, table.statsFileName);
		assertEquals("Verify printStats instance variable", true, table.printStats);
	}

	@Test
	public void testInvalidInsert()
	{
		hashTable1 = new MyHashTable(1);
		try 
		{
			hashTable1.insert(null);
		}
		catch (NullPointerException e) 
		{
			//pass
		}

		String value = "element";
		hashTable1.insert(value);
		assertTrue("Test insertion of duplicate value", !hashTable1.insert(value));
	}

	@Test
	public void testValidInsert()
	{
		hashTable1 = new MyHashTable(5);
		String value = "value";

		double initLoadFactor = hashTable1.nelems / hashTable1.array.length;
		assertTrue("Inserted element successfully", hashTable1.insert(value));
	}


	@Test
	public void testInvalidDelete()
	{
		try 
		{
			hashTable1.delete(null);
		}
		catch(NullPointerException e)
		{
			//pass
		}

		hashTable1.insert("value");
		assertTrue("Verify deletion non-existent string", !hashTable1.delete("value1"));
	}

	@Test
	public void testValidDelete()
	{
		hashTable1 = new MyHashTable(13);

		String value = "value1";
		hashTable1.insert(value);
		assertTrue("Verify deletion of string at index with no other elements", hashTable1.delete(value));
		assertEquals("Verify correct nelems", 0, hashTable1.nelems);


		String toInsert = "valu11"; //same index after hashing as "value1"
		int hashedIndex1 = hashTable1.hashString(value) % hashTable1.array.length;
		int hashedIndex2 = hashTable1.hashString(toInsert) % hashTable1.array.length;
		assertEquals("Verify that index after hashing is equivalent", hashedIndex1, hashedIndex2);

		hashTable1.insert(value);
		hashTable1.insert(toInsert);
		assertTrue("Verify deletion of string at index with other elements", hashTable1.delete("valu11"));
		assertEquals("Verify correct nelems", 1, hashTable1.nelems);
	}

	@Test
	public void testInvalidContains()
	{
		try 
		{
			hashTable1.contains(null);
		}
		catch (NullPointerException e)
		{
			//pass
		}

		hashTable1.insert("value");
		assertTrue("Verify String does not exist in the hash table.", !hashTable1.contains("value1"));
	}

	@Test
	public void testValidContains()
	{
		hashTable1.insert("value");
		assertTrue("Verify String exists in the hash table.", hashTable1.contains("value"));
	}

	@Test
	public void testRehash()
	{
		hashTable1.insert("value1");
		hashTable1.insert("value2");
		assertEquals("Verify number of buckets after rehash (#1)", 5, hashTable1.array.length);
		hashTable1.insert("value3");
		hashTable1.insert("value4");
		hashTable1.insert("value5");
		assertEquals("Verify number of buckets after rehash (#2)", 11, hashTable1.array.length);

	}
	
	@Test
	public void testPrintTable()
	{
		hashTable1 = new MyHashTable(11);
		hashTable1.insert("value");
		// hashTable1.printTable();
	}
}
