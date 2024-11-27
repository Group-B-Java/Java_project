package smarthomesystem;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize the user interface
            UserInterface ui = new UserInterface();

            // Start the Smart Home System
            ui.start();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
