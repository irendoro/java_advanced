package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Calculator operations.
 */
class CalculatorIntegrationTest {

    @Test
    void testCompleteOperationFlow() {
        assertEquals(15.0, Operation.ADDITION.calculate(10.0, 5.0), 0.001);
        assertEquals(5.0, Operation.SUBTRACTION.calculate(10.0, 5.0), 0.001);
        assertEquals(50.0, Operation.MULTIPLICATION.calculate(10.0, 5.0), 0.001);
        assertEquals(2.0, Operation.DIVISION.calculate(10.0, 5.0), 0.001);
    }

    @Test
    void testMathUtilsWithOperations() {
        double result = Operation.ADDITION.calculate(1.23456, 2.34567);
        double rounded = MathUtils.round(result, 2);
        assertEquals(3.58, rounded, 0.001);
    }
}