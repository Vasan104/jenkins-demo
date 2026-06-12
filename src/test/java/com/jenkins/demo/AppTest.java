package com.jenkins.demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testAdd() {
        App app = new App();
        assertEquals(5, App.add(2, 3));
        assertEquals(0, App.add(-1, 1));
        System.out.println("testAdd: PASSED");
    }

    @Test
    public void testGreet() {
        assertEquals("Hello, Jenkins!", App.greet("Jenkins"));
        assertEquals("Hello, World!", App.greet("World"));
        System.out.println("testGreet: PASSED");
    }
}
