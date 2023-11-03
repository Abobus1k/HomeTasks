package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class TomorrowDateHandler implements DateHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        if ("tomorrow".equals(dateString)) {
            return Optional.of(LocalDate.now().plusDays(1));
        }
        return Optional.empty();
    }
}
