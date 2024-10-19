package energymanagementsystem.exceptionHandling;

import java.io.*;

public class ResourceManagement_Streams {
    private static final String BYTE_LOG_FILE = "logs/byteLog.dat";
    private static final String CHAR_LOG_FILE = "logs/charLog.txt";

    // Byte stream methods with try-with-resources for proper resource management
    public void writeByteData(String data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(BYTE_LOG_FILE)) {
            fos.write(data.getBytes());
            System.out.println("Data written to file using byte stream.");
        } // FileOutputStream is automatically closed here
    }

    public void readByteData() throws IOException {
        try (FileInputStream fis = new FileInputStream(BYTE_LOG_FILE)) {
            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println("\nData read from file using byte stream.");
        } // FileInputStream is automatically closed here
    }

    // Character stream methods with try-with-resources
    public void writeCharData(String data) throws IOException {
        try (FileWriter writer = new FileWriter(CHAR_LOG_FILE)) {
            writer.write(data);
            System.out.println("Data written to file using character stream.");
        } // FileWriter is automatically closed here
    }

    public void readCharData() throws IOException {
        try (FileReader reader = new FileReader(CHAR_LOG_FILE)) {
            int content;
            while ((content = reader.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println("\nData read from file using character stream.");
        } // FileReader is automatically closed here
    }
}
