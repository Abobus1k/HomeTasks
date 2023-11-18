package edu.hw5.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestCarNumberChecker {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                "А123ВЕ777",
                true
            ),
            Arguments.of(
                "О777ОО177",
                true
            ),
            Arguments.of(
                "123АВЕ777",
                false
            ),
            Arguments.of(
                "А123ВГ77",
                false
            ),
            Arguments.of(
                "А123ВЕ7777",
                false
            ),
            Arguments.of(
                "1111111111",
                false
            ),
            Arguments.of(
                "АААААААААА",
                false
            ),
            Arguments.of(
                "А777АА777",
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testCarNumberChecker(String number, boolean isCorrect) {
        Assertions.assertEquals(isCorrect, CarNumberChecker.isRightCarNumber(number));
    }
}
