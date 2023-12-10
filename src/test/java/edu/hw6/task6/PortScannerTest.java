package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PortScannerTest {

    @Test
    void testIsPortAvailable() {
        assertTrue(PortScanner.isPortAvailable(8080));
        assertFalse(PortScanner.isPortAvailable(80));
    }

    @Test
    void testGetServiceName() {
        assertEquals("http", PortScanner.getServiceName(80));
        assertEquals("Unknown service", PortScanner.getServiceName(9999));
    }

    @Test
    void testGetServiceProtocol() {
        assertEquals("TCP", PortScanner.getServiceProtocol(80));
        assertEquals("UDP", PortScanner.getServiceProtocol(123));
    }
}
