package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hangman {
    private final HangmanWord hangmanWord;
    private int attempts;
    private static final Logger LOGGER = LogManager.getLogger();

    public Hangman(HangmanWord hangmanWord, int attempts) {
        this.hangmanWord = hangmanWord;
        this.attempts = attempts;
    }

    public void playGame(Scanner scanner) {
        while (attempts > 0 && !hangmanWord.gameWordIsGuessed()) {
            displayGameStatus();
            String input = getInput(scanner);

            if (input.equalsIgnoreCase("quit")) {
                handleQuit();
                break;
            }

            handleGuess(input);
        }

        if (hangmanWord.gameWordIsGuessed()) {
            LOGGER.info("Congratulations! You guessed the word: {}", hangmanWord.getGuessedWord());
        } else if (attempts == 0) {
            LOGGER.info("You ran out of attempts. The word was: {}", hangmanWord.getWord());
        }
    }

    private void displayGameStatus() {
        LOGGER.info("Guess the word: {}", hangmanWord.getGuessedWord());
        LOGGER.info("Attempts left: {}", attempts);
        LOGGER.info("Enter a letter or type 'quit' to give up: ");
    }

    private String getInput(Scanner scanner) {
        return scanner.nextLine();
    }

    private void handleQuit() {
        LOGGER.info("You gave up. The word was: {}", hangmanWord.getWord());
    }

    private void handleGuess(String input) {
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            LOGGER.info("Please enter a single letter.");
        } else {
            char letter = input.charAt(0);

            if (hangmanWord.guessLetter(letter)) {
                LOGGER.info("Hit!");
            } else {
                LOGGER.info("Miss.");
                attempts--;
            }
        }
    }
}
