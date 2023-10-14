package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        int num = 11211230;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        int num = 13001120;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        int num = 23336014;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Четвертый пример из ТЗ")
    void testFourthExample() {
        int num = 11;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Простой пример не палиндрома")
    void testNoPalindrom() {
        int num = 1241;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Число меньшее десяти")
    void testLessThanTen() {
        int num = 9;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Ноль")
    void testZero() {
        int num = 0;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Простой палиндром")
    void testSimplePalindrom() {
        int num = 1221;

        boolean result = Task5.isPalindromDescendant(num);

        Assertions.assertTrue(result);
    }
}
