package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3Util {

    private Task3Util() {}

    public static <T> Map<T, Integer> wordLen(List<T> objectsList) {
        Map<T, Integer> map = new HashMap<>();
        for (T obj: objectsList) {
            map.put(obj, map.getOrDefault(obj,0) + 1);
        }
        return map;
    }
}
