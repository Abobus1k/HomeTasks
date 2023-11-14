package edu.project3;

import java.util.Map;

public record Options(Map<String, String> options) {

    public String get(String key) {
        return options.get(key);
    }

    public String get(String key, String defaultValue) {
        return options.getOrDefault(key, defaultValue);
    }
}
