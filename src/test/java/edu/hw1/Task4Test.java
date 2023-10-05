package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Первый пример из ТЗ")
    void testFirstExample() {
        String brokenString = "123456";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("214365",fixedString);
    }

    @Test
    @DisplayName("Второй пример из ТЗ")
    void testSecondExample() {
        String brokenString = "hTsii  s aimex dpus rtni.g";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("This is a mixed up string.",fixedString);
    }

    @Test
    @DisplayName("Третий пример из ТЗ")
    void testThirdExample() {
        String brokenString = "badce";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("abcde",fixedString);
    }

    @Test
    @DisplayName("Пустая строка")
    void testEmptyString() {
        String brokenString = "";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("",fixedString);
    }

    @Test
    @DisplayName("Односимвольная строка")
    void testOneCharString() {
        String brokenString = "a";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("a",fixedString);
    }

    @Test
    @DisplayName("Cтрока нечетной длины")
    void testOddString() {
        String brokenString = "abc";

        String fixedString = Task4.fixString(brokenString);

        Assertions.assertEquals("bac",fixedString);
    }
}
