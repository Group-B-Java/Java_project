package energymanagementsystem.multithreading;

public class EnergySource extends Thread {
    private final Battery battery;
    private final double chargeRate;

    public EnergySource(Battery battery, double chargeRate, String sourceName) {
        super(sourceName);
        this.battery = battery;
        this.chargeRate = chargeRate;
    }

    @Override
    public void run() {
        while (!battery.isFullyCharged()) {
            battery.charge(chargeRate);
            try {
                Thread.sleep(1000); // Simulate charging time
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted: " + e.getMessage());
            }
        }
        System.out.println(getName() + " stopped charging. Battery is fully charged.");
    }
}