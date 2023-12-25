package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelFactorialTest {
    @Test
    void testParallelFactorial() {
        assertEquals(new BigInteger("3628800"), ParallelFactorial.factorial(10));
    }

    @Test
    void testParallelFactorialExecutionTime() {
        long start = System.currentTimeMillis();
        ParallelFactorial.factorial(100000);
        long end = System.currentTimeMillis();
        assertTrue(end - start < 1000);
    }
}
