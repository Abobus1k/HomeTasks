package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class TestHomeWorkUtils {
    public static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(List.of(
                new Animal("Cot1", Animal.Type.CAT, Animal.Sex.M, 3, 25, -5, false),
                new Animal("Sobaka1", Animal.Type.DOG, Animal.Sex.F, 4, 30, 8, true),
                new Animal("Ptiza1", Animal.Type.BIRD, Animal.Sex.M, 2, 20, 1, false),
                new Animal("Ryba1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
                new Animal("Ptyza2", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 2, false),
                new Animal("Ryba2", Animal.Type.FISH, Animal.Sex.F, 8, 150, 200, false),
                new Animal("Pauk1", Animal.Type.SPIDER, Animal.Sex.M, -1, -1, 3, true),
                new Animal("Pauk2", Animal.Type.SPIDER, Animal.Sex.F, 6, 1, 4, true),
                new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
                new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
                new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
                new Animal("Ptyza3", Animal.Type.BIRD, Animal.Sex.M, 9, 31, 5, false),
                new Animal("Ptyza4", Animal.Type.BIRD, Animal.Sex.F, 11, 10, 6, false),
                new Animal("Cot3", Animal.Type.CAT, Animal.Sex.F, 12, 100, 50, false),
                new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
                new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
                new Animal("Ptyza5", Animal.Type.BIRD, Animal.Sex.F, 14, 21, 10, false),
                new Animal("Pauk3", Animal.Type.SPIDER, Animal.Sex.M, 15, 6, 7, false),
                new Animal("Cot5", Animal.Type.CAT, Animal.Sex.F, 16, 149, 100, false),
                new Animal("Sobaka3", Animal.Type.DOG, Animal.Sex.F, 17, 15, -2, false),
                new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask1(List<Animal> inputAnimalList) {
        List<Animal> rightOutput = List.of(
            new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
            new Animal("Pauk1", Animal.Type.SPIDER, Animal.Sex.M, -1, -1, 3, true),
            new Animal("Pauk2", Animal.Type.SPIDER, Animal.Sex.F, 6, 1, 4, true),
            new Animal("Ryba1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Pauk3", Animal.Type.SPIDER, Animal.Sex.M, 15, 6, 7, false),
            new Animal("Ptyza4", Animal.Type.BIRD, Animal.Sex.F, 11, 10, 6, false),
            new Animal("Sobaka3", Animal.Type.DOG, Animal.Sex.F, 17, 15, -2, false),
            new Animal("Ptiza1", Animal.Type.BIRD, Animal.Sex.M, 2, 20, 1, false),
            new Animal("Ptyza5", Animal.Type.BIRD, Animal.Sex.F, 14, 21, 10, false),
            new Animal("Cot1", Animal.Type.CAT, Animal.Sex.M, 3, 25, -5, false),
            new Animal("Sobaka1", Animal.Type.DOG, Animal.Sex.F, 4, 30, 8, true),
            new Animal("Ptyza3", Animal.Type.BIRD, Animal.Sex.M, 9, 31, 5, false),
            new Animal("Ptyza2", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 2, false),
            new Animal("Cot3", Animal.Type.CAT, Animal.Sex.F, 12, 100, 50, false),
            new Animal("Cot5", Animal.Type.CAT, Animal.Sex.F, 16, 149, 100, false),
            new Animal("Ryba2", Animal.Type.FISH, Animal.Sex.F, 8, 150, 200, false),
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task1(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask2(List<Animal> inputAnimalList) {
        int k = 3;
        List<Animal> rightOutput = List.of(
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true)
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task2(inputAnimalList, k));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask3(List<Animal> inputAnimalList) {
        Map<Animal.Type, Integer> rightOutput = Map.of(
          Animal.Type.DOG, 3,
          Animal.Type.FISH, 4,
          Animal.Type.CAT, 6,
          Animal.Type.BIRD, 5,
          Animal.Type.SPIDER, 3
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task3(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask4(List<Animal> inputAnimalList) {
        Animal rightOutput = new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true);

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task4(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask5(List<Animal> inputAnimalList) {
        Animal.Sex rightOutput = Animal.Sex.M;

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task5(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask6(List<Animal> inputAnimalList) {
        Map<Animal.Type, Animal> rightOutput = Map.of(
            Animal.Type.DOG, new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
            Animal.Type.FISH, new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            Animal.Type.CAT, new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            Animal.Type.BIRD, new Animal("Ptyza5", Animal.Type.BIRD, Animal.Sex.F, 14, 21, 10, false),
            Animal.Type.SPIDER, new Animal("Pauk3", Animal.Type.SPIDER, Animal.Sex.M, 15, 6, 7, false)
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task6(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask7(List<Animal> inputAnimalList) {
        int k = 3;

        Animal rightOutput = new Animal("Ryba1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false);

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task7(inputAnimalList, k));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask8(List<Animal> inputAnimalList) {
        int k = 10;

        Optional<Animal> rightOutput = Optional.of(new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false));

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task8(inputAnimalList, k));
    }

    @Test
    void testTask9() {
        List<Animal> inputAnimalList = List.of(
            new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            new Animal("Ptyza5", Animal.Type.BIRD, Animal.Sex.F, 14, 21, 10, false),
            new Animal("Pauk3", Animal.Type.SPIDER, Animal.Sex.M, 15, 6, 7, false)
        );

        Integer rightOutput = 18;

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task9(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask10(List<Animal> inputAnimalList) {
        List<Animal> rightOutput = List.of(
            new Animal("Cot1", Animal.Type.CAT, Animal.Sex.M, 3, 25, -5, false),
            new Animal("Ryba1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Ptyza2", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 2, false),
            new Animal("Ryba2", Animal.Type.FISH, Animal.Sex.F, 8, 150, 200, false),
            new Animal("Pauk1", Animal.Type.SPIDER, Animal.Sex.M, -1, -1, 3, true),
            new Animal("Pauk2", Animal.Type.SPIDER, Animal.Sex.F, 6, 1, 4, true),
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Sobaka2", Animal.Type.DOG, Animal.Sex.M, 10, -300, 800, false),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ptyza3", Animal.Type.BIRD, Animal.Sex.M, 9, 31, 5, false),
            new Animal("Ptyza4", Animal.Type.BIRD, Animal.Sex.F, 11, 10, 6, false),
            new Animal("Cot3", Animal.Type.CAT, Animal.Sex.F, 12, 100, 50, false),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("Ptyza5", Animal.Type.BIRD, Animal.Sex.F, 14, 21, 10, false),
            new Animal("Pauk3", Animal.Type.SPIDER, Animal.Sex.M, 15, 6, 7, false),
            new Animal("Cot5", Animal.Type.CAT, Animal.Sex.F, 16, 149, 100, false),
            new Animal("Sobaka3", Animal.Type.DOG, Animal.Sex.F, 17, 15, -2, false),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );
        Assertions.assertEquals(rightOutput, HomeWorkUtils.task10(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask11(List<Animal> inputAnimalList) {
        List<Animal> rightOutput = List.of(
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );
        Assertions.assertEquals(rightOutput, HomeWorkUtils.task11(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask12(List<Animal> inputAnimalList) {
        Integer rightOutput = 9;
        Assertions.assertEquals(rightOutput, HomeWorkUtils.task12(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask13(List<Animal> inputAnimalList) {
        List<Animal> rightOutput = new ArrayList<>();
        Assertions.assertEquals(rightOutput, HomeWorkUtils.task13(inputAnimalList));

        List<Animal> changedInputAnimalList = new ArrayList<>(inputAnimalList);
        changedInputAnimalList.add(new Animal("My test animal", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true));
        rightOutput.add(new Animal("My test animal", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true));

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task13(changedInputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask14(List<Animal> inputAnimalList) {
        Assertions.assertTrue(HomeWorkUtils.task14(inputAnimalList, 29));
        Assertions.assertFalse(HomeWorkUtils.task14(inputAnimalList, 31));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask15(List<Animal> inputAnimalList) {
        Assertions.assertEquals(598, HomeWorkUtils.task15(inputAnimalList, 16, 20));
    }

    @Test
    void testTask16() {
        List<Animal> inputAnimalsList = List.of(
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            new Animal("Cot22", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba33", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba44", Animal.Type.FISH, Animal.Sex.M, 13, 400, 601, true),
            new Animal("Cot44", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("ABRAKADABRA1", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );

        List<Animal> rightOutput = List.of(
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            new Animal("ABRAKADABRA1", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true),
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Cot22", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("Cot44", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba33", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("Ryba44", Animal.Type.FISH, Animal.Sex.M, 13, 400, 601, true)
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task16(inputAnimalsList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask17(List<Animal> inputAnimalList) {
        Assertions.assertTrue(HomeWorkUtils.task17(inputAnimalList));
    }

    @Test
    void testTask18() {
        List<Animal> firstList = List.of(
            new Animal("Cot2", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba3", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba4", Animal.Type.FISH, Animal.Sex.M, 13, 400, 600, true),
            new Animal("Cot4", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("ABRAKADABRA", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );

        List<Animal> secondList = List.of(
            new Animal("Cot22", Animal.Type.CAT, Animal.Sex.M, 7, 151, 201, true),
            new Animal("Ryba33", Animal.Type.FISH, Animal.Sex.M, 20, 200, 500, true),
            new Animal("Ryba44", Animal.Type.FISH, Animal.Sex.M, 13, 400, 601, true),
            new Animal("Cot44", Animal.Type.CAT, Animal.Sex.M, -8, 201, 300, true),
            new Animal("ABRAKADABRA1", Animal.Type.CAT, Animal.Sex.M, 1000, 1000, 1000, true)
        );

        List<List<Animal>> inputAnimalLists = new ArrayList<>();
        inputAnimalLists.add(firstList);
        inputAnimalLists.add(secondList);

        Animal rightOutput = new Animal("Ryba44", Animal.Type.FISH, Animal.Sex.M, 13, 400, 601, true);

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task18(inputAnimalLists));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask19(List<Animal> inputAnimalList) {
        Set<ValidationError> sobaka2Errors = Set.of(
            new ValidationError("height", "animal height less than zero")
        );

        Set<ValidationError> sobaka3Errors = Set.of(
            new ValidationError("weight", "animal weight less than zero")
        );

        Set<ValidationError> Cot1Errors = Set.of(
            new ValidationError("weight", "animal weight less than zero")
        );

        Set<ValidationError> Cot4Errors = Set.of(
            new ValidationError("age", "animal age less than zero")
        );

        Set<ValidationError> Pauk1Errors = Set.of(
            new ValidationError("age", "animal age less than zero"),
            new ValidationError("height", "animal height less than zero")
        );

        Map<String, Set<ValidationError>> rightOutput = Map.of(
            "Sobaka2", sobaka2Errors,
            "Sobaka3", sobaka3Errors,
            "Cot1", Cot1Errors,
            "Cot4", Cot4Errors,
            "Pauk1", Pauk1Errors
        );

        Assertions.assertEquals(rightOutput, HomeWorkUtils.task19(inputAnimalList));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testTask20(List<Animal> inputAnimalList) {
        Map<String, String> rightOutput = Map.of(
            "Sobaka2", "height: animal height less than zero",
            "Sobaka3", "weight: animal weight less than zero",
            "Cot1", "weight: animal weight less than zero",
            "Cot4", "age: animal age less than zero",
            "Pauk1", "age: animal age less than zero, height: animal height less than zero"
        );
        Assertions.assertEquals(rightOutput, HomeWorkUtils.task20(inputAnimalList));
    }
}
