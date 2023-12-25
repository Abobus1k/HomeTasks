package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelIncrementerTest {
    @Test
    void testOneThreadIncrementer() {
        ParallelIncrementer parallelIncrementer = new ParallelIncrementer(1, 0);
        assertEquals(1, parallelIncrementer.execute());
    }

    @Test
    void testMultipleThreadIncrementer() {
        ParallelIncrementer parallelIncrementer = new ParallelIncrementer(4, 0);
        assertEquals(4, parallelIncrementer.execute());
    }
}
