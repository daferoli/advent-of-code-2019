package com.dave.day3;

public class UnitTests {
	public static void main(String[] args) {
		testCoordinateManhattanDistance();
		testLinesCrossing();
		testLinesNotCrossing();
	}
	
	public static void testCoordinateManhattanDistance() {
		Integer xDistance = 123;
		Integer yDistance = 321;
		Coordinate coordinate = new Coordinate(xDistance, yDistance);
		System.out.println("Test Coordinate Manhattan Distance. Expect: 444. Actual: " + coordinate.manhattanDistance());
	}
	
	public static void testLinesCrossing() {
		VerticleLine vLine = new VerticleLine(5, 1, 10, true);
		HorizontalLine hLine = new HorizontalLine(5, 5, 10, true);
		System.out.println("Test Lines Crossing. Expect: true. Actual: " + hLine.crossesVericleLine(vLine));
	}
	
	public static void testLinesNotCrossing() {
		VerticleLine vLine = new VerticleLine(5, 1, 10, true);
		HorizontalLine hLine = new HorizontalLine(5, 11, 20, true);
		System.out.println("Test Lines Crossing. Expect: false. Actual: " + vLine.crossesHorizontalLine(hLine));
	}
}
