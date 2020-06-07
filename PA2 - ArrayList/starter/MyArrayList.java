// NAME: KUNAL SINGLA
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

class MyArrayList<E> implements MyList<E> {

  Object[] data;
  int size;
  private final int DEFAULT_CAPACITY = 10;

  /**
   * Creates an ArrayList with capcity of DEFAULT_CAPACITY.
   */
  public MyArrayList() {
    this.data = new Object[this.DEFAULT_CAPACITY];
  }

  /**
   * Created an empty ArrayList with specified initial capacity.
   * 
   * @param initialCapacity
   */
  public MyArrayList(int initialCapacity) {
    if (initialCapacity < 0)
      throw new IllegalArgumentException();
    else {
      this.data = new Object[initialCapacity];
    }
  }

  /**
   * Creates a list containing the elements of the array input.
   * 
   * @param arr Input array to copy data from
   */
  public MyArrayList(E[] arr) {
    /**
     * In case that arr is invalid (i.e. arr == null), constructor falls 
     * back to setting data with default capacity of 10.
     */
    if (arr == null) {
      this.data = new Object[this.DEFAULT_CAPACITY];
    } else {
      this.data = arr.clone();
      for (Object element : this.data) {
        //used to set the size (number of elements) in the list
        this.size++;
      }
    }
  }

  /**
   * Increases capacity of this list so it can hold the number of elements
   * specificed by the requiredCapacity argument.
   * 
   * @param requiredCapacity Desired required capacity for the list
   */
  public void checkCapacity(int requiredCapacity) {
    Object[] temp = this.data.clone();

    if (this.data.length == 0) {
      this.data = new Object[this.DEFAULT_CAPACITY];
    } 
    else if (this.data.length < requiredCapacity) {
      this.data = new Object[this.data.length * 2];
    }

    if (this.data.length < requiredCapacity) {
      this.data = new Object[requiredCapacity];
    }

    for (int i = 0; i < temp.length; ++i) {
      this.data[i] = temp[i];
    }
  }

  /**
   * Returns the capacity of the list.
   *
   * @return The total amount of positions in the ArrayList
   */
  public int getCapacity() {
    return this.data.length;
  }

  /**
   * Inserts an element into the list at a given index.
   * 
   * @param index   The index in the ArrayList in which to insert the element
   * @param element The element to insert into the list
   * @throws IndexOutOfBoundsException if index is greater than the size of list
   */
  public void insert(int index, E element) throws IndexOutOfBoundsException {
    /** index cannot be greater than the current size of the list. 
     * Throws an error if this happens. 
     */
    if(index > size)
      throw new IndexOutOfBoundsException("index: " + index 
      + " greater than size: " + this.size);

    //check capacity for an additional element (current size + 1)
    checkCapacity(this.size + 1);
    //create a copy array to manipulate
    Object[] temp = this.data.clone();

    //a loop to insert the new element at specified index
    for (int i = 0; i < this.data.length; ++i) {
      //when loop reaches specified index, it inserts new element
      if (i == index) {
        this.data[i] = element;
      } else if (i < index) { 
        /** when loop is iterating before given index, it copies items over from
         *  previous array
        */
        this.data[i] = temp[i];
      } else {
        /** when loop is iterating after given index, it copies items over from
         *  previous array
        */
        this.data[i] = temp[i - 1];
      }
    }
    this.size++;
  }

  /**
   * Inserts an element at the beginning (index 0) of the list
   * 
   * @param element element to insert into list
   */
  public void prepend(E element) {
    insert(0, element);
  }

  /**
   * Inserts an element at the end of the list.
   * 
   * @param element element to insert into list
   */
  public void append(E element) {
    insert(this.size, element);
  }

  /**
   * Returns the element at the given position in the list.
   * 
   * @param index index of element to return
   * @return element at given position
   * @throws IndexOutOfBoundsException if index is less than 0 or greater than
   *                                   size of the list
   */
  public E get(int index) throws IndexOutOfBoundsException {
    return (E) this.data[index];
  }

  /**
   * Replaces current value at given index with given element.
   * 
   * @param index   index of element to replace
   * @param element element added at the specified index
   * @return element that was overwritten by new element
   * @throws IndexOutOfBoundsException if index accessed is negative or greater
   *                                   than the size of the array
   */
  public E set(int index, E element) throws IndexOutOfBoundsException {
    //can only insert at <= size
    if(index <= this.size)
    {
      Object overwritten = this.data[index];
      this.data[index] = element;
      return (E) overwritten;
    }
    else
    {
      /**index cannot be greater than the current size of the list. 
      *Throws an error if this happens.
      */
      throw new IndexOutOfBoundsException("index: " + index 
      + " greater than size: " + this.size);
    }
  }

  /**
   * Removes element at given index. Shifts elements after specified index.
   * 
   * @param index   index of element to replace
   * @param element element added at the specified index
   * @return element that was overwritten by new element
   * @throws IndexOutOfBoundsException if index accessed is negative or greater
   *                                   than the size of the array
   */
  public E remove(int index) throws IndexOutOfBoundsException {

    Object removed = this.data[index];

    //if specified index is not last element runs this loop
    if (index != size - 1) {
      /** loop is used to shift over elements in the list to the left
       *  (towards index 0), starting from specfied index
       */
      for (int i = index; i < this.size - 1; i++) {
        this.data[i] = this.data[i + 1];
      }
    }
    //sets the last element to null
    this.data[this.size - 1] = null;
    //decreases the size because element has now been removed
    this.size--;

    return (E) removed;
  }

  /**
   * Returns the number of elements in the list.
   * 
   * @return the number of elements in the list
   */
  public int size() {
    return this.size;
  }

  /**
   * Trims the capacity of the list to match the size of the list.
   */
  public void trimToSize() {
    //creates a copy of array for manipulation
    Object[] temp = this.data.clone();
    //capacity of the list now becomes the size of the list
    this.data = new Object[this.size];

    //copy elements over from cloned array
    for (int i = 0; i < this.data.length; ++i) {
      this.data[i] = temp[i];
    }
  }
}
