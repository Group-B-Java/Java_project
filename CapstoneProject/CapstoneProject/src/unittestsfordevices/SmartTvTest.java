package unittestsfordevices;

import org.junit.Test;

import smartdevices.SmartTv;

import static org.junit.Assert.*;

public class SmartTvTest {

    @Test
    public void testSmartTvCreation() {
        SmartTv tv = new SmartTv("Television", 100.0);

        assertEquals("Television", tv.getName());
        assertEquals(100.0, tv.getPowerUsage(), 0.01);
        assertFalse(tv.isOn());
    }

    @Test
    public void testTurnOnTv() {
        SmartTv tv = new SmartTv("Television", 100.0);
        tv.turnOn();
        assertTrue(tv.isOn());
    }

    @Test
    public void testTurnOffTv() {
        SmartTv tv = new SmartTv("Television", 100.0);
        tv.turnOn();
        tv.turnOff();
        assertFalse(tv.isOn());
    }
}
