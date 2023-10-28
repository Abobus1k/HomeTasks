package edu.hw3.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw3.task2.Task2Util.clasterize;

public class Task2UtilTest {
    @Test
    void testFirstExample() {
        String exampleString = "()()()";

        String clasterizedString = clasterize(exampleString).toString();

        Assertions.assertEquals("[(), (), ()]", clasterizedString);
    }

    @Test
    void testSecondExample() {
        String exampleString = "((()))";

        String clasterizedString = clasterize(exampleString).toString();

        Assertions.assertEquals("[((()))]", clasterizedString);
    }

    @Test
    void testThirdExample() {
        String exampleString = "((())())(()(()()))";

        String clasterizedString = clasterize(exampleString).toString();

        Assertions.assertEquals("[((())()), (()(()()))]", clasterizedString);
    }

    @Test
    void testFourthExample() {
        String exampleString = "((()))(())()()(()())";

        String clasterizedString = clasterize(exampleString).toString();

        Assertions.assertEquals("[((())), (()), (), (), (()())]", clasterizedString);
    }

    @Test
    void testWrongString() {
        String wrongInput = "((())";

        boolean exceptionThrown = false;

        try {
            String clasterizedString = clasterize(wrongInput).toString();
        } catch (InvalidStringException invalidStringException) {
            exceptionThrown = true;
        }
        Assertions.assertTrue(exceptionThrown);
    }
}
