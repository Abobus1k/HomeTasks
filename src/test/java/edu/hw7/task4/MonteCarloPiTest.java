package edu.hw7.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonteCarloPiTest {

    @Test
    void testGetPiInOneThread() {
        MonteCarloPi monteCarloPi = new MonteCarloPi(10000000);
        double result = monteCarloPi.getPiInOneThread();
        assertTrue(result > 3.14 && result < 3.15);
    }

    @Test
    void testGetPiInMultipleThread() throws InterruptedException {
        MonteCarloPi monteCarloPi = new MonteCarloPi(10000000);
        double result = monteCarloPi.getPiParallel(4);
        assertTrue(result > 3.14 && result < 3.15);
    }


    @Test
    void testConcurrency() throws InterruptedException {
        MonteCarloPi monteCarloPi = new MonteCarloPi(10000000);
        double resultMultipleThread = monteCarloPi.getPiParallel(4);
        double resultSingleThread = monteCarloPi.getPiInOneThread();
        assertEquals(resultSingleThread, resultMultipleThread, 0.01);
    }
}
