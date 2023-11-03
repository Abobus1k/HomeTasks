package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoHandler implements DateHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        if (dateString.matches("\\d+ days ago")) {
            int daysAgo = Integer.parseInt(dateString.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(daysAgo));
        }
        return Optional.empty();
    }
}
