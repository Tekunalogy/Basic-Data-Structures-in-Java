# CSE 12 Spring 2020 PA3 - Doubly Linked Lists

**Due date: Monday, April 20 @ 11:59PM PDT**
(Tuesday, April 21 @ 11:59PM PDT w/ slip day. If you submit your assignment late, the autograder will automatically use your slip day if you have any remaining.)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.
- [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)
- [Connecting to the lab machines remotely](https://docs.google.com/document/d/1AM888aGCagZRQYSY3wgtzboTnHHzLOEnuUgl85_oMJQ/edit)
- [Running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)

### Provided Files:
- MyLinkedList.java
- MyLinkedListTester.java

### Files to Submit:
- MyLinkedList.java
- MyLinkedListTester.java

### Goal
The goal of this assignment is to implement a Doubly Linked List, a List Iterator and create JUnit tests to verify their proper operation and implementation. To implement the Doubly Linked List, you will implement the List interface and additional methods. You will then develop a ListIterator to traverse over the Doubly Linked List. You will use existing JUnit tests and also write your own JUnit tests to test your implementation of a Doubly Linked List and the List Iterator.

**As you get started, please pay attention to the following:**
* Please read the ENTIRE write-up before getting started.
* For this homework and all homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.

### Logistics:
In EACH AND EVERY FILE that you turn in, we need the following in comments at the top of each file.  These are essential so that we can more easily process your submissions and ensure that you receive proper credit. This is a very large class with about 700 students when combining both lectures. **No name, no points**. 

NAME: <your name>  
ID: <your student ID>  
EMAIL: <your email>  

## Getting Started
We recommend that you work on the lab machines (for remote access--see instructions [here](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing) or the [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)), but if you choose to work on your own machine, make sure your code runs correctly on the ieng6 server, as we will be using the same environment to test your code.

**What is the ieng6 server?** The ieng6 server is a network of computers at UCSD. When you "ssh" to the ieng6 server, it means you are connected to a remote computer system. Your cs12 account has all of the necessary programs and correct versions installed.   
**Do I have to use the ieng6 server?** No, you can also work on your personal computer locally.  

The reason we reccomend the ieng6 server is so that your results are consistent with what we will see when we run it. One thing you could do is write and test your code locally and then before you submit check to see if your code will run on the ieng6 server.  

If you are still unsure about the ieng6 server ask a tutor or TA and they can help! Please also refer to discussion slides and provided documentation. 


#### Getting the Starter Code 

Get the starter code from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa3-LinkedList-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa3-LinkedList-starter.git`  
2.  Download the repo as a zipped folder.
![](https://i.imgur.com/SjXKMV7.png)


When you run 'javac' and 'java' make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the 'libs' directory in the starter code. This will be similar to how you compiled and executed in PA1. 

## Part 1 - Understanding and Testing First

In this homework assignment you will be developing a Doubly Linked List as well as an Iterator for this list.  Of course, these data structures are already supported in the Java Collections Framework (LinkedList and ListIterator).  But as we’ve mentioned in class, implementing data structures is one of the best ways to become a better programmer and to really understand what’s going on “under the hood”.

**1. First Understand what MyLinkedList and MyListIterator will do**

In parts 2 and 3 you’ll be implementing the classes MyLinkedList and MyListIterator, but in this part (Part 1) you will develop a tester that includes JUnit tests in order to test Java’s own implementation of LinkedList and the methods supported in that class. Later, when you finish Part 2 and Part 3, you will use this tester to test your own implementation of a Doubly Linked List and your list iterator.

In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work.  So before you start writing your tester, you should read part 2 and part 3 in order to understand what your LinkedList and ListIterator classes are supposed to do. You may refer to the documentation for [LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html) and [AbstractList](https://docs.oracle.com/javase/8/docs/api/java/util/AbstractList.html).

**2. MyLinkedListTester.java**
* Download the supplied file MyLinkedListTester.java. This is a starter file that defines a small number of tests against MyLinkedList. Observe and check how each test is written and what it tests for.
* Feel free to add more tests, as the provided starter test is not comprehensive, and your program will later be tested against our master test.
* You should make sure to include tests to check that your method throws the correct exceptions when they are expected to throw them.  There are more sophisticated ways to do this (feel free to investigate and use them), but the simple approach is to do the following (from https://github.com/junit-team/junit4/wiki/Exception-testing):

```java
@Test
public void testExceptionMessage() {
    try {
       new ArrayList<Object>().get(0);
       fail("Expected an IndexOutOfBoundsException to be thrown");
    } catch (IndexOutOfBoundsException anIooBException) {
           assertThat(anIooBoundsException.getMessage(),
                    is("Index: 0, Size: 0"));
    }
 }
```

Note: If you cannot get the assertThat to work on the lab machines (or your machine) it’s sufficient to assume that any IndexOutOfBoundsException is correct, and to simply pass in that case (i.e. fail if you do NOT enter the catch block, and leave the catch block empty).
* After final submission, we will be running more extensive tests on your code. The points awarded from these tests will make up your final grade.
* **Important**: You must not change any method names/method signatures provided in the starter code.

## Part 2 - Doubly Linked Lists

**Once you understand what the behavior of your list and its iterator is supposed to be, your next task is to implement a Doubly Linked List called MyLinkedList and a corresponding MyListIterator for your class.**

#### **⚠️DO NOT IMPORT AND USE JAVA'S BUILTIN LINKEDLIST!!! If we see that you do use these functions, you will receive a zero. You are not allowed to include any imports. I.e., If we see that you *import java.util.LinkedList;* you will not receive any credit! If we see you import any of the builtin data structures you will not receive any credit (i.e. LinkedList etc.)⚠️** 

* Download the starter file `MyLinkedList.java`. This MyLinkedList class will only be a subset of the Java Collection’s Framework LinkedList and you only need to implement the methods stubbed out in the starter code.  In addition to the stubbed out methods which you will be fully implementing yourself, make sure to also look in the source code for the string “XXX-CHANGE-XXX”. These indicate  method returns that need to be changed to reflect proper function. The method returns have been included to allow for compilation, however you should update them to reflect proper implementation. The methods to be implemented are also listed in the table below. However, the behavior of each method must exactly match that given in the LinkedList javadoc, unless specified otherwise.
* To make your life easier make sure that your class extends AbstractList. If you are not sure where `@Override` tag came from, then you need to read the online documentation on AbstractList. What happens if you do not override the `add()` method?
* There are various ways to implement a LinkedList - with only head reference, with both head and tail nodes, with or without dummy nodes. You **must use "dummy" head and tail nodes** in this homework. Refer to the diagram below to get a better idea of how the list would look like with dummy head and tail nodes.
![](https://i.imgur.com/ZUnsTPY.png)
* Since you construct your `MyLinkedList` to use sentinel (or dummy) nodes as discussed in class, and you properly throw exceptions for going out of range, you shouldn't have to worry about checking for null values at the ends of the list since the sentinel nodes are there. Think about how to use a head and tail sentinel (dummy) nodes to achieve this.
* The Java's LinkedList allows **null** objects to be inserted to the list. However, your MyLinkedList will differ from this behavior. You should not allow **null** objects to be inserted into the list and instead throw a NullPointerException if the user attempts to do so.
* When a node is removed from linked list, you **must** update the instance variables so that the removed node will not remain connected to the linked list. (Think about why this is the case, you do not want a removed object still have access to your data structure...).
* Your program will be graded using an automatic script. Please try your best to implement more JUnit tests to push your doubly linked list to be perfect even though we will not grade your MyLinkedListTester.java, because we sincerely promise that our automatic script will do our worst!
* **Constructors**: You should only need to have a single public 0-argument constructor that creates an empty list and initializes all the necessary variables.
* **Method comments**: You must provide method/class/inline comments, following the style guideline posted on Piazza. Some methods have already been provided with these comments for your reference, and you are responsible for the remaining methods.
* **Instance variables**: Do not make these instance variables private, they should have the default access modifier. Do not add any other instance variables and do not add any static variables (other than private static final variables to be used as constants).

### Node class

The following are the instance variables and methods of the **inner class** Node.

| Instance variable | Description |
|-------------|--------------------|
| `E data` | The data of the node |
| `Node next` | Reference to the next node in the linked list |
| `Node prev` | Reference to the previous node in the linked list |

| Method Name | Method Description |
|-------------|--------------------|
| `Node (E element)` | Initializes the node with element |
| `public void setPrev(Node p)` | Set p as the predecessor node. |
| `public void setNext(Node n)` | Set n as the successor node |
| `public void setElement(E e)` | Set e as the node’s data |
| `public Node getPrev()` | Return the node’s predecessor |
| `public Node getNext()` | Return the node’s successor |
| `public E getElement()` | Return the node’s data |

### MyLinkedList class

The following are the methods of the **MyLinkedList** class with a class signature `public class MyLinkedList<E> extends AbstractList<E>`.

**Tip 1**: Make sure to implement all the helper methods (like `getNth(int index)`) first!
**Tip 2**: Make sure you refer to the table below to understand the correct behavior of the method. The behavior of your method must exactly match its specification in order to pass the Master Tests.


| Instance variable | Description |
|-------------|--------------------|
| `Node head` | Reference to the sentinel head of linked list |
| `Node tail` | Reference to the sentinel tail of linked list |
| `int nelems`| Keep track of the number of nodes in the linked list |

| Method Name | Description | Exceptions to Throw |
|-------------|-------------|---------------------|
| `public MyLinkedList()` | Constructor that creates an empty list and initializes all the necessary variables. | None |
| `protected Node getNth(int index)` | a method that returns the Node at a specified index, not the content. | Throw `IndexOutOfBoundsException` when index < 0 or index >= number of elements in the list |
| `public boolean add(E data)` | Add a node at the end into this list <br/><br/> **Note**: the boolean add method will presumably always return true; it is a boolean function due to the method definition in AbstractList | Throw a `NullPointerException` if data is null |
| `public void add(int index, E data)`| Add a node into this list by index. The input index can be any integer in between zero and the number of elements (inclusive on both ends). <br/> E.g You have a doubly linked list with a node a, and you want to add a new node into the linked list by calling `add` with an index as its parameter  (Figure 2) <br/> <br/> _Example (diagram below):_<br/> head <-> a  <-> tail <br/> <br/> `add(0, new Character(b))` <br/> head <-> b <-> a <-> tail <br/> <br/> `add(2, new Character(c))` <br/> head <-> b <-> a <-> c <-> tail | Throw a `NullPointerException` if data is null <br/> <br/> Throw `IndexOutOfBoundsException` when index < 0 or index > number of elements in the list |
| `public E get(int index)` | Get data within the node at position index. | Throw `IndexOutOfBoundsException` when index < 0 or index >= number of elements in the list |
| `public E set(int index,E data)` | Set the element for the node at index to data and return the element that was previously stored in this node. | Throw `NullPointerException` if data is null <br/><br/> Throw `IndexOutOfBoundsException` when index < 0 or index >= number of elements in the list |
| `public E remove(int index)` | Remove the node from position index in this list and returns the data within the removed node. | Throw `IndexOutOfBoundsException` when index < 0 or index >= number of elements in the list |
| `public void clear()` | Remove all nodes from the list | None |
| `public boolean isEmpty()` | Determine if the list is empty | None |
| `public int size()` | Return the number of nodes being stored | None |

<br/>

**Example for Add Method:**
![](https://i.imgur.com/0OO5FdW.png)
![](https://i.imgur.com/jix1K5s.png)
![](https://i.imgur.com/Q8XZb5p.png)



**Inner class**:
In the starter code you can see that it uses a nested class (that is, a class inside a class) to represent a node in your linked list. To accomplish this, you can't declare a Node class as public, but you can include it in the same file (and even in the same class) as MyLinkedList. If it is contained within MyLinkedList<E>, then it can just use the generic label E because it is within the class.
```java
public class MyLinkedList<E> extends AbstractList<E> {
	class Node {	// this is called a Nested Class. Only usable within MyLinkedList
            E data;
            Node next;
            Node prev;
            // more code here
	}
	/* Lots more code will go here */
}
```

## Part 3 - ListIterator
Once your `MyLinkedList` class is working, you will need to create a [ListIterator](http://cs.oberlin.edu/~gr151/jdk-1.6/docs/api/java/util/ListIterator.html) which is returned by the `listIterator()` method. It is important to throughly test out your `MyLinkedList` implemenatation before working on this part, as a buggy `MyLinkedList` implemenation will give you a hard time testing your `ListIterator`.
 
The approach to create this class is to use an inner helper class (contained inside the definition of the `MyLinkedList` class). This is the approach you should use to create your ListIterator.
 
```java
class MyListIterator implements ListIterator<E> {

	// class variables here

	public boolean hasNext() {
        	// your code here
	}
	// more methods, etc.
}
```

The ListIterator is able to traverse the list by moving **a space at a time in either direction** (make certain to write a test to verify). It might be helpful to consider that the iterator has size+1 positions in the list: just after the sentinel head node (i.e. just before the first node containing data), between the 0 and 1 index, ..., just before the sentinel tail node (i.e., just after the last node containing data).  After one call to next(), the iterator is logically in the state shown below

![](https://i.imgur.com/7zLUqyu.png)

![](https://i.imgur.com/ya9VjS7.png)
![](https://i.imgur.com/lGKEhgX.png)
![](https://i.imgur.com/tx5dSLj.png)
![](https://i.imgur.com/iVLKxwj.png)
![](https://i.imgur.com/m42QHLm.png)

#### Implementation:

* Your methods should properly throw `IllegalStateException`, `NoSuchElementException`, and `NullPointerException` exceptions. This means you **DO NOT HAVE TO IMPLEMENT throwing ALL exceptions as the javadoc would indicate**. Please refer to the table below.
* You may assume that the methods of the MyLinkedList and Iterator will be tested separately. **Do not use iterator methods to write MyLinkedList methods, and vice versa**. We’d like you to practice writing them.
* You may refer the [ListIterator JavaDoc](https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html) for details, but there are notes below.

| Instance variable | Description |
|-------------|--------------------|
| `Node left,right` | Two node references to determine the iterator location. <br/> <br/> It is useful to have a number of state fields to simplify the writing of the various methods above. Since the cursor of the ListIterator exists logically between 2 nodes, It is  useful to just keep references to the next Node and the previous Node. |
| `int idx` | Int value of the index of the next node. |
| `boolean forward` | Determine the current moving direction of the iterator |
| `boolean canRemoveOrSet` | True after calling `next` or `previous` <br/> <br/> Since `set()` and `remove()` both change based on what direction was last being traversed, it makes sense to keep  a boolean flag to indicate a forward or reverse direction.|

| Method Name | Description | Exceptions to Throw |
|-------------|-------------|---------------------|
| `public MyListIterator()` | Constructor that is used to initialize the iterator. | None |
| `public boolean hasNext()` | Return true if there are more elements other than dummy nodes when going in the forward direction. | None |
| `public E next()` | Return the next element in the list when going forward, and move the iterator forward for one node. | Throw `NoSuchElementException` if there is no such element |
| `public boolean hasPrevious()` | Return true if there are more elements other than dummy when going in the reverse direction. | None |
| `public E previous()` | Return the next element in the list when going backward, and move the iterator backward for one node. | Throw `NoSuchElementException` if there is no such element |
| `public int nextIndex()` | Return the index of the element that would be returned by a call to next() <br/><br/> Return the list size if at the end of the list | None |
| `public int previousIndex()` | Return the index of the element that would be returned by a call to previous() <br/><br/> Return -1 if at the start of the list | None |
| `public void set(E x)` | Change the value in the node returned by the most recent next/previous with the new value. | Throw a `NullPointerException` if x is null. <br/><br/> Throw an `IllegalStateException` if neither next nor previous were called <br/><br/> Throw an `IllegalStateException` if add or remove have been called since the most recent next/previous |
| `public void remove()` | Remove the last element returned by the most recent call to either next/previous. <br/> <br/> Note: You cannot call `remove` right after you call `add`. | Throw an `IllegalStateException` if neither next nor previous were called <br/><br/> Throw an `IllegalStateException` if add or remove has been called since the most recent next/previous |
| `public void add(E x)` | Insert the given item into the list immediately before whatever would have been returned by a call to next(). <br/><br/> The new item is inserted before the current cursor, so it would be returned by a call to previous() immediately following. <br/><br/> The value of current index of the list iterator is increased by one. | Throw an `NullPointerException` if x is null. |
<br/>

#### Public Methods to add to MyLinkedList.java
Once you are sure your iterator is working, you should override the following methods in MyLinkedList. Each of these should just create a new `MyListIterator` and return it.

```java
ListIterator<E> listIterator()
Iterator<E> iterator()
```
Have these factory methods return your ListIterator class for the current list.
<br/>

## Testing

When testing your code, there is no restriction on what error message you use as your string input.

We expect you to thoroughly test your code in your own tester. Below is a list of tests that we will run against your solution. You can use the descriptions here as a guideline to write your own test cases. Keep in mind that some of these cases test for instance variables after a method is called, while others test for the correct exceptions being thrown. It is your responsibility to distinguish between these two types of tests based on the specifications above.

⚠️**Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn’t compile.**


| Node Test Cases | Description |
|---------------|-------------|
|Construct nodes with non `null` data| Construct nodes by passing in data| |
| Construct Node with `null` data | Construct a node by passing in `null` as the argument| |
|Get Element | Calls `getElement` on a new node| |
|Get Element after change | Calls `getElement` on node after its element is changed |
|Single Node getNext|Calls `getNext` on a single new node  | |
| Two Nodes getNext| Calls `getNext` on a node pointing to another node |
|Single Node getPrev|Calls `getPrev` on a single new node  | |
| Two Nodes getPrev| Calls `getPrev` on a node pointing to another node |
| Set integer element| Calls `setElement` with an `int` argument | |
| Set string element | Calls `setElement` with a `String` argument| |
| Set null element| Calls `setElement` with a null argument| |
|Set next node of a new node |Calls `setNext` on a newly construct node | |
|Set next node of existing node | Calls `setNext` on a node with a `next` already |  |
|Set prev node of a new node |Calls `setPrev` on a newly construct node | |
|Set prev node of existing node | Calls `setPrev` on a node with a `prev` already |  |

| MyLinkedList Test Cases | Description |
|---------------|-------------|
| Add empty | Add a string to an empty string MyLinkedList|  | |
|Add not empty | Add a string to a one element string MyLinkedList | |
|Add multiple times | Add many strings to an empty string MyLinkedList| |
| Add null | `NullPointerException` should be thrown | |
|Add always returns true | Add random ints and make sure each add returns true | |
|Add empty index 0 | Add a string to an empty string MyLinkedList using index 0| |
|Add to greater than size | Add a string to a three element string MyLinkedList using index 4, `IndexOutOfBoundsException` should be thrown | |
|Add to less than 0 | Add a string to a three element string MyLinkedList using index -1, `IndexOutOfBoundsException` should be thrown | |
|Add to middle|Add a string to a two element string MyLinkedList| | 
|Add to head|Add a string to a three element string MyLinkedList| |
|Add to tail|Add a string to a three element string MyLinkedList| |
|Add multiple times randomly|Add many strings to an empty string MyLinkedList using random indices| |
|Add null with index|`NullPointerException` should be thrown| |
|Clear non empty list|Call `clear` on a list with 3 elements| |
|Clear 1-element list|Call `clear` on a list with 1 element| |
|Clear empty list|Call `clear` on a list with 0 elements| |
|Head and tail nodes|Make sure `head` points to `tail` and their elements are `null` initially| |
|Size|Make sure initial nelems is 0| |
|isEmpty non empty list | Call `isEmpty` on a list with 3 elements"| |
|isEmpty 1-element list | Call `isEmpty` on a list with 1 element" | |
|isEmpty empty list|Call `isEmpty` on a list with 0 elements| |
|Remove  valid element|Remove an `int` in an Integer MyLinkedList| |
|Remove first element|Remove the first int in an Integer MLL| |
|Remove last element|Remove the last int in an Integer MLL| |
|Remove when list is empty|Remove index 0 in empty list| |
|Remove greater than size - 1|Remove index 3 in 3 element list| |
|Remove less than 0|Remove index -1 in 3 element list| |
|Set valid element|Set an `int` to another `int` in an Integer MyLinkedList| |
|Set first element|Set the first `int` to another `int` in an Integer MyLinkedList| |
|Set last element|Set the last `int` to another `int` in an Integer MyLinkedList| |
|Set when list is empty|Set index 0 in empty list | |
|Set greater than `size-1`|Set index 3 in 3 element list| |
|Set less than 0|Set index -1 in 3 element list| |
|Set to `null`|Set the `int` to `null` in an Integer MyLinkedList| |
|Size non-empty list|Call `size` on a list with 3 elements| |
|Size 1-element list|Call `size` on a list with 1 element| |
|Size empty list|Call size on a list with 0 elements| |

| Iterator Test Cases | Description |
|---------------|-------------|
| Add on valid index | Add to the middle of a non-empty MyLinkedList |
| Add on first index | Add on first index of a non-empty MyLinkedList |
| Add on end index | Add on last index of a non-empty MyLinkedList |  |
| Add to empty list | Add to a empty MyLinkedList |  |
| Add a null object | Add a `null` object to MyLinkedList |
| Iterator constructor elements - non-empty | Creates an `iterator` on a non-empty MyLinkedList | |
| Iterator constructor elements - empty | Creates an `iterator` on an empty MyLinkedList | |
| hasNext non-empty| Calls `hasNext` multiple times on a non-empty MyLinkedList | |
| hasNext empty | Calls `hasNext` on an empty MyLinkedList | |
| hasPrev non-empty| Calls `hasPrevious` multiple times on a non-empty MyLinkedList | |
| hasPrevious empty | Calls `hasPrevious` on an empty MyLinkedList | |
| Has next, one call | Calls `next` on a non-empty MyLinkedList | |
| Has next, two calls | Calls `next` on a non-empty MyLinkedList (twice) | |
| Doesn't have next - non-empty| Calls `next` on last node of a non-empty MyLinkedList| |
| Doesn't have next - empty| Calls `next` on node of an empty MyLinkedList | |
| nextIndex non-empty | Checks index of `iterator` on non-empty MyLinkedList | |
| nextIndex last node non-empty | Checks index of `iterator` on the last node of a non-empty MyLinkedList | |
| nextIndex empty | Checks index of iterator on an empty MyLinkedList |
| Has previous, one call | Calls `previous` on a non-empty MyLinkedList | |
| Has previous, two calls | Calls `previous` on a non-empty MyLinkedList (twice) | |
| Doesn't have previous - non-empty| Calls `previous` on first node of a non-empty MyLinkedList| |
| Doesn't have previous - empty| Calls `previous` on node of an empty MyLinkedList | |
| prevIndex non-empty | Checks index returned by `iterator` of non-empty MyLinkedList after calling `previousIndex` | |
| prevIndex first node non-empty | Checks index returned by `iterator` on the first node of a non-empty MyLinkedList after calling `previousIndex` | |
| prevIndex empty | Checks index returned by `iterator` of an empty MyLinkedList after calling `previousIndex`  |
| Remove, non-empty, forward | Remove on non-empty MyLinkedList when direction is forward| |
| Remove, non-empty, backward | Remove on non-empty MyLinkedList when direction is backward | |
| Remove non-empty, can't remove | Remove on non-empty MyLinkedList when unable to remove | |
| Remove, empty | Remove on empty MyLinkedList | |
| Set, non-empty, forward | Set new value of node on non-empty MyLinkedList when direction is forward | |
| Set, non-empty, backward | Set new value of node on non-empty MyLinkedList when direction is backward | |
| Set, empty | Set new value of node on empty MyLinkedList | |
| Set, non-empty, cannot remove | Set new value of node on non-empty MyLinkedList when cannot remove | |
| Set, null | Set `null` value of node on non-empty MyLinkedList | |

## Survey
You can find the weekly reflection survey [here](https://forms.gle/5sWuxnFTfM8SPq2t8).
Please fill out the survey, it will count towards 1 point of your PA3 score.

## Submission

**Turning in your code**

Look through your programs and make sure you've included your name, ID and login at the top of each of them.

**Files**
Along with your name and cse12sxx account name at the top of each program you wrote, you also need the appropriate comments at the top of your MyLinkedListTester.java file. We are expecting you to submit the following files to **Gradescope**:
* MyLinkedList.java
* MyLinkedListTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. **Make sure your code compiles in order to receive partial credit.**

**How your assignment will be evaluated**
* **Coding Style** (10 points) We will grade your code style thoroughly. Namely, there are a few things you must have in each file / class / method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
* **Weekly Reflection Survey** (1 point)
* **Correctness** (89 points)
    * Does your code compile? If not, you will get 0 points.
    * Does it pass all of the provided unit tests (MyLinkedListTester.java)?
        * We only provided you with a small subset of our tests, you are responsible for making sure that your program runs correctly under all possible situations.
    





