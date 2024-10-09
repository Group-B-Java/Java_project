package main;

public class LogManagement {
	
	import java.io.IOException;
	import java.nio.file.*;
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;

	public class LogManagement {

	    private static final String LOG_DIRECTORY = "logs/";

	    public static void createLogFile(String stationName) throws IOException {
	        String fileName = LOG_DIRECTORY + stationName + "_" + getCurrentDate() + ".log";
	        Path filePath = Paths.get(fileName);
	        if (!Files.exists(filePath)) {
	            Files.createFile(filePath);
	            System.out.println("Log file created: " + fileName);
	        }
	    }

	    public static void deleteLogFile(String fileName) throws IOException {
	        Path filePath = Paths.get(LOG_DIRECTORY + fileName);
	        if (Files.exists(filePath)) {
	            Files.delete(filePath);
	            System.out.println("Log file deleted: " + fileName);
	        }
	    }

	    public static void archiveLogFile(String fileName) throws IOException {
	        Path sourcePath = Paths.get(LOG_DIRECTORY + fileName);
	        Path destinationPath = Paths.get(LOG_DIRECTORY + "archive/" + fileName);
	        if (!Files.exists(destinationPath.getParent())) {
	            Files.createDirectories(destinationPath.getParent());
	        }
	        Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	        System.out.println("Log file archived: " + fileName);
	    }

	    public static String getCurrentDate() {
	        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    }
	}
`

}
