package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class Task2Util {

    private Task2Util() {}

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public static List<String> clasterize(String text) {
        List<String> cluster = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar == OPEN_BRACKET) {
                int openBracketsCount = 1;
                StringBuilder currentGroup = new StringBuilder();

                currentGroup.append(OPEN_BRACKET);

                while (i+1 < text.length() && openBracketsCount > 0) {
                    i++;

                    currentGroup.append(text.charAt(i));

                    if (text.charAt(i) == OPEN_BRACKET) {
                        openBracketsCount++;
                    }
                    if (text.charAt(i) == CLOSE_BRACKET) {
                        openBracketsCount--;
                    }
                }

                cluster.add(currentGroup.toString());

                if (i == text.length() - 1 && openBracketsCount > 0) {
                    throw new InvalidStringException("Wrong input string");
                }
            }
        }
        return cluster;
    }
}
