package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3), 0.001);
    }

    @Test
    void testSubtraction() {
        assertEquals(1, calculator.subtract(3, 2), 0.001);
    }

    @Test
    void testMultiplication() {
        assertEquals(6, calculator.multiply(2, 3), 0.001);
    }

    @Test
    void testDivision() {
        assertEquals(2, calculator.divide(4, 2), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(4, 0));
    }

    @Test
    void testPower() {
        assertEquals(16, calculator.power(2, 4), 0.001);
    }

    @Test
    void testSqrt() {
        assertEquals(2, calculator.sqrt(4), 0.001);
    }

    @Test
    void testSqrtForNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculator.sqrt(-1));
    }

}
