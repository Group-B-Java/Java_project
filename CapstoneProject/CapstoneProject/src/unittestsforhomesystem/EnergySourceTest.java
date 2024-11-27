package unittestsforhomesystem;

import org.junit.Before;
import org.junit.Test;

import smarthomesystem.EnergySource;

import static org.junit.Assert.*;

public class EnergySourceTest {
    private EnergySource energySource;

    @Before
    public void setUp() {
        energySource = new EnergySource("SolarPanel", 5000.0);
    }

    @Test
    public void testEnergySourceInitialization() {
        assertEquals("SolarPanel", energySource.getName());
        assertEquals(5000.0, energySource.getCapacity(), 0.01);
    }

    @Test
    public void testDeductEnergy() {
        energySource.deductEnergy(1000.0);
        assertEquals(4000.0, energySource.getCapacity(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeductEnergyInsufficientCapacity() {
        energySource.deductEnergy(6000.0);
    }

    @Test
    public void testRechargeEnergy() {
        energySource.deductEnergy(1000.0);
        energySource.recharge(500.0);
        assertEquals(4500.0, energySource.getCapacity(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRechargeNegativeAmount() {
        energySource.recharge(-500.0);
    }

    @Test
    public void testGetRemainingCapacity() {
        assertEquals(5000.0, energySource.getRemainingCapacity(), 0.01);
        energySource.deductEnergy(1000.0);
        assertEquals(4000.0, energySource.getRemainingCapacity(), 0.01);
    }
}
