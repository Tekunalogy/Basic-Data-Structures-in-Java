# CSE 12 Spring 2020 PA2 - Generics & ArrayList (100 Points)
**Due date: Monday, April 13 @ 11:59PM PDT**  
(Tuesday, April 14 @ 11:59pm PDT w/ slip day. If you submit your assignment late, the autograder will automatically use your slip day if you have any remaining.)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.

🌟**NEW**🌟 [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)
- [Connecting to the lab machines remotely](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing)
- [Running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)

### Provided Files:
- MyList.java
- MyArrayListTester.java

### Files to Submit:
- MyArrayList.java
- MyArrayListTester.java

### Goal
In Programming Assignment 2, you will implement a data structure similar to Java's ArrayList and create JUnit tests to verify proper operation and implementation. In order to implement MyArrayList, you will implement the MyList interface and additional methods. You will use existing JUnit tests and also write your own JUnit tests to test your implementation of the MyArrayList.

**As you get started, please pay attention to the following:**
* Please read the ENTIRE write-up before getting started.
* For this homework and all homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.

### Logistics:
In EACH AND EVERY FILE that you turn in, we need the following in comments at the top of each file.  These are essential so that we can more easily process your submissions and ensure that you receive proper credit. This is a very large class with over 700 students when combining all lectures. **No name, no points**. 

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
Get the starter code from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa2-ArrayList-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa2-ArrayList-starter.git`  
2.  Download the repo as a zipped folder.
![](https://i.imgur.com/SjXKMV7.png)


When you run 'javac' and 'java' make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the 'libs' directory in the starter code. This will be similar to how you compiled and executed in PA1. 



## Part 1 - Understanding and Testing First
In this programming assignment you will be developing an ArrayList. Of course, these data structures are already supported in the Java Collections Framework (ArrayList).  But as we’ve mentioned in class, implementing data structures is one of the best ways to become a better programmer and to really understand what’s going on “under the hood”. 

You will be creating a MyArrayList class that implements the given MyList interface. Note, we did not provide starter code for MyArrayList. Read through the full writeup before you begin. Additionally, we have only provided you a few tests in MyArrayListTester.java. You will need to test your code by creating your own tests to ensure that your implemenation functions correctly. 

**First Understand what MyArrayList will do**

In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work.  So before you start writing your tester, you should read part 2 in order to understand what your ArrayList classes are supposed to do. You may refer to the documentation for [ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html). 

**Test MyArrayList with MyArrayListTester.java**
* The repository will contain MyArrayListTester.java. This is a starter file that defines a small number of tests against MyArrayList using the JUnit testing framework introduced in PA 1. Observe and check how each test is written and what it tests for.
* You are strongly encouraged to add more tests, as the provided starter test is not comprehensive, and your program will later be tested against our master test. You may refer to the [Testing section](#Testing) in this write-up when you write your unit tests.
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
Note: If you cannot get the assertThat to work on the lab machines (or your machine) it’s sufficient to assume that any IndexOutOfBoundsException is correct, and to simply pass in that case (i.e. fail if you do NOT enter the catch block, and leave the catch block empty). There is an example of this in `MyArrayListTester.java` that you can refer to.
* After final submission, we will be running more extensive tests on your code. The points awarded from our tests will make up your final grade.
* You must not change any method names/ method signatures provided in the starter code.

## Part 2 - MyArrayList
**Once you understand what the behavior of your list is supposed to be, your next task is to create your own implementation of ArrayList called MyArrayList.**
#### **⚠️DO NOT IMPORT AND USE JAVA'S BUILTIN ARRAYLIST!!! If we see that you do use these functions, you will receive a zero. You are not allowed to include any imports. I.e., If we see that you *import java.util.ArrayList;* you will not receive any credit! If we see you import any of the builtin data structures you will not receive any credit (i.e. LinkedList etc.)⚠️** 
*  Create a file named MyArrayList.java. Make sure the MyArrayList class implements MyList. **MyArrayList is a generic class.** This MyArrayList class will only be a subset of the Java Collection’s Framework ArrayList and you only **need to implement the methods stubbed out in the interface**. The methods to be implemented are also listed in the table below, along with brief description. You may also refer to the ArrayList [javadoc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html).
* There are various ways to implement an ArrayList, be sure to meet the requirements outlined for this assignment
* Your program will be graded using an automatic script. It will grade both the **MyArrayList.java** and the **MyArrayListTester.java** files. It is your responsibility to implement more JUnit tests in **MyArrayListTester.java** to push your array list to be error-free. The autograder will also run some hidden tests that you will not be able to see until after the deadline.
* **Instance variables:** There are **two** instance variables. An Object array that holds all the elements in the ArrayList and an int that keeps track of how many elements are in the ArrayList. Implement them based on specifications below.
* **Constructors:** There are **three** constructors. They should all update the appropriate instance variables based on the specifications below. 
* **Methods:** There are **ten** methods. For more description see below.
    * **Method comments:** You must provide method/class/inline comments, following the style guideline posted on Piazza. Some methods have already been provided with these comments for your reference.

### Instance variables:
**Note: Do not make these instance variables private, they should have the default access modifier. Do not add any other instance variables and do not add any static variables (other than private static final variables to be used as constants).**

* `Object[] data`:\
    The underlying data structure of the ArrayList. The index of an element in this array should correspond to the index of the element in the ArrayList. 
    
    **Note**: You don't need to check if `data` is `null` in any of your code.
    
    **Note**: `null` can be a valid element in your ArrayList. The only way of knowing if an element is valid or not is by checking the `size` variable, defined below. All invalid elements in the array should be `null`. You can find an example illustrating this in the "Example with nulls" section further down below. 

    **Note**: In some of the methods you will write, you will need to return an object of type `E`, but our `data` array is of type `Object`, so we will need to cast whatever we return to type `E` (e.g., `(E) data[0]`). If you compile with code that does this, you will see the following *warning*. 
    ```bash
    Note: MyArrayList.java uses unchecked or unsafe operations.
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
    This variable should be equal to the number of valid elements in your `data` array. A valid element in `data` is an element in your ArrayList. 
    
    **Note**: You may assume that nothing (other than possibly your own code) would change `size` to be something out of bounds of `data` (i.e., `size >= 0 && size <= data.length` will always evaluate to `true`, unless your code manually sets it to be something else).

