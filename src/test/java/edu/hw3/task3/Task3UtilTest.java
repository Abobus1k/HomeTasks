package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw3.task3.Task3Util.wordLen;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3UtilTest {

    static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("a", 2, "bb", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("this", 1, "and", 2, "that", 1)),
            Arguments.of(List.of("код", "код", "код", "ошибка"), Map.of("код", 3, "ошибка", 1))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testStringsWordLen(List<String> input, Map<String, Integer> expected) {
        Map<String, Integer> result = wordLen(input);

        assertEquals(expected, result);
    }

    @Test
    void testIntsWordLen() {
        List<Integer> input = new ArrayList<>(List.of(1, 1, 2, 2));
        Map<Integer, Integer> expected = new HashMap<>(Map.of(1,2,2,2));

        Map<Integer, Integer> result = wordLen(input);

        assertEquals(expected, result);
    }

    @Test
    void testCharsWordLen() {
        List<Character> input = new ArrayList<>(List.of('a', 'b', 'c'));
        Map<Character, Integer> expected = new HashMap<>(Map.of('a',1,'b',1, 'c', 1));

        Map<Character, Integer> result = wordLen(input);

        assertEquals(expected, result);
    }
}
