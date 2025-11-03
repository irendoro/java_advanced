package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Operation enum.
 */
class OperationTest {

    @Test
    void testAddition() {
        assertEquals(5.0, Operation.ADDITION.calculate(2.0, 3.0), 0.001);
        assertEquals(0.0, Operation.ADDITION.calculate(-2.0, 2.0), 0.001);
    }

    @Test
    void testSubtraction() {
        assertEquals(1.0, Operation.SUBTRACTION.calculate(3.0, 2.0), 0.001);
        assertEquals(-1.0, Operation.SUBTRACTION.calculate(2.0, 3.0), 0.001);
    }

    @Test
    void testMultiplication() {
        assertEquals(6.0, Operation.MULTIPLICATION.calculate(2.0, 3.0), 0.001);
        assertEquals(-6.0, Operation.MULTIPLICATION.calculate(2.0, -3.0), 0.001);
    }

    @Test
    void testDivision() {
        assertEquals(2.0, Operation.DIVISION.calculate(6.0, 3.0), 0.001);
        assertEquals(0.5, Operation.DIVISION.calculate(1.0, 2.0), 0.001);
    }
}