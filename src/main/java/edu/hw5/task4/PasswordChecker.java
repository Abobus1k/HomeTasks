package edu.hw5.task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    private PasswordChecker() {

    }

    public static boolean isStrongPassword(String password) {
        String regex = ".*[~!@#$%^&*|].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
