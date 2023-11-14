package edu.project3.utils;

import java.util.HashMap;
import java.util.Map;

public class StatsGenerator {

    private StatsGenerator() {}

    public static Map<String, Map<?, ?>> statics() {
        Map<String, Map<?, ?>> stats = new HashMap<>();
        stats.put("resourceCount", new HashMap<String, Integer>());
        stats.put("responseCodeCount", new HashMap<Integer, Integer>());
        stats.put("ipRequestCount", new HashMap<String, Integer>());
        stats.put("userAgentCount", new HashMap<String, Integer>());
        return stats;
    }
}
