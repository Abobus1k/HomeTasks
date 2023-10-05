package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("First example from technical specifications")
    void testFirstExample() {
        Assertions.assertEquals(60,Task1.seconds("01:00"));
    }

    @Test
    @DisplayName("Second example from technical specifications")
    void testSecondExample() {
        Assertions.assertEquals(836,Task1.seconds("13:56"));
    }

    @Test
    @DisplayName("Third example from technical specifications")
    void testThirdExample() {
        Assertions.assertEquals(-1,Task1.seconds("10:60"));
    }

    @Test
    @DisplayName("Проверка работоспособности когда минут больше 100")
    void testLotMinutes() {
        Assertions.assertEquals(6000,Task1.seconds("100:00"));
    }

    @Test
    @DisplayName("Проверка нулего времени")
    void testZeroTime() {
        Assertions.assertEquals(0,Task1.seconds("00:00"));
    }

    @Test
    @DisplayName("Проверка с количеством секунд больше 60")
    void testLotSeconds() {
        Assertions.assertEquals(-1,Task1.seconds("10:61"));
    }

    @Test
    @DisplayName("Проверка с количеством секунд больше 100")
    void testToMuchSeconds() {
        Assertions.assertEquals(-1,Task1.seconds("10:101"));
    }

    @Test
    @DisplayName("Проверка с количеством минут заданном с ведущими нулями")
    void testMinutesFormat() {
        Assertions.assertEquals(600,Task1.seconds("010:00"));
    }
}
