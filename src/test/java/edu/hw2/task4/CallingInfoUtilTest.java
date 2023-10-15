package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CallingInfoUtilTest {
    @Test
    void testCallingInfoClassName() {
        CallingInfo callingInfo = CallingInfoUtil.callingInfo();

        assertFalse(callingInfo.className().isEmpty());
        assertEquals("edu.hw2.task4.CallingInfoUtilTest", callingInfo.className());

    }

    @Test
    void testCallingInfo() {
        CallingInfo callingInfo = CallingInfoUtil.callingInfo();

        assertFalse(callingInfo.methodName().isEmpty());
        assertEquals("testCallingInfo", callingInfo.methodName());
    }
}
