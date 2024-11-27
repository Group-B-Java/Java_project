package unittestsforhomesystem;

import org.junit.Before;
import org.junit.Test;
import smartdevices.SmartDevice;
import smarthomesystem.EnergySource;
import smarthomesystem.UserInterface;

import java.util.Map;

import static org.junit.Assert.*;

public class UserInterfaceTest {
    private UserInterface ui;

    @Before
    public void setUp() {
        ui = new UserInterface();
    }

    @Test
    public void testEnergySourcesInitialization() {
        Map<String, EnergySource> energySources = ui.getEnergySources();
        assertEquals(3, energySources.size());
        assertTrue(energySources.containsKey("SolarPanel"));
        assertTrue(energySources.containsKey("Grid"));
        assertTrue(energySources.containsKey("Battery"));
    }

    @Test
    public void testDevicesInitialization() {
        Map<String, SmartDevice> devices = ui.getDevices();
        assertEquals(4, devices.size());
        assertTrue(devices.containsKey("Living Room Light"));
        assertTrue(devices.containsKey("Room Heater"));
        assertTrue(devices.containsKey("Television"));
        assertTrue(devices.containsKey("Smart Switch"));
    }

    @Test
    public void testTurnOnDevice() {
        SmartDevice device = ui.getDevices().get("Living Room Light");
        EnergySource source = ui.getEnergySources().get("SolarPanel");
        ui.getDeviceEnergyMapping().put(device, source);
        device.turnOn();
        assertTrue(device.isOn());
        assertEquals(source, ui.getDeviceEnergyMapping().get(device));
    }

    @Test
    public void testTurnOffDevice() {
        SmartDevice device = ui.getDevices().get("Living Room Light");
        EnergySource source = ui.getEnergySources().get("SolarPanel");
        ui.getDeviceEnergyMapping().put(device, source);
        device.turnOn();
        ui.getDeviceEnergyMapping().remove(device);
        device.turnOff();
        assertFalse(device.isOn());
        assertNull(ui.getDeviceEnergyMapping().get(device));
    }

    @Test
    public void testCheckRemainingEnergy() {
        EnergySource source = ui.getEnergySources().get("SolarPanel");
        double initialCapacity = source.getRemainingCapacity();
        source.deductEnergy(100);
        assertEquals(initialCapacity - 100, source.getRemainingCapacity(), 0.01);
    }

    @Test
    public void testRechargeEnergySource() {
        EnergySource source = ui.getEnergySources().get("SolarPanel");
        double initialCapacity = source.getRemainingCapacity();
        source.deductEnergy(1000); // Deduct some energy first
        double capacityAfterDeduction = source.getRemainingCapacity();
        source.recharge(100);
        double newCapacity = source.getRemainingCapacity();
        System.out.println("Initial Capacity: " + initialCapacity);
        System.out.println("Capacity After Deduction: " + capacityAfterDeduction);
        System.out.println("New Capacity: " + newCapacity);
        assertEquals(capacityAfterDeduction + 100, newCapacity, 0.01);
    }
}
