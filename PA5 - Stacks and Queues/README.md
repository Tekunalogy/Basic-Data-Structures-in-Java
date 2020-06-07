# CSE 12 Spring 2020 PA5 - Stacks and Queues with a Deque (100 Points)
**Due date: Monday, May 4th @ 11:59PM PDT**  
(Tuesday, May 5th @ 11:59pm PDT w/ slip day)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.

- [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)
- [Connecting to the lab machines remotely](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing)
- [Running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)

### Provided Files:
- DequeInterface.java
- QueueInterface.java
- StackInterface.java
- MyDequeTester.java

### Files to Submit:
- MyDeque.java
- MyQueue.java
- MyStack.java
- MyDequeTester.java
- MyQueueTester.java
- MyStackTester.java

### Goal
In this Programming Assignment, you will implement data structures similar to Java's Deque, Stack, and Queue and create JUnit tests to verify proper operation and implementation. In order to implement MyDeque, you will implement DequeInterface.java. In order to implement MyQueue and MyStack, you will implement the corresponding interfaces using MyDeque as the underlying structure. Additionally, you will use provided JUnit tests and also write your own JUnit tests to verify the correctness of your solution.

**As you get started, please pay attention to the following:**
* Please read the ENTIRE write-up before getting started.
* For this homework and likely all future homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.

### Logistics:
In EACH AND EVERY FILE that you turn in, we need the following in comments in your file header at the top of each file.  These are essential so that we can more easily process your submissions and ensure that you receive proper credit. This is a very large class with over 700 students when combining all lectures. **No name, no points**. 

NAME: <your name>  
ID: <your student ID>  
EMAIL: <your email>  


## Getting Started



#### Getting the Starter Code
Get the starter code from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa5-Stacks-and-Queues-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa5-Stacks-and-Queues-starter.git`  
2.  Download the repo as a zipped folder.
![](https://i.imgur.com/SjXKMV7.png)


When you run 'javac' and 'java' make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the 'libs' directory in the starter code. 

#### Compile and Execute with JUnit
##### Running on UNIX based systems:

Compile: `javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. *.java`
    
Execute: `java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore <Tester File>`  

Where `<Tester File>` could be `MyDequeTester`, `MyStackTester`, or `MyQueueTester`  

##### Running on Windows systems:

Compile: `javac -cp ..\libs\junit-4.12.jar;..\libs\hamcrest-core-1.3.jar; *.java`

Execute: `java -cp ..\libs\junit-4.12.jar;..\libs\hamcrest-core-1.3.jar; org.junit.runner.JUnitCore <Tester File>`   

Where `<Tester File>` could be `MyDequeTester`, `MyStackTester`, or `MyQueueTester`  




## Part 1 - Understanding and Testing First

You will be creating the MyDeque, MyStack, and MyQueue classes that implement the given DequeInterface, StackInterface, and QueueInterface interfaces. Note, we did not provide starter code for MyDeque, MyStack, and MyQueue. Read through the full writeup before you begin. Additionally, we have only provided you a few tests in MyDequeTester.java. You will need to test your code by creating your own tests to ensure that your implemenation functions correctly and create your own MyStackTester.java and MyQueueTester.java.

**First Understand what MyDeque, MyStack, MyQueue will do**

In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work.  So before you start writing your tester, you should read part 2-4 in order to understand what each class is supposed to do. You may refer to the documentation for [Deque](https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html), [Stack](https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html), and [Queue](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html). 

**Test MyDeque with MyDequeTester.java**
* The repository will contain MyDequeTester.java. This is a starter file that defines a small number of tests against MyDeque using the JUnit testing framework. Observe and check how each test is written and what it tests for.
* You are strongly encouraged to add more tests, as the provided starter test is not comprehensive, and your program will later be tested against our master test. You may refer to the Testing section in this write-up when you write your unit tests.
* You should make sure to also include tests to check that your method throws the correct exceptions when they are expected to throw them. There are more sophisticated ways to do this (feel free to investigate and use them), but the simple approach is to do the following ([source](https://github.com/junit-team/junit4/wiki/Exception-testing)):
```
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
* After final submission, we will be running more extensive tests on your code. The points awarded from our tests will make up your final grade.
* You must not change any method names/ method signatures provided in the starter code.

