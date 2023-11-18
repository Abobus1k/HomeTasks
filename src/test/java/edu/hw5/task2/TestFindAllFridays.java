package edu.hw5.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestFindAllFridays {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                1925,
                "[1925-02-13, 1925-03-13, 1925-11-13]"
            ),
            Arguments.of(
                2024,
                "[2024-09-13, 2024-12-13]"
            ),
            Arguments.of(
                2023,
                "[2023-01-13, 2023-10-13]"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testFindAllFridays(int year, String stringFridaysThe13th) {
        Assertions.assertEquals(stringFridaysThe13th, FridayThe13th.findAllFridays(year).toString());
    }
}