### Constructors:
* `public MyArrayList ()`:\
Initialize the Object array with the default length of 10. The capacity of the ArrayList is the length of the array. **Note: the capacity (length of `data`) is not the same as the size (number of elements in the ArrayList, i.e., the number of _valid_ elements in the array)** 
* `public MyArrayList (int initialCapacity)`:\
Initialize the Object array with length of initialCapacity. 
If the `initialCapacity` is invalid (i.e., less than 0. 0 is a valid value of `initialCapacity`), throw an `IllegalArgumentException`.
* `public MyArrayList (E[] arr)`:\
Initialize the instance variables with the input array. This ArrayList should have capacity equal to the length of `arr`. All elements in `arr` are valid (even the `null`s), so set `size` accordingly. If `arr` is `null`, fall back to the behavior of the no-arg constructor (construct an ArrayList with the default capacity). 


### Public methods:
| Method Name | Description | Example | Exceptions to Throw |
|-------------|-------------|---------|---------------------|
| `public void checkCapacity(int requiredCapacity)`|Check if the current list has at least as much capacity as `requiredCapacity`. If it does not, double the current capacity. Note that it's possible that the current capacity is 0; if so, reset the capacity to the default capacity of 10. If the capacity is still not enough, then set the capacity to `requiredCapacity`. This method should preserve the current `size` and elements in the list. | If the current capacity is 3 and `requiredCapacity` is 4, the the capacity afterward should be 6. If the current capacity is 3 and `requiredCapacity` is 18, then capacity should be set to 18 (because 3x2=6 is less than 18). If the current capacity is 0 and `requiredCapacity` is 11, then the capacity should be set to 11 (the capacity gets reset to 10, which is less than 11, so it is set to 11). | none |
| `public int getCapacity()` | Get the amount of elements that the underlying array can possibily hold, i.e. the length of the underlying array || none |
| `public void insert(int index, E element)`| Insert an element at the specified index.<br><br>If the array is at capacity, update the capacity according to `checkCapacity()`'s rules. You may want to take a look at `checkCapacity()` method | If list is {1,2,3} and you **insert 4 at index 2**, the resulting list will be {1,2,**4**,3} <br><br> If list is initially empty {}, and you **insert 4 at index 0**, the resulting list will be {**4**}|Throw `IndexOutOfBoundsException` when index is _strictly less than 0_ or _strictly greater than size_ of the ArrayList. |
| `public void append(E element)` | Add an element at the end of the list.<br><br>If the array is at capacity, update the capacity according to `checkCapacity()`'s rules. | If list is {1,2,3} and you **append 4**, the resulting list will be {1,2,3,**4**}<br><br>If list is empty and you **append 4**, the resulting list should be {**4**} | none |
| `public void prepend(E element)` | Add an element at the beginning of the list.<br><br>If the array is at capacity, update the capacity according to `checkCapacity()`'s rules. | If list is {1,2,3} and you **prepend 4**, the resulting list will be {**4**,1,2,3}<br><br>If list is empty and you **prepend 4**, the resulting list should be {**4**} | none |
| `public E get(int index)` | Get an element at the specified index. | If list is {1,2,3}, **getting element at index 2** should return 3 |Throw `IndexOutOfBoundsException` when index is strictly less than 0 or greater than or equal to size of the ArrayList. |
| `public E set(int index, E element)` | Set the given element at the specified index and return the overwritten element. | If list is {1,2,3} and you **set element at index 1 to be 4**, the resulting list will be {1,**4**,3}  | Throw `IndexOutOfBoundsException` when index is strictly less than 0 or greater than or equal to size of the ArrayList. |
| `public E remove(int index)` | Remove and return the element at the specified index. | If list is {1,2,3} and you **remove element at index 1**, the resulting list will be {1,3} | Throw `IndexOutOfBoundsException` when index is strictly less than 0 or greater than or equal to size of the ArrayList. |
| `public int size()` | Return the number of elements that exist in the arraylist | none |
| `public void trimToSize()` | Adjust the capacity of the array to be the same as the number of elements in the array | If list is {1,2,3} with capacity 6, then the capacity of the underlying array should be updated to be 3 (In other words, the underlying array holds a maximum of 3 elements) | none |

