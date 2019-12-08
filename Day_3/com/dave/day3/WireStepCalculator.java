package com.dave.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WireStepCalculator {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_3/distances.txt";
		try {
			FileReader fileReader = new FileReader(fileName);
	
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String routeOne = bufferedReader.readLine();
	        String routeTwo = bufferedReader.readLine();
	        bufferedReader.close();
	        
	        ArrayList<Line> routeOneLines = new ArrayList<Line>();
	        ArrayList<Line> routeTwoLines = new ArrayList<Line>();
	        
	        drawLines(routeOne, routeOneLines);
	        drawLines(routeTwo, routeTwoLines);
	        	        
	        ArrayList<Coordinate> junctions = new ArrayList<Coordinate>();
	        
	        calculateAllCrossingCoordinates(routeOneLines, routeTwoLines, junctions);

	        Integer minSteps = null;
	        for(Coordinate junction : junctions) {
	        	System.out.println("X: " + junction.getX() + " Y: " + junction.getY());
	        	Integer horizontalSteps = junction.gethLine().calculateTotalMagnitudeFromCoordinate(junction);
	        	Integer verticleSteps = junction.getvLine().calculateTotalMagnitudeFromCoordinate(junction);
	        	Integer totalSteps = horizontalSteps + verticleSteps;
	        	if(totalSteps != 0) {
	        		if(minSteps == null) {
		        		minSteps = totalSteps;
		        	} else {
		        		minSteps = Math.min(minSteps, totalSteps);
		        	}
	        	}
	        }
	        System.out.println("Min Steps: " + minSteps);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static void drawLines(String route, ArrayList<Line> lines) {
		String[] lineStrings = route.split(",");
		Coordinate currentCoordinate = new Coordinate();
		for(int i = 0; i < lineStrings.length; i++) {
			String lineString = lineStrings[i];
			Integer distance = Integer.parseInt(lineString.substring(1));
			Line nextLine;
			switch(lineString.charAt(0)) {
				case 'U': 
					nextLine = new VerticleLine(currentCoordinate.getX(), currentCoordinate.getY(), currentCoordinate.getY() + distance, true);
					currentCoordinate.moveY(distance);
					break;
				case 'D':
					nextLine = new VerticleLine(currentCoordinate.getX(), currentCoordinate.getY() - distance, currentCoordinate.getY(), false);
					currentCoordinate.moveY(-distance);
					break;
				case 'R':
					nextLine = new HorizontalLine(currentCoordinate.getY(), currentCoordinate.getX(), currentCoordinate.getX() + distance, true);
					currentCoordinate.moveX(distance);
					break;
				case 'L':
					nextLine = new HorizontalLine(currentCoordinate.getY(), currentCoordinate.getX() - distance, currentCoordinate.getX(), false);
					currentCoordinate.moveX(-distance);
					break;
				default:
					throw new Error("Invalid direction: " + lineString.charAt(0));
			}
			if(lines.size() != 0) {
				System.out.println("Setting previous line");
				nextLine.setPreviousLine(lines.get(lines.size() - 1));
			}
			lines.add(nextLine);
		}
	}
	
	public static void calculateAllCrossingCoordinates(ArrayList<Line> routeOneLines, ArrayList<Line> routeTwoLines, ArrayList<Coordinate> coordinates) {
		for (Line firstLine : routeOneLines) {
			boolean firstIsVerticle = firstLine instanceof VerticleLine;
			for(Line secondLine : routeTwoLines) {
				boolean secondIsVerticle = secondLine instanceof VerticleLine;
				if(firstIsVerticle && !secondIsVerticle && ((VerticleLine)firstLine).crossesHorizontalLine((HorizontalLine)secondLine)) {
					Coordinate junction = new Coordinate(((VerticleLine)firstLine).getX(), ((HorizontalLine)secondLine).getY());
					junction.setvLine((VerticleLine)firstLine);
					junction.sethLine((HorizontalLine)secondLine);
					coordinates.add(junction);
				} else if (!firstIsVerticle && secondIsVerticle && ((VerticleLine)secondLine).crossesHorizontalLine((HorizontalLine)firstLine)) {
					Coordinate junction = new Coordinate(((VerticleLine)secondLine).getX(), ((HorizontalLine)firstLine).getY());
					junction.setvLine((VerticleLine)secondLine);
					junction.sethLine((HorizontalLine)firstLine);
					coordinates.add(junction);
				}
			}
		}
	}
}
