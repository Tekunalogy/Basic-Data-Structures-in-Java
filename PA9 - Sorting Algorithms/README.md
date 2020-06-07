# CSE 12 Spring 2020 PA9 - Sorting (100 Points)
**Due date: Monday, June 1st @ 11:59PM PDT**  
(Tuesday, June 2nd @ 11:59pm PDT w/ slip day)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.

- [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)
- [Connecting to the lab machines remotely](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing)
- [Running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)

### Provided Files:
- None

### Files to Submit:
- Sorting.java

### Goal
In this Programming Assignment, you will implement two sort methods, Merge Sort and Insertion Sort. You will also be answering questions related to sorting algorithms on Gradescope. 

**As you get started, please pay attention to the following:**
* Please read the ENTIRE write-up before getting started.
* For this homework , you must have the same method signatures  as defined below.


## Getting Started


#### Compile and Execute with JUnit
##### Running on UNIX based systems:

Compile: `javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. *.java`
    
Execute: `java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore <Tester File>`  

##### Running on Windows systems:

Compile: `javac -cp ..\libs\junit-4.12.jar;..\libs\hamcrest-core-1.3.jar; *.java`

Execute: `java -cp ..\libs\junit-4.12.jar;..\libs\hamcrest-core-1.3.jar; org.junit.runner.JUnitCore <Tester File>`   
 
