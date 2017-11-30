# Homework 0

## Assignment
Your customer wants you to implement a Java calculator program. You agreed on the specification and interface sketched below. 

* `sum(m,n)` must perform n increments (+1) to the value of m and return the result
* `divide(m,n)` must subtract n from m until it gets to 0 and return the result
* `subtract(m,n)` must perform n decrements (-1) to the value of m and return the result
* `multiply(m,n)` must sum the value of m for n times and return the result

```java
package Calculator;
public interface CalculatorIF {      
	int sum(int m, int n);      
	int subtract(int m, int n);      
	int multiply(int m, int n);     
	int divide(int m, int n);  
}
```

Implement a prototype capable of performing all operations, also extending the unit tests (JUnit) provided by your customer (ed: given by the teacher) so to stress test subtraction and multiplication. Push the prototype code to your GitHub.

 
### Learning outcomes

- [x] Revise basic programming constructs with Java. 
- [x] Revise command-line usage. 
- [x] Get familiar with IDE programming and unit testing (JUnit). 
- [x] Get familiar with GitHub and learn some advanced features.  