## Part 2 - MyDeque
**Once you understand what the behavior of Deque is supposed to be, your next task is to create your own implementation of of the provided DequeInterface called MyDeque.**
#### **⚠️DO NOT IMPORT AND USE JAVA'S BUILTIN DEQUE!!! If we see that you do use these functions, you will receive a zero. If we see that you *import java.util.Deque;* you will not receive any credit! Furthermore, if we see you import any of the built-in data structure implementations that are not permitted you will not receive any credit (i.e. ArrayLists, Stack, Queue etc.)⚠️** 
*  Create a file named MyDeque.java. Make sure the MyDeque class implements DequeInterface. **MyDeque is a generic class.** **You only need to implement the methods stubbed out in the interface**. The methods to be implemented are also listed in the table below, along with brief description. You may also refer to descriptions for the same methods provided by the Deque [javadoc](https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html).
* There are various ways to implement a Deque, but be sure to meet the requirements outlined for this assignment.
* **Instance variables:** There are **four** instance variables. An Object array that holds all the elements in the Deque, an int that keeps track of how many elements are in the Deque, and two other ints to keep track of the front and rear indices of the Deque. Implement them based on the specifications below.
* **Constructors:** There is **one** constructor. It should initialize the appropriate instance variables based on the specifications below. 
* **Methods:** There are **eight** methods. For more description see below.


### Instance variables:
**Note: Do not make these instance variables private, they should have the default access modifier. Do not add any other instance variables and do not add any static variables (other than private static final variables to be used as constants).**

* `Object[] data`:\
    The underlying data structure of the Deque. Treat this as a circular array. For your reference on the expected behavior, view the diagrams for the add/remove methods below. 
    
    **Note**: You don't need to check if the `data` array is `null` in any of your code.
    
    **Note**: `null` cannot be a valid element in your Deque. It is used to represent an empty space in the array. 

    **Note**: In some of the methods you will write, you will need to return an object of type `E`, but our `data` array is of type `Object`, so we will need to cast whatever we return to type `E` (e.g., `(E) data[0]`). If you compile with code that does this, you will see the following *warning*. 
    ```bash
    Note: MyDeque.java uses unchecked or unsafe operations.
    Note: Recompile with -Xlint:unchecked for details.
    ```
    The code will still work exactly the same, but if you want to hide this warning, add `@SuppressWarnings("unchecked")` above any method that does this cast. 
    ```java
    @SuppressWarnings("unchecked")
    public E someMethod() {
        return (E) data[0]; 
    }
    ```


* `int size`:\
    This variable should be equal to the number of valid elements in your `data` array. A valid element in `data` is an element in your Deque. 
    
    **Note**: You may assume that nothing (other than possibly your own code) would change `size` to be something out of bounds of `data` (i.e., `size >= 0 && size <= data.length` will always evaluate to `true`, unless your code manually sets it to be something else).

* `int rear`:
    This variable should be equal to the index of the last element in the Deque.
    
    **Note**: when the Deque is initialized, `rear` will start at index 0.

* `int front`:  
    This variable should be equal to the index of the first element in the Deque.
    
    **Note**: when the Deque is initialized, `front` will start at index 0.
    
    **Note**: `rear` and `front` can be equal to each other. This will happen when the Deque is empty or only has one element. 

### Constructor:

* `public MyDeque(int initialCapacity)`:\
Initialize the Object array `data` with length of initialCapacity. 
If the `initialCapacity` is invalid (i.e., less than 0. Note: 0 is a valid value for `initialCapacity`), throw an `IllegalArgumentException`  

**Note**: The capacity of the Deque is the length of the array. The capacity (length of `data`) is not the same as the size (number of elements in the Deque, i.e., the number of _valid_ elements in the array) 



