package edu.hw3.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static edu.hw3.task5.Task5Util.func;

public class Task5UtilTest {
    static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Contact("John", "Locke"),
                    new Contact("Thomas", "Aquinas"),
                    new Contact("David", "Hume"),
                    new Contact("Rene", "Descartes")
                ),
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                ),
                "ASC"
            ),
            Arguments.of(
                List.of(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                ),
                List.of(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                ),
                "DESC"
            ),
            Arguments.of(new ArrayList<>(), new ArrayList<>(), "DESC"),
            Arguments.of(null, new ArrayList<>(), "DESC"),
            Arguments.of(
                List.of(
                    new Contact("Carl"),
                    new Contact("Leonhard"),
                    new Contact("Paul")
                ),
                List.of(
                    new Contact("Carl"),
                    new Contact("Leonhard"),
                    new Contact("Paul")
                ),
                "ASC"
            ),
            Arguments.of(
                List.of(
                    new Contact("Aaa"),
                    new Contact("Zzz", "Yyy"),
                    new Contact("Ivan")
                ),
                List.of(
                    new Contact("Aaa"),
                    new Contact("Ivan"),
                    new Contact("Zzz", "Yyy")
                ),
                "ASC"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testSortingContacnts(List<Contact> inputList, List<Contact> expectedList, String order) {
        List<Contact> sortedList = func(inputList, order);

        Assertions.assertEquals(expectedList, sortedList);
    }
}
