package edu.hw3.task1;

public final class Task1Util {

    private Task1Util() {}

    private static final char LOWERCASE_START_CHAR = 'a';
    private static final char UPPERCASE_START_CHAR = 'A';
    private static final char LOWERCASE_END_CHAR = 'z';
    private static final char UPPERCASE_END_CHAR = 'Z';

    public static String atbash(String text) {
        StringBuilder encryptedString = new StringBuilder();

        for (char ch: text.toCharArray()) {
            char currentChar = ch;

            if (Character.isLetter(currentChar)) {
                currentChar = encryptLetter(currentChar);
            }

            encryptedString.append(currentChar);
        }

        return encryptedString.toString();
    }

    private static char encryptLetter(char letter) {
        boolean firstHalf = true;

        if (Character.isLowerCase(letter)) {
            return (char) (LOWERCASE_START_CHAR + (LOWERCASE_END_CHAR - letter));
        }

        return (char) (UPPERCASE_START_CHAR + (UPPERCASE_END_CHAR - letter));
    }
}
