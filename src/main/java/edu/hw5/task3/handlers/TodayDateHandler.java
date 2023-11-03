package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class TodayDateHandler implements DateHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        if ("today".equals(dateString)) {
            return Optional.of(LocalDate.now());
        }
        return Optional.empty();
    }
}
