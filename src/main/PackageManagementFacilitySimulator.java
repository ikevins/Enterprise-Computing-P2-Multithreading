/* Name: Kevin Singh
   Course: CNT 4714 Fall 2022 
   Assignment title: Project 2 – Multi-threaded programming in Java 
   Date:  October 2, 2022 
   Class: PackageManagementFacilitySimulator.java
*/ 

// this is the main driver class
// creates the conveyer objects, the threads, and begins their execution
package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PackageManagementFacilitySimulator {

	static int MAX = 10;
	
	
	public static void main(String[] args) {
		int numRoutingStations;
		try {
			System.out.println("\n * * * * * * * * * * PACKAGE MANAGEMENT FACILITY SIMULATION BEGINS * * * * * * * * * * \n");
			// read in config.txt file
			Scanner file = new Scanner(new File("config.txt"));
			
			// array list to store the integers from config.txt
			ArrayList<Integer> configIntegers = new ArrayList<Integer>();
			while(file.hasNext()) {
				configIntegers.add(file.nextInt());
			}
			
			// create thread pool of MAX size
			ExecutorService pool = Executors.newFixedThreadPool(MAX); 
			
			file.close(); // close configuration file
			
			// save the first integer in the config.txt file as number of routing stations in the simulation run
			numRoutingStations = configIntegers.get(0);
			
			// assign the workloads to each station from the values in the config.txt file
			for(int i = 0; i < numRoutingStations; i++) {
				System.out.println("Routing Station " + i + " Has Total Workload Of " + configIntegers.get(i + 1) + "\n\n");
			}
			
			// create an array of conveyer objects
			// fill the array with the conveyers for this simulation run
			
			// create the routing stations for the simulation run
			// loop through each RoutingStation in the simulation
				// start threads executing using the ExecutorService objects
			
			// application shutdown - different techniques for shutting down the ExecutorService are shown below
			System.out.println("\n * * * * * * * * * * ALL WORKLOADS COMPLETE * * * PACKAGE MANAGEMENT FACILITY SIMULATION ENDS * * * * * * * * * * \n");
		}
		catch(FileNotFoundException e) {
			System.out.println("file not found");
		}
	}
}
