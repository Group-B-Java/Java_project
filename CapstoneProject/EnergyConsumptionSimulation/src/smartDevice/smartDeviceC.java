package smartDevice;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import lib.logManager;
import simulation.simulation;

public class smartDeviceC implements Runnable{
	
	final Logger logger = logManager.setupLogger(simulation.class.getName());

	public static final int OFF = 0;
	public static final int ON = 1;
	
	private ArrayList<Integer> state;
	private ArrayList<Integer> energySource;
	
	public smartDeviceC(ArrayList<Integer> state, ArrayList<Integer> energySource){
		this.state=state;
		this.energySource=energySource;
	}
	

	@Override
	public void run() {
		
		while(true) {
			switch (state.get(0)) {
				case OFF:
			        off();
			        break;
				case ON:
					on();
					break;
		
			}
		}
	}
	
	public synchronized void on() {
		
		//System.out.println("Device is on..");
		logger.info("Device is on..");
		
		while (true) {
			if(state.get(0) == OFF){
				return;
			}
			energySource.set(0, energySource.get(0) -5);
			
			// sleep
			try {
	            // Sleep for 5 seconds
	            TimeUnit.SECONDS.sleep(2);
	        } catch (InterruptedException e) {
	            //System.out.println("Sleep was interrupted.");
	            logger.warning("Sleep was interrupted.");
	        }
			
		}
	}
	
	public synchronized void off() {
		
		//System.out.println("lamp is off..");
		logger.info("Device is on..");
		
		while (true) {
			if(state.get(0) == ON) {
				return;
			}
		}
			
	}
}
