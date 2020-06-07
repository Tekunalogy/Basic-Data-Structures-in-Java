#!/bin/bash

echo Starting Compiler...

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. BinaryTree.java BinaryTreeTester.java

echo Done Compiling!

echo Running Tester...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore BinaryTreeTester

echo ********************DONE WITH TESTS******************************

rm *.class
