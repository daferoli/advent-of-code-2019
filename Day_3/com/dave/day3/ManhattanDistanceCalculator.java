package com.dave.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManhattanDistanceCalculator {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_3/distances.txt";
		try {
			FileReader fileReader = new FileReader(fileName);
	
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String routeOne = bufferedReader.readLine();
	        String routeTwo = bufferedReader.readLine();
	        bufferedReader.close();
	        
	        ArrayList<VerticleLine> routeOneVerticleLines = new ArrayList<VerticleLine>();
	        ArrayList<HorizontalLine> routeOneHorizontalLines = new ArrayList<HorizontalLine>();
	        ArrayList<VerticleLine> routeTwoVerticleLines = new ArrayList<VerticleLine>();
	        ArrayList<HorizontalLine> routeTwoHorizontalLines = new ArrayList<HorizontalLine>();
	        
	        drawLines(routeOne, routeOneVerticleLines, routeOneHorizontalLines);
	        drawLines(routeTwo, routeTwoVerticleLines, routeTwoHorizontalLines);
	        
	        ArrayList<Coordinate> crossingCoordinates = new ArrayList<Coordinate>();
	        
	        calculateAllCrossingCoordinates(routeOneVerticleLines, routeTwoHorizontalLines, crossingCoordinates);
	        calculateAllCrossingCoordinates(routeTwoVerticleLines, routeOneHorizontalLines, crossingCoordinates);
	        
	        Integer minManhattan = null;
	        for(Coordinate coordinate : crossingCoordinates) {
	        	System.out.println(coordinate.manhattanDistance());
	        	if(coordinate.manhattanDistance() != 0) {
		        	if(minManhattan == null) {
		        		minManhattan = coordinate.manhattanDistance();
		        	} else {
		        			minManhattan = Math.min(minManhattan, coordinate.manhattanDistance());
		        	}
	        	}
	        }
	        System.out.println("The minimum manhattan distance is: " + minManhattan);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static void drawLines(String route, ArrayList<VerticleLine> verticleLines, ArrayList<HorizontalLine> horizontalLines) {
		String[] lineStrings = route.split(",");
		Coordinate currentCoordinate = new Coordinate();
		for(int i = 0; i < lineStrings.length; i++) {
			String lineString = lineStrings[i];
			Integer distance = Integer.parseInt(lineString.substring(1));
			switch(lineString.charAt(0)) {
				case 'U': 
					verticleLines.add(new VerticleLine(currentCoordinate.getX(), currentCoordinate.getY(), currentCoordinate.getY() + distance, true));
					currentCoordinate.moveY(distance);
					break;
				case 'D':
					
					verticleLines.add(new VerticleLine(currentCoordinate.getX(), currentCoordinate.getY() - distance, currentCoordinate.getY(), false));
					currentCoordinate.moveY(-distance);
					break;
				case 'R':
					
					horizontalLines.add(new HorizontalLine(currentCoordinate.getY(), currentCoordinate.getX(), currentCoordinate.getX() + distance, true));
					currentCoordinate.moveX(distance);
					break;
				case 'L':
					horizontalLines.add(new HorizontalLine(currentCoordinate.getY(), currentCoordinate.getX() - distance, currentCoordinate.getX(), false));
					currentCoordinate.moveX(-distance);
					break;
				default:
					throw new Error("Invalid direction: " + lineString.charAt(0));
			}
		}
	}
	
	public static void calculateAllCrossingCoordinates(ArrayList<VerticleLine> verticleLines, ArrayList<HorizontalLine> horizontalLines, ArrayList<Coordinate> coordinates) {
		for (VerticleLine verticleLine : verticleLines) {
			for(HorizontalLine horizontalLine : horizontalLines) {
				if(verticleLine.crossesHorizontalLine(horizontalLine)) {
					System.out.println("X: " + verticleLine.getX() + " Y: " + horizontalLine.getY());
					coordinates.add(new Coordinate(verticleLine.getX(), horizontalLine.getY()));
				}
			}
		}
	}
	
}

