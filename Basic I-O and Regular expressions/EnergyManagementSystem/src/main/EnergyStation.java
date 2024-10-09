package main;

public class EnergyStation {
	
	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;

	public class EnergyStation {

	    private String stationName;
	    private String energySource;

	    public EnergyStation(String stationName, String energySource) {
	        this.stationName = stationName;
	        this.energySource = energySource;
	    }

	    public void logEnergyData(double amount) throws IOException {
	        LogManagement.createLogFile(stationName);
	        String logEntry = "Energy Source: " + energySource + ", Charged: " + amount + " kWh";

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/" + stationName + "_" + LogManagement.getCurrentDate() + ".log", true))) {
	            writer.write(logEntry);
	            writer.newLine();
	        }

	        System.out.println("Logged data: " + logEntry);
	    }
	}

}
