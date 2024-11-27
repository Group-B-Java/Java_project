package unittestsfordevices;

import org.junit.Test;

import smartdevices.SmartSwitch;

import static org.junit.Assert.*;

public class SmartSwitchTest {

    @Test
    public void testSmartSwitchCreation() {
        SmartSwitch smartSwitch = new SmartSwitch("Smart Switch", 10.0);

        assertEquals("Smart Switch", smartSwitch.getName());
        assertEquals(10.0, smartSwitch.getPowerUsage(), 0.01);
        assertFalse(smartSwitch.isOn());
    }

    @Test
    public void testTurnOnSwitch() {
        SmartSwitch smartSwitch = new SmartSwitch("Smart Switch", 10.0);
        smartSwitch.turnOn();
        assertTrue(smartSwitch.isOn());
    }

    @Test
    public void testTurnOffSwitch() {
        SmartSwitch smartSwitch = new SmartSwitch("Smart Switch", 10.0);
        smartSwitch.turnOn();
        smartSwitch.turnOff();
        assertFalse(smartSwitch.isOn());
    }
}
