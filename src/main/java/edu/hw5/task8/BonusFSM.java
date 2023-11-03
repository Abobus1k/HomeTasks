package edu.hw5.task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 4 таски так как сказано что на доп балл половину нужно решить
public class BonusFSM {

    private BonusFSM() {

    }

    // первая из дз
    public static boolean isFirstRule(String input) {
        String regex = "^[01]([01]{2})*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    // вторая из дз
    public static boolean isSecondRule(String input) {
        String regex = "^(0([01]{2})*|1[01]([01]{2})*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    // пятая из дз
    public static boolean isThirdRule(String input) {
        String regex = "^(1[01])*1?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    // четвертая из дз
    public static boolean isFourthRule(String input) {
        String regex = "^(?!11$|111$)[01]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
