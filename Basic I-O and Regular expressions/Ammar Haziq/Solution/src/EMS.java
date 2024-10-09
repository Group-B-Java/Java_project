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

    public void createLogFile(String fileName) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        currentLogFileName = "energyLog_" + timestamp + ".txt";
        Path path = Paths.get(LOG_DIR + currentLogFileName);
        if (Files.notExists(path)) {
            Files.createFile(path);
            System.out.println("Log file created: " + path.getFileName());
        }
    }

    public void archiveLogFile() throws IOException {
        Path sourcePath = Paths.get(LOG_DIR + currentLogFileName);
        Path targetPath = Paths.get(ARCHIVE_DIR + currentLogFileName);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Log file archived to: " + targetPath);
    }

    public void deleteLogFile() throws IOException {
        Path path = Paths.get(LOG_DIR + currentLogFileName);
        if (Files.exists(path)) {
            Files.delete(path);
            System.out.println("Log file deleted: " + currentLogFileName);
        } else {
            System.out.println("Log file does not exist: " + currentLogFileName);
        }
    }
    
    public void addDevice(EnergyDevice device) {
        devices.add(device);
    }

    
    private void logDailyUsage(String identifier, double usage) throws IOException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String logFileName = LOG_DIR + identifier + "_log_" + date + ".txt";

        // Create log file if it doesn't exist
        createLogFile(identifier + "_log_" + date + ".txt");
        
        //Ensure the device's log directory exists
        Files.createDirectories(Paths.get(LOG_DIR));

        // Log the usage to the log file
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFileName), 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(LocalDate.now() + ": " + usage + " watts\n");
        }
    }

    public void monitorEnergyUsage() throws IOException {
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


    public static void main(String[] args) throws IOException {
        EMS ems = new EMS();
        Files.createDirectories(Paths.get(LOG_DIR));
        Files.createDirectories(Paths.get(ARCHIVE_DIR));

        ems.addDevice(new EnergyDevice("HVAC", 3000));
        ems.addDevice(new EnergyDevice("Light", 100));

        ems.monitorEnergyUsage();
        ems.archiveLogFile();
        ems.deleteLogFile();
    }
}