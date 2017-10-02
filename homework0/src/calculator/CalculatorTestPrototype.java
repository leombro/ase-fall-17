/*
 * Advanced Software Engineering - Homework 0
 */

package calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTestPrototype {

    @Test
    public void testSum() {
        /*
            Tests the correctness of the sum between two positive numbers.
         */
        CalculatorPrototype calculator = new CalculatorPrototype();
        int sum = calculator.sum(3,3);
        assertEquals(6, sum);
    }

    @Test
    public void testDivisionEven() {
        /*
            Tests the correctness of the division without remainder.
         */
        CalculatorPrototype calculator = new CalculatorPrototype();
        int sum = calculator.divide(6,3);
        assertEquals(2, sum);
    }

    @Test
    public void testDivisionOdd() {
        /*
            Tests the correctness of the division with remainder.
         */
        CalculatorPrototype calculator = new CalculatorPrototype();
        int sum = calculator.divide(7,3);
        assertEquals(2, sum);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        /*
            Tests the correctness of the division by zero.
         */
        CalculatorPrototype calculator = new CalculatorPrototype();
        int sum = calculator.divide(3,0);
    }

}