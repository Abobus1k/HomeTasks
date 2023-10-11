package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Dictionary {
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
