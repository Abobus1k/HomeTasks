package edu.hw5.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestPasswordChecker {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                "qwerty",
                false
            ),
            Arguments.of(
                "qwerty!",
                true
            ),
            Arguments.of(
                "*qwerty",
                true
            ),
            Arguments.of(
                "qwe#rty",
                true
            ),
            Arguments.of(
                "q*wer#ty",
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testPasswordChecker(String password, boolean isStrong) {
        Assertions.assertEquals(isStrong, PasswordChecker.isStrongPassword(password));
    }
}
