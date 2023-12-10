package edu.project3;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StatsTest {

    @Test
    void testGetStatistic() {
        Map<String, Map<?, ?>> statisticsMap = new HashMap<>();
        Map<String, Integer> resourceCount = new HashMap<>();
        resourceCount.put("resource1", 10);
        resourceCount.put("resource2", 20);
        statisticsMap.put("resourceCount", resourceCount);

        Stats stats = new Stats(statisticsMap);

        Map<String, Integer> result = stats.getStatistic("resourceCount");

        assertThat(result).containsEntry("resource1", 10);
        assertThat(result).containsEntry("resource2", 20);
    }

    @Test
    void testGetNullStatistic() {
        Map<String, Map<?, ?>> statisticsMap = new HashMap<>();
        Stats stats = new Stats(statisticsMap);

        Map<String, Integer> result = stats.getStatistic("nonexistentKey");

        assertThat(result).isNull();
    }
}
