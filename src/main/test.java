package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {
	
	public static void main(String[] args) {
		try {
			// read in config.txt file
			Scanner file = new Scanner(new File("config.txt"));
			
			// array list to store the integers from config.txt
			ArrayList<Integer> configIntegers = new ArrayList<>();
			while(file.hasNext()) {
				configIntegers.add(file.nextInt());
			}
			System.out.println(Arrays.asList(configIntegers));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
