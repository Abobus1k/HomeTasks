package edu.project1;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DictionaryTest {
    private static Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    void testAddWord() {
        final String word = "apple";

        dictionary.addWord(word);

        Assertions.assertEquals("apple", dictionary.getWord());
    }

    @Test
    void testGetWord() {
        NoSuchElementException thrown = assertThrows(
            NoSuchElementException.class,
            () -> dictionary.getWord(),
            "Expected getWord() to throw, but it didn't"
        );

        Assertions.assertEquals("No words in the dictionary.", thrown.getMessage());
    }

}
