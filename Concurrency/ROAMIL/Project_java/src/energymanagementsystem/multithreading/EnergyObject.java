 package energymanagementsystem.multithreading;

public class EnergyObject extends Thread {
    private final Battery battery;
    private final double usageRate;

    public EnergyObject(Battery battery, double usageRate, String objectName) {
        super(objectName);
        this.battery = battery;
        this.usageRate = usageRate;
    }

    @Override
    public void run() {
        while (!battery.isEmpty()) {
            battery.discharge(usageRate);
            try {
                Thread.sleep(1000); // Simulate usage time
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted: " + e.getMessage());
            }
        }
        System.out.println(getName() + " stopped using energy. Battery is empty.");
    }
}
