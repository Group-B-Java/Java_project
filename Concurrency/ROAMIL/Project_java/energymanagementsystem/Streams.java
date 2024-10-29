package energymanagementsystem;

import java.io.*;

public class Streams {
    private static final String BYTE_LOG_FILE = "logs/byteLog.dat";
    private static final String CHAR_LOG_FILE = "logs/charLog.txt";

    public static void main(String[] args) {
        try {
            Streams energy = new Streams();
            energy.writeByteData("Energy usage data in bytes.");
            energy.readByteData();
            energy.writeCharData("Energy usage data in characters.");
            energy.readCharData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Byte stream methods
    public void writeByteData(String data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(BYTE_LOG_FILE)) {
            fos.write(data.getBytes());
            System.out.println("Data written to file using byte stream.");
        }
    }

    public void readByteData() throws IOException {
        try (FileInputStream fis = new FileInputStream(BYTE_LOG_FILE)) {
            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println("\nData read from file using byte stream.");
        }
    }

    // Character stream methods
    public void writeCharData(String data) throws IOException {
        try (FileWriter writer = new FileWriter(CHAR_LOG_FILE)) {
            writer.write(data);
            System.out.println("Data written to file using character stream.");
        }
    }

    public void readCharData() throws IOException {
        try (FileReader reader = new FileReader(CHAR_LOG_FILE)) {
            int content;
            while ((content = reader.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println("\nData read from file using character stream.");
        }
    }
}
