package edu.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static long seconds(String time) {
        String rightForm = "^\\d{2,}:\\d{2}$";
        boolean correct = true;

        Pattern pattern = Pattern.compile(rightForm);
        Matcher matcher = pattern.matcher(time);

        if (!matcher.matches()) return -1;

        String[] splitTime = time.split(":");

        long minutesToSeconds = Long.parseLong(splitTime[0]);
        long countOfSeconds = Long.parseLong(splitTime[1]);

        if (countOfSeconds < 0 || countOfSeconds >= 60) correct = false;

        return correct ? minutesToSeconds * 60 + countOfSeconds : -1;
    }
}
