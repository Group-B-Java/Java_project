package smarthomesystem;

import java.util.concurrent.*;
import java.util.Map;
import smartdevices.SmartDevice;

public class ConcurrencyManager {

    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;

    public ConcurrencyManager() {
        // Create a fixed thread pool for concurrent tasks
        this.executor = Executors.newFixedThreadPool(5);

        // Create a scheduled executor for periodic tasks
        this.scheduler = Executors.newScheduledThreadPool(2);
    }

    /**
     * Execute a task asynchronously.
     */
    public void executeTask(Runnable task) {
        executor.execute(task);
    }

    /**
     * Schedule a task to run periodically.
     */
    public void scheduleTask(Runnable task, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(task, initialDelay, period, unit);
    }

    /**
     * Turn on multiple devices concurrently.
     */
    public void turnOnDevicesConcurrently(Map<String, SmartDevice> devices) {
        for (SmartDevice device : devices.values()) {
            executor.submit(() -> {
                if (!device.isOn()) {
                    device.turnOn();
                    System.out.println(device.getName() + " has been turned ON.");
                }
            });
        }
    }

    /**
     * Shutdown executors gracefully.
     */
    public void shutdown() {
        try {
            executor.shutdown();
            scheduler.shutdown();
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            scheduler.shutdownNow();
        }
    }

    /**
     * Example: Log energy usage periodically.
     */
    public void startEnergyUsageLogging(UserInterface ui) {
        scheduleTask(() -> {
            ui.displayLogs(); // Assuming this displays energy logs.
        }, 0, 10, TimeUnit.SECONDS); // Logs every 10 seconds
    }
}
