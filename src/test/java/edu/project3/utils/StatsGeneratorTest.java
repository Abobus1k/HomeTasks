package edu.project3.utils;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class StatsGeneratorTest {

    @Test
    void testStatsGenerator() {
        Map<String, Map<?, ?>> stats = StatsGenerator.statics();

        assertThat(stats).isNotNull();
        assertThat(stats).containsKeys("resourceCount", "responseCodeCount", "ipRequestCount", "userAgentCount");

        Map<?, ?> resourceCount = stats.get("resourceCount");
        assertThat(resourceCount).isNotNull().isEmpty();

        Map<?, ?> responseCodeCount = stats.get("responseCodeCount");
        assertThat(responseCodeCount).isNotNull().isEmpty();

        Map<?, ?> ipRequestCount = stats.get("ipRequestCount");
        assertThat(ipRequestCount).isNotNull().isEmpty();

        Map<?, ?> userAgentCount = stats.get("userAgentCount");
        assertThat(userAgentCount).isNotNull().isEmpty();
    }
}
