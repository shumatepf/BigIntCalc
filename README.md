# Big Integer Calculator

## Description

This program works similar to Java [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) in that operations are performed on Strings rather than ints.
Each function breaks the Strings into arrays of individual ints and returns a String object.
The 4 functions are:  
- Add  
- Subtract  
- Multiply  
- Divide  

## Instructions

This program requires the JAVA JDK to be installed.

Compile:  
`javac BigIntCalc.java`

Run:  
`java BigInt [num1] [num2]`

or

Call the static methods:  
`add(str1, str2)`  
`subtract(str1, str2)`  
`multiply(str1, str2)`  
`divide(str1, str2)`  

## Rules

`[num1]` >= `[num2]`  
`[num2]` != `0`

The program operates `[num1] + [num2]`, `[num1] - [num2]`, etc.
