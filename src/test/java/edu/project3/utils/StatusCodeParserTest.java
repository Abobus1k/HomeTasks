package edu.project3.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusCodeParserTest {

    @Test
    void testGetResponseName200() {
        String responseName = StatusCodeParser.getResponseName(200);
        assertEquals("OK", responseName);
    }

    @Test
    void testGetResponseName206() {
        String responseName = StatusCodeParser.getResponseName(206);
        assertEquals("Partial Content", responseName);
    }

    @Test
    void testGetResponseName304() {
        String responseName = StatusCodeParser.getResponseName(304);
        assertEquals("Not Modified", responseName);
    }

    @Test
    void testGetResponseName403() {
        String responseName = StatusCodeParser.getResponseName(403);
        assertEquals("Forbidden", responseName);
    }

    @Test
    void testGetResponseName416() {
        String responseName = StatusCodeParser.getResponseName(416);
        assertEquals("Range Not Satisfiable", responseName);
    }

    @Test
    void testGetResponseName404() {
        String responseName = StatusCodeParser.getResponseName(404);
        assertEquals("Not Found", responseName);
    }

    @Test
    void testGetResponseNameForOtherCodes() {
        String responseName = StatusCodeParser.getResponseName(500);
        assertEquals("Unknown", responseName);
    }
}
