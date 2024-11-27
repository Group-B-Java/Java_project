package smarthomesystem;

import java.time.LocalDateTime;

public class EnergyLog {
    private String sourceType; // SolarPanel, Grid, Battery
    private LocalDateTime timeStamp;
    private double energyGenerated;
    private double energyConsumed;

    // Constructor
    public EnergyLog(String sourceType, LocalDateTime timeStamp, double energyGenerated, double energyConsumed) {
        if (sourceType == null || sourceType.isEmpty()) {
            throw new IllegalArgumentException("Source type cannot be null or empty.");
        }
        if (timeStamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null.");
        }
        if (energyGenerated < 0 || energyConsumed < 0) {
            throw new IllegalArgumentException("Energy generated and consumed cannot be negative.");
        }
        this.sourceType = sourceType;
        this.timeStamp = timeStamp;
        this.energyGenerated = energyGenerated;
        this.energyConsumed = energyConsumed;
    }

    // Getters
    public String getSourceType() {
        return sourceType;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public double getEnergyGenerated() {
        return energyGenerated;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    // Display Log
    @Override
    public String toString() {
        return "EnergyLog{" +
                "sourceType='" + sourceType + '\'' +
                ", timeStamp=" + timeStamp +
                ", energyGenerated=" + energyGenerated +
                ", energyConsumed=" + energyConsumed +
                '}';
    }
}