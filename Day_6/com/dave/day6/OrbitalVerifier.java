package com.dave.day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrbitalVerifier {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_6/OrbitChart.txt";
		try {
			FileReader fileReader = new FileReader(fileName);

	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String line;
	        HashMap<String, Node> orbits = new HashMap<>();
	        while((line = bufferedReader.readLine()) != null) {
	        	String[] planets = line.split("\\)");
	        	if (orbits.get(planets[0]) == null) {
	        		orbits.put(planets[0], new Node(planets[0]));
	        	}
	        	if (orbits.get(planets[1]) == null) {
	        		orbits.put(planets[1], new Node(planets[1], orbits.get(planets[0])));
	        	} else {
	        		orbits.get(planets[1]).setParent(orbits.get(planets[0]));
	        	}
	        }
	        bufferedReader.close();
	        Integer sum = 0;
	        Integer count = 0;
	        for(String planet : orbits.keySet()) {
	        	sum += orbits.get(planet).getTotalOrbitCount();
	        }
	        System.out.println(count);
	        System.out.println("Total Orbits: " + sum);
	        //Part 2
	      	Node me = orbits.get("YOU");
	      	Node santa = orbits.get("SAN");
	      	Node i = me.getParent();
	      	Integer jumpsToMid = 0;
	      	while(i != null) {
	      		Integer jumpsFromMid = 0;
	      		Node j = santa.getParent();
	      		while(j != null) {
	      			if(i == j) {
	      				//Found Santa!
	      				Integer totalJumps = jumpsFromMid + jumpsToMid;
	      				System.out.println("Total Jumps to Santa: " + totalJumps);
	      				return;
	      			} else {
	      				jumpsFromMid++;
	      				j = j.getParent();
	      			}
	      		}
	      		jumpsToMid++;
	      		i = i.getParent();
	      	}
	      	System.out.println("ERROR: Could not find path to Santa!!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
