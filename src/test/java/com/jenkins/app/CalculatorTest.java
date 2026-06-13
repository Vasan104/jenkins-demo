package com.jenkins.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(15, calc.add(10, 5));
        assertEquals(0, calc.add(-5, 5));
        System.out.println("testAdd: PASSED");
    }

    @Test
    public void testSubtract() {
        assertEquals(5, calc.subtract(10, 5));
        assertEquals(-10, calc.subtract(0, 10));
        System.out.println("testSubtract: PASSED");
    }

    @Test
    public void testMultiply() {
        assertEquals(50, calc.multiply(10, 5));
        assertEquals(0, calc.multiply(10, 0));
        System.out.println("testMultiply: PASSED");
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calc.divide(10, 5), 0.001);
        System.out.println("testDivide: PASSED");
    }

    @Test
    public void testDivideByZero() {
        try {
            calc.divide(10, 0);
            fail("Expected ArithmeticException");
        } catch (ArithmeticException e) {
            assertEquals("Cannot divide by zero!", e.getMessage());
            System.out.println("testDivideByZero: PASSED");
        }
    }

    @Test
    public void testIsEven() {
        assertTrue(calc.isEven(10));
        assertFalse(calc.isEven(7));
        System.out.println("testIsEven: PASSED");
    }
}
