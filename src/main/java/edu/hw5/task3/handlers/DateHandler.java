package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public interface DateHandler {
    Optional<LocalDate> parseDate(String dateString);
}
