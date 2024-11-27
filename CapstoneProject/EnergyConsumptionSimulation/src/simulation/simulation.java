package simulation;

import smartDevice.smartDeviceC;


import java.util.ArrayList;

import energySource.energyManager;

public class simulation {
	

	public static void main(String[] args) {
		

		
		ArrayList<Integer>  lampState = new ArrayList<Integer>();
		//ArrayList<Integer>  smartTvState = new ArrayList<Integer>();
		ArrayList<Integer>  energyConnection = new ArrayList<Integer>();
		
		lampState.add(1);
		
		//smartTvState.add(1);
		
		energyConnection.add(0);
		
		energyManager energyManager = new energyManager(energyConnection);
		smartDeviceC smartLamp = new smartDeviceC(lampState, energyConnection);
		//smartDeviceC smartTv = new smartDeviceC(smartTvState, energyConnection);
		
		Thread t1 = new Thread(energyManager, "t1");
		Thread t2 = new Thread(smartLamp, "t2");
		
		//Thread t3 = new Thread(smartTv, "t3");
		

		t2.start();
		t1.start();
		
		//t3.start();
		
		

	}

}
