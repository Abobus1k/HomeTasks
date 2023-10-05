package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        Assertions.assertEquals(4,Task2.countOfDigits(4666));
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        Assertions.assertEquals(3,Task2.countOfDigits(544));
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        Assertions.assertEquals(1,Task2.countOfDigits(0));
    }

    @Test
    @DisplayName("Проверка для отрицательного числа")
    void testNegativeNumber() {
        Assertions.assertEquals(2,Task2.countOfDigits(-10));
    }

    @Test
    @DisplayName("Проверка числа с двумя разрядами")
    void testTwoDigitNumber() {
        Assertions.assertEquals(2,Task2.countOfDigits(10));
    }

    @Test
    @DisplayName("Проверка числа с одним разрядом")
    void testOneDigitNumber() {
        Assertions.assertEquals(1,Task2.countOfDigits(4));
    }
}
