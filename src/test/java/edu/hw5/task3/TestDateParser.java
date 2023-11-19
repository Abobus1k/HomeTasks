package edu.hw5.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class TestDateParser {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, 10, 10))
            ),
            Arguments.of(
                "2020-12-2",
                Optional.of(LocalDate.of(2020, 12, 2))
            ),
            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, 3, 1))
            ),
            Arguments.of(
                "1/3/20",
                Optional.of(LocalDate.of(2020, 3, 1))
            ),
            Arguments.of(
                "tomorrow",
                Optional.of(LocalDate.of(2023, 11, 20))
            ),
            Arguments.of(
                "today",
                Optional.of(LocalDate.of(2023, 11, 19))
            ),
            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.of(2023, 11, 18))
            ),
            Arguments.of(
                "2 days ago",
                Optional.of(LocalDate.of(2023, 11, 17))
            ),
            Arguments.of(
                "10 dney nazad",
                Optional.empty()
            ),
            Arguments.of(
                "vchera",
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testDateParser(String dateToParse, Optional<LocalDate> date) {
        DateParser parser = new DateParser();
        Assertions.assertEquals(date, parser.parseDate(dateToParse));
    }
}
