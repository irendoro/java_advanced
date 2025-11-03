package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MathUtils class.
 */
class MathUtilsTest {

    @Test
    void testRound() {
        assertEquals(3.14, MathUtils.round(3.14159, 2), 0.001);
        assertEquals(3.142, MathUtils.round(3.14159, 3), 0.001);
        assertEquals(3.0, MathUtils.round(3.14159, 0), 0.001);
    }

    @Test
    void testRoundWithNegativePlaces() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MathUtils.round(5.0, -1));
        assertTrue(exception.getMessage().contains("Decimal places cannot be negative"));
    }

    @Test
    void testIsZero() {
        assertTrue(MathUtils.isZero(0.0));
        assertTrue(MathUtils.isZero(0.00000000009)); // Меньше чем 1e-10
        assertFalse(MathUtils.isZero(0.00000000011)); // Больше чем 1e-10
        assertFalse(MathUtils.isZero(0.1));
        assertFalse(MathUtils.isZero(1.0));
    }
}