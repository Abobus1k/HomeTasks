package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void testParallelFibonacci() throws Exception {
        Fibonacci fibonacci = new Fibonacci(10);
        long[] result = fibonacci.parallelFib(4);
        assertNotNull(result);
        assertEquals(10, result.length);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
        assertEquals(1, result[2]);
        assertEquals(2, result[3]);
        assertEquals(3, result[4]);
        assertEquals(5, result[5]);
        assertEquals(8, result[6]);
        assertEquals(13, result[7]);
        assertEquals(21, result[8]);
        assertEquals(34, result[9]);
    }
}
