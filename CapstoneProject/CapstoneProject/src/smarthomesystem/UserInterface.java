package smarthomesystem;

import smartdevices.SmartDevice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    private Map<String, EnergySource> energySources = new HashMap<>();
    private Map<String, SmartDevice> devices = new HashMap<>();
    private Map<SmartDevice, EnergySource> deviceEnergyMapping = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        // Initialize energy sources
        energySources.put("SolarPanel", new EnergySource("SolarPanel", 5000.0));
        energySources.put("Grid", new EnergySource("Grid", 10000.0));
        energySources.put("Battery", new EnergySource("Battery", 2000.0));

        // Initialize devices
        devices.put("Living Room Light", new SmartDevice("Living Room Light", 50.0));
        devices.put("Room Heater", new SmartDevice("Room Heater", 1500.0));
        devices.put("Television", new SmartDevice("Television", 100.0));
        devices.put("Smart Switch", new SmartDevice("Smart Switch", 10.0));
    }
    
    // Add getter methods 
    public Map<String, EnergySource> getEnergySources() { 
    	return energySources; 
    } 
    
    public Map<String, SmartDevice> getDevices() { 
    	return devices; 
    } 
    
    public Map<SmartDevice, EnergySource> getDeviceEnergyMapping() { 
    	return deviceEnergyMapping; 
    }

    public void start() {
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("\n=== Smart Home System ===");
            System.out.println("1. Turn On Device");
            System.out.println("2. Turn Off Device");
            System.out.println("3. Check Remaining Energy");
            System.out.println("4. Recharge Energy Source");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            if (!scanner.hasNextInt()) {
                scanner.next(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    turnOnDevice();
                    break;
                case 2:
                    turnOffDevice();
                    break;
                case 3:
                    checkRemainingEnergy();
                    break;
                case 4:
                    rechargeEnergySource();
                    break;
                case 5:
                    System.out.println("Exiting... Would you like to restart? (yes/no)");
                    scanner.nextLine(); // Consume newline
                    String restart = scanner.nextLine();
                    if (restart.equalsIgnoreCase("no")) {
                        continueRunning = false;
                    } else if (!restart.equalsIgnoreCase("yes")) {
                        System.out.println("Invalid input. Restarting by default...");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid menu item.");
            }
        }
    }

    private void turnOnDevice() {
	    try {
	        SmartDevice device = selectDevice();
	        if (device != null) {
	            if (deviceEnergyMapping.containsKey(device)) {
	                System.out.println(device.getName() + " is already connected to an energy source.");
	            } else {
	                EnergySource source = selectEnergySource();
	                if (source != null) {
	                    device.turnOn();
	                    deviceEnergyMapping.put(device, source);
	                    source.deductEnergy(device.getPowerUsage());
	                    System.out.println(device.getName() + " is now ON, using " + source.getName() +
	                            ". Remaining energy: " + source.getRemainingCapacity() + " watts.");
	                }
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error turning on device: " + e.getMessage());
	    }
	}


    private void turnOffDevice() {
	    try {
	        SmartDevice device = selectDevice();
	        if (device != null && device.isOn()) {
	            device.turnOff();
	            deviceEnergyMapping.remove(device);
	            System.out.println(device.getName() + " is now OFF.");
	        } else {
	            System.out.println(device.getName() + " is already OFF.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error turning off device: " + e.getMessage());
	    }
	}


    private void checkRemainingEnergy() {
	    try {
	        for (EnergySource source : energySources.values()) {
	            System.out.println(source.getName() + ": " + source.getRemainingCapacity() + " watts remaining.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error checking remaining energy: " + e.getMessage());
	    }
	}


    private void rechargeEnergySource() {
	    try {
	        EnergySource source = selectEnergySource();
	        if (source != null) {
	            System.out.print("Enter amount to recharge: ");
	            if (scanner.hasNextDouble()) {
	                double amount = scanner.nextDouble();
	                source.recharge(amount);
	            } else {
	                scanner.next(); // Clear invalid input
	                throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error recharging energy source: " + e.getMessage());
	    }
	}


    private SmartDevice selectDevice() {
	    try {
	        System.out.println("Select a device:");
	        int i = 1;
	        for (SmartDevice device : devices.values()) {
	            System.out.println(i++ + ". " + device.getName());
	        }
	        if (scanner.hasNextInt()) {
	            int choice = scanner.nextInt();
	            return choice > 0 && choice <= devices.size()
	                    ? (SmartDevice) devices.values().toArray()[choice - 1]
	                    : null;
	        } else {
	            scanner.next(); // Clear invalid input
	            System.out.println("Invalid input. Returning to the main menu.");
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error selecting device: " + e.getMessage());
	        return null;
	    }
	}

    private EnergySource selectEnergySource() {
	    try {
	        System.out.println("Select an energy source:");
	        int i = 1;
	        for (EnergySource source : energySources.values()) {
	            System.out.println(i++ + ". " + source.getName());
	        }
	        if (scanner.hasNextInt()) {
	            int choice = scanner.nextInt();
	            return choice > 0 && choice <= energySources.size()
	                    ? (EnergySource) energySources.values().toArray()[choice - 1]
	                    : null;
	        } else {
	            scanner.next(); // Clear invalid input
	            System.out.println("Invalid input. Returning to the main menu.");
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error selecting energy source: " + e.getMessage());
	        return null;
	    }
	}

	public void displayLogs() {
		// TODO Auto-generated method stub
		
	}
}