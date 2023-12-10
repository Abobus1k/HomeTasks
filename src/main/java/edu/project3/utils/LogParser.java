package edu.project3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private LogParser() {}

    public static Matcher parseLog(String log) {
        String logPattern = getLogPattern();
        Pattern pattern = Pattern.compile(logPattern);
        return pattern.matcher(log);
    }

    public static String getLogPattern() {
        return "([\\d.]+) - ([\\w-]+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"([A-Z]+ .+?)\""
            + " (\\d+) (\\d+) \"(.+?)\" \"(.+?)\"";
    }
}
