package edu.hw5.task7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestFSM {

    public static Stream<Arguments> provideFirstRuleData() {
        return Stream.of(
            Arguments.of(
                "110110010101",
                true
            ),
            Arguments.of(
                "110",
                true
            ),
            Arguments.of(
                "111",
                false
            ),
            Arguments.of(
                "00",
                false
            ),
            Arguments.of(
                "2",
                false
            )
        );
    }

    public static Stream<Arguments> provideSecondRuleData() {
        return Stream.of(
            Arguments.of(
                "11",
                true
            ),
            Arguments.of(
                "00",
                true
            ),
            Arguments.of(
                "110",
                false
            ),
            Arguments.of(
                "10",
                false
            ),
            Arguments.of(
                "0",
                true
            ),
            Arguments.of(
                "",
                false
            )
        );
    }

    public static Stream<Arguments> provideThirdRuleData() {
        return Stream.of(
            Arguments.of(
                "11",
                true
            ),
            Arguments.of(
                "00",
                true
            ),
            Arguments.of(
                "110",
                true
            ),
            Arguments.of(
                "0",
                true
            ),
            Arguments.of(
                "",
                false
            ),
            Arguments.of(
                "1111",
                false
            ),
            Arguments.of(
                "0000",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstRuleData")
    void testFirstRuleWorking(String input, boolean isRight) {
        Assertions.assertEquals(isRight, FSM.isFirstRule(input));
    }

    @ParameterizedTest
    @MethodSource("provideSecondRuleData")
    void testSecondRuleWorking(String input, boolean isRight) {
        Assertions.assertEquals(isRight, FSM.isSecondRule(input));
    }

    @ParameterizedTest
    @MethodSource("provideThirdRuleData")
    void testThirdRule(String input, boolean isRight) {
        Assertions.assertEquals(isRight, FSM.isThirdRule(input));
    }
}
