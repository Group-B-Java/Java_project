package energymanagementsystem;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EnergyManagementSystem {
    private static final String LOG_DIR = "logs/";
    private static final String ARCHIVE_DIR = "logs/archive/";
    private List<EnergyDevice> devices = new ArrayList<>();
    private String currentLogFileName;

    public void createLogFile() throws IOException {
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

    public void monitorEnergyUsage() throws IOException {
        double totalUsage = 0;
        for (EnergyDevice device : devices) {
            totalUsage += device.getCurrentPowerUsage();
        }
        createLogFile();
        System.out.println("Total energy usage: " + totalUsage + " watts");
    }

    public void addDevice(EnergyDevice device) {
        devices.add(device);
    }

    public static void main(String[] args) throws IOException {
        EnergyManagementSystem ems = new EnergyManagementSystem();
        Files.createDirectories(Paths.get(LOG_DIR));
        Files.createDirectories(Paths.get(ARCHIVE_DIR));

        ems.addDevice(new EnergyDevice("HVAC", 3000));
        ems.addDevice(new EnergyDevice("Light", 100));

        ems.monitorEnergyUsage();
        ems.archiveLogFile();
        ems.deleteLogFile();
    }
}