#### Example with nulls

As stated earlier, `null` can be a valid element in your ArrayList. In this example,
**bolded elements are elements in the ArrayList (valid elements of `data`)** and non-bolded `null` represent unoccupied spots in `data`.

Statements on the left-hand side of the array should evaluate to expressions on the right-hand side. 
<pre>
...
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, <b>null</b>, null, null] // capacity is 7
list.size() -> 5 
list.append(20);
list.size() -> 6
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, <b>null</b>, <b>20</b>, null] 
list.append(null);
list.size() -> 7
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, <b>null</b>, <b>20</b>, <b>null</b>] // capacity is still 7
list.remove(6) -> null
list.size() -> 6
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, <b>null</b>, <b>20</b>, null]
list.remove(5) -> 20
list.size() -> 5
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, <b>null</b>, null, null]
list.remove(4) -> null
list.size() -> 4
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, <b>5</b>, null, null, null]
list.remove(3) -> 5
list.size() -> 3
list.data -> [<b>3</b>, <b>1</b>, <b>null</b>, null, null, null, null]
list.remove(2) -> null
list.size() -> 2
list.data -> [<b>3</b>, <b>1</b>, null, null, null, null, null]
list.remove(1) -> 1
list.size() -> 1
list.data -> [<b>3</b>, null, null, null, null, null, null]
list.remove(0) -> 3
list.size() -> 0
list.data -> [null, null, null, null, null, null, null] 
</pre>

## Part 3 - Testing
The testing cases and score distribution below should provide guidance on your own testing.

We have provided some basic test cases in the MyArrayListTester.java starter code but these are different from the Sanity Check. You should use these tests as a guide for writing unit tests, and you should also write your own additional unit tests to comprehensively test MyArrayList. 

⚠️ Note that the only tests visible to you when submitting to Gradescope are the Sanity Checks (Test Case 1). There will be hidden test cases that you will not see until after the deadline, so use the remaining test case descriptions to guide your own unit testing.

**⚠️Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn't compile.**

