#!/bin/bash

echo Starting Compiler...

javac -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. MyBST.java MyBSTTester.java

echo Done Compiling!

echo Running Tester...

java -cp ../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyBSTTester

echo ********************DONE WITH TESTS******************************

rm *.class
