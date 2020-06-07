#!/bin/bash

echo Starting Compiler...

# javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. Sorting.java TestSorting.java
javac Sorting.java TestSorting.java

echo Done Compiling!

echo Running Tester...

# java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore TestSorting
java TestSorting

echo ********************DONE WITH TESTS******************************

rm *.class
