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

    public synchronized void discharge(double amount) {
        if (chargeLevel - amount >= 0) {
            chargeLevel -= amount;
            System.out.println("Battery discharged by " + amount + " units. Current level: " + chargeLevel);
        } else {
            System.out.println("Battery is empty. Cannot discharge.");
        }
    }

    public double getChargeLevel() {
        return chargeLevel;
    }

    public boolean isFullyCharged() {
        return chargeLevel >= capacity;
    }

    public boolean isEmpty() {
        return chargeLevel <= 0;
    }
}
