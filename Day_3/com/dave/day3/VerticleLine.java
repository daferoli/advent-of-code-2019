package com.dave.day3;

public class VerticleLine extends Line {
	private Integer x;
	//This has the assumption that "start" is always the lower of the two numbers.
	private Integer yStart;
	private Integer yEnd;
	private boolean isPositive;
	
	public VerticleLine (Integer x, Integer yStart, Integer yEnd, boolean isPositive) {
		super();
		this.x = x;
		this.yStart = yStart;
		this.yEnd = yEnd;
		this.setMagnitude(yEnd - yStart);
		this.isPositive = isPositive;
	}
	
	public VerticleLine () {
		super();
		this.x = 0;
		this.yStart = 0;
		this.yEnd = 0;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getyStart() {
		return yStart;
	}

	public void setyStart(Integer yStart) {
		this.yStart = yStart;
	}

	public Integer getyEnd() {
		return yEnd;
	}

	public void setyEnd(Integer yEnd) {
		this.yEnd = yEnd;
	}
	
	public String toString() {
		return "VerticleLine: X: " + this.x + " yStart: " + this.yStart + " yEnd: " + this.yEnd;
	}
	
	public boolean crossesHorizontalLine(HorizontalLine line) {
		return line.getY() >= this.getyStart() && line.getY() <= this.getyEnd()
				&& this.getX() >= line.getxStart() && this.getX() <= line.getxEnd();
	}
	
	public Integer calculateTotalMagnitudeFromCoordinate(Coordinate startingPoint) {
		if(startingPoint.getY() >= this.yStart && startingPoint.getY() <= this.yEnd) {
			if(this.isPositive) {
				return this.calculateTotalMagnitude() - (this.yEnd - startingPoint.getY());
			} else {
				return this.calculateTotalMagnitude() - (startingPoint.getY() - this.yStart);
			}
		} else {
			return -1;
		}
	}
}
