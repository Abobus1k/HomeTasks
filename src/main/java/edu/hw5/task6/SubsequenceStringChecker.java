package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubsequenceStringChecker {

    private SubsequenceStringChecker() {

    }

    public static boolean isSubsequence(String s, String t) {
        String regex = "^.*" + s + ".*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(t);
        return matcher.matches();
    }
}
