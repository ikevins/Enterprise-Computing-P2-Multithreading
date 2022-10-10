/* Name: Kevin Singh
   Course: CNT 4714 Fall 2022 
   Assignment title: Project 2 – Multi-threaded programming in Java 
   Date:  October 9, 2022 
   Class: PackageManagementFacilitySimulator.java
*/ 

package main;

import java.util.Random;

public class RoutingStation implements Runnable {
	// define all the RoutingStation attributes
	// protected
	protected int stationNumber;
	protected int workload;
	protected int workloadCounter;
	protected Conveyor input;
	protected Conveyor output;
	protected Random rand = new Random();

	public RoutingStation(int stationNumber, int workload, Conveyor input, Conveyor output) {
		this.stationNumber = stationNumber;
		this.workload = workload;
		this.input = input;
		this.output = output;
		workloadCounter = workload;
	}
	
	// method for threads to go to sleep
	// note: a sleeping thread in Java maintains all resources allocated to it, included locks
	// locks are not relinquished during a sleep cycle
	public void goToSleep() {
		try {
			Thread.sleep(rand.nextInt(500)); // Sleep a random time up to 500ms
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// method for simulating Routing Station work - i.e., some time period during which the station is moving packages
	public void doWork() {
		// Print simulation output messages
		// A routing station successfully moves packages in and out of the routing station:
		System.out.println(" * * * * * * Routing Station " + stationNumber + ": * * * * CURRENTLY HARD AT WORK MOVING PACKAGES * * * * * *");
		System.out.println("	Routing Station " + stationNumber + ": successfully moves packages into station on input conveyor C" + input.conveyorNumber);
		System.out.println("	Routing Station " + stationNumber + ": successfully moves packages out of station on output conveyor C" + output.conveyorNumber);
		
		// Decrement workload Counter
		workloadCounter--;
		System.out.println("	Routing Station " + stationNumber + ": has " + workloadCounter + " package groups left to move.");
		
		// hold the conveyers for a random period of time to simulate work flow, i.e sleep the thread
		goToSleep();
		
		// check if workload has reached 0 - if so print out message indicating station done
		if(workloadCounter == 0) {
            System.out.println("\n # # # # # Routing Station " + stationNumber + ": WORKLOAD SUCCESSFULLY COMPLETED. * * * Routing Station " + stationNumber + " preparing to go offline. # # # #\n");
            input.unlockConveyor(); // workload is done, unlock the conveyor
            output.unlockConveyor(); // workload is done, unlock the conveyor
            goToSleep();
		}
		
	}
	
	// the run() method  - this is what a station does
	public void run() {
		// dump out the conveyer assignments and workload settings for the station - simulation output criteria
		System.out.println("\n\n% % % % % ROUTING STATION " + stationNumber + " Coming Online - Initializing Conveyers % % % % % \n");
		
		// An input conveyor is assigned to a routing station:
		System.out.println("	Routing Station " + stationNumber + ": Input conveyor set to conveyor number C" + input.conveyorNumber + ".");
		
		// An output conveyor is assigned to a routing station:
		System.out.println("	Routing Station " + stationNumber + ": Output conveyor set to conveyor number C" + output.conveyorNumber + ".");
		
		// A routing station’s workload is set:
		System.out.println("	Routing Station " + stationNumber + ": Workload set. Station " + stationNumber + " has a total of " + workload + " package groups to move.");
		
		// A routing station is online:
		System.out.println("	\n\nRouting Station " + stationNumber + ": Now Online");
		
		// run the simulation on the station for its entire workload
		for(int i = 0; i < workload; i++) {
            System.out.println("	Routing Station " + stationNumber + ": Entering Lock Acquisition Phase.");
			// loop for the workload of this station
			// get input lock
			// attempt to get output lock
			// if both locks acquired - then go to work
            while(workloadCounter > 0) {
            	if(input.lockConveyors() == true) { // The locks are free and access is granted
                    System.out.println("	Routing Station " + stationNumber + ": Holds lock on input conveyor C" + input.conveyorNumber + ".");
                    if(output.lockConveyors() == true) {
	                    System.out.println("	Routing Station " + stationNumber + ": Holds lock on output conveyor C" + output.conveyorNumber + ".");
	                    System.out.println("\n * * * * * * Routing Station " + stationNumber +": holds locks on both input conveyor C" + input.conveyorNumber + " and on output conveyor C" + output.conveyorNumber + ". * * * * * *  \n");
	                    doWork();
	                    System.out.println("	Routing Station " + stationNumber + ": Entering Lock Release Phase.");
	                    input.unlockConveyor();
	                    System.out.println("	Routing Station " + stationNumber + ": Unlocks/releases input conveyor C" + input.conveyorNumber);
	                    output.unlockConveyor();
	                    System.out.println("	Routing Station " + stationNumber + ": Unlocks/releases output conveyor C" + output.conveyorNumber);
                    }
                    else {
                        System.out.println("	Routing Station " + stationNumber + ": Unable to lock output conveyor C" + output.conveyorNumber + ". SYNCHRONIZATION ISSUE: releasing lock on input conveyor C" + input.conveyorNumber);
                  		// unlock conveyors
                  		input.unlockConveyor(); // unlock the input conveyor
                  		goToSleep(); // sleep
                    }
            	}
            	else {
            		goToSleep(); // input conveyor must be locked, go to sleep
            	}
            }
		}
		// loops ends - station is done - print out message
		System.out.println("\n\n @ @ @ @ @ @ @ ROUTING STATION " + stationNumber + ": OFF LINE @ @ @ @ @ @ @ \n\n");
	}
}
