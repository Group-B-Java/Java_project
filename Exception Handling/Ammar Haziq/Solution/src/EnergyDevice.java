
public class EnergyDevice {
    private String name;
    private double powerRating;
    private boolean isOn;

    public EnergyDevice(String name, double powerRating) {
        this.name = name;
        this.powerRating = powerRating;
        this.isOn = true;
    }

    public double getCurrentPowerUsage() {
        return isOn ? powerRating : 0;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public String getName() {
        return name;
    }
}
