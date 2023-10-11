package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTest {
    private static HangmanWord hangmanWord;

    @BeforeEach
    void setUp() {
        hangmanWord = new HangmanWord("apple");
    }

    @Test
    void testGuessLetter() {
        Assertions.assertTrue(hangmanWord.guessLetter('a'));
        Assertions.assertFalse(hangmanWord.guessLetter('b'));
    }

    @Test
    void testIsGuessed() {
        Assertions.assertFalse(hangmanWord.gameWordIsGuessed());

        hangmanWord.guessLetter('a');
        hangmanWord.guessLetter('p');

        Assertions.assertFalse(hangmanWord.gameWordIsGuessed());

        hangmanWord.guessLetter('l');
        hangmanWord.guessLetter('e');

        Assertions.assertTrue(hangmanWord.gameWordIsGuessed());
    }

    @Test
    void testHalfGuessedWord() {

        hangmanWord.guessLetter('a');
        hangmanWord.guessLetter('p');

        Assertions.assertEquals("app**", hangmanWord.getGuessedWord());
    }

    @Test
    void testGuessedWordWithoutGuesses() {
        Assertions.assertEquals("*****", hangmanWord.getGuessedWord());
    }
}
