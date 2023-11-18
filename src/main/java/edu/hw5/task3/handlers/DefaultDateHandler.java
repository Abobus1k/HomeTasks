package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DefaultDateHandler implements DateHandler {
    private final DateTimeFormatter formatter;

    public DefaultDateHandler(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return Optional.of(date);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
