#!/bin/bash

echo Starting Compile...

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. StackInterface.java DequeInterface.java QueueInterface.java

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. MyStack.java MyDeque.java MyQueue.java

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. MyStackTester.java MyDequeTester.java MyQueueTester.java

echo Done Compiling!

echo Running Deque Teste...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyDequeTester

echo ********************DONE WITH DEQUE******************************

echo Running Stack Teste...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyStackTester

echo ********************DONE WITH STACK******************************

echo Running Queue Teste...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyQueueTester

echo ********************DONE WITH QUEUE******************************
