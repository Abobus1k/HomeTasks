package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task1Test {
    public static Stream<Arguments> provideTimeData() {
        return Stream.of(
            Arguments.of("01:00", 60),
            Arguments.of("13:56", 836),
            Arguments.of("10:60", -1),
            Arguments.of("100:00", 6000),
            Arguments.of("00:00", 0),
            Arguments.of("10:61", -1),
            Arguments.of("10:101", -1),
            Arguments.of("010:00", 600)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTimeData")
    void testTime(String inputTime, int expectedSeconds) {
        Assertions.assertEquals(expectedSeconds, Task1.seconds(inputTime));
    }
}
