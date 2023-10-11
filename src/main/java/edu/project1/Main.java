package edu.project1;

import java.util.Scanner;


public final class Main {

    private static final int ATTEMPTS = 5;

    private Main() {

    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        addSomeWords(dictionary);
        HangmanWord hangmanWord = new HangmanWord(dictionary.getWord());
        Hangman hangman = new Hangman(hangmanWord, ATTEMPTS);
        hangman.playGame(new Scanner(System.in));
    }

    public static void addSomeWords(Dictionary dictionary) {
        dictionary.addWord("apple");
        dictionary.addWord("watermelon");
        dictionary.addWord("orange");
    }
}







