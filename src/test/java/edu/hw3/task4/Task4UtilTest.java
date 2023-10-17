package edu.hw3.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static edu.hw3.task4.Task4Util.romanToInteger;

public class Task4UtilTest {

    static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(50, "L"),
            Arguments.of(49, "XLIX"),
            Arguments.of(196, "CXCVI"),
            Arguments.of(1001, "MI"),
            Arguments.of(0,"")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testConverting(int inputNumber, String expectedRomanNumber) {
        String resNumber = romanToInteger(inputNumber);
        Assertions.assertEquals(expectedRomanNumber, resNumber);
    }
}
