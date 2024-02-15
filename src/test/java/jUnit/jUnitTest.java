package jUnit;

import org.junit.jupiter.api.*;

public class jUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Это метод @BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Это метод @AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("    Это метод @BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("    Это метод @AfterEach");
    }

    @Test
    void firstTest() {
        System.out.println("            Это firstTest()");
        Assertions.assertTrue(3 > 2);
    }

    @Test
    void secondTest() {
        System.out.println("            Это secondTest()");
        Assertions.assertTrue(3 > 2);
    }
}