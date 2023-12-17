package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {

    @Test
    public void testCacheFileCreation() {
        FibCalculator calculator = new FibCalculatorImpl();
        String cacheDirectory = "/home/mocklinux/IdeaProjects/HomeTasks/src/main/java/edu/hw10/task2/cache"; // Замените на реальный путь
        boolean persist = true;

        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class, persist, cacheDirectory);
        proxy.fib(5);

        String cacheFileName = cacheDirectory + "/cache.ser"; // Путь к файлу кеша
        assertTrue(new File(cacheFileName).exists(), "Cache file not created");
    }
}
