package edu.hw5.task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarNumberChecker {

    private CarNumberChecker() {

    }

    public static boolean isRightCarNumber(String carNumber) {
        String regex = "^[А-Я][0-9]{3}[А-Я]{2}[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(carNumber);
        return matcher.matches();
    }
}
