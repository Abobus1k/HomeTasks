package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        int num = 8;
        int shift = 1;

        int result = Task7.rotateRight(num,shift);

        Assertions.assertEquals(4, result);
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        int num = 16;
        int shift = 1;

        int result = Task7.rotateLeft(num,shift);

        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        int num = 17;
        int shift = 2;

        int result = Task7.rotateLeft(num,shift);

        Assertions.assertEquals(6, result);
    }

    @Test
    @DisplayName("Циклический сдвиг на сто элементов числа 1")
    void testABigRotateOfNumberOne() {
        int num = 1;
        int shift = 100;

        int result = Task7.rotateRight(num,shift);

        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("Циклический сдвиг числа 0")
    void testZeroRotate() {
        int num = 0;
        int shift = 3;

        int result = Task7.rotateRight(num,shift);

        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("Циклический сдвиг до первоначального состояния")
    void testFullRotate() {
        int num = 32;
        int shift = 6;

        int result = Task7.rotateRight(num,shift);

        Assertions.assertEquals(32, result);
    }
}
