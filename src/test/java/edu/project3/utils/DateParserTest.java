package edu.project3.utils;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DateParserTest {

    @Test
    void testParseLogDate() {
        String dateStr = "07/Oct/2023:22:50:36 +0000";
        LocalDateTime result = DateParser.parseLogDate(dateStr);
        assertThat(result).isNotNull();
        assertThat(result.getYear()).isEqualTo(2023);
        assertThat(result.getMonthValue()).isEqualTo(10);
        assertThat(result.getDayOfMonth()).isEqualTo(7);
        assertThat(result.getHour()).isEqualTo(22);
        assertThat(result.getMinute()).isEqualTo(50);
        assertThat(result.getSecond()).isEqualTo(36);
    }

    @Test
    void testParseInvalidLogDate() {
        String dateStr = "invalid_date";
        LocalDateTime result = DateParser.parseLogDate(dateStr);
        assertThat(result).isNull();
    }

    @Test
    void testParseCmdDate() {
        String dateStr = "2023-10-07";
        LocalDateTime result = DateParser.parseCmdDate(dateStr);
        assertThat(result).isNotNull();
        assertThat(result.getYear()).isEqualTo(2023);
        assertThat(result.getMonthValue()).isEqualTo(10);
        assertThat(result.getDayOfMonth()).isEqualTo(7);
        assertThat(result.getHour()).isEqualTo(0);
        assertThat(result.getMinute()).isEqualTo(0);
        assertThat(result.getSecond()).isEqualTo(0);
    }

    @Test
    void testParseInvalidCmdDate() {
        String dateStr = "invalid_date";
        LocalDateTime result = DateParser.parseCmdDate(dateStr);
        assertThat(result).isNull();
    }
}
