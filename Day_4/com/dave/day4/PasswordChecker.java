package com.dave.day4;

import java.util.ArrayList;

public class PasswordChecker {
	public static void main(String[] args) {
		Integer startingInt = 172851;
		Integer endingInt = 675869;
		ArrayList<Integer> matchingPasswords = new ArrayList<Integer>();
		
		for(int i = startingInt; i <= endingInt; i++) {
			Integer[] splitPassword = new Integer[6];
			splitPassword[0] = Math.floorDiv(i, 100000);
			splitPassword[1] = Math.floorDiv(i, 10000) % 10;
			splitPassword[2] = Math.floorDiv(i, 1000) % 10;
			splitPassword[3] = Math.floorDiv(i, 100) % 10;
			splitPassword[4] = Math.floorDiv(i, 10) % 10;
			splitPassword[5] = i % 10;
			if(containsRepeatingNumber(splitPassword) && sequentialNumbersIncrease(splitPassword)) {
				matchingPasswords.add(i);
				//System.out.println("MATCH!");
			}
		}
		System.out.println("Total Matching Passwords: " + matchingPasswords.size());
	}
	
	public static boolean containsRepeatingNumber(Integer[] password) {
		if(password[0] == password[1] && password[0] != password[2]) { 
			return true;
		}
		for(int i = 1; i < 4; i++) {
			if(password[i] == password[i + 1] && password[i] != password[i + 2] && password[i - 1] != password[i]) {
				return true;
			}
		}
		if(password[4] == password[5] && password[3] != password[4]) {
			return true;
		}
		return false;
	}

	
	public static boolean sequentialNumbersIncrease(Integer[] password) {
		for(int i = 0; i < 5; i++) {
			if(password[i] > password[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
