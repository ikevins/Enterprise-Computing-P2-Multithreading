/* Name: Kevin Singh
   Course: CNT 4714 Fall 2022 
   Assignment title: Project 2 – Multi-threaded programming in Java 
   Date:  October 9, 2022 
   Class: PackageManagementFacilitySimulator.java
*/ 

// this is the main driver class
// creates the conveyer objects, the threads, and begins their execution
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PackageManagementFacilitySimulator {

	static int MAX = 10;
	
	public static void main(String[] args) {
		int numRoutingStations;
		try {
			System.out.println("FALL 2022 - Project 2 - Package Management Facility Simulator\n\n\n");
			System.out.println("* * * * * * * * * * PACKAGE MANAGEMENT FACILITY SIMULATION BEGINS * * * * * * * * * *\n\n");
			// read in config.txt file
			Scanner file = new Scanner(new File("config.txt"));
			
			// array list to store the integers from config.txt
			ArrayList<Integer> configIntegers = new ArrayList<Integer>();
			while(file.hasNext()) {
				configIntegers.add(file.nextInt());
			}
			file.close(); // close configuration file
			
			// create thread pool of MAX size
			ExecutorService pool = Executors.newFixedThreadPool(MAX); 
			
			// save the first integer in the config.txt file as number of routing stations in the simulation run
			numRoutingStations = configIntegers.get(0);
			
			// assign the workloads to each station from the values in the config.txt file
			System.out.println("	The parameters for this simulation run are: \n");
			for(int i = 0; i < numRoutingStations; i++) {
				System.out.println("	Routing Station " + i + " Has Total Workload Of " + configIntegers.get(i + 1) + " Package Groups.");
			}
			
			// create an array of conveyer objects
			Conveyor[] conveyorObjects = new Conveyor[numRoutingStations];
			
			// fill the array with the conveyors for this simulation run
			for(int i = 0; i < numRoutingStations; i++) {
				conveyorObjects[i] = new Conveyor(i);
			}
			
			// create the routing stations for the simulation run
			// loop through each RoutingStation in the simulation
			for(int i = 0; i < numRoutingStations; i++) {
				// start threads executing using the ExecutorService objects
				// int stationNumber = i;
				// int workload = configIntegers.get(i + 1);
				// int input = conveyorObjects[i];
				// int output = conveyorObjects[(i + numRoutingStations - 1) % (numRoutingStations)];
                try {
					pool.execute(new RoutingStation
								(i, 
								configIntegers.get(i + 1), 
								conveyorObjects[i], 
								conveyorObjects[(i + numRoutingStations - 1) % (numRoutingStations)]));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
			}
			
			// application shutdown - different techniques for shutting down the ExecutorService are shown below
			// shutdown technique courtesy of the javadocs
			// The following method shuts down an ExecutorService in two phases, 
			// first by calling shutdown to reject incoming tasks, 
			// and then calling shutdownNow, if necessary, to cancel any lingering tasks:
		    pool.shutdown(); // Disable new tasks from being submitted
		    try {
		        // Wait a while for existing tasks to terminate
		        if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
		            pool.shutdownNow(); // Cancel currently executing tasks
		            // Wait a while for tasks to respond to being cancelled
		            if (!pool.awaitTermination(60, TimeUnit.SECONDS))
		                System.err.println("Pool did not terminate");
		        }
		    } catch (InterruptedException ie) {
		        // (Re-)Cancel if current thread also interrupted
		        pool.shutdownNow();
		        // Preserve interrupt status
		        Thread.currentThread().interrupt();
		    }
			System.out.println("\n * * * * * * * * * * ALL WORKLOADS COMPLETE * * * PACKAGE MANAGEMENT FACILITY SIMULATION TERMINATES * * * * * * * * * * \n");
			System.out.println("\n * % * % * % SIMULATION ENDS % * % * % *");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
