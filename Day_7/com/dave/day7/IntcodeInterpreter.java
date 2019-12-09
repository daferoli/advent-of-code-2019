package com.dave.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dave.day5.OpcodeUtils;

public class IntcodeInterpreter {
	private List<Integer> codeArray;
	private List<Integer> inputList;
	private Integer inputIndex;
	private Integer opLoc;
	private boolean executionComplete;
	
	public IntcodeInterpreter(String codeList) {
		codeArray = Arrays.stream(codeList.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		inputList = new ArrayList<Integer>();
		opLoc = 0;
		inputIndex = 0;
		executionComplete = false;
	}
	
	public void reset() {
		inputList = new ArrayList<Integer>();
		opLoc = 0;
		inputIndex = 0;
		executionComplete = false;
	}
	
	public void addInput(Integer input) {
		inputList.add(input);
	}
	
	public boolean isExecutionComplete() {
		return executionComplete;
	}
	
	public Integer runCodeInterpreter() {
		 //operation location
	    Integer operand;
	    while((operand = codeArray.get(opLoc)) % 100 != 99) {
	    	Integer[] parameterModes = OpcodeUtils.parseParameterModes(operand);
	    	Integer parameterCount = OpcodeUtils.getParametersCountForOpcode(operand);
	    	Integer opcode = operand % 100;
	    	Integer result = null;
	    	Integer resultLoc = null;
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
		    		//result = scanner.nextInt();
		    		//TODO Must halt program here until another input is sent
		    		if(inputList.get(inputIndex) == null) {
		    			return null;
		    		} else {
		    			result = inputList.get(inputIndex);
		    			resultLoc = codeArray.get(opLoc + 1);
		    			inputIndex++;
		    		}
		    		
		    		break;
		    	case 4:
		    		//Print Output
		    		firstParam = getParameterValue(codeArray, opLoc, parameterModes, 0);
		    		System.out.println("Output: " + firstParam);
		    		opLoc += parameterCount + 1;
		    		return firstParam;
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
		    		return null;
	    	}
	    	if(result != null) {
	    		codeArray.set(resultLoc, result);
	    	}
	    	opLoc += parameterCount + 1;
	    }
	    executionComplete = true;
	    return null;
	}
	
	public Integer getParameterValue(List<Integer> codeArray, Integer location, Integer[] parameterModes, Integer paramNum) {
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
