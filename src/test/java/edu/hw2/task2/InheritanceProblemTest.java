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
        Rectangle firstChangedRect = rect.setWidth(20);
        Rectangle finalRect = firstChangedRect.setHeight(10);

        Assertions.assertEquals(200.0, finalRect.area());
    }

    @Test
    void squareArea() {
        Square square = new Square();

        Square resultSquare = square.setSide(50);

        Assertions.assertEquals(2500, resultSquare.area());
    }

    @Test
    void rectangleAfterSquareArea() {
        Square square = new Square();

        Rectangle squareAfterChange = square.setHeight(50);
        Rectangle resultRect = squareAfterChange.setWidth(100);

        Assertions.assertEquals(5000, resultRect.area());
    }
}