### Public methods:
| Method Name | Description |  Exceptions to Throw |
|-------------|-------------|----------------------|
| `public int size()` | Return the number of elements that exist in the deque| None|
`public void expandCapacity()`|Double the current capacity. If the capacity is 0, set the capacity to a default value of 10. This method should preserve the current `size` and elements in the list.<br><br>Elements need to be contiguous after expanding. This means the `front` needs to be 0 and `rear` should be at size-1 or 0 if there are no elements present. | None |
| `public void addFirst(E element)` | Before trying to add the element, check if the deque is at capacity and call `expandCapacity` if necessary.<br><br>Add the specified element to the front of the deque and update `front`.<br><br>Updates `size` accordingly.|Throw `NullPointerException` when element is null. | Throw `NullPointerException` when element is null.|
| `public void addLast(E element)`| Before trying to add the element, check if the deque is at capacity and call `expandCapacity` if necessary.<br><br>Add the specified element to the rear of the deque and update `rear`.<br><br>Updates `size` accordingly.|Throw `NullPointerException` when element is null.|
| `public E removeFirst()` | Removes and returns the element at the front of the deque if there is such an element. If there are no elements in the deque, return `null`. <br><br>Updates the relevant instance variables accordingly.| None |
| `public E removeLast()` | Removes and returns the element at the rear of the deque if there is such an element. If there are no elements in the deque, return `null`.  <br><br>Updates the relevant instance variables accordingly. | None |
| `public E peekFirst()` | Returns the element at the front of the deque if there is such an element. If there are no elements in the deque, return `null`. | None |
| `public E peekLast()` | Returns the element at the rear of the deque if there is such an element. If there are no elements in the deque, return `null`. | None|

#### Removing the Final Element
Don't explicitly reset `rear` or `front` to 0 when removing the final element in the Deque. 

#### Example of Circular Behavior

As stated earlier, the element is inserted into the next available space in the array. In this example, **bolded elements are elements in the deque (valid elements of `data`)** and non-bolded `null` represent unoccupied spots in `data`.

Statements on the left-hand side of the array should evaluate to expressions on the right-hand side. 
<pre>
deque.data -> [<b>1</b>, <b>2</b>, <b>3</b>, <b>4</b>, <b>5</b>] // capacity is 5
deque.size -> 5 
deque.front -> 0
deque.rear -> 4

deque.addFirst(0);
deque.data -> [<b>1</b>, <b>2</b>, <b>3</b>, <b>4</b>, <b>5</b>, null, null, null, null, <b>0</b>]// capacity is 10
deque.size -> 6
deque.front -> 9
deque.rear -> 4

deque.removeFirst();
deque.data -> [<b>1</b>, <b>2</b>, <b>3</b>, <b>4</b>, <b>5</b>, null, null, null, null, null] // capacity is still 10
deque.size -> 5
deque.front -> 0
deque.rear -> 4

deque.removeFirst();
deque.data -> [null, <b>2</b>, <b>3</b>, <b>4</b>, <b>5</b>, null, null, null, null, null] // capacity is still 10
deque.size -> 4
deque.front -> 1
deque.rear -> 4

deque.addLast(6);
deque.data -> [null, <b>2</b>, <b>3</b>, <b>4</b>, <b>5</b>, <b>6</b>, null, null, null, null] // capacity is still 10
deque.size -> 5
deque.front -> 1
deque.rear -> 5
</pre>

**Note**: This example only shows how `front` wraps around. `rear` should wrap around too!!!

**Tip**: When determining the index for `front` and `rear`, you can try using the modulus operator (%) to wrap around to the next available space. You are **not** required to handle the wrapping in this way, you may use whichever implementation as long as the behavior is correct.

#### Diagrams for MyDeque

The following Diagram shows one case where only 4 spaces in the array are occupied in the middle of the array. In this case, when we call `addFirst`, the element will be added to index 0. When we call `removeFirst`, we will remove the element at index 1. When we call `addLast`, the element will be added to index 5. And when we call `removeLast`, we will remove the element at index 4. 

**Note**: Blue boxes represent a value is stored while white boxes represent `null`.

