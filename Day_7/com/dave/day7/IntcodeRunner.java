package com.dave.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.dave.day5.OpcodeUtils;

public class IntcodeRunner {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_7/operations.txt";
		FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String allCodes = bufferedReader.readLine();
	    bufferedReader.close();
	    List<Integer> codeArray = Arrays.stream(allCodes.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	    List<Integer[]> possibleSettings = new ArrayList<Integer[]>();
	    Integer[] availableSettings = {5,6,7,8,9};
	    heapAlgorithm(possibleSettings, availableSettings, availableSettings.length);
	    List<Integer> allOutputs = new ArrayList<>();
	    IntcodeInterpreter aThruster = new IntcodeInterpreter(allCodes);
	    IntcodeInterpreter bThruster = new IntcodeInterpreter(allCodes);
	    IntcodeInterpreter cThruster = new IntcodeInterpreter(allCodes);
	    IntcodeInterpreter dThruster = new IntcodeInterpreter(allCodes);
	    IntcodeInterpreter eThruster = new IntcodeInterpreter(allCodes);
	    Integer aResult = null;
	    Integer bResult = null;
	    Integer cResult = null;
	    Integer dResult = null;
	    Integer eResult = null;
	    for(Integer[] settings : possibleSettings) {
	    	System.out.println("Setting Used: " + settings[0] + "," + settings[1] + "," + settings[2] + "," + settings[3] + "," + settings[4]);
	    	boolean allThrustersExited = false;
	    	Integer lastResult = 0;
	    	aThruster.addInput(settings[0]);
	    	bThruster.addInput(settings[1]);
	    	cThruster.addInput(settings[2]);
	    	dThruster.addInput(settings[3]);
	    	eThruster.addInput(settings[4]);
	    	
	    	while(!allThrustersExited) {
	    		
	    		aThruster.addInput(eResult == null ? 0 : eResult);
		    	aResult = aThruster.runCodeInterpreter();
		    	bThruster.addInput(aResult);
			    bResult = bThruster.runCodeInterpreter();
			    cThruster.addInput(bResult);
			    cResult = cThruster.runCodeInterpreter();
			    dThruster.addInput(cResult);
			    dResult = dThruster.runCodeInterpreter();
			    eThruster.addInput(dResult);
			    eResult = eThruster.runCodeInterpreter();
			    lastResult = eResult == null ? lastResult : eResult;
			    allThrustersExited = aThruster.isExecutionComplete() &&
			    					 bThruster.isExecutionComplete() &&
			    					 cThruster.isExecutionComplete() &&
			    					 dThruster.isExecutionComplete() &&
			    					 eThruster.isExecutionComplete();
	    	}	    	
		    
		    System.out.println("Final Thruster output: " + lastResult);
		    allOutputs.add(lastResult);
		    aThruster.reset();
		    bThruster.reset();
		    cThruster.reset();
		    dThruster.reset();
		    eThruster.reset();
	    }
	    Integer max = 0;
	    for(Integer output : allOutputs) {
	    	max = Math.max(max, output);
	    }
	    System.out.println("Max Settings: " + max);
	}
	
	public static void heapAlgorithm(List<Integer[]> outputList, Integer[] a, Integer k) {
		if(k == 1) {
			outputList.add(a.clone());
		} else {
			for(int i = 0; i < k; i++) {
				heapAlgorithm(outputList, a, k - 1);
				if(a.length % 2 == 1) {
					int temp = a[0];
					a[0] = a[k - 1];
					a[k - 1] = temp;
				} else {
					int temp = a[i];
					a[i] = a[k - 1];
					a[k - 1] = temp;
				}
			}
		}
	}
}
