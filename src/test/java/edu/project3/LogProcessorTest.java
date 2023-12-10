package edu.project3;

import edu.project3.utils.LogParser;
import edu.project3.utils.StatsGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LogProcessorTest {

    @Test
    void testProcessLogEntry() {
        String log = "46.4.66.76 - - [17/May/2015:08:05:45 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 318 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"";
        Matcher matcher = LogParser.parseLog(log);

        assertThat(matcher).isNotNull();
        assertThat(matcher.matches()).isTrue();

        Stats stats = new Stats(StatsGenerator.statics());
        LogProcessor logProcessor = new LogProcessor(stats);

        logProcessor.processLogEntry(matcher);

        assertThat(logProcessor.getResourceCount()).containsEntry("/downloads/product_1", 1);
        assertThat(logProcessor.getResponseCodeCount()).containsEntry(404, 1);
        assertThat(logProcessor.getIpRequestCount()).containsEntry("46.4.66.76", 1);
        assertThat(logProcessor.getUserAgentCount()).containsEntry("Debian APT-HTTP/1.3 (1.0.1ubuntu2)", 1);
        assertThat(logProcessor.getTotalRequests()).isEqualTo(1);
        assertThat(logProcessor.getAverageResponseSize()).isEqualTo(318.0);
    }

    @Test
    void testProcessLogsFromFile() throws IOException {
        Path tempLogFile = Files.createTempFile("test_logs", ".txt");
        Files.write(tempLogFile, """
            93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
            217.168.17.5 - - [17/May/2015:08:05:34 +0000] "GET /downloads/product_1 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
            217.168.17.5 - - [17/May/2015:08:05:09 +0000] "GET /downloads/product_2 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
            93.180.71.3 - - [17/May/2015:08:05:57 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            217.168.17.5 - - [17/May/2015:08:05:02 +0000] "GET /downloads/product_2 HTTP/1.1" 404 337 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
            217.168.17.5 - - [17/May/2015:08:05:42 +0000] "GET /downloads/product_1 HTTP/1.1" 404 332 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
            80.91.33.133 - - [17/May/2015:08:05:01 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
                """.getBytes());

        LocalDateTime fromDate = LocalDateTime.parse("2015-05-17T00:00:00");
        LocalDateTime toDate = LocalDateTime.parse("2015-05-18T00:00:00");

        Stats stats = new Stats(StatsGenerator.statics());
        LogProcessor logProcessor = new LogProcessor(stats);

        logProcessor.processLogs(tempLogFile.toString(), fromDate, toDate);

        assertThat(logProcessor.getResourceCount()).containsEntry("/downloads/product_1", 7);
        assertThat(logProcessor.getResourceCount()).containsEntry("/downloads/product_2", 2);
        assertThat(logProcessor.getResponseCodeCount()).containsEntry(304, 5);
        assertThat(logProcessor.getResponseCodeCount()).containsEntry(200, 2);
        assertThat(logProcessor.getResponseCodeCount()).containsEntry(404, 2);
        assertThat(logProcessor.getIpRequestCount()).containsEntry("93.180.71.3", 3);
        assertThat(logProcessor.getIpRequestCount()).containsEntry("80.91.33.133", 2);
        assertThat(logProcessor.getIpRequestCount()).containsEntry("217.168.17.5", 4);
        assertThat(logProcessor.getTotalRequests()).isEqualTo(9);
        assertThat((int) logProcessor.getAverageResponseSize()).isEqualTo(183);
    }
}

