package edu.hw3.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.task1.Task1Util.atbash;

public class Task1UtilTest {

    @Test
    void testHelloWorld() {
        String helloWorld = "Hello world!";

        String encryptedHelloWorld = atbash(helloWorld);

        Assertions.assertEquals("Svool dliow!", encryptedHelloWorld);
    }

    @Test
    void testEmptyString() {
        String zeroString = "";

        String encryptedZeroString = atbash(zeroString);

        Assertions.assertEquals("", encryptedZeroString);
    }

    @Test
    @DisplayName("Проверка сложной строки")
    void testSomethingHard() {
        String testString = "SoMethinG unUsual";

        String encryptedHardString = atbash(testString);

        Assertions.assertEquals("HlNvgsrmT fmFhfzo", encryptedHardString);
    }
}
