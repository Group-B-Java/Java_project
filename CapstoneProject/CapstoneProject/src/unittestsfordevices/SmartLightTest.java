package unittestsfordevices;

import org.junit.Test;

import smartdevices.SmartLight;

import static org.junit.Assert.*;

public class SmartLightTest {

    @Test
    public void testSmartLightCreation() {
        SmartLight light = new SmartLight("Living Room Light", 50.0);

        assertEquals("Living Room Light", light.getName());
        assertEquals(50.0, light.getPowerUsage(), 0.01);
        assertFalse(light.isOn());
    }

    @Test
    public void testTurnOnLight() {
        SmartLight light = new SmartLight("Living Room Light", 50.0);
        light.turnOn();
        assertTrue(light.isOn());
    }

    @Test
    public void testTurnOffLight() {
        SmartLight light = new SmartLight("Living Room Light", 50.0);
        light.turnOn();
        light.turnOff();
        assertFalse(light.isOn());
    }
}
