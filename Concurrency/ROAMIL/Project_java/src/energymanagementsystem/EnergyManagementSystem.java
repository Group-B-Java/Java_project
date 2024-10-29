package energymanagementsystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnergyManagementSystem {
    private static final String LOG_DIR = "logs/";
    private static final String ARCHIVE_DIR = "logs/archive/";
    private List<EnergyDevice> devices = new ArrayList();
    private String currentLogFileName;

    public EnergyManagementSystem() {
    }

    public void createLogFile() throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.currentLogFileName = "energyLog_" + timestamp + ".txt";
        Path path = Paths.get("logs/" + this.currentLogFileName);
        if (Files.notExists(path, new LinkOption[0])) {
            Files.createFile(path);
            System.out.println("Log file created: " + String.valueOf(path.getFileName()));
        }

    }

    public void archiveLogFile() throws IOException {
        Path sourcePath = Paths.get("logs/" + this.currentLogFileName);
        Path targetPath = Paths.get("logs/archive/" + this.currentLogFileName);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Log file archived to: " + String.valueOf(targetPath));
    }

    public void deleteLogFile() throws IOException {
        Path path = Paths.get("logs/" + this.currentLogFileName);
        if (Files.exists(path, new LinkOption[0])) {
            Files.delete(path);
            System.out.println("Log file deleted: " + this.currentLogFileName);
        } else {
            System.out.println("Log file does not exist: " + this.currentLogFileName);
        }

    }

    public void monitorEnergyUsage() throws IOException {
        double totalUsage = 0.0;

        EnergyDevice device;
        for(Iterator var4 = this.devices.iterator(); var4.hasNext(); totalUsage += device.getCurrentPowerUsage()) {
            device = (EnergyDevice)var4.next();
        }

        this.createLogFile();
        System.out.println("Total energy usage: " + totalUsage + " watts");
    }

    public void addDevice(EnergyDevice device) {
        this.devices.add(device);
    }

    public static void main(String[] args) throws IOException {
        EnergyManagementSystem ems = new EnergyManagementSystem();
        Files.createDirectories(Paths.get("logs/"));
        Files.createDirectories(Paths.get("logs/archive/"));
        ems.addDevice(new EnergyDevice("HVAC", 3000.0));
        ems.addDevice(new EnergyDevice("Light", 100.0));
        ems.monitorEnergyUsage();
        ems.archiveLogFile();
        ems.deleteLogFile();
    }
}