|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity check (1 test per method) | <ul><li>Constructors: instantiate MyArrayList with no arguments. Default length is 10 and size is 0</li></ul><ul><li>append: size increments and last value is equal to the new element</li></ul><ul><li>prepend: size increments and the first value is equal to the new element </li></ul><ul><li>insert: insert element at the end</li></ul><ul><li>remove: size decrements by one</li></ul><ul><li>get: index is in bound and returns the correct element</li></ul><ul><li>checkCapacity: required capacity is the current capacity + 1. Size stays the same and capacity doubles</li></ul><ul><li>trimToSize: size stays the same </li></ul>| 12 |
| 2 | Constructors | MyArrayList objects instantiated with the three different constructors should have the correct properties specified. If argument passed into constructor is invalid, throw the correct exception. | 5 |
| 3 | append | Test if an element is correctly inserted at the end of the ArrayList, while preserving existing elements. Size and capacity should be updated to reflect the change to the ArrayList where applicable.<ul><li>MyArrayList is empty</li><li>MyArrayList contains existing elements</li><li>MyArrayList is at capacity</li></ul> | 6 |
| 4 | prepend | Test if an element is correctly inserted at the beginning of the ArrayList, while preserving existing elements. Size and capacity should be updated to reflect the change to the ArrayList where applicable.<ul><li>MyArrayList is empty</li><li>MyArrayList contains existing elements</li><li>MyArrayList is at capacity</li></ul> | 6 |
| 5 | insert | Test if an element is inserted at correct location and if size and capacity are updated appropriately to account for the newly inserted element<ul><li>Insert at index in the range [0, size - 1]</li><li>MyArrayList is at capacity</li><li>Insert out-of-bounds</li></ul> | 15 |
| 6 | get | Test if the value returned is correct. Size and capacity should remain unchanged.<ul><li>Get an element at index in the range [0, size - 1]</li><li>Get an element at an out-of-bounds index</li></ul> | 5 |
| 7 | set | Test if the element is changed correctly. Size and capacity should remain unchanged.<ul><li>Set an element at index in the range [0, size - 1]</li><li>Set an element at an out-of-bounds index</li></ul> | 6 |
| 8 | remove | Test if the correct element is removed. Capacity remains unchanged.<ul><li>Remove an element at index in the range [0, size - 1]</li><li>Remove from an empty list</li></ul> | 15 |
| 9 | size | Test if the correct size is returned. The ArrayList should remain unchanged after call to `size()` | 1 |
| 10 | checkCapacity | Test if capacity is large enough to include an additional element.<ul><li>If it is large enough, capacity remains unchanged.</li><li>If it is not large enough, capacity is updated correctly to be double the previous amount</li><li>If capacity reaches 0, it should be reset to the default of 10</li></ul> | 6 |
| 11 | getCapacity | Test that the correct capacity size is being returned for empty and populated ArrayList. The ArrayList should remain unchanged after call to `getCapacity()` | 1 |
| 12 | trimToSize | Check that the capacity is trimmed to the correct value. <ul><li>empty list</li><li>populated list such that the capacity has not been reached</li><li>populated list such that capacity has been reached</li></ul> | 4 |
| 13 | Student tests | Passing your own JUnit tests in MyArrayListTester.java. Make sure that you write tests for all of your functions as there will be a coverage report portion for this test. Make sure to cover all cases (normal and edge) for each function (ex: Both the true and false options of the function). | 12 |

## Survey
You can find the weekly reflection survey [here](https://forms.gle/EjTHUhRuLjBF1DdP6).   
Please fill out the survey, it will count towards 1 point of your PA2 score. 

## Submission
[//]: # (TODO: Update this to Gradescope submission instructions)

**Turning in your code**

Look through your files and make sure you've included your name, ID, and cs12 account at the top of each  of them. Submit the following files to **Gradescope**:
* MyArrayList.java
* MyArrayListTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. ⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How your assignment will be evaluated**
* **Coding Style** (5 points)
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
* **Correctness** (94 points)
    * Does your code compile? If not, you will get 0 points.
    * Does it pass all of the provided unit tests (MyArrayListTester.java)?
        * We only provided you with a small subset of our tests, you are responsible for making sure that your program runs correctly under all possible situations.
    


