package edu.project3;

import java.util.Map;

public class Stats {
    private final Map<String, Map<?, ?>> statisticsMap;

    public Stats(Map<String, Map<?, ?>> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public <K, V> Map<K, V> getStatistic(String key) {
        return (Map<K, V>) statisticsMap.get(key);
    }
}
