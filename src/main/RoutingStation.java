package main;

public class RoutingStation implements Runnable {
	// define all the RoutingStation attributes
	// protected

	public RoutingStation(? ? ?) {
		//build a RoutingStation object
	}
	
	// method for threads to go to sleep
	// note: a sleeping thread in Java maintains all resources allocated to it, included locks
	// locks are not relinquished during a sleep cycle
	public void goToSleep() {
		
		try {
			Thread.sleep(gen.nextInt(500)); // Sleep a random time up to 500ms
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// method for simulating Routing Station work - i.e., some time period during which the station is moving packages
	public void doWork() {
		// Print simulation output messages
		// Decrement workload Counter
		// hold the conveyers for a random period of time to simulate work flow, i.e sleep the thread
		// check if workload has reached 0 - if so print out message indicating station done
	}
	
	
	// the run() method  - this is what a station does
	public void run() {
		// dump out the conveyer assignments and workload settings for the station - simulation output criteria
		// example System.out.println(" \n% % % % % ROUTING STATION " + stationNum + " Coming Online - Initializing Conveyers % % % % % \n");
		
		// run the simulation on the station for its entire workload
		// loop for the workload of this station
			// get input lock
			// attempt to get output lock
			// if both locks acquired - then go to work
		// loops ends - station is done - print out message
		System.out.println("\n\n @ @ @ @ @ @ @ ROUTING SATION " + stationNum + ": OFF LINE @ @ @ @ @ @ @ \n\n");
	}
}
