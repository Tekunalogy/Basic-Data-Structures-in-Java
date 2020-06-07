# CSE 12 Spring 2020 PA8 - Binary Search Tree (100 Points)
**Due date: Monday, May 25 @ 11:59PM PDT**  
(Tuesday, May 26 @ 11:59pm PDT w/ slip day. If you submit your assignment late, the autograder will automatically use your slip day if you have any remaining.)

## Provided Files:
None

## Files to Submit:
- MyBST.java (required)
- MyBSTEC.java (optional, for extra credit)
- MyBSTTester.java (optional)

## Goal:
In Programming Assignment 8, we will be implementing a binary search tree (`MyBST`) with a static nested class `MyBSTNode`. We'll also be having optional extra credit (yay!), which will have a few inner classes -  `MyBSTNodeIterator`, `MyBSTKeyIterator`, and `MyBSTValueIterator`.

**As you get started, please pay attention to the following:**
* Please read the **ENTIRE write-up** before getting started.
* For this homework and all homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.


## Getting Started
We strongly recommend that you work on the lab machines (for remote access--see instructions [here](https://docs.google.com/document/d/1AM888aGCagZRQYSY3wgtzboTnHHzLOEnuUgl85_oMJQ/edit?usp=sharing)), but if you choose to work on your own machine, make sure your code runs correctly on ieng6, as that is where we will be testing it. 

**What is the ieng6 server?** The ieng6 server is a network of computers at UCSD. When you "ssh" to the ieng6 server, it means you are connected to a remote computer system. Your cs12 account has all of the necessary programs and correct versions installed.   
**Do I have to use the ieng6 server?** No, you can also work on your personal computer locally.  

The reason we reccomend the ieng6 server is so that your results are consistent with what we will see when we run it. One thing you could do is write and test your code locally and then before you submit check to see if your code will run on the ieng6 server.  

If you are still unsure about the ieng6 server ask a tutor or TA and they can help! Please also refer to discussion slides and provided documentation. 

### Getting the Starter Code
Get the starter code (there isn't any other than the JUnit .jar files) from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa8-BinarySearchTree-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa8-BinarySearchTree-starter.git`  
2.  Download the repo as a zipped folder.

![](https://i.imgur.com/SjXKMV7.png)

When you run `javac` and `java` make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the `libs` directory in the starter code. This will be similar to how you compiled and executed in PA1, PA2, PA3, PA4, PA5, PA6, and PA7. 


## Part 1 - Understanding and Testing First
In this programming assignment, you will be implementing the `MyBST` and  `MyBSTNode` classes. If you choose to do the extra credit, you will also be implementing the `MyBSTEC`, `MyBSTNodeIterator`, `MyBSTKeyIterator`, and `MyBSTValueIterator` classes. 

**First, Understand what our classes will do**

In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work. So before you start writing your tester, you should read through the rest of the writeup in order to understand what each class is supposed to do. 

By now, you should have plenty of practice writing JUnit tests. You should write all your testers in a file called `MyBSTTester.java`. This file will not be graded, but we'll provide the usual check against the solution code at submission time. 

---

## Part 2 - MyBST.java
```java
public class MyBST<K extends Comparable<K>, V> {}
static class MyBSTNode<K, V> {}
```

We are writing two classes in this file: `MyBST` and `MyBSTNode`. `MyBSTNode` will be a static nested class of `MyBST`.

Here, `K` will be the type for our key, which is what we'll use for sorting/ordering the tree, and `V` is the type for our value, the actual data stored. Note the default access modifiers of `MyBSTNode`. 

Our binary search tree has the property that all nodes in the left subtree of a node `node` will have a key smaller than `node.key` and all nodes in the right subtree of node `node` will have a key greater than `node.key`. **We will not be allowing duplicate keys in our tree**, but we can have many different keys associated with the same value! For our concept of smaller/greater/equal keys, we are again using our old friend, `compareTo()`. 

---

## Part 3 - MyBSTNode
```java
static class MyBSTNode<K, V> {}
```

This class is a static nested class of the `MyBST` class. Objects of this class represent nodes of the tree and contain a key for sorting, a value, and pointers to the children and parent of this object. 

---

### MyBSTNode Instance Variables
```java
K key; 
V value; 
MyBSTNode<K, V> parent; 
MyBSTNode<K, V> left; 
MyBSTNode<K, V> right; 
```
- **Do not change these instance variables and do not add any extra instance variables. You can still have `private static final` variables for your constants, but do not add any other static variables.**  
- Note that all these instance variables have the default access modifier. Similar to PA 7, do not directly access these instance variables - as this might affect our autograder - always use their appropriate getters and setters. 

#### `K key`
- This represents the key that we are using to sort our nodes. 
- This key cannot be `null` and we are not supporting duplicate keys (duplicates will be defined by `compareTo()` returning that the keys are equal). 

#### `V value`
- This represents the data stored by this `MyBSTNode`. 
- This value is allowed to be `null`. 

#### `MyBSTNode<K, V> parent`
- This stores a reference to the parent node of this `MyBSTNode`. 
- If this node has no parent, then this will be `null`. 

#### `MyBSTNode<K, V> left`
- This stores a reference to the left child of this `MyBSTNode`. 
- If this node has no left child, then this will be `null`. 

#### `MyBSTNode<K, V> right`
- This stores a reference to the right child of this `MyBSTNode`. 
- If this node has no right child, then this will be `null`. 

---

### MyBSTNode Constructor
```java
public MyBSTNode(K key, V value, MyBSTNode<K, V> parent); 
```

- This is the only constructor for our node. It should initialize the `key`, `value`, and `parent` instance variables appropriately using a shallow copy. 
- `left` and `right` will be initialized as `null`. 
- Note, we **do not** set the left or right pointer of `parent` here.

---

### MyBSTNode Methods
```java
public K getKey(); 
public V getValue(); 
public MyBSTNode<K, V> getParent(); 
public MyBSTNode<K, V> getLeft(); 
public MyBSTNode<K, V> getRight(); 
public void setKey(K newKey); 
public void setValue(V newValue); 
public void setParent(MyBSTNode<K, V> newParent); 
public void setLeft(MyBSTNode<K, V> newLeft); 
public void setRight(MyBSTNode<K, V> newRight); 
public MyBSTNode<K, V> successor(); 
```

For all of these methods, do shallow copies. 

#### `public K getKey()`
- Returns the `key` of this node. 

#### `public V getValue()`
- Returns the `value` of this node. 

#### `public MyBSTNode<K, V> getParent()`
- Returns the `parent` of this node (even if it is `null`). 

#### `public MyBSTNode<K, V> getLeft()`
- Returns the `left` child of this node (even if it is `null`). 

#### `public MyBSTNode<K, V> getRight()`
- Returns the `right` child of this node (even if it is `null`). 

#### `public void setKey(K newKey)`
- Change the `key` of this node to be the argument key. 

#### `public void setValue(V newValue)`
- Change the `value` of this node to be the argument value. 

#### `public void setParent(MyBSTNode<K, V> newParent)`
- Change the `parent` of this node to be the argument node. 

#### `public void setLeft(MyBSTNode<K, V> newLeft)`
- Change the `left` child of this node to be the argument node. 

#### `public void setRight(MyBSTNode<K, V> newRight)`
- Change the `right` child of this node to be the argument node. 

#### `public MyBSTNode<K, V> successor()`
- This method returns the node with the next largest key after the key of this node. If there is no larger key, return `null`. 
- Note that this is not necessarily the same as the "successor" written about in the pseudocode in Zybooks, which only cares about a successor for replacing a removed node that has two children. 
- Every node in our BST should/will already have the proper connections (parent and child references) with our sorted key property, so make sure to use this to figure out how to write this method. We suggest that you draw out a tree and try to create an algorithm such that your algorithm works for all nodes in that tree. 


---

## Part 4 - MyBST
```java
public class MyBST<K extends Comparable<K>, V> {}
```

This class is the public class of this file. It represents a binary search tree where sorting is done based on the keys. It is **not** a self-balancing tree. 

---

### MyBST Instance Variables
```java
MyBSTNode<K, V> root; 
int size; 
```
- **Do not change these instance variables and do not add any extra instance variables. You can still have `private static final` variables for your constants, but do not add any other static variables.**  
- Note that both of these instance variables have the default access modifier. 

#### `MyBSTNode<K, V> root`
- This is a reference to the root node of our tree. If our tree is empty, then this will be `null`. 

#### `int size`
- This represents the number of nodes in our tree. You should update `size` appropriately whenever an insertion or removal happens. 

--- 

### MyBST Constructor
```java
public MyBST(); 
```

- This constructor creates an empty BST. Initialize `root` and `size` accordingly. 
- (Note for learning: this constructor is exactly the same as if we defined no constructor and allowed Java to insert a no-arg constructor for us. Remember from your Java class that all instance variables have the default value of `null` for objects and the equivalent of `0` for primitives.)

---

### MyBST Methods
```java
protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node); 
public int size(); 
public V insert(K key, V value); 
public V search(K key); 
public V remove(K key); 
public ArrayList<MyBSTNode<K, V>> inorder(); 
```

All of these methods are required to be implemented iteratively, meaning that all traversals to reach some node should be done without recursive calls but with loops. **Do not use recursion except where it is explicitly stated to be allowed**. You will earn a **0** in all relevant categories if we find that you have implemented them using recursion. For most recursive implementations, you would use a recursive helper method, so if you aren't using a recursive helper method, you're probably implementing the operations iteratively. 

#### `protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node)`
- If `node` is `null`, return `null`. 
- This method should function exactly as if we called the `successor()` method on `node`. 
- In your other methods, you can use either the `successor()` from `MyBSTNode` or this one, it won't affect our autograder. 

#### `public int size()`
- Return the size (number of elements) of this tree. 

#### `public V insert(K key, V value)`
- If `key` is null, throw a `NullPointerException`. 
- Insert a new node containing the arguments `key` and `value` into the binary search tree according to the binary search tree properties.
- If a node with an equal key to `key` already exists in the tree, replace the value in the current node with `value` (note that we won't change the key of the preexisting node).
- Return the value replaced by the new `value`. If the insertion did not lead to a replacement (i.e., it created a new node as a leaf, note that if the tree only has a root then the root is a leaf), return `null` instead. Note that we've overloaded the meaning of returning `null` as it can mean that we've inserted a node with a new key into the tree or that we've replaced the value of a node whose original value was `null`. 
- This is not a self-balancing tree, meaning the shape of the tree should not change after an insertion, other than there possibly being a new leaf. 
<p align="center">
  <img src="https://i.imgur.com/sdgAp4l.png">
  <br>
  <b>insert() Figure 0</b>: Call to <code>insert(100, "Niema")</code>. Begin insertion of a node with key = 100 and value = "Niema".
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/Icjc0j8.png">
  <br>
  <b>insert() Figure 1</b>: Go down the BST to find the place of insertion. First compare with the root. Since the key of the root (20) is less than 100, we go right, as indicated by the green arrow.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/MB2idsY.png">
  <br>
  <b>insert() Figure 2</b>: We continue to go down the BST to find the place of insertion. Since the key of the right child of the root (95) is less than 100, we continue to go right, as indicated by the green arrow.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/yetYDM8.png">
  <br>
  <b>insert() Figure 3</b>: Now we want to go left because the key at the current node (120) is greater than 100.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/34VsQCd.png">
  <br>
  <b>insert() Figure 4</b>: We continue to go left because the key at the current node (105) is greater than 100.
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/pyGRQfE.png">
  <br>
  <b>insert() Figure 5</b>: However, because a node with key equal to 100 already exists in the BST, we will replace the original value ("Sander") with the new value ("Niema").
</p>

<br><br><br>


#### `public V search(K key)`
- Search for a node with key equal to `key` and return the value associated with that node. The tree structure should not be affected by this. 
- If no such node exists, return `null` instead. Again, note that we've overloaded the meaning of returning `null`. 
- Note that `key` might be `null`. We'll assume that no other key is equal to `null` (even though the `compareTo()` of type `K` might define this differently). This means that `search(null)` should always return `null`. 

#### `public V remove(K key)`
- Search for a node with key equal to `key` and return the value associated with that node. The node should be removed (disconnected) from the tree and all references should be fixed, if needed. 
- When removing the node, if the node is not a leaf node, then we may have to make some changes to the tree to maintain its integrity. Make sure to fix all relevant references to parents/children. 
  - For leaf nodes, no replacement is needed. 
  - If the node has a single child, move that child up to take its place. 
  - If the node has two children, then we will just overwrite the data at the node we plan to remove with the data from the **successor**, saving us the need to reconnect all the nodes if we had completely removed the node from the tree. Then remove the successor from the tree (this does not need to be done iteratively, you can do this with a recursive call). **Note**: think about what you would have to do if you were to entirely replace the node rather than to just replace the key/value in the node. 
- If no such node exists, return `null` instead. Again, note that we've overloaded the meaning of returning `null`. 
- Note that `key` might be `null`. We'll assume that no other key is equal to `null` (even though the `compareTo()` of type `K` might define this differently). This means that `remove(null)` should always return `null` and not remove any nodes. 

<p align="center">
  <img src="https://i.imgur.com/WcVav2E.png">
  <br>
  <b>remove() for node with one child Figure 0</b>: Call to <code>remove(11)</code>. Before this step, we've already iterated down the tree to find where the node with key 11 is. Begin removal of the node with key = 11, and value = "Joe". 
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/c6qOM8q.png">
  <br>
  <b>remove() for node with one child Figure 1</b>: Because the node we want to remove only has one child (the left child), we simply replace the node with its only child. 
</p>

<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/rgADvxv.png">
  <br>
  <b>remove() for node with one child Figure 2</b>: We disconnect the original node from the tree and fix the parent pointer of the replacement (8:"Gerald") and child pointer of the parent (12:"Haytham"), completing the removal and replacement. 
</p>
<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/iAGVpp9.png">
  <br>
  <b>remove() for node with two children Figure 0</b>: Begin removal of the node with key = 95 and value = "Paul". Again, note that the steps to locate this node are not shown. 
</p>
<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/DTdVNBf.png">
  <br>
  <b>remove() for node with two children Figure 1</b>: Since this node has two children, we want to first find its successor (the blue node) and replace the data (key and value) in this node with its successor's data. 
</p>
<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/oUz4cLx.png">
  <br>
  <b>remove() for node with two children Figure 2</b>: Now we replace the data (key and value) in this node with its successor's data, keeping all the parent/child references. 
</p>
<br><br><br>

<p align="center">
  <img src="https://i.imgur.com/mIrALd8.png">
  <br>
  <b>remove() for node with two children Figure 3</b>: The final step is to remove the successor and now we are done with removing a node with two children.
</p>
<br><br><br>



#### `public ArrayList<MyBSTNode<K, V>> inorder()`
- Do an in-order traversal of the tree, adding each node to the end of an `ArrayList`, which will be returned. 
- After the entire traversal, this `ArrayList` should contain all nodes in the tree. These nodes will be sorted by the key order (with the smallest key at index 0) due to the order of the traversal. 
- If the tree is empty, an empty (not `null`) `ArrayList` should be returned. 
- Note, it might be helpful to think about what the `successor()` method does and how this relates to an in-order traversal. 

---

## Part 5 - MyBSTEC.java - EXTRA CREDIT
```java
public class MyBSTEC<K extends Comparable<K>, V> extends MyBST<K, V> {}
abstract class MyBSTNodeIterator<T> implements Iterator<T> {}
class MyBSTKeyIterator extends MyBSTNodeIterator<K> {}
class MyBSTValueIterator extends MyBSTNodeIterator<V> {}
```
Parts 5 - 9 are all extra credit. The specific material of this extra credit is not required knowledge, although all concepts used are concepts from this class. 

Code for this portion should be written in a file called `MyBSTEC.java`. 

We are writing four classes: `MyBSTEC`, `MyBSTNodeIterator`, `MyBSTKeyIterator`, and `MyBSTValueIterator`. `MyBSTIterator` will be an  inner (abstract) class of `MyBST`. `MyBSTKeyIterator` and `MyBSTValueIterator` will be inner classes of `MyBST` that extend from `MyBSTKeyIterator`. 

Some of the design in this file may not be obvious. See the [appendix](#appendix) for details. We'll also be linking to the appropriate points at the appendix as needed, but as a whole, it may make more sense to read the appendix after you have implemented all the functionality. We ask that you don't ask any questions about the extra credit portions at tutor hours (you will gain more by thinking about it yourself) and that you save your Piazza questions, other than questions about clarification, until after the late submission deadline for PA 9 (week 10) so that we can save some tutor bandwidth. 

---

## Part 6 - MyBSTEC - EXTRA CREDIT
```java
public class MyBSTEC<K extends Comparable<K>, V> extends MyBST<K, V> {}
```

This class is an extension of the `MyBST` class that we wrote for the non-EC part of the PA. See [appendix A](#appendix-a-why-separate-classes) and [appendix B](#mybstec-class-declaration) for more details about why we split the EC and non-EC classes and for more details about this class declaration. 

---

### MyBSTEC Constructors

We won't define any constructor for the `MyBSTEC` class, we'll just use the no-arg constructor inserted by Java. 

---

### MyBSTEC Instance Variables 

We won't be using any extra instance variables. The only variables we need are inherited. As usual, do not add any of your own instance (or static) variables, but `private static final` variables are fine. 

---

### MyBSTEC Methods
```java
public MyBSTKeyIterator getKeyIterator();
public MyBSTValueIterator getValueIterator();
```

The only methods we will define in this class simply give us back the appropriate iterator. 

---

#### `public MyBSTKeyIterator getKeyIterator()`
- Return a `MyBSTKeyIterator` that starts at the node in this tree with the smallest key. If there is no node with the smallest key, the iterator should start at `null`. Continue reading to learn more about this iterator. 

#### `public MyBSTValueIterator getValueIterator()`
- Return a `MyBSTValueIterator` that starts at the node in this tree with the smallest key. If there is no node with the smallest key, the iterator should start at `null`. Continue reading to learn more about this iterator. 

---

## Part 7 - MyBSTNodeIterator - EXTRA CREDIT
```java
abstract class MyBSTNodeIterator<T> implements Iterator<T> {}
```

This class is an inner class of the `MyBSTEC` class. This iterator iterates through the tree in order, moving to the node with the next largest key upon each advancement. See [appendix C](#appendix-C-extensions) and [appendix B](#mybstnodeiterator-class-declaration) for extensions and more details about the class declaration. 


---

### MyBSTNodeIterator Instance Variables
```java
MyBSTNode<K, V> next; 
MyBSTNode<K, V> lastVisited; 
```
- **Do not change these instance variables and do not add any extra instance variables. You can still have `private static final` variables for your constants, but do not add any other static variables.**  
- Note that all these instance variables have the default access modifier. 

#### `MyBSTNode<K, V> next`
- This represents the next node that we will iterate to. If there is no next node, `next` will be `null`. 

#### `MyBSTNode<K, V> lastVisited`
- This represents the most recent node that we visited. In other words, this is the current node that our iterator is at. If we are not currently at any node (i.e., we just created the iterator or we just removed the current node), `lastVisited` will be `null`. 

---

### MyBSTNodeIterator Constructor
```java
MyBSTNodeIterator(MyBSTNode<K,V> first);  
```
- This constructor creates an iterator to iterate through our BST. `first` represents the first node we will iterate to. Hence, `next` should be initialized to `first` and `lastVisited` should be initialized to `null` (since our iterator isn't currently at any node). Note that `first` being `null` is not a special case. 
- Note the default access modifier. 

---

### MyBSTNodeIterator Methods
```java
public boolean hasNext(); 
public MyBSTNode<K, V> nextNode(); 
public void remove(); 
```

Remember that since this is an inner class of `MyBSTEC`, we can use the instance variables/methods from `MyBSTEC` (and the inherited ones from `MyBST`). Remember that to get the instance of our current `MyBSTNodeIterator`, we would use `this`. To get the current instance of our outer class `MyBSTEC`, we use `MyBSTEC.this`. This will be necessary because we have methods with the same name (remember how in our constructors, when the parameter has the same name as our instance variable, we need to use `this.` to set the instance variable - it's the same here to access the outer class's methods). 

#### `public boolean hasNext()`
- Return a boolean representing if the iterator has or does not have a next node. This is used to check when we've reached the end of the iterator. 

#### `public MyBSTNode<K, V> nextNode()`
- This method advances our iterator to the next node and returns the node we advanced to. 
- If there is no next node, throw a `NoSuchElementException` and do not modify the iterator's state. 
- Upon a successful advancement, `next` should be updated to the new next node (possibly `null` if we've now reached the end of the iterator) and `lastVisited` should be updated to the node we just advanced to. Remember the `successor()` method that we previously wrote. 

#### `public void remove()`
- This method removes the current node of the iterator from the BST (this should do removal by using `remove()` from `MyBST`). Remember that this is equivalently the most previous node we advanced to or the most previous node returned by a call to `nextNode()`. 
- If `lastVisited` is `null`, throw an `IllegalStateException`. 
- Remember how we handled `remove()` from `MyBST` for a node that has both children. You may need to do something special so that `next` refers to the correct node in this case. 

---

## Part 8 - MyBSTKeyIterator - EXTRA CREDIT
```java
class MyBSTKeyIterator extends MyBSTNodeIterator<K> {}
```

`MyBSTKeyIterator` is an inner class of `MyBSTEC`. It will be used when we want to iterate through our tree's keys in order. See [appendix B](#mybstkeyiterator-and-mybstvalueiterator-class-declarations) for more details about this class declaration. 

---

### MyBSTKeyIterator Instance Variables
This class has no instance variables other than the ones it inherits. 

---

### MyBSTKeyIterator Constructor
```java
MyBSTKeyIterator(MyBSTNode<K, V> first); 
```
- This constructor creates an iterator to iterate through our BST's keys. `first` represents the first node we will iterate to. 
- Remember that a constructor always calls its superclass's constructor first. Also remember that if we do not make the call explicit, then there will be an implicit call to the superclass's no-arg constructor, which doesn't exist in this case. 
- Note the default access modifier. 

---

### MyBSTKeyIterator Method
```java
public K next();
```
- Remember that `MyBSTNodeIterator`, which this class extends from, implements the `Iterator` interface, which has the abstract method `next()`. We defined `MyBSTNodeIterator` as `abstract` to force its children to define `next()` by themselves. 

#### `public K next()`
- Advance our iterator to the next node and return the key of the node we advanced to. 
- The expected exceptions are the same as for `nextNode()` in `MyBSTNodeIterator`. 

---

## Part 9 - MyBSTValueIterator - EXTRA CREDIT
```java
class MyBSTValueIterator extends MyBSTNodeIterator<V> {}
```
`MyBSTValueIterator` is an inner class of `MyBSTEC`. It will be used when we want to iterate through our tree's values in their key order. See [appendix B](#mybstkeyiterator-and-mybstvalueiterator-class-declarations) for more details about this class declaration.  

---

### MyBSTValueIterator Instance Variables
This class has no instance variables other than the ones it inherits. 

---

### MyBSTValueIterator Constructor
```java
MyBSTValueIterator(MyBSTNode<K, V> first); 
```
- This constructor creates an iterator to iterate through our BST's values. `first` represents the first node we will iterate to. 
- Remember that a constructor always calls its superclass's constructor first. Also remember that if we do not make the call explicit, then there will be an implicit call to the superclass's no-arg constructor, which doesn't exist in this case. 
- Note the default access modifier. 

---

### MyBSTValueIterator Method
```java
public V next();
```
- Remember that `MyBSTNodeIterator`, which this class extends from, implements the `Iterator` interface, which has the abstract method `next()`. We defined `MyBSTNodeIterator` as `abstract` to force its children to define `next()` by themselves. 

#### `public V next()`
- Advance our iterator to the next node and return the value of the node we advanced to. 
- The expected exceptions are the same as for `nextNode()` in `MyBSTNodeIterator`. 

---

## Point Distribution
For the `Successor` (from `MyBST`), `Insert`, `Search`, `Remove`, and `Inorder`, we'll be using our solution `MyBSTNode` implementation, which will be implemented as specified in the writeup. For the EC, we'll be using our solution `MyBST.java` file, and for the TestJUnit (which is just for you and worth 0 points to the final grade), we will use our solution `MyBST.java` and `MyBSTEC.java` files. 
| Tester Name | Point Value |
|-------------|-------------|
| TestPrototype | 0 |
| TestSanity | 11 |
| NodeConstructor | 1 |
| NodeGetKey | 1 |
| NodeGetValue | 1 |
| NodeGetParent | 1 |
| NodeGetLeft | 1 |
| NodeGetRight | 1 |
| NodeSetKey | 2 |
| NodeSetValue | 2 |
| NodeSetParent | 2 |
| NodeSetLeft | 2 |
| NodeSetRight | 2 |
| NodeSuccessor | 15 |
| Successor | 1 |
| Size | 1 |
| Insert | 15 |
| Search | 5 |
| Remove | 15 |
| Inorder | 10 |
| EC | 10 |
| TestJUnit | 0 |

## Surveys
**Weekly Reflection Survey**

You can find the weekly reflection survey [here](https://forms.gle/wMq4eVzqRDStQwnh8).
Please fill out the survey, it will count towards **1 point** of your PA8 score. 

## Submission

**Turning in your code**

Submit all of the following files to Gradescope by **Monday, May 25 @ 11:59PM PDT**  (Tuesday, May 26 @ 11:59pm PDT w/ slip day):
- MyBST.java (required)
- MyBSTEC.java (optional, for extra credit)
- MyBSTTester.java (optional)

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial credit for the tests that you passed. ⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How your assignment will be evaluated**
* **Coding Style** (10 points)
We will grade your code style in `MyBST.java` thoroughly (the tester files will not be graded for style) according to the style guidelines. Namely, there are a few things you must have in each file / class / method:

1. File headers
2. Class headers
3. Method headers
4. Inline comments
5. Proper indentation (do not intermingle spaces and tabs for indentation)
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters (note, tabs will be counted as 4 characters toward this limit. It is a good idea to set your tab width/size to be 4)
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
- **Weekly Reflection Survey** (1 point)
- **Correctness** (Up to 99 out of 89 points)
    - You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the naming and visibility specifications in this writeup), you will not earn credit. 

---

## Appendix
### Appendix A: Why Separate Classes
We separate the EC and non-EC classes so that your work for the extra credit will not affect the main part of your assignment (e.g., your EC part doesn't compile). This also allows us to test your EC code while using a complete implementation of the non-EC code, which like in some previous PAs, means that you won't fail tests in the EC for problems from your non-EC code.

Note that the `MyBSTEC` class is extending the `MyBST` class, so everything is inherited, which means that all code you write in your `MyBSTEC` class will be identical to if you had written it in your `MyBST` class other than a few things. One of these things is private methods and instance/static variables (you shouldn't have any such variables except `private static final` variables for constants though), which you will not be able to use in the derived class. Another is that in your inner class, you will only be accessing the outer class as `MyBSTEC.this` instead of with `MyBST.this`. 


### Appendix B: Class Declarations
This section mostly talks about alternatives, and thus should not be referred to when implementing the code you will turn in. 

#### MyBSTEC Class Declaration
```java
public class MyBSTEC<K extends Comparable<K>, V> extends MyBST<K, V> {}
```
In this class, we've gotten pretty accustomed to the `K extends Comparable<K>` thing. Looking at the [`Comparable` javadoc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) makes it clear why this is necessary, as the `K` here will be the `T` in the `Comparable` class, which will be the type taken by the `compareTo()` method. Thus, a statement such as `int compareToResult = Integer.valueOf(1).compareTo((Number)Integer.valueOf(1));` will fail to compile (`Number` is not exactly the same type as `Integer`). This `K` is then also the same `K` that parameterizes the superclass (and also the node and iterator classes). 

Another way to have declared the `K` for this assignment is as `K extends Comparable<? super K>`. In this way, we allow polymorphic `compareTo()`. You can try to think about when we would want this and when we wouldn't. 

Note that the class declaration `public class MyBSTEC extends MyBST<K, V>` would not compile, since the compiler will not know what the `K` and `V` are. Thus, if we want to require the same `K` and `V` throughout the class (and we do), we need to parameterize it here. 

#### MyBSTNodeIterator Class Declaration
```java
abstract class MyBSTNodeIterator<T> implements Iterator<T> {}
```
Here, we define `MyBSTNodeIterator` as a generic type that takes some type `T`. Notice though that we don't ever actually use this type `T`. Thus, it could make sense that we simply declare the class as `abstract class MyBSTNodeIterator implements Iterator`, using the raw type `Iterator`. However, look to the [`Iterator` javadoc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Iterator.html). Notice that the `next()` method (and also the `forEachRemaining()`, which is really interesting if you want to look into it more) returns type `E`, which is type `T` here. Thus, since we haven't implemented the abstract `next()` in this class, it makes sense that `T` is not used here. However, in the child classes, `MyBSTKeyIterator` and `MyBSTValueIterator`, a generic type will be passed up (the part that says `extends MyBSTNodeIterator<...>`), which will determine the return type of the `next()` method in those subclasses. This is also why this inheritance pattern exists, so that our child iterators can determine what they want `next()` to return, instead of requiring the client to use `getKey()` or `getValue()` to parse out the information they need. However, we will see below why maybe this doesn't matter. 


#### MyBSTKeyIterator and MyBSTValueIterator Class Declarations
```java
class MyBSTKeyIterator extends MyBSTNodeIterator<K> {}
class MyBSTValueIterator extends MyBSTNodeIterator<V> {}
```
Note, the `K` and `V` in these declarations do not actually determine any of the `K` or `V` used in these classes. These `K` and `V` in the class body are the `K` and `V` defined by the outer class, `MyBSTEC`. 

We'll discuss a bit about the `T` talked about above for the `MyBSTNodeIterator<T>`. Notice that if we had used the raw type `Iterator` for `MyBSTNodeIterator`'s class declaration, then the generic type would basically be defaulted to `Object`. However, Java 5+ supports covariant return types, so we could still override the `Object next()` method with `V next()` or `K next()`, since `K` and `V` are both subclasses of `Object`. 


### Appendix C: Extensions
#### ConcurrentModificationException
For this assignment, we won't be worried about the BST changing while we are using an iterator (this would normally lead to a `ConcurrentModificationException`) except for changes caused by the iterator. It should be pretty obvious that if we remove some element from the tree, the iterator might no longer iterate through the tree correctly, since the tree's structure would change. An easy way to see this is to look at your implementation of `nextNode()`/`hasNext()`. Chances are, you trust your `next` variable, so what would happen if that is the variable removed? If you want some practice, try implementing throwing `ConcurrentModificationException` when something like this happens. Think about all the cases when some change by an iterator may affect other iterators. It's pretty simple to keep an instance variable that keeps track of the number of these such modifications in your `MyBSTEC` class. Then, you can have a separate instance variable in your iterator class that simply keeps track of how many modifications you expect there to be. This way, your iterator knows that a removal done by the iterator will be okay, but a removal done not by this iterator should cause a `ConcurrentModificationException`. 

#### Other Extensions to the Iterator
Some ideas for other extensions for the iterators: 
  - Iterator(s) that iterate through the tree backward
  - `predecessor()` method (basically just `successor()` in reverse)
  - `prev()` and `hasPrev()` methods
  - Concrete iterator that returns the key/value pairs/nodes. 