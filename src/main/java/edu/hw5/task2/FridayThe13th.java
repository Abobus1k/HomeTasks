package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FridayThe13th {

    private static final int MONTHS_NUMBER = 12;

    private FridayThe13th() {

    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> findAllFridays(int year) {
        List<LocalDate> fridays = new ArrayList<>();

        for (int month = 1; month <= MONTHS_NUMBER; month++) {
            LocalDate date = LocalDate.of(year, month, 13);

            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(date);
            }
        }
        return fridays;
    }
}
