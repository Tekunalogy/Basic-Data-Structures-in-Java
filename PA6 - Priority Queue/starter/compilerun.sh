#!/bin/bash

echo Starting Compile...

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. MyMinHeap.java MyPriorityQueue.java

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. MyMinHeapTester.java MyPriorityQueueTester.java

echo Done Compiling!

echo Running MyMinHeap Tester...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyMinHeapTester

echo ****************************DONE****************************
echo Running MyPriorityQueue Tester...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyPriorityQueueTester

echo ****************************DONE****************************
echo Done Testing!

rm *.class