package energymanagementsystem.exceptionHandling;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ResourceManagement_LogManagement {

    private static final String LOG_DIRECTORY = "logs/";

    // Use try-with-resources for safe file creation
    public static void createLogFile(String stationName) throws IOException {
        String fileName = LOG_DIRECTORY + stationName + "_" + getCurrentDate() + ".log";
        Path filePath = Paths.get(fileName);
        
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Log file created: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
            throw e; // Ensure exception is propagated
        }
    }

    // Use try-with-resources for safe deletion of log files
    public static void deleteLogFile(String fileName) throws IOException {
        Path filePath = Paths.get(LOG_DIRECTORY + fileName);
        
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("Log file deleted: " + fileName);
            } else {
                System.out.println("Log file does not exist: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error deleting log file: " + e.getMessage());
            throw e; // Ensure exception is propagated
        }
    }

    // Safe file archival with try-with-resources
    public static void archiveLogFile(String fileName) throws IOException {
        Path sourcePath = Paths.get(LOG_DIRECTORY + fileName);
        Path destinationPath = Paths.get(LOG_DIRECTORY + "archive/" + fileName);
        
        try {
            if (!Files.exists(destinationPath.getParent())) {
                Files.createDirectories(destinationPath.getParent());
            }
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Log file archived: " + fileName);
        } catch (IOException e) {
            System.err.println("Error archiving log file: " + e.getMessage());
            throw e; // Ensure exception is propagated
        }
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
