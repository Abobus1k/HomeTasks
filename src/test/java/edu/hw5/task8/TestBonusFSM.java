package edu.hw5.task8;

import edu.hw5.task7.FSM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestBonusFSM {
    public static Stream<Arguments> provideFirstRuleData() {
        return Stream.of(
            Arguments.of(
                "1",
                true
            ),
            Arguments.of(
                "11",
                false
            ),
            Arguments.of(
                "000",
                true
            ),
            Arguments.of(
                "0000",
                false
            ),
            Arguments.of(
                "11111",
                true
            )
        );
    }

    public static Stream<Arguments> provideSecondRuleData() {
        return Stream.of(
            Arguments.of(
                "1",
                false
            ),
            Arguments.of(
                "0",
                true
            ),
            Arguments.of(
                "00",
                false
            ),
            Arguments.of(
                "11",
                true
            ),
            Arguments.of(
                "000",
                true
            ),
            Arguments.of(
                "111",
                false
            )
        );
    }

    public static Stream<Arguments> provideThirdRuleData() {
        return Stream.of(
            Arguments.of(
                "10",
                true
            ),
            Arguments.of(
                "01",
                false
            ),
            Arguments.of(
                "111",
                true
            ),
            Arguments.of(
                "001",
                false
            ),
            Arguments.of(
                "1",
                true
            ),
            Arguments.of(
                "0",
                false
            ),
            Arguments.of(
                "1010",
                true
            )
        );
    }

    public static Stream<Arguments> provideFourthRuleData() {
        return Stream.of(
            Arguments.of(
                "10100101",
                true
            ),
            Arguments.of(
                "11",
                false
            ),
            Arguments.of(
                "111",
                false
            ),
            Arguments.of(
                "001",
                true
            ),
            Arguments.of(
                "1",
                true
            ),
            Arguments.of(
                "0",
                true
            ),
            Arguments.of(
                "",
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstRuleData")
    void testFirstRuleWorking(String input, boolean isRight) {
        Assertions.assertEquals(isRight, BonusFSM.isFirstRule(input));
    }

    @ParameterizedTest
    @MethodSource("provideSecondRuleData")
    void testSecondRuleWorking(String input, boolean isRight) {
        Assertions.assertEquals(isRight, BonusFSM.isSecondRule(input));
    }

    @ParameterizedTest
    @MethodSource("provideThirdRuleData")
    void testThirdRule(String input, boolean isRight) {
        Assertions.assertEquals(isRight, BonusFSM.isThirdRule(input));
    }

    @ParameterizedTest
    @MethodSource("provideFourthRuleData")
    void testFourthRule(String input, boolean isRight) {
        Assertions.assertEquals(isRight, BonusFSM.isFourthRule(input));
    }
}
