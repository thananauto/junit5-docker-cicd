package com.test.qa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleJUnit5Tests {

    @Test
    void testEquality() {
        String expected = "Hello, JUnit 5!";
        String actual = "Hello, JUnit 5!";

        // Assert equality
        assertEquals(expected, actual, "The strings are not equal");
    }

    @Test
    void testNotNull() {
        Object obj = new Object();

        // Assert not null
        assertNotNull(obj, "The object should not be null");
    }

    @Test
    void testTrue() {
        boolean condition = true;

        // Assert true
        assertTrue(condition, "The condition should be true");
    }

    @Test
    void testArrayEquality() {
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};

        // Assert array equality
        assertArrayEquals(expectedArray, actualArray, "The arrays are not equal");
    }

    @Test
    void testException() {
        // Example of testing an exception
        String str = null;

        // Assert that a specific exception is thrown
        assertThrows(NullPointerException.class, () -> {
            str.length(); // This will throw a NullPointerException
        });
    }

    @Test
    void testAll() {
        // Example of asserting multiple conditions at once
        int value = 42;

        assertAll("Properties",
                () -> assertEquals(42, value),
                () -> assertEquals(4, 2 * 2),
                () -> assertTrue(value > 0)
        );
    }
}
