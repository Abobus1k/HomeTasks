package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NullTreeMapComparatorTest {
    @Test
    @DisplayName("Единственный тест который нас интересует :)")
    void testNullContains() {
        TreeMap<String, String> treeMap = new TreeMap<>(new NullTreeMapComparator<>());
        treeMap.put(null, "test");

        Assertions.assertTrue(treeMap.containsKey(null));
        Assertions.assertEquals("test", treeMap.get(null));
    }

}
