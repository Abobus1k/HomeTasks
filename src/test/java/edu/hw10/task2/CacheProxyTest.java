package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {

    @Test
    public void testCacheFileCreation() {
        FibCalculator calculator = new FibCalculatorImpl();
        String cacheDirectory = "src/test/java/edu/hw10/task2/cache";
        boolean persist = true;

        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class, persist, cacheDirectory);
        proxy.fib(5);

        String cacheFileName = cacheDirectory + "/cache.ser";
        assertTrue(new File(cacheFileName).exists(), "Cache file not created");
    }
}
