package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTestClass {
    public static void regularMethod() {
        System.out.println("This is a regular method");
    }

    @Test
    public static void passingTest() {
        assertTrue(true);
    }

    @Test
    public void SoyGurt() {
        System.out.println("Hola");
    }


    @Test
    public static void failingTest() {
        assertEquals(1, 2, "This test should fail");
    }

    @Test
    public static void passingTest2() {
        String str = "Hello";
        assertNotNull(str);
        assertEquals("Hello", str);
    }

    public void nonStaticMethod() {
        System.out.println("This is a non-static method");
    }
} 