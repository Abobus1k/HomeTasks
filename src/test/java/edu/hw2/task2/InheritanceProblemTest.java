package edu.hw2.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InheritanceProblemTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);

        Assertions.assertEquals(200.0, rect.area());
    }

    @Test
    void squareArea() {
        Square square = new Square();

        square.setHeight(50);

        Assertions.assertEquals(2500, square.area());
    }

    @Test
    void rectangleAfterSquareArea() {
        Square square = new Square();

        square.setHeight(50);
        square.setWidth(100);

        Assertions.assertEquals(5000, square.area());
    }
}
