package com.dave.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IntcodeInterpreter {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_5/operations.txt";
		FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String allCodes = bufferedReader.readLine();
	    bufferedReader.close();
	    List<Integer> codeArray = Arrays.stream(allCodes.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	    Integer opLoc = 0; //operation location
	    Integer operand;
	    Scanner scanner = new Scanner(System.in);
	    while((operand = codeArray.get(opLoc)) % 100 != 99) {
	    	Integer[] parameterModes = OpcodeUtils.parseParameterModes(operand);
	    	Integer parameterCount = OpcodeUtils.getParametersCountForOpcode(operand);
	    	Integer opcode = operand % 100;
	    	Integer result = null;
	    	Integer resultLoc = codeArray.get(opLoc + 3);
	    	Integer firstParam, secondParam;
	    	switch(opcode) {
		    	case 1:
		    		//Add Operation
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		resultLoc = codeArray.get(opLoc + 3);
		    		result = firstParam + secondParam;
		    		break;
		    	case 2:
		    		//Multiply Operation
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		resultLoc = codeArray.get(opLoc + 3);
		    		result = firstParam * secondParam;
		    		break;
		    	case 3:
		    		//Read Input
		    		System.out.print("Please provide an input: ");
		    		result = scanner.nextInt();
		    		break;
		    	case 4:
		    		//Print Output
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		System.out.println("Output: " + firstParam);
		    		break;
		    	case 5:
		    		//Jump if True
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		if(firstParam != 0) {
		    			opLoc = secondParam;
		    			continue;
		    		}
		    		break;
		    	case 6:
		    		//Jump if False
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		if(firstParam == 0) {
		    			opLoc = secondParam;
		    			continue;
		    		}
		    		break;
		    	case 7:
		    		//Less Than Comparison
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		resultLoc = codeArray.get(opLoc + 3);
		    		result = firstParam < secondParam ? 1 : 0;
		    		break;
		    	case 8:
		    		//Equals Comparison
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		secondParam = getParameterValue(codeArray, opLoc, parameterModes, 1);
		    		resultLoc = codeArray.get(opLoc + 3);
		    		result = firstParam.equals(secondParam) ? 1 : 0;
		    		break;
		    	default:
		    		System.out.println("There was an invalid operand: " + operand + " in location: " + opLoc);
		    		continue;
	    	}
	    	if(result != null) {
	    		codeArray.set(resultLoc, result);
	    	}
	    	opLoc += parameterCount + 1;
	    }
	    scanner.close();
	    System.out.println("Final Code Array: ");
	    for(int i = 0; i < codeArray.size(); i++) {
	    	System.out.print(codeArray.get(i) + ",");
	    }
	}
	
	public static Integer getParameterValue(List<Integer> codeArray, Integer location, Integer[] parameterModes, Integer paramNum) {
		if(parameterModes[paramNum] == 1) {
			//return literal value
			return codeArray.get(location + paramNum + 1);
		} else {
			//return value at position
//			if(codeArray.get(location + paramNum + 1) == 223) {
//				System.out.println("here");
//			}
			return codeArray.get(codeArray.get(location + paramNum + 1));
		}
	}
}
