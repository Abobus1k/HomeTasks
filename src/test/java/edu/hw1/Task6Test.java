package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        int num = 3524;

        int result = Task6.countK(num);

        Assertions.assertEquals(3, result);
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        int num = 6621;

        int result = Task6.countK(num);

        Assertions.assertEquals(5, result);
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        int num = 6554;

        int result = Task6.countK(num);

        Assertions.assertEquals(4, result);
    }

    @Test
    @DisplayName("Четвертый пример из ТЗ")
    void testFourthExample() {
        int num = 1234;

        int result = Task6.countK(num);

        Assertions.assertEquals(3, result);
    }

    @Test
    @DisplayName("Тестирование для числа 9990")
    void testSomeNum() {
        int num = 9990;

        int result = Task6.countK(num);

        Assertions.assertEquals(4, result);
    }

    @Test
    @DisplayName("Тестирование для максимального числа")
    void testMaxNum() {
        int num = 9998;

        int result = Task6.countK(num);

        Assertions.assertEquals(5, result);
    }

    @Test
    @DisplayName("Тестирование для максимального числа")
    void testMinNum() {
        int num = 1001;

        int result = Task6.countK(num);

        Assertions.assertEquals(4, result);
    }
}
