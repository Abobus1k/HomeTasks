package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComputerClubAnalytics {
    private final List<String> sessions;

    public ComputerClubAnalytics(List<String> sessions) {
        this.sessions = sessions;
    }

    public String getAverageSession() {
        Duration duration = Duration.ZERO;

        for (String session : sessions) {
            duration = duration.plus(sessionDuration(session));
        }

        return reinterpretDuration(duration.dividedBy(sessions.size()));
    }

    public static Duration sessionDuration(String inputSession) {
        String[] parts = inputSession.split(" - ");
        String stringStartTime = parts[0];
        String stringEndTime = parts[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(stringStartTime, formatter);
        LocalDateTime endTime = LocalDateTime.parse(stringEndTime, formatter);

        return Duration.between(startTime, endTime);
    }

    public static String reinterpretDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return hours + "ч " + minutes + "м";
    }
}