## Part 1 - Understanding MergeSort and InsertionSort 
Before diving into the code, make sure you understand how each algorithm works. 
- InsertionSort - [ZyBooks Chapter 14.3](https://learn.zybooks.com/zybook/UCSDCSE12Spring2020/chapter/14/section/3)
- MergeSort - [ZyBooks Chapter 15.2](https://learn.zybooks.com/zybook/UCSDCSE12Spring2020/chapter/15/section/2)


You are encouraged to use the pseudocode provided by ZyBooks when applicable! Make sure though to include the requirements that are stated in this writeup. 
## Part 2 - MySort
**Once you understand the behavior, your next task is to create your own implementation of both algorithms in a single class**

*  Create a file named Sorting.java. **Sorting is a generic class whose type parameter extends Comparable.**
* There are various ways to implement these algorithms. To ensure that you won't lose points, please strictly follow the guidelines that we have listed.
* You will be writing three public methods. One for InsertionSort and two for MergeSort. 

#### Imports:

**You are only allowed this single import statement in Sorting.java.** Do **NOT** import using the wildcard (i.e. `import java.util.*`). 

* List - [java.util.Arrays](https://docs.oracle.com/javase/10/docs/api/java/util/Arrays.html)

From Arrays, feel free to use built-in methods such as `toString()` and  `copyOfRange()`.
However, do **NOT** use the built-in sort methods from Arrays. If you do, you will not recieve credit for your methods. 



### Insertion Sort
You will be implementing your own Insertion Sort alogirthm. You may base it off the ZyBook pseudocode or write your own. However, it must have the correct behavior of the sorting algorithm and you must follow these requirements: 
- Your method should be named insertionSort and take one arguement, a generic array. 

Method header: `public void insertionSort(E[] array)`

- If `array` is `null`, throw a NullPointerException. 
- If an element in `array` is `null`, throw a NullPointerException.
- At the end of each iteration, print the current state of your array.  Please see example output below. ***Important***: Your output must match our test output exactly to receive points!
- Use `Arrays.toString` to print out the array.
- Start your sorting algorithm from index 0. By starting at 0, during the first iteration nothing will happen. This is reflected in the output as the first array printed is the original array.
  


#### Example output for Insertion Sort
**Note**: These examples are not comprehensive, you will have to come up with your own test cases as well. 

Array to sort: [6, 14, 11, 2, 9, 5]
```[]
[6, 14, 11, 2, 9, 5]
[6, 14, 11, 2, 9, 5]
[6, 11, 14, 2, 9, 5]
[2, 6, 11, 14, 9, 5]
[2, 6, 9, 11, 14, 5]
[2, 5, 6, 9, 11, 14]
```

Array to sort: [45, 1, 25, 36, 7]
```[]
[45, 1, 25, 36, 7]
[1, 45, 25, 36, 7]
[1, 25, 45, 36, 7]
[1, 25, 36, 45, 7]
[1, 7, 25, 36, 45]
```

Array to sort: [5, 4]
```[]
[5, 4]
[4, 5]
```

Array to sort: [7, 6, 5, 4, 3, 2, 1]
```[]
[7, 6, 5, 4, 3, 2, 1]
[6, 7, 5, 4, 3, 2, 1]
[5, 6, 7, 4, 3, 2, 1]
[4, 5, 6, 7, 3, 2, 1]
[3, 4, 5, 6, 7, 2, 1]
[2, 3, 4, 5, 6, 7, 1]
[1, 2, 3, 4, 5, 6, 7]
```

Array to sort: [1, 2, 3, 4]
```[]
[1, 2, 3, 4]
[1, 2, 3, 4]
[1, 2, 3, 4]
[1, 2, 3, 4]
```

### MergeSort

You will be implementing your own Merge Sort alogirthm. You may base it off the ZyBook pseudocode or write your own. However, it must have the correct behavior of the sorting algorithm and you must follow these requirements: 

`public void mergeSort(E[] array)`

`public void merge(E[] array, E[] leftArray, E[] rightArray, int left, int right)`

- Merge sort is a sorting algorithm that divides a list into two halves, recursively sorts each half, and then merges the sorted halves to produce a sorted list. The recursive partitioning continues until a list of 1 element is reached, as a list of 1 element is already sorted. **Instead of dividing the list into halves, you will be dividing the list into 1:3 ratio.** E.g. if you have an integer array of [1, 2, 3, 4, 5], you will be splitting the array into [1] and [2, 3, 4, 5].
- If the size of array is less than four, apply the normal merge sort algorithm by dividing the list into halves.
- If `array` is `null`, throw a NullPointerException. 
- If an element in `array` is `null`, throw a NullPointerException.
- At the end of each iteration in `mergeSort`, print the current state of your array after you `merge`. Please see example output below. ***Important***: Your output must match our test output exactly to receive points!
- Use `Arrays.toString` to print out the array.


Note: The method signatures are slightly different than Zybook's example, **you must follow and use the method signatures given above, failure to do so will results in zero credit for this part**. We will be testing your implementation using the printed values, make sure to verify our output against the example given below.


#### Example output for Merge Sort
**Note**: These examples are not comprehensive, you will have to come up with your own test cases as well. 

Array to sort: [6, 14, 11, 2, 9, 5]
```[]
[5, 9]
[2, 5, 9]
[2, 5, 9, 11]
[2, 5, 9, 11, 14]
[2, 5, 6, 9, 11, 14]
```

Array to sort: [45, 1, 25, 36, 7]
```[]
[7, 36]
[7, 25, 36]
[1, 7, 25, 36]
[1, 7, 25, 36, 45]
```

Array to sort: [5, 4]
```[]
[4, 5]
```

Array to sort: [7, 6, 5, 4, 3, 2, 1]
```[]
[1, 2]
[1, 2, 3]
[1, 2, 3, 4]
[1, 2, 3, 4, 5]
[1, 2, 3, 4, 5, 6]
[1, 2, 3, 4, 5, 6, 7]
```

Array to sort: [1, 2, 3, 4]
```[]
[3, 4]
[2, 3, 4]
[1, 2, 3, 4]
```

Array to sort: [12, 1, 3, 15, 8, 9, 4, 16, 0, 2, 5, 6, 7, 11, 13, 22, 18]
```[]
[3, 15]
[1, 3, 15]
[1, 3, 12, 15]
[4, 9]
[4, 8, 9]
[0, 16]
[2, 5]
[18, 22]
[13, 18, 22]
[11, 13, 18, 22]
[7, 11, 13, 18, 22]
[6, 7, 11, 13, 18, 22]
[2, 5, 6, 7, 11, 13, 18, 22]
[0, 2, 5, 6, 7, 11, 13, 16, 18, 22]
[0, 2, 4, 5, 6, 7, 8, 9, 11, 13, 16, 18, 22]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15, 16, 18, 22]

```

### How to Print the Array
For grading/testing purposes, you will need to output each iteration of your sorting algorithms. In order to print an array, you can use the Java Arrays method `toString()`

```
System.out.println(Arrays.toString(array));
```
This method is used to print the given `array`. You will need it to output the current state of your array after each iteration. 




## Part 2 - Sorting
For part 2 of this assignment, you will be answering a series of questions related to sorting algorithms.

The questions can be found on Gradescope under the assignment: **Programming Assignment 9 (Part 2)**

You have an unlimited number of attempts and no time limit; however, make sure to submit before the PA deadline. 

**Note**: Follow the format guidelines carefully. You may lose points if your answer is incorrectly formatted. 

For your convenience, you may also view the questions [here](https://docs.google.com/document/d/1g4XwECE1z2zR31xWt0HX8wqmbp6msumcA5Bj2fVPsoU/edit?usp=sharing).

This worksheet is also part of your PA so feel free to ask tutors/TAs for help! They can help you with any conceptual questions, just make sure to know what you want to ask to get the best possible help. 




## Testing
For this PA, we will not be providing descriptions of test cases that we will be testing your code against. Learning how to create your own test cases is an extremely useful skill. If you need inspiration for your test cases, refer back to previous assignments.

Although your tester will not be graded for style or correctness, creating your own test cases and testing your code is the only way to be sure that your code works.

## Survey and CAPE
You can find the weekly reflection survey [here](https://forms.gle/3BmMHG6j1WBD6vF36).
Please fill out the survey, as it will count towards 1 point of your PA9 score. 

Additionally, please complete the CAPE evaluation and indicate that you did so on quesiton 2 of **Programming Assignment 9 (Part 2)**. This will count towards 1 point of your PA9 score. 


## Submission

**Turning in Your Code**

Submit the following files to **Gradescope**. Please make sure that you submit code to **Programming Assignment 9 (Part 1)**.


Submit your worksheet answers to **Programming Assignment 9 (Part 2)** on **Gradescope**.

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. 
⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How Your Assignment Will Be Evaluated**
* **Sorting Worksheet** (43 points)
* **Correctness** (50 points) 
    * Does your code compile? If not, you will get 0 points.
    * You are responsible for making sure that your program runs correctly under all possible situations.
* **Weekly Reflection Survey** (1 point)
* **CAPE Evaulation** (1 point)
* **Coding Style** (5 points)
We will grade your code style in `Sorting.java` thoroughly. Namely, there are a few things you must have in each file / class / method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation (do not intermingle spaces and tabs for indentation)
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters (Note: tabs will be counted as 4 characters toward this limit. It is a good idea to set your tab width/size to be 4)
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
