package com.example;

import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class GreetingServiceTest {

    private GreetingService greetingService;

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
        greetingService = new GreetingService();
    }

    @Test
    @DisplayName("Test 1")
    void testGreeting1() {
        System.out.println("Test 1");
        assertEquals("Hello Ofer", greetingService.greeting("Ofer"));
    }

    @Test
    @DisplayName("Test 2")
    void testGreeting2() {
        System.out.println("Test 2");
        assertEquals("Hello Fani", greetingService.greeting("Fani"));
    }

    @Test
    @DisplayName("Test 3")
    @Disabled("Test is disabled because it is not implemented yet")
    void testGreeting3() {
        System.out.println("Test 3");
    }

    @Test
    @DisplayName("Test 4")
    void lambdaExpressions() {
        System.out.println("Test 4");
        assertTrue(Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    @DisplayName("Test 5")
    void groupAssertions() {
        System.out.println("Test 5");
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }

    @Test
    @DisplayName("Test 6")
    void trueAssumption() {
        System.out.println("Test 6");
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    @DisplayName("Test 7")
    void falseAssumption() {
        System.out.println("Test 7");
        //If an assumption fails, a TestAbortedException is thrown and the test is simply skipped
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    @DisplayName("Test 8")
    void assumptionThat() {
        System.out.println("Test 8");
        String someString = "Just a string";
        //Assumptions also understand lambda expressions
        assumingThat(
                someString.equals("Just a string"),
                () -> assertEquals(2 + 2, 4)
        );
    }

    @Test
    @DisplayName("Test 9")
    void shouldThrowException() {
        System.out.println("Test 9");
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @Test
    @DisplayName("Test 10")
    void assertThrowsException() {
        System.out.println("Test 10");
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
        greetingService = null;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }
}