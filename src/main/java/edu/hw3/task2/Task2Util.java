package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public final class Task2Util {

    private Task2Util() {}

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public static List<String> clasterize(String text) {
        List<String> cluster = new ArrayList<>();

        int index = 0;
        while (index < text.length()) {
            char currentChar = text.charAt(index);

            if (currentChar == OPEN_BRACKET) {
                int openBracketsCount = 1;
                StringBuilder currentGroup = new StringBuilder();

                currentGroup.append(OPEN_BRACKET);

                while (index + 1 < text.length() && openBracketsCount > 0) {
                    index++;

                    currentGroup.append(text.charAt(index));

                    if (text.charAt(index) == OPEN_BRACKET) {
                        openBracketsCount++;
                    }
                    if (text.charAt(index) == CLOSE_BRACKET) {
                        openBracketsCount--;
                    }
                }

                cluster.add(currentGroup.toString());

                if (index == text.length() - 1 && openBracketsCount > 0) {
                    throw new InvalidStringException("Wrong input string");
                }
            }
            index++;
        }
        return cluster;
    }
}
