package unittestsfordevices;

import org.junit.Test;

import smartdevices.SmartHeater;

import static org.junit.Assert.*;

public class SmartHeaterTest {

    @Test
    public void testSmartHeaterCreation() {
        SmartHeater heater = new SmartHeater("Room Heater", 1500.0);

        assertEquals("Room Heater", heater.getName());
        assertEquals(1500.0, heater.getPowerUsage(), 0.01);
        assertFalse(heater.isOn());
    }

    @Test
    public void testTurnOnHeater() {
        SmartHeater heater = new SmartHeater("Room Heater", 1500.0);
        heater.turnOn();
        assertTrue(heater.isOn());
    }

    @Test
    public void testTurnOffHeater() {
        SmartHeater heater = new SmartHeater("Room Heater", 1500.0);
        heater.turnOn();
        heater.turnOff();
        assertFalse(heater.isOn());
    }
}
