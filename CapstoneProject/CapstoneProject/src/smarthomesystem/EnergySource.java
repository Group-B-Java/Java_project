package smarthomesystem;

public class EnergySource {
    private String name;
    private double capacity;
    private double maxCapacity;

    public EnergySource(String name, double maxCapacity) {
        this.name = name;
        this.capacity = maxCapacity; // Initialize with full capacity
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }

    public void deductEnergy(double energyUsed) {
        if (energyUsed <= capacity) {
            capacity -= energyUsed;
        } else {
            throw new IllegalArgumentException("Not enough energy in " + name);
        }
    }

    public void recharge(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Recharge amount cannot be negative.");
        }
        capacity = Math.min(capacity + amount, maxCapacity);
        System.out.println(name + " recharged to " + capacity + " watts.");
    }

    public double getRemainingCapacity() {
        return capacity;
    }
}
