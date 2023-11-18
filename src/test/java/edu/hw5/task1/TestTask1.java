package edu.hw5.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class TestTask1 {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3ч 40м"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:00 - 2022-03-12, 21:00",
                    "2022-03-12, 20:00 - 2022-03-12, 22:00",
                    "2022-03-12, 20:00 - 2022-03-12, 23:00"
                    ),
                "2ч 0м"
            ),
            Arguments.of(
                List.of(
                    "2022-07-20, 19:00 - 2022-07-20, 20:00",
                    "2022-07-20, 22:00 - 2022-07-21, 00:00"
                ),
                "1ч 30м"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testTask1(List<String> sessions, String averageDuration) {
        ComputerClubAnalytics analytics = new ComputerClubAnalytics(sessions);
        Assertions.assertEquals(averageDuration, analytics.getAverageSession());
    }
}
