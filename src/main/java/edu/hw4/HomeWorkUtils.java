package edu.hw4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class HomeWorkUtils {
    private HomeWorkUtils() {

    }

    public static List<Animal> task1(List<Animal> animalList) {
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> task2(List<Animal> animalList, int k) {
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public static Animal task4(List<Animal> animalList) {
        return animalList.stream()
            .max(Comparator.comparingInt(firstAnimal -> firstAnimal.name().length())).orElseThrow(
                NoSuchElementException::new);
    }

    public static Animal.Sex task5(List<Animal> animalList) {
        long maleCount = animalList.stream()
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();

        long femaleCount = animalList.stream()
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();

        return maleCount > femaleCount ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(), (x, y) -> x.weight() > y.weight() ? x : y));
    }

    public static Animal task7(List<Animal> animalList, int k) {
        if (k >= animalList.size() || k < 0) {
            return null;
        }

        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::age))
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public static Optional<Animal> task8(List<Animal> animalList, int k) {
        return animalList.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(List<Animal> animalList) {
        return animalList.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> task10(List<Animal> animalList) {
        return animalList.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animalList) {
        return animalList.stream()
            .filter(animal -> (animal.bites() && animal.height() > 100))
            .collect(Collectors.toList());
    }

    public static Integer task12(List<Animal> animalList) {
        return (int) animalList.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> task13(List<Animal> animalList) {
        return animalList.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static  Boolean task14(List<Animal> animalList, int k) {
        return animalList.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k)
            .anyMatch(animal -> true);
    }

    public static Integer task15(List<Animal> animalList, int k, int l) {
        return animalList.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> task16(List<Animal> animalList) {
        return animalList.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static Boolean task17(List<Animal> animalList) {
        long biteSpiders = animalList.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long biteDogs = animalList.stream()
            .filter((animal -> animal.type() == Animal.Type.DOG && animal.bites()))
            .count();

        return biteSpiders > biteDogs;
    }

    public static Animal task18(List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name().isEmpty()) {
            errors.add(new ValidationError("name", "animal name is empty"));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("age", "animal age less than zero"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("weight", "animal weight less than zero"));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("height", "animal height less than zero"));
        }

        if (animal.paws() < 0) {
            errors.add(new ValidationError("paws", "animal number of paws less than zero"));
        }

        return errors;
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.toMap(
                Animal::name,
                HomeWorkUtils::validateAnimal
            ))
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> task20(List<Animal> animalList) {
        return animalList.stream()
            .flatMap(animal -> {
                Set<ValidationError> errors = validateAnimal(animal);
                return errors.stream()
                    .map(error -> new ValidationError(animal.name(), error.getField() + ": " + error.getMessage()));
            })
            .collect(Collectors.groupingBy(
                ValidationError::getField,
                Collectors.mapping(ValidationError::getMessage, Collectors.joining(", "))
            ));
    }
}
