package smartdevices;

public class SmartDevice {
    private String name;
    private double powerUsage; // Energy usage in watts
    private boolean isOn;

    public SmartDevice(String name, double powerUsage) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be null or empty.");
        }
        if (powerUsage < 0) {
            throw new IllegalArgumentException("Power usage cannot be negative.");
        }
        this.name = name;
        this.powerUsage = powerUsage;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}