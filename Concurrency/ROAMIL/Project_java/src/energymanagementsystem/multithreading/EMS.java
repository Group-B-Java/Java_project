package energymanagementsystem.multithreading;

import java.io.IOException;

public class EMS {
    private Battery battery;

    public EMS(double batteryCapacity) {
        battery = new Battery(batteryCapacity);
    }

    public void startChargingSimulation() {
        // Create multiple energy sources with different charge rates
        EnergySource solar = new EnergySource(battery, 10, "Solar Panel");
        EnergySource wind = new EnergySource(battery, 5, "Wind Turbine");
        EnergySource grid = new EnergySource(battery, 20, "Grid");

        // Start all sources to charge concurrently
        solar.start();
        wind.start();
        grid.start();

        // Wait for all threads to finish
        try {
            solar.join();
            wind.join();
            grid.join();
        } catch (InterruptedException e) {
            System.out.println("Charging simulation interrupted: " + e.getMessage());
        }

        System.out.println("Battery charging complete. Final charge level: " + battery.getChargeLevel());
    }

    public void startUsageSimulation() {
        // Create multiple energy objects with different usage rates
        EnergyObject light = new EnergyObject(battery, 5, "Light");
        EnergyObject fan = new EnergyObject(battery, 10, "Fan");
        EnergyObject heater = new EnergyObject(battery, 20, "Heater");

        // Start all objects to use energy concurrently
        light.start();
        fan.start();
        heater.start();

        // Wait for all threads to finish
        try {
            light.join();
            fan.join();
            heater.join();
        } catch (InterruptedException e) {
            System.out.println("Usage simulation interrupted: " + e.getMessage());
        }

        System.out.println("Energy usage complete. Final charge level: " + battery.getChargeLevel());
    }

    public static void main(String[] args) throws IOException {
        EMS ems = new EMS(100); // Initialize EMS with battery capacity of 100 units
        ems.startChargingSimulation();
        ems.startUsageSimulation();
    }
}
