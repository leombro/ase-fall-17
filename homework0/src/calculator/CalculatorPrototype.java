/*
 * Advanced Software Engineering - Homework 0
 * Author: Orlando Leombruni - https://github.com/leombro/ase-fall-17
 */

package calculator;

public class CalculatorPrototype implements CalculatorIF {

    @Override
    public int sum(int m, int n) {

        /*
         * Contract: sum(m, n) must perform n increments (+1)
         * to the value of m, and return the result
         */

        int result = m;

        if (n >= 0) {
            while(n > 0) {
                result += 1;
                n -= 1;
            }
        } else {
            /*
                When n is negative, we treat the sum as a subtraction
             */
            result = subtract(m, -n);
        }

        return result;
    }

    @Override
    public int subtract(int m, int n) {

        /*
         * Contract: subtract(m, n) must perform n decrements (-1)
         * to the value of m, and return the result
         */

        int result = m;

        if (n >= 0) {
            while (n > 0) {
                result -= 1;
                n -= 1;
            }
        } else {
            /*
                When n is negative, we treat the subtraction as a sum
             */
            result = sum(m, -n);
        }

        return result;

    }

    @Override
    public int multiply(int m, int n) {

        /*
         * Contract: multiply(m, n) must sum the value of m for
         * n times, and return the result
         */

        int result = 0;
        boolean opposite = n < 0;
        if (n < 0) n = -n;


        while (n > 0) {
            result = sum(result, m);
            n -= 1;
        }

        if (opposite) result = -result;

        return result;
    }

    @Override
    public int divide(int m, int n) {

        /*
         * Contract: divide(m, n) must subtract n from n
         * until it goes to 0, and return the result
         */

        if (n == 0) throw new ArithmeticException("Division by zero");
        int result = 0;
        boolean opposite1 = n < 0, opposite2 = m < 0;
        if (opposite1) n = -n;
        if (opposite2) m = -m;

        while (m > 0) {
            m = subtract(m, n);
            result += 1;
        }

        if (m != 0) result -= 1;
        if (opposite1 ^ opposite2) result = -result;

        return result;
    }
}
