package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@SuppressWarnings("MagicNumber")
public class NextFridayThe13th implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);

        do {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (date.getDayOfMonth() != 13);

        return date;
    }
}
