package lib;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class logManager {
	
	public static Logger setupLogger(String loggerName) {
        Logger logger = Logger.getLogger(loggerName);
        
        try {
            // Prevent duplicate log messages by removing default handlers
            logger.setUseParentHandlers(false);

            // File Handler: Logs to a file
            FileHandler fileHandler = new FileHandler("systemOutput.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Console Handler: Logs to the console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
        } catch (IOException e) {
            System.err.println("Failed to set up logging: " + e.getMessage());
        }
        
        return logger;
    }

}
