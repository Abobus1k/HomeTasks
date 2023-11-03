package edu.hw5.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestSubsequenceStringChecker {
    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                "abc",
                "achfdbaabgabcaabg",
                true
            ),
            Arguments.of(
                "1",
                "1",
                true
            ),
            Arguments.of(
                "aaa",
                "asdjasdjasodasoidjaaadisajdiasjd",
                true
            ),
            Arguments.of(
                "aaa",
                "dsakodasdkasogjdskf",
                false
            ),
            Arguments.of(
                "a1",
                "ba1",
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testSubsequenceStringChecker(String s, String t, boolean isSubsequence) {
        Assertions.assertEquals(isSubsequence, SubsequenceStringChecker.isSubsequence(s,t));
    }
}
