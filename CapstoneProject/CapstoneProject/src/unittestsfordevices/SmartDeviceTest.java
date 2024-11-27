package unittestsfordevices;

import org.junit.Test;

import smartdevices.SmartDevice;

import static org.junit.Assert.*;

public class SmartDeviceTest {

    @Test
    public void testSmartDeviceCreation() {
        SmartDevice device = new SmartDevice("Living Room Light", 50.0);

        assertEquals("Living Room Light", device.getName());
        assertEquals(50.0, device.getPowerUsage(), 0.01);
        assertFalse(device.isOn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDeviceName() {
        new SmartDevice(null, 50.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDeviceName() {
        new SmartDevice("", 50.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePowerUsage() {
        new SmartDevice("Living Room Light", -50.0);
    }

    @Test
    public void testTurnOnDevice() {
        SmartDevice device = new SmartDevice("Living Room Light", 50.0);
        device.turnOn();
        assertTrue(device.isOn());
    }

    @Test
    public void testTurnOffDevice() {
        SmartDevice device = new SmartDevice("Living Room Light", 50.0);
        device.turnOn();
        device.turnOff();
        assertFalse(device.isOn());
    }
}
