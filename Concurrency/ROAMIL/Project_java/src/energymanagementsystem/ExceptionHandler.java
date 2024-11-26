package energymanagementsystem;

import java.io.*;

public class ExceptionHandler {
    public void processData(String data) throws IOException {
        try {
            if (data == null) {
                throw new IllegalArgumentException("Data cannot be null");
            }

            // Simulate processing data
            System.out.println("Processing data...");
        } catch (Exception ex) {
            // Log the exception (for example purposes, we'll just print it)
            System.out.println("Exception caught: " + ex.getMessage());
            // Re-throw the exception
            throw ex;
        }
    }
}
