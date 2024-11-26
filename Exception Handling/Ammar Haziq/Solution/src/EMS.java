import java.io.BufferedWriter;
import java.time.LocalDate;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EMS {
    private static final String LOG_DIR = "logs/";
    private static final String ARCHIVE_DIR = "logs/archive/";
    private List<EnergyDevice> devices = new ArrayList<>();
    private String currentLogFileName;

    public void createLogFile(String fileName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        currentLogFileName = "energyLog_" + timestamp + ".txt";
        Path path = Paths.get(LOG_DIR + currentLogFileName);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                System.out.println("Log file created: " + path.getFileName());
            }
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
        }
    }

    public void archiveLogFile() {
        Path sourcePath = Paths.get(LOG_DIR + currentLogFileName);
        Path targetPath = Paths.get(ARCHIVE_DIR + currentLogFileName);
        try {
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Log file archived to: " + targetPath);
        } catch (IOException e) {
            System.err.println("Error archiving log file: " + e.getMessage());
        }
    }

    public void deleteLogFile() {
        Path path = Paths.get(LOG_DIR + currentLogFileName);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Log file deleted: " + currentLogFileName);
            } else {
                System.out.println("Log file does not exist: " + currentLogFileName);
            }
        } catch (IOException e) {
            System.err.println("Error deleting log file: " + e.getMessage());
        }
    }
    
    public void addDevice(EnergyDevice device) {
        devices.add(device);
    }

    private void logDailyUsage(String identifier, double usage) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String logFileName = LOG_DIR + identifier + "_log_" + date + ".txt";

        // Ensure the device's log directory exists
        try {
            Files.createDirectories(Paths.get(LOG_DIR));
            // Create log file if it doesn't exist
            createLogFile(identifier + "_log_" + date + ".txt");

            // Log the usage to the log file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFileName),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                writer.write(LocalDate.now() + ": " + usage + " watts\n");
            }
        } catch (IOException e) {
            System.err.println("Error logging daily usage for " + identifier + ": " + e.getMessage());
        }
    }

    public void monitorEnergyUsage() {
        double totalUsage = 0;

        // Log usage for each device (charging station)
        for (EnergyDevice device : devices) {
            double usage = device.getCurrentPowerUsage();
            totalUsage += usage;
            logDailyUsage(device.getName(), usage); // Log usage for each device
        }

        // Log total usage for the system
        logDailyUsage("system", totalUsage); // Log total usage for the system
        createLogFile("system_log_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt");
        System.out.println("Total energy usage: " + totalUsage + " watts");
    }

    public static void main(String[] args) {
        EMS ems = new EMS();
        try {
            Files.createDirectories(Paths.get(LOG_DIR));
            Files.createDirectories(Paths.get(ARCHIVE_DIR));

            ems.addDevice(new EnergyDevice("HVAC", 3000));
            ems.addDevice(new EnergyDevice("Light", 100));

            ems.monitorEnergyUsage();
            ems.archiveLogFile();
            ems.deleteLogFile();
        } catch (IOException e) {
            System.err.println("Error initializing EMS: " + e.getMessage());
        }
    }
}