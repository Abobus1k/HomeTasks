package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayDateHandler implements DateHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        if ("yesterday".equals(dateString)) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        return Optional.empty();
    }
}
