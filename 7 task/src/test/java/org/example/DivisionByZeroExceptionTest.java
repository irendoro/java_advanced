package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DivisionByZeroException.
 */
class DivisionByZeroExceptionTest {

    @Test
    void testExceptionCreation() {
        String errorMessage = "Division by zero error";
        DivisionByZeroException exception = new DivisionByZeroException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}