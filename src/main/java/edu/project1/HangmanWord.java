package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HangmanWord {
    private final char[] word;
    private Map<Character, List<Integer>> map;

    private final char[] guessWord;

    private int closedLetters;

    public HangmanWord(String word) {
        this.word = word.toCharArray();
        fillMap();
        guessWord = new char[this.word.length];
        Arrays.fill(guessWord, '*');
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

    public boolean gameWordIsGuessed() {
        return closedLetters == 0;
    }

    public String getWord() {
        return new String(word);
    }

}
