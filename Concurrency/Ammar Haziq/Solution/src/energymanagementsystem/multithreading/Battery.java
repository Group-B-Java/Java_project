package energymanagementsystem.multithreading;

public class Battery {
    private double chargeLevel = 0;
    private final double capacity;

    public Battery(double capacity) {
        this.capacity = capacity;
    }

    public synchronized void charge(double amount) {
        if (chargeLevel + amount <= capacity) {
            chargeLevel += amount;
            System.out.println("Battery charged by " + amount + " units. Current level: " + chargeLevel);
        } else {
            chargeLevel = capacity;
            System.out.println("Battery fully charged. Current level: " + chargeLevel);
        }
    }

    public double getChargeLevel() {
        return chargeLevel;
    }

    public boolean isFullyCharged() {
        return chargeLevel >= capacity;
    }
}