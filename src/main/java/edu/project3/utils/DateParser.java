package edu.project3.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateParser {

    private DateParser() {}

    public static LocalDateTime parseLogDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

        try {
            return LocalDateTime.parse(dateStr, inputDateFormat);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDateTime parseCmdDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        DateTimeFormatter cmdDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        try {
            return LocalDate.parse(dateStr, cmdDateFormat).atStartOfDay();
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
