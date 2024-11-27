package unittestsforhomesystem;

import org.junit.Test;

import smarthomesystem.EnergyLog;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EnergyLogTest {

    @Test
    public void testEnergyLogCreation() {
        LocalDateTime now = LocalDateTime.now();
        EnergyLog log = new EnergyLog("SolarPanel", now, 500.0, 300.0);

        assertEquals("SolarPanel", log.getSourceType());
        assertEquals(now, log.getTimeStamp());
        assertEquals(500.0, log.getEnergyGenerated(), 0.01);
        assertEquals(300.0, log.getEnergyConsumed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSourceType() {
        new EnergyLog(null, LocalDateTime.now(), 500.0, 300.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySourceType() {
        new EnergyLog("", LocalDateTime.now(), 500.0, 300.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTimeStamp() {
        new EnergyLog("SolarPanel", null, 500.0, 300.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeEnergyGenerated() {
        new EnergyLog("SolarPanel", LocalDateTime.now(), -500.0, 300.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeEnergyConsumed() {
        new EnergyLog("SolarPanel", LocalDateTime.now(), 500.0, -300.0);
    }

    @Test
    public void testToString() {
        LocalDateTime now = LocalDateTime.now();
        EnergyLog log = new EnergyLog("SolarPanel", now, 500.0, 300.0);
        String expected = "EnergyLog{sourceType='SolarPanel', timeStamp=" + now + ", energyGenerated=500.0, energyConsumed=300.0}";
        assertEquals(expected, log.toString());
    }
}