![](https://i.imgur.com/FQaE45w.png)

MyDeque can also run into the situation where `front` > `rear`. This means that the front has wrapped around to the end of the array OR the rear has wrapped around to the beginning of the array. The following diagrams show how `addFirst`, `removeFirst`, `addLast`, and `removeLast` should behave in this situation. <br/>
![](https://i.imgur.com/fOCX3qv.png)
<br/><br/>
### `addFirst` when `front` > `rear`
![](https://i.imgur.com/w6ki32y.png)
<br/><br/>
### `removeFirst` when `front` > `rear`
![](https://i.imgur.com/CziYGNG.png)
<br/><br/>
### `addLast` when `front` > `rear`
![](https://i.imgur.com/bqeiepm.png)
<br/><br/>
### `removeLast` when `front` > `rear`
![](https://i.imgur.com/XuT5gsQ.png)


## Part 3 - MyStack

An important aspect of Abstract Data Types is that the definition of an ADT only describes the functionality required and not how the implementation is to be accomplished. Adhering to the idea that an ADT is implemention-independent, your next task is to constuct an implementation of the Stack ADT by using a Deque as your underlying data structure. 

You will accomplish this by creating a file called MyStack.java that contains generic class `MyStack` that implements the Stack ADT, which for our purposes, is defined in the generic interface, StackInterface.java. The details for the `MyStack` class are conveniently described for you below.

### Instance variables:

* `MyDeque<E> theStack`:\
    The underlying data structure of MyStack. You will use your implementation of the Deque ADT (in the form of a `MyDeque<E>` object) to manage your data.
    
    
    **Note**: `null` cannot be a valid element in the `MyDeque<E>` object, which is your underlying data structure. As such, `null` can't be valid element for `theStack` either.

**Note: You should not make this instance variable private, and instead define it with the default access modifier. You should not need to create any other instance variables or any static variables.**

### Constructor:

* `public MyStack (int initialCapacity)`:

    Initialize a `MyDeque<E>` object with the parameter initialCapacity. 
    
### Public Methods:

Each of these methods are already defined in StackInterface.java. You need to complete implementation for these methods using the `MyDeque<E>` object created in your constructor. There are a couple of ways to implement each method using the `MyDeque<E>` object. As long as the implementation you choose is consistent with all the other `MyStack` methods and the `MyStack` works as you would expect an implementation of the Stack ADT to work (LIFO ordering), you can choose anyway to use the `MyDeque<E>` object.

| Method Name | Description | Exceptions to Throw |
|-------------|-------------|---------------------|
| `public boolean empty()` | Checks whether or not the stack is empty. If it is empty, return true. | none|
| `public void push(E e)` | Pushes the specified element to the top of the stack.  | none |
| `public E pop()` | Removes an element from the top of the stack. Returns the removed element, or `null` if there was no such element. | none |
| `public E peek()` | Returns the element at the top of the stack.  | none |

**Note**: When using a method from the MyDeque class, only use the methods outlined in the DequeInterface.java (i.e. removeFirst, addLast, size, etc). If you wrote any additional helper methods in MyDeque, refrain from using them when implementing MyStack. 

## Part 4 - MyQueue

Similar to the Stack ADT, the Queue ADT is implementation-independent. It turns out, we can also implement a Queue using our Deque. To do so, create a file named MyQueue.java. In this file, write the generic class `MyQueue<E>` to implement the given `QueueInterface<E>` interface in QueueInterface.java. 

### Instance variables:

* `MyDeque<E> theQueue`:\
    The underlying data structure of `MyQueue`. You will again use your implementation of the Deque ADT (in the form of a `MyDeque<E>` object) to manage your data.
    
    
    **Note**: `null` cannot be a valid element in the `MyDeque<E>` object, which is your underlying data structure. As such, `null` can't be valid element for `theQueue` either.

**Note: You should not make this instance variable private, and instead define it with the default access modifier. You should not need to create any other instance variables or any static variables.**

### Constructor:

* `public MyQueue (int initialCapacity)`:

    Initialize a `MyDeque<E>` object with the parameter initialCapacity. 
    
### Public Methods:

Each of these methods are already defined in QueueInterface.java. You only need to complete implementation for these methods in your `MyQueue` class using the `MyDeque<E>` object created in your constructor. Again, there are a couple ways to use the `MyDeque<E>` object to implement each of these methods. Make sure whatever implementation you choose ensures that the proper behavior outlined by the Queue ADT is ahered to (i.e., FIFO ordering). 

| Method Name | Description | Exceptions to Throw |
|-------------|-------------|---------------------|
| `public boolean empty()` | Checks whether or not the stack is empty. If it is empty, return true. | none|
| `public void enqueue(E e)` | Adds the specified element to the back of queue.  | none |
| `public E dequeue()` | Removes an element from the front of the queue. Returns the removed element, or `null` if there was no such element. | none |
| `public E peek()` | Returns the element at the front of the queue. | none |

**Note**: When using a method from the MyDeque class, only use the methods outlined in the DequeInterface.java (i.e. removeFirst, addLast, size, etc). If you wrote any additional helper methods in MyDeque, refrain from using them when implementing MyQueue. 


## Part 5 - Testing
You will need to create two additional files:
- MyStackTester.java
- MyQueueTester.java

Your program will be graded using an automatic script. You will be graded for points on the **MyDeque.java**, **MyStack.java**, and **MyQueue.java** files. The Gradescope autograder will also run some hidden tests that you will not be able to see until after the deadline. 

We will also provide a code coverage score for **MyDequeTester.java**, **MyStackTester.java**, and **MyQueueTester.java** on Gradescope that will be worth 0 points. Code coverage checks that your tests account for all of the different cases for each of the methods, but does not verify that your tests are correct or that your solutions are correct. So, just because your code passes the code coverage, this doesn't necessarily mean your solution is error free. It is your responsibility to implement more JUnit tests in **MyDequeTester.java** and create **MyStackTester.java** and **MyQueueTester.java** to push your code to be error-free. 

**⚠️Important⚠️** When grading your `MyStack` and `MyQueue` classes, we will use our solution code for `MyDeque`. This way, if your `MyDeque` class isn't completely correct you won't also lose points for `MyStack` and `MyQueue`. With this in mind, when using a method from the `MyDeque` class, only use the methods outlined in DequeInterface.java (i.e. `removeFirst`, `addLast`, `size`, etc). If you wrote any additional helper methods in your `MyDeque` class, refrain from using them when implementing `MyStack` and `MyQueue`. 

The testing cases and score distribution below should provide guidance on your own testing.

We have provided some basic test cases in the MyDequeTester.java starter code but these are different from the Sanity Check. You should use these tests as a guide for writing unit tests, and you should also write your own additional unit tests to comprehensively test your MyDeque class. 



**⚠️Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn't compile.**


### MyDeque

|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity Check | <ul><li>Constructor: initialize MyDeque with capacity 10</li></ul><ul><li>expandCapacity: expandCapacity with several elements at the start of the array</li></ul><ul><li>addFirst: deque containing several elements in the middle of the array</li></ul><ul><li>addLast: deque containing several elements in the middle of the array</li></ul><ul><li>removeFirst: deque containing several elements in the middle of the array</li></ul><ul><li>removeLast: deque containing several elements in the middle of the array</li></ul><ul><li>peekFirst: deque containing several elements at the start of the array</li></ul><ul><li>peekLast: deque containing several elements at the start of the array</li></ul>| 8 |
| 2 | Constructor | MyDeque objects instantiated with the constructor should have the correct properties specified. | 3 |
| 3 | size | Test if the correct size is returned. The Deque should remain unchanged after a call to size() | 1 |
| 4 | expandCapacity | Tests if the capacity is doubled. <ul><li>several elements are in MyDeque</li><li>expand the capacity when front > rear</li><li>MyDeque is at different states (full capacity, partially full, one element etc)</li></ul> | 7 |
| 5 | addFirst | Test if an element is inserted at correct location and if size, front, and capacity are updated appropriately to account for the newly inserted element<ul><li>add a null element</li><li>MyDeque is at capacity</li><li>add multiple times</li></ul> | 7 |
| 6 | addLast | Test if an element is inserted at correct location and if size, rear, and capacity are updated appropriately to account for the newly inserted element<ul><li>add a null element</li><li>MyDeque is at capacity</li><li>add multiple times</li></ul> | 7 |
| 7 | removeFirst| Test if the correct element is removed. Capacity remains unchanged and size and front are updated.<ul><li>Remove when front is at varying locations</li><li>Remove from an empty deque</li></ul> | 7 |
| 8 | removeLast | Test if the correct element is removed. Capacity remains unchanged and size and rear are updated.<ul><li>Remove when rear is at varying locations</li><li>Remove from an empty deque</li></ul> | 7 |
| 9 | peekFirst | Test if the correct value at front is returned. The MyDeque should remain unchanged after call to `peekFirst()` | 3 |
| 10 | peekLast | Test if the correct value at rear is returned. The MyDeque should remain unchanged after call to `peekLast()` | 3 |


### MyStack
|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity check | <ul><li>Constructor: Initialize stack with capacity 10</li></ul><ul><li>empty: check stack with size 0</li></ul><ul><li>push: empty stack </li></ul><ul><li>pop: stack with serveral elements</li></ul><ul><li>peek: stack with several elements</li></ul>| 5 |
| 2 | Constructor | MyStack objects instantiated with the constructor should have the correct properties specified. | 1 |
| 3 | empty | Test if the stack is empty. The stack should remain unchanged after a call to `empty()`| 1.5 |
| 4 | push | Test if an element is correctly inserted into the stack, while preserving existing elements. <ul><li>MyStack is empty</li><li>MyStack contains existing elements</li>| 2 |
| 5 | pop| Test if the correct element is returned<ul><li>MyStack is empty</li><li>MyStack has existing elements</li><li>call pop multiple times</li></ul> | 2 |
| 6 | peek | Test if the correct value is returned. The MyStack should remain unchanged after call to `peek()`  | 1.5 |
|7 | holistic testing | Test if the overal functionality of MyStack is correct using push, pop, and peek  | 5 |


### MyQueue
|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity check | <ul><li>Constructor: Initialize queue with capacity 10</li></ul><ul><li>empty: check queue with size 0</li></ul><ul><li>push: empty queue</li></ul><ul><li>pop: queue with serveral elements</li></ul><ul><li>peek: queue with several elements</li></ul>| 5 |
| 2 | Constructor | MyQueue objects instantiated with the constructor should have the correct properties specified. | 1 |
| 3 | empty | Test if the queue is empty. The queue should remain unchanged after a call to `empty()`| 1.5 |
| 4 | enqueue | Test if an element is correctly inserted into the queue, while preserving existing elements. <ul><li>MyQueue is empty</li><li>MyQueue contains existing elements</li> | 2 |
| 5 | dequeue | Test if the correct element is returned<ul><li>MyQueue is empty</li><li>MyQueue has exisitng elements</li><li>call dequeue multiple times</li></ul>  | 2 |
| 6 | peek | Test if the correct value is returned. The MyQueue should remain unchanged after call to `peek()`  | 1.5 |
| 7 | holistic testing | Test if the overal functionality of MyQueue is correct using enqueue, dequeue, and peek  | 5 |

## Survey
You can find the weekly reflection survey [here](https://forms.gle/uBFWBdsPar1FnH2G9).   
Please fill out the survey, as it will count towards 1 point of your PA5 score. 

## Submission

**Turning in your code**

Look through your files and make sure you've included your name, ID, and cs12 account at the top of each  of them. Submit the following files to **Gradescope**:
* MyDeque.java
* MyStack.java
* MyQueue.java
* MyDequeTester.java
* MyStackTester.java
* MyQueueTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. ⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How your assignment will be evaluated**
* **Coding Style** (10 points)
We will grade your code style thoroughly. Namely, there are a few things you must have in each file / class / method:

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
    * Does it pass all of the provided unit tests (MyDequeTester.java)?
        * We only provided you with a small subset of our tests, you are responsible for making sure that your program runs correctly under all possible situations.
    


