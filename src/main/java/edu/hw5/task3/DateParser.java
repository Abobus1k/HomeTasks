package edu.hw5.task3;

import edu.hw5.task3.handlers.DateHandler;
import edu.hw5.task3.handlers.DaysAgoHandler;
import edu.hw5.task3.handlers.DefaultDateHandler;
import edu.hw5.task3.handlers.OneDayAgoHandler;
import edu.hw5.task3.handlers.TodayDateHandler;
import edu.hw5.task3.handlers.TomorrowDateHandler;
import edu.hw5.task3.handlers.YesterdayDateHandler;
import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private final DateHandler[] handlers;

    public DateParser() {
        handlers = new DateHandler[]{
            new DefaultDateHandler("yyyy-MM-dd"),
            new DefaultDateHandler("yyyy-MM-d"),
            new DefaultDateHandler("d/M/yyyy"),
            new DefaultDateHandler("d/M/yy"),
            new DefaultDateHandler("yyyy/M/d"),
            new TomorrowDateHandler(),
            new TodayDateHandler(),
            new YesterdayDateHandler(),
            new OneDayAgoHandler(),
            new DaysAgoHandler()
        };
    }

    public Optional<LocalDate> parseDate(String dateString) {
        for (DateHandler handler : handlers) {
            Optional<LocalDate> result = handler.parseDate(dateString);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }
}
