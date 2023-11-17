package edu.hw6.task1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {

    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/task1/testFiles/test_diskmap.txt";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE_PATH);
    }

    @AfterEach
    void clearFile() {
        diskMap.clear();
    }

    @Test
    void testSize() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertEquals(2, diskMap.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(diskMap.isEmpty());

        diskMap.put("k", "v");

        assertFalse(diskMap.isEmpty());
    }

    @Test
    void testContainsKey() {
        diskMap.put("key1", "value1");

        assertTrue(diskMap.containsKey("key1"));
        assertFalse(diskMap.containsKey("key"));
    }

    @Test
    void testContainsValue() {
        diskMap.put("key1", "value1");

        assertTrue(diskMap.containsValue("value1"));
        assertFalse(diskMap.containsValue("val"));
    }

    @Test
    void testGet() {
        diskMap.put("key1", "value1");

        assertEquals("value1", diskMap.get("key1"));
        assertNull(diskMap.get("nonexistentKey"));
    }

    @Test
    void testPut() {
        diskMap.put("key1", "value1");
        assertEquals("value1", diskMap.get("key1"));
    }

    @Test
    void testRemove() {
        diskMap.put("key1", "value1");

        assertEquals("value1", diskMap.remove("key1"));
        assertNull(diskMap.remove("nonexistentKey"));
    }

    @Test
    void testPutAll() {
        Map<String, String> testData = new HashMap<>();
        testData.put("key1", "value1");
        testData.put("key2", "value2");

        diskMap.putAll(testData);

        assertEquals(2, diskMap.size());
        assertEquals("value1", diskMap.get("key1"));
        assertEquals("value2", diskMap.get("key2"));
    }

    @Test
    void testClear() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        diskMap.clear();

        assertEquals(0, diskMap.size());
        assertTrue(diskMap.isEmpty());
    }

    @Test
    void testKeySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Set<String> keySet = diskMap.keySet();

        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }

    @Test
    void testValues() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Collection<String> values = diskMap.values();

        assertEquals(2, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
    }

    @Test
    void testEntrySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Set<Map.Entry<String, String>> entrySet = diskMap.entrySet();

        assertEquals(2, entrySet.size());
        assertTrue(entrySet.contains(Map.entry("key1", "value1")));
        assertTrue(entrySet.contains(Map.entry("key2", "value2")));
    }

}
