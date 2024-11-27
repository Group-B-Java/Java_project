package energySource;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import lib.logManager;
import simulation.simulation;

public class energyManager implements Runnable{
	
	final Logger logger = logManager.setupLogger(simulation.class.getName());
	
	private final int  solarBattery = 0;
	private final int  houseAC= 1;
	private int total_battery_new = 0;
	private int  energyType = solarBattery;
	
	
	int totalBattery = 100;
	
	private ArrayList<Integer>  energy;
	
	public energyManager(ArrayList<Integer>  energy) {
		this.energy = energy;		
	}
	
	@Override
	public void run() {
		while (true){
			if (energyType == solarBattery) {
				runSolarBattery();
			}

			else if (energyType == houseAC) {
				run_ac();
			}
		}
	}
	
	public synchronized void runSolarBattery() {
		//System.out.println("running on battery");
		logger.info("running on battery");
		
		while (energyType == solarBattery){

			if (totalBattery < 25) {
				//System.out.println("Solar battery is low.. now switching to house AC");
				logger.info("Solar battery is low.. now switching to house AC");
				energyType = houseAC;
				break;
			}
			
			total_battery_new = totalBattery + energy.get(0);
			energy.set(0, 0);
			
			
			if (total_battery_new != totalBattery) {
				
				totalBattery = total_battery_new;
				//System.out.println("remaining battery capacity: "+ totalBattery);
				logger.info("remaining battery capacity: " + totalBattery);
				
			}

			// sleep
			try {
	            // Sleep for 5 seconds
	            TimeUnit.SECONDS.sleep(4);
	        } catch (InterruptedException e) {
	            //System.out.println("Sleep was interrupted.");
	            logger.warning("Sleep was interrupted.");
	        }
			
		}
		
		
	}
	
	public void run_ac() {
		//System.out.println("running on AC");
		logger.info("running on AC");
		
		while (energyType == houseAC){
			energy.set(0,0);;
			
		}
			
	}

				
}
