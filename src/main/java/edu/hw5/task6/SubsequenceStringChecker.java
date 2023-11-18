package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SubsequenceStringChecker {

    private SubsequenceStringChecker() {

    }

    public static boolean isSubsequence(String s, String t) {
        String regex = ".*" + s.chars().mapToObj(c -> "(?=.*" + (char) c + ")").collect(Collectors.joining()) + ".*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(t);
        return matcher.matches();
    }
}
