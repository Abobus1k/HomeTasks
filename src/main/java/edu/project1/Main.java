package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    private Main() {

    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("apple");
        dictionary.addWord("orange");
        dictionary.addWord("watermelon");

        Scanner scanner = new Scanner(System.in);
        Game game = new Game(dictionary.getWord());

        int attempts = 5;

        while (attempts > 0 && !game.isGuessed()) {
            LOGGER.info("Guess the word: {}",game.getGuessedWord());


            LOGGER.info("Attempts left: {}",attempts);
            LOGGER.info("Enter a letter or type 'quit' to give up: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                LOGGER.info("You gave up. The word was: {}",game.getWord());
                break;
            }

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                LOGGER.info("Please enter a single letter.");
            } else {
                char letter = input.charAt(0);

                if (game.guessLetter(letter)) {
                    LOGGER.info("Hit!");
                } else {
                    LOGGER.info("Miss(.");
                    attempts--;
                }
            }
        }

        if (game.isGuessed()) {
            LOGGER.info("Congratulations! You guessed the word: {}",game.getGuessedWord());
        } else if (attempts == 0) {
            LOGGER.info("You ran out of attempts. The word was: {}",game.getWord());
        }

        scanner.close();
    }
}

class Game {
    private final char[] word;
    private Map<Character,List<Integer>> map;

    private final char[] guessWord;

    private int closedLetters;

    public Game(String word) {
        this.word = word.toCharArray();
        fillMap();
        guessWord = new char[this.word.length];
        Arrays.fill(guessWord,'*');
        closedLetters = this.word.length;
    }

    public boolean guessLetter(char letter) {
        if (!map.containsKey(letter)) {
            return false;
        }
        var currentList = map.get(letter);
        for (Integer index : currentList) {
            guessWord[index] = letter;
            closedLetters--;
        }
        return true;
    }

    public final void fillMap() {
        map = new HashMap<>();
        for (int i = 0; i < word.length; i++) {
            char letter = word[i];
            map.computeIfAbsent(letter, key -> new ArrayList<>()).add(i);
        }
    }

    public String getGuessedWord() {
        return new String(guessWord);
    }

    public boolean isGuessed() {
        return closedLetters == 0;
    }

    public String getWord() {
        return new String(word);
    }

}


class Dictionary {
    private final List<String> words;
    public final Random random;

    public Dictionary() {
        words = new ArrayList<>();
        random = new Random();
    }
    public void addWord(String word) {
        words.add(word);
    }

    public String getWord() {
        if (words.isEmpty()) {
            throw new NoSuchElementException("No words in the dictionary.");
        }
        return words.get(random.nextInt(words.size()));
    }
}


