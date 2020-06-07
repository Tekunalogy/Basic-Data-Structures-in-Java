// NAME: KUNAL SINGLA
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import java.util.*;

/** TODO: Add class header here
 *  
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int nelems;
	Node head;
	Node tail;

	/**
	 * Node Inner Class for MyLinkedList
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		public Node(E element)
		{
			this.data = element;
		}

		public void setPrev(Node p)
		{
			this.prev = p;
		}

		public void setNext(Node n)
		{
			this.next = n;
		}

		public void setElement(E e)
		{
			this.data = e;
		}

		public Node getNext()
		{
			return this.next;
		}

		public Node getPrev()
		{
			return this.prev;
		} 

		public E getElement()
		{
			return this.data;
		} 
	}

	/**
	 *  List Iterator implementation for MyLinkedList
	 */ 
	protected class MyListIterator implements ListIterator<E> {

		boolean forward;
		boolean canRemoveOrSet;
		Node left,right;
		int idx;

		/**
		 * ListIterator Constructor
		 */
		public MyListIterator()
		{
			idx = 0;
			left = head;
			right = head.getNext();
			forward = true;
			canRemoveOrSet = false;

		}

		/**
		 * Method used to add a new node while iterating
		 * @param e data for new node to add
		 * @throws NullPointerException if data param is null
		 */
		@Override
		public void add(E e) throws NullPointerException
		{
			if(e == null)
				throw new NullPointerException();

			Node toAdd = new Node(e);

			toAdd.next = right;
			toAdd.prev = left;
			left.next = toAdd;
			right.prev = toAdd;

			if(forward)
				left = toAdd;
			else
				right = toAdd;
			
			nelems++;
			canRemoveOrSet = false;
		}

		/**
		 * Checks if iterator has a next node
		 */
		@Override
		public boolean hasNext()
		{
			return right.next != null;
		}

		/**
		 * Checks if iterator has a previous node
		 */
		@Override
		public boolean hasPrevious()
		{
			return left.prev != null;
		}

		/**
		 * Goes forward in the iterator
		 * @throws NoSuchElementException if next element is non-existant
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			if(hasNext())
			{
				Node temp = right;
				right = right.getNext();
				left = temp;
				idx++;
				forward = true;
				canRemoveOrSet = true;
				return left.data;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		@Override
		public int nextIndex()
		{
			return idx;
		}

		/**
		 * Goes in reverse in iterator (backward)
		 * @throws NoSuchElementException if previous element is non-existant
		 */
		@Override
		public E previous() throws NoSuchElementException
		{
			if(hasPrevious())
			{
				Node temp = left;
				left = left.getPrev();
				right = temp;
				idx--;
				forward = false;
				canRemoveOrSet = true;
				return right.data;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		@Override
		public int previousIndex()
		{
			if(left.equals(head))
				return -1;
			return idx - 1;
		}

		/**
		 * Remove the last element returned by the most recent call to either 
		 * next/previous.
		 * @throws IllegalStateException canRemoveOrSet is false.
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if(!canRemoveOrSet)
				throw new IllegalStateException();

			if(forward)
			{
				Node toRemove = left;

				left = toRemove.prev;
				left.next = right;
				right.prev = left;

				toRemove.next = null;
				toRemove.prev = null;

			}
			else
			{
				Node toRemove = right;

				right = toRemove.next;
				right.prev = left;
				left.next = right;

				toRemove.next = null;
				toRemove.prev = null;

			}
			canRemoveOrSet = false;
			nelems--;
			
		}

		/**
		 * Change the value in the node returned by the most recent 
		 * next/previous with the new value.
		 * @throws NullPointerException if e is null
		 * @throws IllegalStateException canRemoveOrSet is false.
		 */
		@Override
		public void set(E e) throws NullPointerException, IllegalStateException
		{
			if(e == null)
				throw new NullPointerException();
			if(!canRemoveOrSet)
			{
				throw new IllegalStateException();
			}

			if(forward)
			{
				left.data = e;
			}
			else
				right.data = e;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is define */
	public MyLinkedList()
	{
		this.nelems = 0;

		this.clear();
	}

	@Override
	public int size()
	{
    	return this.nelems;
	}

	/**
	 * 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index >= nelems || index < 0)
			throw new IndexOutOfBoundsException();

		Node tmp = this.head.getNext();
		for(int i = 0; i < index; ++i)
		{
			tmp = tmp.getNext();
		}

		return tmp.data;
	}

	@Override
	public void add(int index, E data) throws NullPointerException, 
									IndexOutOfBoundsException
	{
		if(data == null)
			throw new NullPointerException();
		else if(index > nelems || index < 0)
			throw new IndexOutOfBoundsException();
		
		Node currNode = this.head; //NOTE: change based on what is zero index

		for(int i = 0; i < index; ++i)
		{
			currNode = currNode.getNext();
		}

		Node newNode = new Node(data);

		Node successorNode = currNode.getNext();

		newNode.next = successorNode;
		newNode.prev = currNode;
		currNode.next = newNode;
		successorNode.prev = newNode;
		this.nelems++;
	}

	public boolean add(E data) throws NullPointerException
	{
		if(data == null)
			throw new NullPointerException();
		
		Node newNode = new Node(data);
		this.tail.getPrev().next = newNode;
		newNode.next = this.tail;
		newNode.prev = this.tail.prev;
		this.tail.prev = newNode;
		++this.nelems;
		
		return true;
	}

	public E set(int index, E data) throws NullPointerException, 
									IndexOutOfBoundsException
	{
		if(data == null)
			throw new NullPointerException();
		else if(index < 0 || index >= nelems)
			throw new IndexOutOfBoundsException();

		Node currNode = this.head.getNext();
		for(int i = 0; i < index; ++i)
		{
			currNode = currNode.getNext();
		}

		E output = currNode.data;
		currNode.data = data;

		return output;
	}

	public E remove(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= nelems)
			throw new IndexOutOfBoundsException();

		Node currNode = this.head.getNext();

		for(int i = 0; i < index; ++i)
		{
			currNode = currNode.getNext();
		}

		E output = currNode.data;
		Node prev = currNode.getPrev();
		Node suc = currNode.getNext();

		prev.next = suc;
		suc.prev = prev;

		currNode.next = null;
		currNode.prev = null;
		this.nelems--;
		
		return output;
	}

	public void clear()
	{
		this.head = new Node(null); //dummy/sentinel head node
		this.tail = new Node(null); //dummy/sentinel tail node

		this.head.next = this.tail;
		this.head.prev = null;

		this.tail.next = null;
		this.tail.prev = this.head;
		this.nelems = 0;
	}

	public boolean isEmpty()
	{
		return nelems == 0;
	}

	protected Node getNth(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= nelems)
			throw new IndexOutOfBoundsException();

		Node currNode = this.head.getNext();

		for(int i = 0; i < index; ++i)
		{
			currNode = currNode.getNext();
		}
		
		return currNode;
	}

	public Iterator<E> iterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> listIterator()
	{
		return new MyListIterator();
	}

}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4