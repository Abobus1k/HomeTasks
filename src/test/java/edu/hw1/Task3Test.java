package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        int[] a1 = new int[] {1,2,3,4};
        int[] a2 = new int[] {0,6};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertTrue(Nestable);
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        int[] a1 = new int[] {3,1};
        int[] a2 = new int[] {4,0};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertTrue(Nestable);
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        int[] a1 = new int[] {9,9,8};
        int[] a2 = new int[] {8,9};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertFalse(Nestable);
    }

    @Test
    @DisplayName("Четвертый пример из ТЗ")
    void testFourthExample() {
        int[] a1 = new int[] {1,2,3,4};
        int[] a2 = new int[] {2,3};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertFalse(Nestable);
    }

    @Test
    @DisplayName("Проверка правильности работы когда второй массив вложен в первый")
    void testSecondNestedWithinFirst() {
        int[] a1 = new int[] {1,4};
        int[] a2 = new int[] {2,3};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertFalse(Nestable);
    }

    @Test
    @DisplayName("Проверка для равных массивов")
    void testEqualsArrays() {
        int[] a1 = new int[] {1,4};
        int[] a2 = new int[] {1,4};

        boolean Nestable = Task3.isNestable(a1,a2);

        Assertions.assertFalse(Nestable);
    }
}
