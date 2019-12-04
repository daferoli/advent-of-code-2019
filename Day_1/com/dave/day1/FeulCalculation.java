package com.dave.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FeulCalculation {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_1/FeulCalculation.txt";
		try {
			FileReader fileReader = new FileReader(fileName);
	
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String line;
	        Integer sum = 0;
	        while((line = bufferedReader.readLine()) != null) {
	          	Integer lineVal = Integer.valueOf(line);
	           	Integer output = calculate(lineVal);
	           	sum += output;
	           	System.out.println(lineVal + " => " + output);
	      	}
	      	System.out.println(sum);
	      	bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	private static Integer calculate(Integer mass) {
	    Integer feulForMass = (int)Math.floor(mass / 3) - 2;
	    if(feulForMass <= 0) {
	    	return 0;
	    } else {
	    	return feulForMass + calculate(feulForMass);
	    }
	}
}
