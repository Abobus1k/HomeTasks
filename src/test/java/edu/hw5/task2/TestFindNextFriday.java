package edu.hw5.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.stream.Stream;

public class TestFindNextFriday {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2023,11,3),
                LocalDate.of(2024, 9, 13)
            ),
            Arguments.of(
                LocalDate.now(),
                LocalDate.of(2024, 9, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testFindNextFriday(LocalDate dateFrom, LocalDate nextFridayThe13th) {
        Assertions.assertEquals(nextFridayThe13th, dateFrom.with(new NextFridayThe13th()));
    }
}
