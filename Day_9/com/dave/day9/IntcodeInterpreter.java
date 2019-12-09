package com.dave.day9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntcodeInterpreter {
	private List<Long> codeArray;
	private List<Integer> inputList;
	private Integer inputIndex;
	private Integer opLoc;
	private boolean executionComplete;
	private Integer relativePos;
	
	public IntcodeInterpreter(String codeList) {
		codeArray = Arrays.stream(codeList.split(",")).map(Long::parseLong).collect(Collectors.toList());
		inputList = new ArrayList<Integer>();
		opLoc = 0;
		relativePos = 0;
		inputIndex = 0;
		executionComplete = false;
	}
	
	public void reset() {
		inputList = new ArrayList<Integer>();
		opLoc = 0;
		relativePos = 0;
		inputIndex = 0;
		executionComplete = false;
	}
	
	public void addInput(Integer input) {
		inputList.add(input);
	}
	
	public boolean isExecutionComplete() {
		return executionComplete;
	}
	
	public Long runCodeInterpreter() {
		 //operation location
	    Integer operand;
	    while((operand = codeArray.get(opLoc).intValue()) % 100 != 99) {
	    	Integer[] parameterModes = OpcodeUtils.parseParameterModes(operand);
	    	Integer parameterCount = OpcodeUtils.getParametersCountForOpcode(operand);
	    	Integer opcode = operand % 100;
	    	Long result = null;
	    	Integer resultLoc = null;
	    	Long firstParam, secondParam;
	    	switch(opcode) {
		    	case 1:
		    		//Add Operation
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		resultLoc = getParameterLocation(parameterModes, 2);
		    		result = firstParam + secondParam;
		    		break;
		    	case 2:
		    		//Multiply Operation
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		resultLoc = getParameterLocation(parameterModes, 2);
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
		    			result = new Long(inputList.get(inputIndex));
		    			resultLoc = getParameterLocation(parameterModes, 0);
		    			inputIndex++;
		    		}
		    		
		    		break;
		    	case 4:
		    		//Print Output
		    		firstParam = getParameterValue(parameterModes, 0);
		    		System.out.println("Output: " + firstParam);
		    		opLoc += parameterCount + 1;
		    		return firstParam;
		    	case 5:
		    		//Jump if True
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		if(firstParam != 0) {
		    			opLoc = secondParam.intValue();
		    			continue;
		    		}
		    		break;
		    	case 6:
		    		//Jump if False
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		if(firstParam == 0) {
		    			opLoc = secondParam.intValue();
		    			continue;
		    		}
		    		break;
		    	case 7:
		    		//Less Than Comparison
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		resultLoc = getParameterLocation(parameterModes, 2);
		    		result = firstParam < secondParam ? 1L : 0L;
		    		break;
		    	case 8:
		    		//Equals Comparison
		    		firstParam = getParameterValue(parameterModes, 0);
		    		secondParam = getParameterValue(parameterModes, 1);
		    		resultLoc = getParameterLocation(parameterModes, 2);
		    		result = firstParam.equals(secondParam) ? 1L : 0L;
		    		break;
		    	case 9:
		    		//Alter relative position
		    		firstParam = getParameterValue(parameterModes, 0);
		    		relativePos += firstParam.intValue();
		    		break;
		    	default:
		    		System.out.println("There was an invalid operand: " + operand + " in location: " + opLoc);
		    		return null;
	    	}
	    	if(result != null) {
	    		if(resultLoc > codeArray.size() - 1) {
					for(int i = codeArray.size() - 1; i < resultLoc; i++) {
						codeArray.add(0L);
					}
				}
	    		codeArray.set(resultLoc, result);
	    	}
	    	opLoc += parameterCount + 1;
	    }
	    executionComplete = true;
	    return null;
	}
	
	public Long getParameterValue(Integer[] parameterModes, Integer paramNum) {
		Long valueAtParam = codeArray.get(opLoc + paramNum + 1);
		switch(parameterModes[paramNum]) {
			case 1:
				return valueAtParam;
			case 2:
				if(relativePos + valueAtParam > codeArray.size() - 1) {
					for(int i = codeArray.size() - 1; i < relativePos + valueAtParam; i++) {
						codeArray.add(0L);
					}
				}
				return codeArray.get(relativePos + valueAtParam.intValue());
			case 0: default:
				//Check if the address already exists
				if(valueAtParam > codeArray.size() - 1) {
					for(int i = codeArray.size() - 1; i < valueAtParam; i++) {
						codeArray.add(0L);
					}
				}
				return codeArray.get(valueAtParam.intValue());
		}
	}
	
	//Should return resultLoc based off of parameterModes
	public Integer getParameterLocation(Integer[] parameterModes, Integer paramNum) {
		switch(parameterModes[paramNum]) {
			case 2:
				return relativePos + codeArray.get(opLoc + paramNum + 1).intValue();
			default:
				return codeArray.get(opLoc + paramNum + 1).intValue();
		}
	}
}