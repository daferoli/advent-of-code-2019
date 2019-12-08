package com.dave.day3;

public class HorizontalLine extends Line {
	private Integer y;
	//This has the assumption that "start" is always the lower of the two numbers.
	private Integer xStart;
	private Integer xEnd;
	private boolean isPositive;
	
	public HorizontalLine (Integer y, Integer xStart, Integer xEnd, boolean isPositive) {
		super();
		this.y = y;
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.setMagnitude(xEnd - xStart);
		this.isPositive = isPositive;
	}
	
	public HorizontalLine () {
		super();
		this.setY(0);
		this.setxStart(0);
		this.setxEnd(0);
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getxStart() {
		return xStart;
	}

	public void setxStart(Integer xStart) {
		this.xStart = xStart;
	}

	public Integer getxEnd() {
		return xEnd;
	}

	public void setxEnd(Integer xEnd) {
		this.xEnd = xEnd;
	}
	
	public String toString() {
		return "HorizontalLine: Y: " + this.y + " xStart: " + this.xStart + " xEnd: " + this.xEnd;
	}
	
	public boolean crossesVericleLine(VerticleLine line) {
		return this.getY() >= line.getyStart() && this.getY() <= line.getyEnd()
				&& line.getX() >= this.getxStart() && line.getX() <= this.getxEnd();
	}
	
	public Integer calculateTotalMagnitudeFromCoordinate(Coordinate startingPoint) {
		if(startingPoint.getX() >= this.xStart && startingPoint.getX() <= this.xEnd) {
			if(this.isPositive) {
				return this.calculateTotalMagnitude() - (this.xEnd - startingPoint.getX());
			} else {
				return this.calculateTotalMagnitude() - (startingPoint.getX() - this.xStart);
			}
		} else {
			return -1;
		}
	}
}
