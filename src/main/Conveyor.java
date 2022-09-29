/* Name: Kevin Singh
   Course: CNT 4714 Fall 2022 
   Assignment title: Project 2 – Multi-threaded programming in Java 
   Date:  October 2, 2022 
   Class: Conveyor.java
*/ 

package main;

import java.util.concurrent.locks.*;

public class Conveyor {
	// define the attributes of a conveyer
	// define a lock on the conveyer object - a ReentrantLock() with no fairness policy - starvation not an issue in this application
	private ReentrantLock lock = new ReentrantLock();
	private int conveyorNumber;
	
	// constructor method - simply assign the conveyer its number
	public Conveyor(int conveyorNumber) {
		this.conveyorNumber = conveyorNumber;
	}
	
	public boolean lockConveyers() {
		// use tryLock()
		// tryLock() returns true if the lock request is granted by the Lock Manager
		// i.e., the lock was free and was granted to the requesting thread - otherwise return is false
		return lock.tryLock();
	}
	
	public void unlockConveyer() {
		// simply call unlock() on the lock
		lock.unlock();
	}
}
