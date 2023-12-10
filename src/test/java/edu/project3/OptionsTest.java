package edu.project3;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OptionsTest {

    @Test
    void testGet() {
        Map<String, String> optionsMap = new HashMap<>();
        optionsMap.put("key1", "value1");
        optionsMap.put("key2", "value2");

        Options options = new Options(optionsMap);

        assertEquals("value1", options.get("key1"));
        assertEquals("value2", options.get("key2"));
    }

    @Test
    void testGetNull() {
        Map<String, String> optionsMap = new HashMap<>();
        Options options = new Options(optionsMap);

        assertNull(options.get("none"));
    }

    @Test
    void testGetWithDefault() {
        Map<String, String> optionsMap = new HashMap<>();
        optionsMap.put("key1", "value1");

        Options options = new Options(optionsMap);

        assertEquals("value1", options.get("key1", "defaultValue"));
    }

    @Test
    void testGetWithDefaultNull() {
        Map<String, String> optionsMap = new HashMap<>();
        Options options = new Options(optionsMap);

        assertEquals("defaultValue", options.get("nonexistentKey", "defaultValue"));
    }
}
