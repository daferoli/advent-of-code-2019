package com.dave.day9;

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
		String fileName = "./Day_9/operations.txt";
		FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String allCodes = bufferedReader.readLine();
	    bufferedReader.close();
	    IntcodeInterpreter computer = new IntcodeInterpreter(allCodes);
	    //Part 1
	    //computer.addInput(1);
	    //Part 2
	    computer.addInput(2);
	    computer.runCodeInterpreter();
	}
}
