package edu.project3.utils;

import java.util.regex.Matcher;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogParserTest {

    @Test
    void testParseLog() {
        String log = "46.4.66.76 - - [17/May/2015:08:05:45 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 318 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"";
        Matcher result = LogParser.parseLog(log);
        assertThat(result).isNotNull();
        assertThat(result.matches()).isTrue();
        assertThat(result.group(1)).isEqualTo("46.4.66.76");
        assertThat(result.group(2)).isEqualTo("-");
        assertThat(result.group(3)).isEqualTo("17/May/2015:08:05:45 +0000");
        assertThat(result.group(4)).isEqualTo("GET /downloads/product_1 HTTP/1.1");
        assertThat(result.group(5)).isEqualTo("404");
        assertThat(result.group(6)).isEqualTo("318");
        assertThat(result.group(7)).isEqualTo("-");
        assertThat(result.group(8)).isEqualTo("Debian APT-HTTP/1.3 (1.0.1ubuntu2)");
    }

    @Test
    void testParseInvalidLog() {
        String log = "invalid_log";
        Matcher result = LogParser.parseLog(log);
        assertThat(result).isNotNull();
        assertThat(result.matches()).isFalse();
    }
}
