// NAME: KUNAL SINGLA
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;


import org.junit.*;

public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Integer[] arrInts = {1,2,3};

  //null array for testing null array constructor
  Object[] arrNull = null;

  private MyArrayList list1, list2, list3, list4, list5, list6;

  /**
   * Instantiating all lists to be used in the following tests.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
    list6 = new MyArrayList<>(arrNull);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, list1.size());
    assertEquals("Check size for constructor with given capacity of 10", 0, 
            list2.size());
    assertEquals("Check size for constructor with given capacity of 3", 0, 
            list3.size());
    assertEquals("Check size for constructor with given array", 10, 
            list4.size());
    assertEquals("Check size for constructor with given int array", 3, 
            list5.size());
    assertEquals("Check size for constructor with given null array", 0, 
            list6.size());
  }
  
  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity", DEFAULT_CAPACITY, 
            list1.getCapacity());
    assertEquals("Check given capacity", MY_CAPACITY, list3.getCapacity());
    assertEquals("Check null array constructor capacity", DEFAULT_CAPACITY, 
            list6.getCapacity());
  }

  @Test
  public void testAppend() {
    int[] nums = {2,4};
    list1.append(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, 
            list1.getCapacity());

  }

  @Test
  public void testPrepend() {
    int[] nums = {2,4};
    list1.prepend(nums[0]);
    assertEquals("Check that prepend increments size", 1, list1.size());
    list1.prepend(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, 
            list1.getCapacity());
  }


  @Test
  public void testInsertAtEnd() {
    int[] nums = {2,4};
    list1.insert(list1.size, nums[0]);
    assertEquals("Check that insert increments size", 1, list1.size());
    list1.insert(list1.size, nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, 
            list1.getCapacity());
  }

  @Test
  public void testInsertMiddle() {
    int[] nums = {2,4};
    list1.insert(0, nums[0]);
    assertEquals("Check that insert increments size", 1, list1.size());
    list1.insert(1, nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, 
          list1.getCapacity());
    assertEquals("Check that the element at index 0 is correct", nums[0], 
            list1.get(0));
    assertEquals("Check that the element at index 1 is correct", nums[1], 
            list1.get(1));
  }

  @Test
  public void testInsertInvalidIndex() {
    try {
      list1.insert(-1, 5);
      fail("Expected an IndexOutOfBoundsException to be thrown");
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    try {
      list1.insert(list1.size + 1, 5);
      fail("Expected an IndexOutOfBoundsException to be thrown");
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }
  }

  @Test
  public void testRemove() {
    Object removed = list5.remove(1);
    assertEquals("Check that remove decrements size", 2, list5.size());
    assertEquals("Check that the removed item is correct", arrInts[1], removed);

    //try to remove at negative index
    try {
      list5.remove(-1);
      fail(
      "Expected an IndexOutOfBoundsException to be thrown for negative index"
      );
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    //try to remove at index greater than size
    try {
      list5.remove(list5.size + 1);
      fail(
      "Expected an IndexOutOfBoundsException to be thrown for index greater" + 
      "than size"
      );
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }
  }

  @Test
  public void testCheckCapacity() {
    list5.checkCapacity(4);//list should be set to capacity of 6
    assertEquals("Check that capacity of list doubles", 6, list5.getCapacity());

    list5.checkCapacity(13);//list should be set to capacity of 13
    assertEquals("Check that capacity of list is more than double", 13, 
            list5.getCapacity());
      
    list5.checkCapacity(5);//list should still be capacity of 13
    assertEquals("Check that capacity of list doesn't change", 13, 
            list5.getCapacity());
  }

  @Test
  public void testGet() {
    //try to access negative index in list5
    try {
      list5.get(-1);
      fail(
      "Expected an IndexOutOfBoundsException to be thrown for negative index"
      );
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    //try to access index greater than size of list5
    try {
      list5.get(list5.size + 1);
      fail(
        "Expected an IndexOutOfBoundsException to be thrown for index greater than size"
        );
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    //access valid index
    Object element = list5.get(0);
    assertEquals("Check that the element index is correct", arrInts[0], element);
  }

  @Test
  public void testSet() {
    //try to access negative index in list5
    try {
      list5.set(-1, 5);
      fail("Expected an IndexOutOfBoundsException to be thrown for negative index");
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    //try to access index greater than size of list5
    try {
      list5.set(list5.size + 1, 5);
      fail(
      "Expected an IndexOutOfBoundsException to be thrown for index greater than size"
      );
    }
    catch (IndexOutOfBoundsException anIooBException) {
      //pass
    }

    //access valid index
    Object element = list5.set(2, 4);
    assertEquals("Check that the element at valid index is correct", 4, 
            list5.get(2));
  }

  @Test
  public void testTrimToSize() {
    //list2 has capacity of DEFAULT_CAPACITY

    list2.append(1);
    list2.append(2);
    list2.append(3);

    list2.trimToSize();
    assertEquals("Check that trimToSize correctly trims the capacity", list2.size,
            list2.getCapacity());

    list5.trimToSize();
    assertEquals("Check that trimToSize does not trim the capcity", list5.size, 
            list5.getCapacity());
  }

  /** checks if passing in null into specific methods that allow it causes any 
  *   errors
  */
  @Test
  public void testNullArguments() {
    list2.append(null);
    assertEquals("Check last element is null", null, list2.get(list2.size - 1));
    list2.prepend(null);
    assertEquals("Check first element is null", null, list2.get(0));
    list2.insert(1, null);
    assertEquals("Check index 1 element is null", null, list2.get(1));
  }

}