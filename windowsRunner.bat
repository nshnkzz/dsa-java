@echo off

rem Compile and run Java code
javac Runner.java
java Runner

rem Delete compiled class files
del *.class
