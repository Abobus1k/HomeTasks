package edu.hw5.task7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FSM {

    private FSM() {

    }

    public static boolean isFirstRule(String input) {
        String regex = "^[01]{2}0[01]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isSecondRule(String input) {
        String regex = "^([01])[01]*\\1$|^[01]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isThirdRule(String input) {
        String regex = "^[01]{1,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
