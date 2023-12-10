package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Matcher;
import static edu.project3.utils.DateParser.parseLogDate;
import static edu.project3.utils.LogParser.parseLog;
import static edu.project3.utils.Reader.getBufferedReader;

@SuppressWarnings("MagicNumber")
public class LogProcessor {
    private final Map<String, Integer> resourceCount;
    private final Map<Integer, Integer> responseCodeCount;
    private final Map<String, Integer> ipRequestCount;
    private final Map<String, Integer> userAgentCount;
    private int totalRequests;
    private double averageResponseSize;

    public LogProcessor(Stats stats) {
        this.resourceCount = stats.getStatistic("resourceCount");
        this.responseCodeCount = stats.getStatistic("responseCodeCount");
        this.ipRequestCount = stats.getStatistic("ipRequestCount");
        this.userAgentCount = stats.getStatistic("userAgentCount");
        this.totalRequests = 0;
        this.averageResponseSize = 0;
    }

    public void processLogs(String path, LocalDateTime fromDate, LocalDateTime toDate) {
        try (BufferedReader reader = getBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = parseLog(line);
                if (matcher.matches()) {
                    LocalDateTime logDate = parseLogDate(matcher.group(3));
                    if ((toDate == null || logDate.isBefore(toDate) || logDate.isEqual(toDate))
                        && (fromDate == null || logDate.isAfter(fromDate) || logDate.isEqual(fromDate))) {
                        processLogEntry(matcher);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Неправильный формат вывода");
        }
    }

    void processLogEntry(Matcher matcher) {
        String ipAddress = matcher.group(1);
        String resource = extractResource(matcher.group(4));
        String userAgent = matcher.group(8);
        int responseCode = Integer.parseInt(matcher.group(5));
        int responseSize = Integer.parseInt(matcher.group(6));

        totalRequests++;
        averageResponseSize = averageResponseSize / totalRequests * (totalRequests - 1)
            + (double) responseSize / totalRequests;

        resourceCount.put(resource, resourceCount.getOrDefault(resource, 0) + 1);
        responseCodeCount.put(responseCode, responseCodeCount.getOrDefault(responseCode, 0) + 1);
        ipRequestCount.put(ipAddress, ipRequestCount.getOrDefault(ipAddress, 0) + 1);
        userAgentCount.put(userAgent, userAgentCount.getOrDefault(userAgent, 0) + 1);
    }

    public Map<String, Integer> getResourceCount() {
        return resourceCount;
    }

    public Map<Integer, Integer> getResponseCodeCount() {
        return responseCodeCount;
    }

    public Map<String, Integer> getIpRequestCount() {
        return ipRequestCount;
    }

    public Map<String, Integer> getUserAgentCount() {
        return userAgentCount;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public double getAverageResponseSize() {
        return averageResponseSize;
    }

    private static String extractResource(String request) {
        String[] parts = request.split("\\s+");
        return parts.length > 1 ? parts[1] : request;
    }
}
