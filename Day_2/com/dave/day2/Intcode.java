package com.dave.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Intcode {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_2/operations.txt";
		FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String allCodes = bufferedReader.readLine();
	    bufferedReader.close();
	    String[] codeArray = allCodes.split(",");
	    Integer opLoc = 0; //operation location
	    Integer operand;
	    while((operand = Integer.parseInt(codeArray[opLoc])) != 99) {
	    	System.out.println("Operand: " + codeArray[opLoc]);
	    	System.out.println("First Position: " + codeArray[opLoc + 1]);
	    	System.out.println("Second Position: " + codeArray[opLoc + 2]);
	    	Integer firstPosNum = Integer.parseInt(codeArray[opLoc + 1]);
	    	Integer firstNum = Integer.parseInt(codeArray[firstPosNum]);
	    	Integer secondPosNum = Integer.parseInt(codeArray[opLoc + 2]);
	    	Integer secondNum = Integer.parseInt(codeArray[secondPosNum]);
	    	Integer result;
	    	if(operand == 1) {
	    		result = firstNum + secondNum;
	    	} else if (operand == 2) {
	    		result = firstNum * secondNum;
	    	} else {
	    		System.out.println("There was an invalid operand: " + operand + " in location: " + opLoc);
	    		continue;
	    	}
	    	Integer resultLoc = Integer.parseInt(codeArray[opLoc + 3]);
	    	System.out.println("Result: " + result + " => " + resultLoc);
	    	codeArray[resultLoc] = result.toString();
	    	opLoc += 4;
	    }
	    System.out.println("Final Code Array: ");
	    for(int i = 0; i < codeArray.length; i++) {
	    	System.out.print(codeArray[i] + ",");
	    }
	}
}
