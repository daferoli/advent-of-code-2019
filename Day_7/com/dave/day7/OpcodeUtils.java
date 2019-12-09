package com.dave.day7;

public class OpcodeUtils {
	//first digit is the parameter mode for the third parameter
	//first digit is the parameter mode for the third parameter
	//first digit is the parameter mode for the third parameter
	//final two digits is the actual opcode (not calculated here)
	public static Integer[] parseParameterModes(Integer fullOpCode) {
		Integer[] parameterModes = new Integer[3];
		parameterModes[0] = Math.floorDiv(fullOpCode, 100) % 10;
		parameterModes[1] = Math.floorDiv(fullOpCode, 1000) % 10;
		parameterModes[2] = Math.floorDiv(fullOpCode, 10000) % 10;
		return parameterModes;
	}
	
	public static Integer getParametersCountForOpcode(Integer opcode) {
		switch(opcode % 100) {
			case 3: case 4:
				return 1;
			case 5: case 6:
				return 2;
			case 1: case 2: case 7: case 8:
				return 3;
			case 99: default:
				return 0;
		}
	}
}
